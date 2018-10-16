package main

import main.Handlers.PublisherHandler
import main.Receivers.PublisherReceiver
import main.Structures.Broker
import main.Structures.MessageTime
import main.Structures.PublisherContent

import java.util.concurrent.CopyOnWriteArrayList

class Publisher {

    static int batchNo
    static int port

    static ArrayList<Broker> brokers
    static ArrayList<PublisherContent> trailingMessages
    static CopyOnWriteArrayList<MessageTime> awaitingAck

    Publisher() {
        batchNo = 4
        brokers = new ArrayList<>()
        trailingMessages = new ArrayList<>()

        ArrayList<String> test = new ArrayList<>()
        test.add("test")
        trailingMessages.add(new PublisherContent(0, 0, test, "resr"))
        trailingMessages.add(new PublisherContent(0, 1, test, "resr"))
        trailingMessages.add(new PublisherContent(0, 2, test, "resr"))
        trailingMessages.add(new PublisherContent(0, 3, test, "resr"))

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
