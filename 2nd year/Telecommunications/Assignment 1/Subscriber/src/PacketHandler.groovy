class PacketHandler implements Runnable{
    DatagramPacket packet

    PacketHandler(DatagramPacket packet){
        this.packet = packet
    }

    @Override
    void run() {
        byte[] buffer = packet.getData()
        ByteArrayInputStream bstream = new ByteArrayInputStream(buffer)
        ObjectInputStream ostream = new ObjectInputStream(bstream)
        BrokerContent brokerContent = ostream.readObject() as BrokerContent
1
        println("______________________\nCONTENT:\n______________________\nTopics: $brokerContent.topics\nMessage: $brokerContent.message\n______________________")
    }
}
