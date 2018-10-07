package main.Senders

import main.Broker
import main.Structures.IdentificationContent

class PublisherIdentificationSender implements Runnable{

    InetAddress address
    int port
    int uniqueId

    PublisherIdentificationSender(InetAddress address, int port, int uniqueId){
        this.address = address
        this.port = port
        this.uniqueId = uniqueId
    }

    @Override
    void run() {
        IdentificationContent identificationContent = new IdentificationContent(uniqueId, Broker.pubPort)

        ByteArrayOutputStream bstream = new ByteArrayOutputStream()
        ObjectOutputStream ostream = new ObjectOutputStream(bstream)
        ostream.writeObject(identificationContent)
        ostream.flush()

        byte[] buffer = bstream.toByteArray()

        DatagramSocket socket = new DatagramSocket()
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port)

        println("Sending to $port")
        socket.send(packet)

        Broker.uniqueId++
    }
}
