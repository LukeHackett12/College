package main


import main.Handlers.TopicSubscriptionHandler
import main.Receivers.SubscriberReceiver
import main.Structures.BrokerContent
import main.Structures.SubscriberContent
import tcdIO.Terminal

class Subscriber{

    final int DEFAULT_BROKER_PORT = 5151

    static int portNumber
    static ArrayList<String> brokers
    static ArrayList<SubscriberContent> awaitingAck
    static ArrayList<BrokerContent> messagesReceived

    static Terminal messageTerminal

    Subscriber(){
        portNumber = findFreePort()
        brokers = new ArrayList<>()
        brokers = new ArrayList<>()
        awaitingAck = new ArrayList<>()
        messagesReceived = new ArrayList<>()

        messageTerminal = new Terminal("Messages")

        TopicSubscriptionHandler topicSubscription = new TopicSubscriptionHandler(DEFAULT_BROKER_PORT)
        createThread(topicSubscription)
        SubscriberReceiver receiver = new SubscriberReceiver(portNumber)
        createThread(receiver)
    }

    int findFreePort(){
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

    static void main(String[] args){
        new Subscriber()
    }
}