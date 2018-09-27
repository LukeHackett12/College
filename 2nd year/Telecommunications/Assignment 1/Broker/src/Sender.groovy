class Sender implements Runnable{

    int port
    ArrayList<String> topics
    String message

    Sender(ArrayList<String> topics, String message){
        this.topics = topics
        this.message = message
    }

    @Override
    void run() {
        println("Sending message to subscribers of $topics")

        ByteArrayOutputStream bstream = new ByteArrayOutputStream()
        ObjectOutputStream ostream = new ObjectOutputStream(bstream)
        ostream.writeObject(new BrokerContent(topics, message))
        ostream.flush()

        byte[] buffer = bstream.toByteArray()

        DatagramPacket packet
        DatagramSocket socket = new DatagramSocket()

        Broker.subscribersList.forEach{ Subscriber subscriber ->
            if(subscribedToTopic(subscriber.subscribedTopics, topics)) {
                packet = new DatagramPacket(buffer, buffer.length, subscriber.address, subscriber.port)
                socket.send(packet)
            }
        }

        socket.close()

        println("Subscriptions sent")
    }

    boolean subscribedToTopic(ArrayList<String> subscriberTopics, ArrayList<String> topics){
        boolean isValid = false
        topics.forEach{ String topic ->
            if (subscriberTopics.contains(topic)) isValid = true
        }

        return isValid
    }
}
