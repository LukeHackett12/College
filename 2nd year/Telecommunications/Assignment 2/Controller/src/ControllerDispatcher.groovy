class ControllerDispatcher {

    static void acknowledgeCode(int code, InetAddress address, int port){
        byte[] flag = [(byte) code]
        DatagramPacket packet = new DatagramPacket(flag, flag.length, address, port)
        DatagramSocket socket = new DatagramSocket()
        socket.send(packet)
    }
}
