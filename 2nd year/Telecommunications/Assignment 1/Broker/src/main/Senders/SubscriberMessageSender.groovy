package main.Senders

import main.Broker
import main.Structures.BrokerContent
import main.Structures.Subscriber

class SubscriberMessageSender implements Runnable{

    String batchNo
    ArrayList<String> topics
    String message

    SubscriberMessageSender(String batchNo, ArrayList<String> topics, String message){
        this.batchNo = batchNo
        this.topics = topics
        this.message = message
    }

    @Override
    void run() {
        println("Sending message to subscribers of $topics")

        ByteArrayOutputStream bstream = new ByteArrayOutputStream()
        ObjectOutputStream ostream = new ObjectOutputStream(bstream)
        ostream.writeObject(new BrokerContent(batchNo, topics, message))
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

    String getBatchNo(){
        return ("$batchNo")
    }
}
