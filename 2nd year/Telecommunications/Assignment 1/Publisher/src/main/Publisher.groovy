package main

import main.Handlers.MessageHandler
import main.Senders.PublisherIdentificationSender

class Publisher {

    final int DEFAULT_BROKER_PORT = 5050
    final int DEFAULT_IDENTIFICATION_PORT = 5252

    static int batchNo
    static int publisherId
    static int port

    Publisher(){
        batchNo = 0

        PublisherIdentificationSender publisherIdentificationSender = new PublisherIdentificationSender(DEFAULT_IDENTIFICATION_PORT)
        createThread(publisherIdentificationSender)

        MessageHandler messagePublisher = new MessageHandler(DEFAULT_BROKER_PORT)
        createThread(messagePublisher)
    }

    void createThread(Runnable runnable) {
        Thread thread = new Thread(runnable)
        thread.start()
    }

    static void main(String[] args){
        new Publisher()
    }
}
