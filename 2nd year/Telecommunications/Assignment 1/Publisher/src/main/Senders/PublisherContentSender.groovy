package main.Senders

import main.Publisher
import main.Receivers.PublisherLossReceiver
import main.Structures.Broker
import main.Structures.PublisherContent

class PublisherContentSender implements Runnable {

    InetAddress address
    int port
    boolean received
    PublisherContent publisherContent

    PublisherContentSender(Broker broker, PublisherContent publisherContent) {
        this.address = InetAddress.getByName(broker.location.split(":").first())
        this.port = broker.location.split(":").last().toInteger()
        this.publisherContent = publisherContent
    }

    PublisherContentSender(String broker, PublisherContent publisherContent){
        this.address = InetAddress.getByName(broker.split(":").first())
        this.port = broker.split(":").last().toInteger()
        this.publisherContent = publisherContent
    }

    @Override
    void run() {

        ByteArrayOutputStream bstream = new ByteArrayOutputStream()
        ObjectOutputStream ostream = new ObjectOutputStream(bstream)
        ostream.writeObject(publisherContent)
        ostream.flush()

        byte[] buffer = bstream.toByteArray()

        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port)
        DatagramSocket socket = new DatagramSocket()

        socket.send(packet)
        socket.close()

        Publisher.awaitingAck.add(Publisher.batchNo)
    }
}