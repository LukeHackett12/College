package main

import main.Receivers.BrokerReceiver
import main.Structures.Publisher
import main.Structures.Subscriber

class Broker {
    public static final String PUBLISHER = 'Publisher'
    public static final String SUBSCRIBER = 'main.Structures.Subscriber'
    public static final String IDENTITY = 'Identity'

    static int pubPort
    static int idPort
    static int subPort

    static int uniqueId
    static ArrayList<Subscriber> subscribersList
    static ArrayList<Publisher> publishersList

    Broker(){
        uniqueId = 0
        subscribersList = new ArrayList<>()
        publishersList = new ArrayList<>()

        BrokerReceiver publisherReceiver = new BrokerReceiver(PUBLISHER, (pubPort = findFreePort()))
        createThread(publisherReceiver)

        BrokerReceiver identityReceiver = new BrokerReceiver(IDENTITY, (idPort = findFreePort()))
        createThread(identityReceiver)

        BrokerReceiver subscriberReceiver = new BrokerReceiver(SUBSCRIBER, (subPort = findFreePort()))
        createThread(subscriberReceiver)
    }

    int findFreePort(){
        ServerSocket ss = new ServerSocket()
        ss.bind(new InetSocketAddress(0))
        int port = ss.localPort
        ss.close()
        port
    }

    void createThread(Runnable runnable){
        Thread thread = new Thread(runnable)
        thread.start()
    }

    static void main(String[] args){
        new Broker()
    }
}
