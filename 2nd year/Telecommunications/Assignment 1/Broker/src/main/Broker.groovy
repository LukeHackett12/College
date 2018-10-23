package main

import main.Receivers.BrokerReceiver
import main.Structures.Publisher
import main.Structures.Subscriber

import java.util.concurrent.CopyOnWriteArrayList

class Broker {
    static int port
    static ArrayList<Subscriber> subscribersList
    static ArrayList<Publisher> publishersList
    static CopyOnWriteArrayList<String> awaitingAck

    Broker() {
        subscribersList = new ArrayList<>()
        publishersList = new ArrayList<>()
        awaitingAck = new CopyOnWriteArrayList<>()

        BrokerReceiver brokerReceiver = new BrokerReceiver((port = findFreePort()))
        createThread(brokerReceiver)
    }

    int findFreePort() {
        ServerSocket ss = new ServerSocket()
        ss.bind(new InetSocketAddress(0))
        int port = ss.localPort
        ss.close()
        port
    }

    void createThread(Runnable runnable) {
        Thread thread = new Thread(runnable)
        thread.start()
    }

    static void main(String[] args) {
        new Broker()
    }
}
