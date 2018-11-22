class ControllerReceiver implements Runnable{
    @Override
    void run() {

        while(true){
            DatagramSocket socket = new DatagramSocket(Controller.port)

            byte[] buffer = new byte[65508]
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length)

            socket.receive(packet)
            socket.close()

            PacketHandler handler = new PacketHandler(packet)
            Thread t = new Thread(handler)
            t.start()
        }
    }
}
