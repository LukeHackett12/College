package main.Receivers

import main.Handlers.SubscriberPacketHandler

class SubscriberReceiver implements Runnable {
    int port

    SubscriberReceiver(int port) {
        this.port = port
    }

    @Override
    void run() {
        DatagramSocket socket = new DatagramSocket(port, InetAddress.getLocalHost())

        while (true) {
            byte[] buffer = new byte[65508]
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length)

            socket.receive(packet)

            SubscriberPacketHandler packetHandler = new SubscriberPacketHandler(packet)
            Thread thread = new Thread(packetHandler)
            thread.start()
        }
    }
}
