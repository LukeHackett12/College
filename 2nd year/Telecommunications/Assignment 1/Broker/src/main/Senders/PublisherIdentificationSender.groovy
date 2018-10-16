package main.Senders

import main.Broker

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
        String identificationContent = "identity $Broker.port:$uniqueId"

        ByteArrayOutputStream bstream = new ByteArrayOutputStream()
        ObjectOutputStream ostream = new ObjectOutputStream(bstream)
        ostream.writeUTF(identificationContent)
        ostream.flush()

        byte[] flag = [(byte)0]
        byte[] buffer = new byte[flag.length + bstream.toByteArray().length]
        System.arraycopy(flag, 0, buffer, 0, flag.length)
        System.arraycopy(bstream.toByteArray(), 0, buffer, flag.length, bstream.toByteArray().length)

        DatagramSocket socket = new DatagramSocket()
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port)

        println("Sending to $port")
        socket.send(packet)
        socket.close()

        Broker.uniqueId++

        Thread.currentThread().interrupt()
        return
    }
}
