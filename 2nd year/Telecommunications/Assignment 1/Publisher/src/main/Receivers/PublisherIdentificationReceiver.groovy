package main.Receivers

import main.Publisher
import main.Structures.IdentificationContent

class PublisherIdentificationReceiver implements Runnable {

    @Override
    void run() {
        byte[] buffer = new byte[65508]
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length)

        DatagramSocket socket = new DatagramSocket(Publisher.port, InetAddress.getLocalHost())

        socket.receive(packet)
        socket.close()

        buffer = packet.getData()
        ByteArrayInputStream bstream = new ByteArrayInputStream(buffer)
        ObjectInputStream ostream = new ObjectInputStream(bstream)
        IdentificationContent identificationContent = ostream.readObject() as IdentificationContent

        String address = packet.address.toString().substring(1, packet.address.toString().length())
        Publisher.brokers.find {
            it.location == "$address:$identificationContent.publisherPort"
        }.id = identificationContent.uniqueId

        println("ID is $identificationContent.uniqueId for broker $packet.address:$identificationContent.publisherPort")

        PublisherReceiver publisherReceiver = new PublisherReceiver()
        Thread thread = new Thread(publisherReceiver)
        thread.start()
    }
}
