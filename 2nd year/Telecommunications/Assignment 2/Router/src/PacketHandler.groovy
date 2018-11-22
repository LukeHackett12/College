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
                helloReceived()
                break
            default:
                //Return not yet implemented
                break
        }
        Thread.currentThread().interrupt()
    }

    void helloReceived() {
        println("Hello(0) exchanged with controller")
    }
}
