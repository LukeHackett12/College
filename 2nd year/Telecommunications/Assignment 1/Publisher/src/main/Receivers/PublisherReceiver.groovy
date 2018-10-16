package main.Receivers

import main.Publisher

class PublisherReceiver implements Runnable {

    DatagramSocket socket

    PublisherReceiver() {
        socket = new DatagramSocket()
        Publisher.port = socket.localPort
        socket.close()
    }

    @Override
    void run() {
        while (true) {
            byte[] buffer = new byte[65508]
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length)

            socket = new DatagramSocket(Publisher.port, InetAddress.getLocalHost())

            socket.receive(packet)
            socket.close()

            buffer = packet.getData()
            ByteArrayInputStream bstream = new ByteArrayInputStream(Arrays.copyOfRange(buffer, 1, buffer.length - 1))
            ObjectInputStream ostream = new ObjectInputStream(bstream)
            String content = ostream.readUTF()

            switch (content.split(' ').first()) {
                case 'acknowledgement':
                    startReadingAcknowledgement(content.split(' ').last())
                    break
                case 'identity':
                    startReadingIdentity(content.split(' ').last(), packet)
                    break
            }
        }
    }

    void startReadingAcknowledgement(String content) {
        if (Publisher.awaitingAck.first().messageNo == content.toInteger()) {
            Publisher.awaitingAck.remove(0)
            Publisher.batchNo++
        }
    }

    void startReadingIdentity(String content, DatagramPacket packet) {
        String port = content.split(':').first()
        int id = content.split(':').last().toInteger()

        Publisher.brokers.find {
            "$packet.address:$port".contains(it.location)
        }.id = id

        println("ID is $id for broker $packet.address:$port")
    }
}
