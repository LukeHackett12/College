package main.Senders

import main.Publisher
import main.Structures.Broker
import main.Structures.MessageTime
import main.Structures.PublisherContent

class PublisherContentSender implements Runnable {

    InetAddress address
    int port
    PublisherContent publisherContent
    boolean sent

    PublisherContentSender(Broker broker, PublisherContent publisherContent) {
        this.address = InetAddress.getByName(broker.location.split(":").first())
        this.port = broker.location.split(":").last().toInteger()
        this.publisherContent = publisherContent
    }

    @Override
    void run() {
        ByteArrayOutputStream bstream = new ByteArrayOutputStream()
        ObjectOutputStream ostream = new ObjectOutputStream(bstream)
        ostream.writeObject(publisherContent)
        ostream.flush()

        byte[] flag = [(byte) 0]
        byte[] buffer = new byte[flag.length + bstream.toByteArray().length]
        System.arraycopy(flag, 0, buffer, 0, flag.length)
        System.arraycopy(bstream.toByteArray(), 0, buffer, flag.length, bstream.toByteArray().length)

        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port)
        DatagramSocket socket = new DatagramSocket()

        socket.send(packet)
        socket.close()

        Publisher.awaitingAck.add(new MessageTime(Publisher.batchNo))
        sent = true

        Thread.currentThread().interrupt()
        return
    }
}