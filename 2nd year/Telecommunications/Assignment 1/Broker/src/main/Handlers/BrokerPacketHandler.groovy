package main.Handlers

import main.Broker
import main.Senders.PublisherIdentificationSender
import main.Senders.PublisherLossSender
import main.Senders.MessageAcknowledgement
import main.Senders.SubscriberMessageSender
import main.Structures.Publisher
import main.Structures.PublisherContent
import main.Structures.Subscriber
import main.Structures.SubscriberContent

class BrokerPacketHandler implements Runnable {

    static final String SUBSCRIBE = "subscribe"

    String contentType
    DatagramPacket packet

    BrokerPacketHandler(String contentType, DatagramPacket packet) {
        this.contentType = contentType
        this.packet = packet
    }

    @Override
    void run() {
        byte[] buffer = packet.getData()

        switch (contentType) {
            case Broker.PUBLISHER:
                ByteArrayInputStream bstream = new ByteArrayInputStream(buffer)
                ObjectInputStream ostream = new ObjectInputStream(bstream)
                readPublisherContent(ostream)
                break
            case Broker.SUBSCRIBER:
                ByteArrayInputStream bstream = new ByteArrayInputStream(buffer)
                ObjectInputStream ostream = new ObjectInputStream(bstream)
                readSubscriberContent(ostream)
                break
            case Broker.IDENTITY:
                readIdentityContent(packet)
        }
    }

    void readPublisherContent(ObjectInputStream ostream) {
        PublisherContent publisherContent = ostream.readObject() as PublisherContent
        println("Data : $publisherContent.message \nFor $publisherContent.topics")

        int uniqueId = publisherContent.batchNo.split('\\.').first().toInteger()
        int currentMessage = publisherContent.batchNo.split('\\.').last().toInteger()
        Publisher publisher = Broker.publishersList.find { Integer.compare(it.uniqueId, uniqueId) == 0 }

        (publisher.currentMessage != currentMessage) ? missedPublisherContent(publisher, currentMessage) : publisher.currentMessage++

        SubscriberMessageSender sender = new SubscriberMessageSender(publisherContent.batchNo, publisherContent.topics, publisherContent.message)
        Thread thread = new Thread(sender)
        thread.start()

        MessageAcknowledgement acknowledgement = new MessageAcknowledgement(publisher)
        thread = new Thread(acknowledgement)
        thread.start()
    }

    void missedPublisherContent(Publisher publisher, int targetNumber) {
        for (int i = publisher.currentMessage; i < targetNumber; i++) {
            PublisherLossSender sender = new PublisherLossSender(publisher.address, publisher.port, publisher.currentMessage)
            Thread thread = new Thread(sender)
            thread.start()
        }
    }

    void readSubscriberContent(ObjectInputStream ostream) {
        SubscriberContent subscriberContent = ostream.readObject() as SubscriberContent

        (subscriberContent.type == SUBSCRIBE) ? subscribeUser(subscriberContent) : unsubscribeUser(subscriberContent)
    }

    private void subscribeUser(SubscriberContent subscriberContent) {
        Subscriber subscriber = isNewSubscriber()
        (!subscriber) ? addSubscriber(subscriberContent) : updateSubscriberTopics(subscriber, subscriberContent)
    }

    private void unsubscribeUser(SubscriberContent subscriberContent) {
        Subscriber subscriber = isNewSubscriber()
        Subscriber newSubscriber = subscriber

        newSubscriber.subscribedTopics.removeAll(subscriberContent.topics)
        updateSubscriber(subscriber, newSubscriber)
    }

    Subscriber isNewSubscriber() {
        Subscriber foundSubscriber = null
        Broker.subscribersList.any { Subscriber subscriber ->
            if (packet.address == subscriber.address
                    && packet.port == subscriber.port) {
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

    void readIdentityContent(DatagramPacket packet) {
        InetAddress address = packet.getAddress()
        int port = packet.getPort()
        int uniqueId = Broker.uniqueId

        Broker.publishersList.add(new Publisher(address, port, uniqueId))

        PublisherIdentificationSender sender = new PublisherIdentificationSender(address, port, uniqueId)
        Thread thread = new Thread(sender)
        thread.start()
    }

}
