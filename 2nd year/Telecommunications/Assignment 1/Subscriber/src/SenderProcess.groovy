class SenderProcess implements Runnable{

    int port
    ArrayList<String> topics

    SenderProcess(int port, ArrayList<String> topics){
        this.port = port
        this.topics = topics
    }

    @Override
    void run() {
        println("Sending subscriptions")
        InetAddress address = InetAddress.getLocalHost()

        ByteArrayOutputStream bstream = new ByteArrayOutputStream()
        ObjectOutputStream ostream = new ObjectOutputStream(bstream)
        ostream.writeObject(new SubscriberContent(topics, Subscriber.portNumber))
        ostream.flush()

        byte[] buffer = bstream.toByteArray()

        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port)
        DatagramSocket socket = new DatagramSocket()

        socket.send(packet)
        socket.close()

        println("Subscriptions sent")
    }
}
