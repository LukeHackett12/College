package main.Senders


import main.Structures.Connection

class MessageAcknowledgement implements Runnable{
    Connection connection

    MessageAcknowledgement(Connection connection){
        this.connection = connection
    }

    @Override
    void run() {
        println("Acknowledging receipt from $connection.address:$connection.port")

        ByteArrayOutputStream bstream = new ByteArrayOutputStream()
        ObjectOutputStream ostream = new ObjectOutputStream(bstream)
        ostream.writeUTF("OK")
        ostream.flush()

        byte[] buffer = bstream.toByteArray()

        DatagramPacket packet
        DatagramSocket socket = new DatagramSocket()

        packet = new DatagramPacket(buffer, buffer.length, connection.address, connection.port)
        socket.send(packet)

        socket.close()

        println("Acknowledgement sent")
    }
}
