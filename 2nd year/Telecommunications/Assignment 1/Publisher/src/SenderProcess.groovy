class SenderProcess implements Runnable{

    int port
    PublisherContent publisherContent

    SenderProcess(int port, PublisherContent publisherContent){
        this.port = port
        this.publisherContent = publisherContent
    }

    @Override
    void run() {
        println("Messages subscriptions")
        InetAddress address = InetAddress.getLocalHost()

        ByteArrayOutputStream bstream = new ByteArrayOutputStream()
        ObjectOutputStream ostream = new ObjectOutputStream(bstream)
        ostream.writeObject(publisherContent)
        ostream.flush()

        byte[] buffer = bstream.toByteArray()

        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port)
        DatagramSocket socket = new DatagramSocket()

        socket.send(packet)
        socket.close()

        println("Messages sent")
    }
}
