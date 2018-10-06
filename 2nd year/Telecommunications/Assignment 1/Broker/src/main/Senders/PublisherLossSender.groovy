package main.Senders


class PublisherLossSender implements Runnable{

    InetAddress address
    int port
    int messageNo

    PublisherLossSender(InetAddress address, int port, int messageNo){
        this.address = address
        this.port = port
        this.messageNo = messageNo
    }

    @Override
    void run() {
        ByteArrayOutputStream bstream = new ByteArrayOutputStream()
        ObjectOutputStream ostream = new ObjectOutputStream(bstream)
        ostream.writeInt(messageNo)
        ostream.flush()

        byte[] buffer = bstream.toByteArray()

        DatagramSocket socket = new DatagramSocket()
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port)

        println("Sending to $port")
        socket.send(packet)
    }
}
