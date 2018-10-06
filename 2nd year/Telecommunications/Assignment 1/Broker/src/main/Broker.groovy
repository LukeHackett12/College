package main

import main.Receivers.BrokerReceiver
import main.Structures.Publisher
import main.Structures.Subscriber

class Broker {
    public static final String PUBLISHER = 'Publisher'
    public static final String SUBSCRIBER = 'main.Structures.Subscriber'
    public static final String IDENTITY = 'Identity'

    static int DEFAULT_PUBLISHER_PORT = 5050
    static int DEFAULT_SUBSCRIBER_PORT = 5151
    static int DEFAULT_IDENTITY_PORT = 5252

    static int uniqueId
    static ArrayList<Subscriber> subscribersList
    static ArrayList<Publisher> publishersList

    Broker(){
        uniqueId = 0
        subscribersList = new ArrayList<>()
        publishersList = new ArrayList<>()

        BrokerReceiver publisherReceiver = new BrokerReceiver(PUBLISHER, DEFAULT_PUBLISHER_PORT)
        createThread(publisherReceiver)

        BrokerReceiver identityReceiver = new BrokerReceiver(IDENTITY, DEFAULT_IDENTITY_PORT)
        createThread(identityReceiver)

        BrokerReceiver subscriberReceiver = new BrokerReceiver(SUBSCRIBER, DEFAULT_SUBSCRIBER_PORT)
        createThread(subscriberReceiver)
    }

    void createThread(Runnable runnable){
        Thread thread = new Thread(runnable)
        thread.start()
    }

    static void main(String[] args){
        new Broker()
    }
}
