package main.Handlers

import main.Senders.SubscriberSender
import main.Subscriber

class TopicSubscriptionHandler implements Runnable {

    static final String SUBSCRIBE = "subscribe"
    static final String UNSUBSCRIBE = "unsubscribe"

    SubscriberSender senderProcess
    int port

    TopicSubscriptionHandler(int port){
        this.port = port
    }

    @Override
    void run(){
        while(true) {
            println("Do you want to subscribe to a topic(1), add a broker(2) or unsubscribe from a topic(3)? ")
            int answer = System.in.newReader().readLine().toInteger()

            if (answer == 1)
                subscribeToTopic()
            else if (answer == 2)
                addBroker()
            else if (answer == 3)
                unsubscribeFromTopic()
        }
    }

    private void subscribeToTopic() {
        print("What do you want to subscribe to? ")
        ArrayList<String> topics = System.in.newReader().readLine().split(',')

        senderProcess = new SubscriberSender(port, SUBSCRIBE, topics)
        Thread thread = new Thread(senderProcess)
        thread.start()
    }

    private void unsubscribeFromTopic() {
        print("What do you want to unsubscribe from? ")
        ArrayList<String> topics = System.in.newReader().readLine().split(',')

        senderProcess = new SubscriberSender(port, UNSUBSCRIBE, topics)
        Thread thread = new Thread(senderProcess)
        thread.start()
    }

    private void addBroker(){
        print("Enter any amount of brokers you want to add separated by commas: ")
        ArrayList<String> brokers = System.in.newReader().readLine().split(',')

        Subscriber.brokers.addAll(brokers)
        Subscriber.brokers.unique()
    }
}
