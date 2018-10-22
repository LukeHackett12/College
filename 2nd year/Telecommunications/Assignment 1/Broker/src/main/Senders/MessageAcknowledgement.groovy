package main.Senders


import main.Structures.Connection
import main.Structures.SubscriberContent

class MessageAcknowledgement implements Runnable {
    Connection connection
    int messageNo
    SubscriberContent subscriberContent

    MessageAcknowledgement(Connection connection, int messageNo) {
        this.connection = connection
        this.messageNo = messageNo
        this.subscriberContent = null
    }

    MessageAcknowledgement(Connection connection, SubscriberContent subscriberContent) {
        this.connection = connection
        this.subscriberContent = subscriberContent
    }

    @Override
    void run() {
        println("Acknowledging receipt from $connection.address:$connection.port")

        ByteArrayOutputStream bstream = new ByteArrayOutputStream()
        ObjectOutputStream ostream = new ObjectOutputStream(bstream)

        int type
        if (subscriberContent != null) {
            ostream.writeObject(subscriberContent)
            type = 1
        } else {
            ostream.writeUTF("acknowledgement $messageNo")
            type = 0
        }

        ostream.flush()

        byte[] flag = [(byte) type]
        byte[] buffer = new byte[flag.length + bstream.toByteArray().length]
        System.arraycopy(flag, 0, buffer, 0, flag.length)
        System.arraycopy(bstream.toByteArray(), 0, buffer, flag.length, bstream.toByteArray().length)

        DatagramSocket socket = new DatagramSocket()
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, connection.address, connection.port)
        socket.send(packet)

        socket.close()

        println("Acknowledgement sent")

        Thread.currentThread().interrupt()
        return
    }
}
