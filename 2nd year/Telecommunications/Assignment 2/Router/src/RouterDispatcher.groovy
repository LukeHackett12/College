class RouterDispatcher{

    static void phoneHome() {
        byte[] flag = [(byte) 0]
        DatagramPacket packet = new DatagramPacket(flag, flag.length, Router.controllerAddress, Router.controllerPort)
        DatagramSocket socket = new DatagramSocket()
        socket.send(packet)
    }
}

