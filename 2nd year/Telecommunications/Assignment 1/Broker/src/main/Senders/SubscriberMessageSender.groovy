package main.Senders

import groovy.time.TimeCategory
import groovy.time.TimeDuration
import main.Broker
import main.Structures.BrokerContent
import main.Structures.MessageTime
import main.Structures.Subscriber

class SubscriberMessageSender implements Runnable {

    static final TimeDuration FIVE_SECONDS = new TimeDuration(0, 0, 5, 0)

    String batchNo
    ArrayList<String> topics
    String message

    SubscriberMessageSender(String batchNo, ArrayList<String> topics, String message) {
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

        DatagramSocket socket = new DatagramSocket()

        sendMessage(bstream, socket, buffer)

        String parsed = (batchNo) ? batchNo.split('\\.').last() : ""
        MessageTime waiting = new MessageTime(Integer.parseInt(parsed))
        int retries = 0
        while (Broker.awaitingAck.find { it == batchNo } && retries < 5) {
            //Just wait a sec
            if (TimeCategory.minus(new Date(), waiting.timeStart) > FIVE_SECONDS) {
                retries++
                Broker.awaitingAck.remove{it == batchNo}
                sendMessage(bstream, socket, buffer)
                waiting.timeStart = new Date()
            }
        }

        if (retries == 5) {
            println("Did not recieve ack")
        } else {
            println("Received ack")
        }

        Thread.currentThread().interrupt()
        return
    }

    private void sendMessage(ByteArrayOutputStream bstream, DatagramSocket socket, byte[] buffer) {
        Broker.subscribersList.forEach { Subscriber subscriber ->
            if (subscribedToTopic(subscriber.subscribedTopics, topics)) {
                byte[] flag = [(byte) 0]
                buffer = new byte[flag.length + bstream.toByteArray().length]
                System.arraycopy(flag, 0, buffer, 0, flag.length)
                System.arraycopy(bstream.toByteArray(), 0, buffer, flag.length, bstream.toByteArray().length)

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, subscriber.address, subscriber.port)
                socket.send(packet)
            }
        }

        Broker.awaitingAck.add(batchNo)
        socket.close()
        println("Subscriptions sent")
    }

    boolean subscribedToTopic(ArrayList<String> subscriberTopics, ArrayList<String> topics) {
        boolean isValid = false
        topics.forEach { String topic ->
            if (subscriberTopics.contains(topic)) isValid = true
        }

        return isValid
    }

    String getBatchNo() {
        return ("$batchNo")
    }
}
