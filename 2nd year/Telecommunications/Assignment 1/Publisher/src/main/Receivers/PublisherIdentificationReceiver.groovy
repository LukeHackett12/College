package main.Receivers

import main.Publisher
import main.Structures.IdentificationContent

class PublisherIdentificationReceiver implements Runnable{

    int port

    PublisherIdentificationReceiver(int port){
        this.port = port
    }

    @Override
    void run() {
        byte[] buffer= new byte[65508]
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length)

        DatagramSocket socket = new DatagramSocket(port, InetAddress.getLocalHost())

        socket.receive(packet)
        socket.close()

        buffer = packet.getData()
        ByteArrayInputStream bstream = new ByteArrayInputStream(buffer)
        ObjectInputStream ostream = new ObjectInputStream(bstream)
        IdentificationContent identificationContent = ostream.readObject() as IdentificationContent
        Publisher.publisherId = identificationContent.uniqueId

        println("ID is $Publisher.publisherId")
    }
}
