package main.Senders

import main.Publisher
import main.Receivers.PublisherIdentificationReceiver

class PublisherIdentificationSender implements Runnable {

    int port

    PublisherIdentificationSender(int port){
        this.port = port
    }

    @Override
    void run() {
        InetAddress address = InetAddress.getByName("localhost")

        byte[] buffer = new byte[0]

        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port)
        DatagramSocket socket = new DatagramSocket()

        Publisher.port = socket.localPort
        socket.send(packet)
        socket.close()

        PublisherIdentificationReceiver publisherReceiver = new PublisherIdentificationReceiver()
        Thread thread = new Thread(publisherReceiver)
        thread.start()
    }
}
