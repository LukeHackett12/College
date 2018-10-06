package main.Handlers

import main.Publisher
import main.Senders.PublisherContentSender
import main.Structures.PublisherContent

class MessageHandler implements Runnable {

    PublisherContentSender senderProcess
    int port

    MessageHandler(int port) {
        this.port = port
    }

    @Override
    void run() {
        while (true) {
            println("What topics does this message have? ")
            ArrayList<String> topics = System.in.newReader().readLine().split(',')

            println("What is the message? ")
            String message = System.in.newReader().readLine()

            PublisherContent content = new PublisherContent("$Publisher.publisherId.$Publisher.batchNo", topics, message)

            senderProcess = new PublisherContentSender(port, content)
            Thread thread = new Thread(senderProcess)
            thread.start()

            while(!senderProcess.received){
                println("Waiting for acknowledgement")
                sleep(200)
            }

            println("Message acknowledged")
        }
    }
}
