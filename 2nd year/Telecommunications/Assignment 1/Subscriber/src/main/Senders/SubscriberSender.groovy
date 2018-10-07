package main.Senders

import main.Structures.SubscriberContent
import main.Subscriber

class SubscriberSender implements Runnable{

    ArrayList<String> topics
    String type

    SubscriberSender(String type, ArrayList<String> topics){
        this.topics = topics
        this.type = type
    }

    @Override
    void run() {
        for(String broker : Subscriber.brokers) {
            InetAddress address = InetAddress.getByName(broker.split(":").first())

            ByteArrayOutputStream bstream = new ByteArrayOutputStream()
            ObjectOutputStream ostream = new ObjectOutputStream(bstream)
            ostream.writeObject(new SubscriberContent(topics, type, Subscriber.portNumber))
            ostream.flush()

            byte[] buffer = bstream.toByteArray()

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, broker.split(":").last().toInteger())
            DatagramSocket socket = new DatagramSocket()

            socket.send(packet)
            socket.close()
        }
    }
}