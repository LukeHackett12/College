package main.Senders

import main.Publisher

class PublisherIdentificationSender implements Runnable {

    InetAddress address
    int port

    PublisherIdentificationSender(String broker) {
        this.address = InetAddress.getByName(broker.split(':').first())
        this.port = broker.split(':').last().toInteger()
    }

    @Override
    void run() {
        ByteArrayOutputStream bstream = new ByteArrayOutputStream()
        ObjectOutputStream ostream = new ObjectOutputStream(bstream)
        ostream.writeInt(Publisher.port)
        ostream.flush()

        byte[] flag = [(byte) 2]
        byte[] buffer = new byte[flag.length + bstream.toByteArray().length]
        System.arraycopy(flag, 0, buffer, 0, flag.length)
        System.arraycopy(bstream.toByteArray(), 0, buffer, flag.length, bstream.toByteArray().length)

        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port)
        DatagramSocket socket = new DatagramSocket()

        socket.send(packet)
        socket.close()

        Thread.currentThread().interrupt()
        return
    }
}
