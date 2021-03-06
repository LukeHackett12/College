package main.Handlers

import main.Structures.BrokerContent
import main.Structures.SubscriberContent
import main.Subscriber

import static main.Subscriber.messagesReceived
import static main.Subscriber.terminal

class SubscriberPacketHandler implements Runnable {
    DatagramPacket packet

    SubscriberPacketHandler(DatagramPacket packet) {
        this.packet = packet
    }

    void run() {
        byte[] buffer = packet.getData()

        ByteArrayInputStream bstream = new ByteArrayInputStream(Arrays.copyOfRange(buffer, 1, buffer.length - 1))
        ObjectInputStream ostream = new ObjectInputStream(bstream)
        switch ((int) buffer[0]) {
            case 0:
                BrokerContent brokerContent = ostream.readObject() as BrokerContent
                if (!messagesReceived.contains { it.batchNo == brokerContent.batchNo }) {
                    messagesReceived.add(brokerContent)
                    printMessage(brokerContent, 'r' as char, 'b' as char)
                }

                acknowledgeReceipt(brokerContent.batchNo)
                break
            case 1:
                SubscriberContent subscriberContent = ostream.readObject() as SubscriberContent
                subscriptionAcknowledgement(subscriberContent)
                break
        }
    }

    void printMessage(BrokerContent brokerContent, char foreground, char background) {
        terminal.clear()
        terminal.setTextColor(foreground, background)

        String pub = brokerContent.batchNo.split('\\.').first()
        String batch =  brokerContent.batchNo.split('\\.').last()

        terminal.println(
                "Publisher : $pub" +
                "\nBatch No. : $batch" +
                        "\n______________________\nContent" +
                        "\n______________________" +
                        "\nTopics: $brokerContent.topics" +
                        "\nMessage: $brokerContent.message" +
                        "\n______________________")
        terminal.setDefaultColors()
        terminal.print("Do you want to subscribe to a topic(1), unsubscribe from a topic(2), view messages(3), or add a broker(4)? ")
    }

    void subscriptionAcknowledgement(SubscriberContent subscriberContent) {
        Subscriber.awaitingAck.remove(subscriberContent)
        println("Subsription/Unsubscription for $subscriberContent.topics was acknowledged")
    }

    void acknowledgeReceipt(String batchNo) {
        ByteArrayOutputStream bstream = new ByteArrayOutputStream()
        ObjectOutputStream ostream = new ObjectOutputStream(bstream)
        ostream.writeUTF(batchNo)
        ostream.flush()

        byte[] flag = [(byte) 1, (byte) 1]
        byte[] buffer = new byte[flag.length + bstream.toByteArray().length]
        System.arraycopy(flag, 0, buffer, 0, flag.length)
        System.arraycopy(bstream.toByteArray(), 0, buffer, flag.length, bstream.toByteArray().length)

        String packetAddress = packet.address.toString()
        int port = Subscriber.brokers.find {
            packetAddress.contains(it.split(':').first())
        }.split(':').last().toInteger()

        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, packet.address, port)
        DatagramSocket socket = new DatagramSocket()

        socket.send(packet)
        socket.close()

        println("Acknowledgement sent for message $batchNo")
    }
}
