package main.Senders

import main.Structures.SubscriberContent
import main.Subscriber

class SubscriberSender implements Runnable {

    ArrayList<String> topics
    String type

    SubscriberSender(String type, ArrayList<String> topics) {
        this.topics = topics
        this.type = type
    }

    @Override
    void run() {
        SubscriberContent subscriberContent = new SubscriberContent(topics, type, Subscriber.portNumber)

        for (String broker : Subscriber.brokers) {
            InetAddress address = InetAddress.getByName(broker.split(":").first())

            ByteArrayOutputStream bstream = new ByteArrayOutputStream()
            ObjectOutputStream ostream = new ObjectOutputStream(bstream)
            ostream.writeObject(subscriberContent)
            ostream.flush()

            byte[] flag = [(byte) 1, (byte) 0]
            byte[] buffer = new byte[flag.length + bstream.toByteArray().length]
            System.arraycopy(flag, 0, buffer, 0, flag.length)
            System.arraycopy(bstream.toByteArray(), 0, buffer, flag.length, bstream.toByteArray().length)

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, broker.split(":").last().toInteger())
            DatagramSocket socket = new DatagramSocket()

            socket.send(packet)
            socket.close()

        }
        Subscriber.awaitingAck.add(subscriberContent)
    }
}