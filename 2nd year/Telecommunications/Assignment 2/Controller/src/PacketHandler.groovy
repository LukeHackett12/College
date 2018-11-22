class PacketHandler implements Runnable{
    DatagramPacket packet

    PacketHandler(DatagramPacket packet) {
        this.packet = packet
    }

    @Override
    void run() {
        switch (packet.data[0]){
            case 0:
                //Hello
                Controller.routers.add(packet.address)
                Controller.routers.unique()
                codeAcknowledgement(0)
                break
            default:
                //Return not yet implemented
                break
        }
        Thread.currentThread().interrupt()
    }

    void codeAcknowledgement(int code) {
        InetAddress routerAddress = packet.address

        ControllerDispatcher.acknowledgeCode(code, routerAddress, 5050)
    }
}
