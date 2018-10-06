package main.Handlers

import main.Structures.BrokerContent

class SubscriberPacketHandler implements Runnable{
    DatagramPacket packet

    SubscriberPacketHandler(DatagramPacket packet){
        this.packet = packet
    }

    @Override
    void run() {
        byte[] buffer = packet.getData()
        ByteArrayInputStream bstream = new ByteArrayInputStream(buffer)
        ObjectInputStream ostream = new ObjectInputStream(bstream)
        BrokerContent brokerContent = ostream.readObject() as BrokerContent

        println("\n\nBatch No. $brokerContent.batchNo\n______________________\nCONTENT:\n______________________\nTopics: $brokerContent.topics\nMessage: $brokerContent.message\n______________________")
        acknowledgeReceipt()
    }

    void acknowledgeReceipt(){

    }
}
