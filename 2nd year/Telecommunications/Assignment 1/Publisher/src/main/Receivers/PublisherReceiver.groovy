package main.Receivers

import main.Publisher
import main.Structures.Broker

class PublisherReceiver implements Runnable{
    @Override
    void run() {
        while(true){
            byte[] buffer= new byte[65508]
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length)

            DatagramSocket socket = new DatagramSocket(Publisher.port, InetAddress.getLocalHost())

            socket.receive(packet)
            socket.close()

            buffer = packet.getData()
            ByteArrayInputStream bstream = new ByteArrayInputStream(buffer)
            ObjectInputStream ostream = new ObjectInputStream(bstream)
            String content = ostream.readUTF()

            (content.split(" ").first() == "acknowledgement") ?
                    startReadingAcknowledgement(content.split(" ").last())
                    : startReadingLoss(content.split(" ").last(), packet)
        }
    }

    void startReadingAcknowledgement(String content) {
        if (Publisher.awaitingAck.contains(content.toInteger())) {
            Publisher.awaitingAck.remove(content.toInteger())
            Publisher.batchNo++
        }
    }

    void startReadingLoss(String content, DatagramPacket packet) {
        PublisherLossReceiver publisherLossReceiver = new PublisherLossReceiver(content.toInteger(), packet, getid(packet))
        Thread thread = new Thread(publisherLossReceiver)
        thread.start()
    }

    int getid(DatagramPacket datagramPacket) {
        Broker broker = Publisher.brokers.find{it.location == "$datagramPacket.address:$datagramPacket.port"}
        broker.id
    }
}
