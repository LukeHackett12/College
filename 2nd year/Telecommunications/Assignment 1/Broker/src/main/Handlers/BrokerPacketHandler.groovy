package main.Handlers

import main.Broker
import main.Receivers.BrokerReceiver
import main.Senders.MessageAcknowledgement
import main.Senders.PublisherIdentificationSender
import main.Senders.SubscriberMessageSender
import main.Structures.*

class BrokerPacketHandler implements Runnable {

    static final String SUBSCRIBE = "subscribe"

    String contentType
    DatagramPacket packet
    byte[] buffer

    BrokerPacketHandler(String contentType, DatagramPacket packet, byte[] buffer) {
        this.contentType = contentType
        this.packet = packet
        this.buffer = buffer
    }

    @Override
    void run() {
        byte[] buffer = packet.getData()

        switch (contentType) {
            case BrokerReceiver.PUBLISHER:
                ByteArrayInputStream bstream = new ByteArrayInputStream(Arrays.copyOfRange(buffer, 1, buffer.length - 1))
                ObjectInputStream ostream = new ObjectInputStream(bstream)
                readPublisherContent(ostream)
                break
            case BrokerReceiver.SUBSCRIBER:
                ByteArrayInputStream bstream = new ByteArrayInputStream(Arrays.copyOfRange(buffer, 2, buffer.length - 1))
                ObjectInputStream ostream = new ObjectInputStream(bstream)
                readSubscriberContent(ostream, buffer)
                break
            case BrokerReceiver.IDENTITY:
                ByteArrayInputStream bstream = new ByteArrayInputStream(Arrays.copyOfRange(buffer, 1, buffer.length - 1))
                ObjectInputStream ostream = new ObjectInputStream(bstream)
                readIdentityContent(ostream, packet)
        }

        Thread.currentThread().interrupt()
        return
    }

    void readPublisherContent(ObjectInputStream ostream) {
        PublisherContent publisherContent = ostream.readObject() as PublisherContent

        println("Data : $publisherContent.message \nFor $publisherContent.topics")

        String uniqueId = publisherContent.uniqueId
        int currentMessage = publisherContent.batchNo
        Publisher publisher = Broker.publishersList.find { it.uniqueId == uniqueId }

        String id = publisher.uniqueId
        String batchNo = publisherContent.batchNo.toString()
        SubscriberMessageSender sender = new SubscriberMessageSender("$id.$batchNo", publisherContent.topics, publisherContent.message)
        Thread thread = new Thread(sender)
        thread.start()

        MessageAcknowledgement acknowledgement = new MessageAcknowledgement(publisher, currentMessage)
        thread = new Thread(acknowledgement)
        thread.start()
    }

    void readSubscriberContent(ObjectInputStream ostream, byte[] buffer) {
        switch ((int) buffer[1]) {
            case 0:
                SubscriberContent subscriberContent = ostream.readObject() as SubscriberContent
                (subscriberContent.type == SUBSCRIBE) ? subscribeUser(subscriberContent) : unsubscribeUser(subscriberContent)
                subscriptionAcknowledgement(subscriberContent)
                break
            case 1:
                messageAcknowledgement(ostream.readUTF())
                break
        }
    }

    void subscriptionAcknowledgement(SubscriberContent subscriberContent) {
        MessageAcknowledgement acknowledgement = new MessageAcknowledgement(new Connection(address: packet.address, port: subscriberContent.receivingPort), subscriberContent)
        Thread thread = new Thread(acknowledgement)
        thread.start()
    }

    void messageAcknowledgement(String content) {
        Broker.awaitingAck.remove(content)
        println("Recieved message acknowledgment for $content")
    }

    private void subscribeUser(SubscriberContent subscriberContent) {
        Subscriber subscriber = isNewSubscriber(subscriberContent)
        (!subscriber) ? addSubscriber(subscriberContent) : updateSubscriberTopics(subscriber, subscriberContent)
    }

    private void unsubscribeUser(SubscriberContent subscriberContent) {
        Subscriber subscriber = Broker.subscribersList.find {
            it.port == subscriberContent.receivingPort && it.address == packet.address
        }
        Subscriber newSubscriber = subscriber

        newSubscriber.subscribedTopics.removeAll(subscriberContent.topics)
        updateSubscriber(subscriber, newSubscriber)
    }

    Subscriber isNewSubscriber(SubscriberContent subscriberContent) {
        Subscriber foundSubscriber = null
        Broker.subscribersList.find { Subscriber subscriber ->
            if (packet.address == subscriber.address
                    && subscriberContent.receivingPort == subscriber.port) {
                foundSubscriber = subscriber
            }
        }

        foundSubscriber
    }

    void addSubscriber(SubscriberContent subscriberContent) {
        Subscriber subscriber = new Subscriber(
                packet.address,
                subscriberContent.receivingPort,
                subscriberContent.topics
        )

        Broker.subscribersList.add(subscriber)
    }

    void updateSubscriberTopics(Subscriber subscriber, SubscriberContent subscriberContent) {
        Subscriber newSubscriber = subscriber

        newSubscriber.subscribedTopics.addAll(subscriberContent.topics)
        newSubscriber.subscribedTopics.unique()

        updateSubscriber(subscriber, newSubscriber)
    }

    void updateSubscriber(Subscriber subscriber, Subscriber updatedSubscriber) {
        Broker.subscribersList.remove(subscriber)
        Broker.subscribersList.add(updatedSubscriber)
    }

    void readIdentityContent(ObjectInputStream ostream, DatagramPacket packet) {
        InetAddress address = packet.getAddress()
        int port = ostream.readInt()
        String uniqueId = UUID.randomUUID().toString()

        Broker.publishersList.add(new Publisher(address, port, uniqueId))

        PublisherIdentificationSender sender = new PublisherIdentificationSender(address, port, uniqueId)
        Thread thread = new Thread(sender)
        thread.start()
    }

}
