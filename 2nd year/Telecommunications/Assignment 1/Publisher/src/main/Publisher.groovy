package main

import main.Handlers.PublisherHandler
import main.Receivers.PublisherReceiver
import main.Structures.Broker
import main.Structures.MessageTime

import java.util.concurrent.CopyOnWriteArrayList

class Publisher {

    static int batchNo
    static int port

    static ArrayList<Broker> brokers
    static CopyOnWriteArrayList<MessageTime> awaitingAck

    Publisher() {
        batchNo = 0
        brokers = new ArrayList<>()

        awaitingAck = new CopyOnWriteArrayList<>()

        PublisherHandler messagePublisher = new PublisherHandler()
        createThread(messagePublisher)

        PublisherReceiver publisherReceiver = new PublisherReceiver()
        createThread(publisherReceiver)
    }

    void createThread(Runnable runnable) {
        Thread thread = new Thread(runnable)
        thread.start()
    }

    static void main(String[] args) {
        new Publisher()
    }
}
