package main

import main.Handlers.PublisherHandler
import main.Receivers.PublisherReceiver
import main.Structures.Broker
import main.Structures.PublisherContent

class Publisher {

    static int batchNo
    static int port

    static ArrayList<Broker> brokers
    static ArrayList<PublisherContent> trailingMessages
    static ArrayList<Integer> awaitingAck

    Publisher(){
        batchNo = 0
        brokers = new ArrayList<>()
        trailingMessages = new ArrayList<>()
        awaitingAck = new ArrayList<>()

        PublisherHandler messagePublisher = new PublisherHandler()
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
