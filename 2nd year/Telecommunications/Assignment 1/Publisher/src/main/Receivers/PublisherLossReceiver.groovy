package main.Receivers

import main.Publisher
import main.Senders.PublisherContentSender
import main.Structures.PublisherContent

class PublisherLossReceiver implements Runnable{

    int messagesNeeded
    DatagramPacket packet
    int id

    PublisherLossReceiver(int messagesNeeded, DatagramPacket packet, int id){
        this.messagesNeeded = messagesNeeded
        this.packet = packet
        this.id = id
    }

    @Override
    void run() {
        for(int i = messagesNeeded-1; i >= 0; i--){
            PublisherContent content = Publisher.trailingMessages.get(i)
            content.batchNo = "$id.$content.batchNo.split(.).last()"
            senderProcess = new PublisherContentSender("$packet.address:$packet.port", content)
            Thread thread = new Thread(senderProcess)
            thread.start()
        }
    }
}
