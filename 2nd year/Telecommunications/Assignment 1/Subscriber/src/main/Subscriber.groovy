package main


import main.Handlers.TopicSubscriptionHandler
import main.Receivers.SubscriberReceiver

class Subscriber{

    final int DEFAULT_BROKER_PORT = 5151

    static int portNumber
    static ArrayList<String> brokers

    Subscriber(){
        portNumber = findFreePort()
        brokers = new ArrayList<>()

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