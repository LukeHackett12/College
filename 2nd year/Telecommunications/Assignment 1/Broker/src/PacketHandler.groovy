class PacketHandler implements Runnable {
    String contentType
    DatagramPacket packet

    PacketHandler(String contentType, DatagramPacket packet) {
        this.contentType = contentType
        this.packet = packet
    }

    @Override
    void run() {
        byte[] buffer = packet.getData()
        ByteArrayInputStream bstream = new ByteArrayInputStream(buffer)
        ObjectInputStream ostream = new ObjectInputStream(bstream)

        (contentType == Broker.PUBLISHER) ? readPublisherContent(ostream) : readSubscriberContent(ostream)
    }

    void readPublisherContent(ObjectInputStream ostream) {
        PublisherContent publisherContent = ostream.readObject() as PublisherContent
        println("Data : $publisherContent.message /nFor $publisherContent.topics")

        Sender sender = new Sender(publisherContent.topics, publisherContent.message)
        Thread thread = new Thread(sender)
        thread.start()
    }

    void readSubscriberContent(ObjectInputStream ostream) {
        SubscriberContent subscriberContent = ostream.readObject() as SubscriberContent

        Subscriber subscriber = isNewSubscriber()
        (!subscriber) ? addSubscriber(subscriberContent) : updateSubscriberTopics(subscriber, subscriberContent)
    }

    Subscriber isNewSubscriber() {
        Subscriber foundSubscriber = null
        Broker.subscribersList.any { Subscriber subscriber ->
            if (packet.address == subscriber.address
                    && packet.port == subscriber.port){
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
}
