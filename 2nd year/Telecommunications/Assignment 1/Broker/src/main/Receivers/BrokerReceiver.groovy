package main.Receivers

import main.Handlers.BrokerPacketHandler

class BrokerReceiver implements Runnable {
    int port

    public static final String PUBLISHER = "publisher"
    public static final String SUBSCRIBER = "subscriber"
    public static final String IDENTITY = "identity"

    BrokerReceiver(int port) {
        this.port = port
    }

    @Override
    void run() {
        while (true) {
            println("Listening on port $port")

            DatagramSocket socket = new DatagramSocket(port, InetAddress.getLocalHost())
            byte[] buffer = new byte[65508]
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length)

            socket.receive(packet)
            socket.close()

            String contentType

            buffer = packet.getData()
            switch ((int) buffer[0]) {
                case 0:
                    contentType = PUBLISHER
                    break
                case 1:
                    contentType = SUBSCRIBER
                    break
                case 2:
                    contentType = IDENTITY
                    break
            }

            BrokerPacketHandler packetHandler = new BrokerPacketHandler(contentType, packet, buffer)
            Thread thread = new Thread(packetHandler)
            thread.start()
            println("Starting Receive for $contentType")
        }
    }
}
