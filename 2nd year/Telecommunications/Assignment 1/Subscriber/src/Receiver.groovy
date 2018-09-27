class Receiver implements Runnable {
    int port

    Receiver(int port) {
        this.port = port
    }

    @Override
    void run() {
        DatagramSocket socket = new DatagramSocket(port, InetAddress.getLocalHost())

        while (true) {
            byte[] buffer= new byte[65508]
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length)

            socket.receive(packet)

            PacketHandler packetHandler = new PacketHandler(packet)
            Thread thread = new Thread(packetHandler)
            thread.start()
        }
    }
}
