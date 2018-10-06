package main.Senders

import main.Structures.SubscriberContent
import main.Subscriber

class SubscriberSender implements Runnable{

    int port
    ArrayList<String> topics
    String type

    SubscriberSender(int port, String type, ArrayList<String> topics){
        this.port = port
        this.topics = topics
        this.type = type
    }

    @Override
    void run() {
        for(String ip : Subscriber.brokers) {
            InetAddress address = InetAddress.getByName(ip)

            ByteArrayOutputStream bstream = new ByteArrayOutputStream()
            ObjectOutputStream ostream = new ObjectOutputStream(bstream)
            ostream.writeObject(new SubscriberContent(topics, type, Subscriber.portNumber))
            ostream.flush()

            byte[] buffer = bstream.toByteArray()

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port)
            DatagramSocket socket = new DatagramSocket()

            socket.send(packet)
            socket.close()
        }
    }
}