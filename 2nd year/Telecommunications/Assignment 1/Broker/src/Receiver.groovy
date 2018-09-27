class Receiver implements Runnable {
    String contentType
    int port

    Receiver(String contentType, int port) {
        this.contentType = contentType
        this.port = port
    }

    @Override
    void run() {
        DatagramSocket socket = new DatagramSocket(port, InetAddress.getLocalHost())

        while (true) {
            println("Listening for $contentType on port $port")
            byte[] buffer= new byte[65508]
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length)

            socket.receive(packet)

            PacketHandler packetHandler = new PacketHandler(contentType, packet)
            Thread thread = new Thread(packetHandler)
            thread.start()
            println("Starting Receive for $contentType")
        }
    }
}
