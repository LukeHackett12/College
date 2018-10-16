package main.Handlers

import main.Senders.SubscriberSender
import main.Structures.BrokerContent
import main.Subscriber

class TopicSubscriptionHandler implements Runnable {

    static final String SUBSCRIBE = "subscribe"
    static final String UNSUBSCRIBE = "unsubscribe"

    SubscriberSender senderProcess
    int port

    TopicSubscriptionHandler(int port) {
        this.port = port
    }

    @Override
    void run() {
        while (true) {
            println("Do you want to subscribe to a topic(1), unsubscribe from a topic(2), add a broker(3), or view past messages(4)? ")

            String line = System.in.newReader().readLine()
            int answer
            try {
                answer = line.toInteger()
            } catch (NumberFormatException ignored) {
                println("Cannot recognise choice")
            }

            if (answer == 1)
                subscribeToTopic()
            else if (answer == 2)
                unsubscribeFromTopic()
            else if (answer == 3)
                addBroker()
            else if (answer == 4)
                viewMessages()

            clearScreen()
        }
    }

    private void subscribeToTopic() {
        print("What do you want to subscribe to? ")
        ArrayList<String> topics = System.in.newReader().readLine().split(',')

        senderProcess = new SubscriberSender(SUBSCRIBE, topics)
        Thread thread = new Thread(senderProcess)
        thread.start()
    }

    private void unsubscribeFromTopic() {
        print("What do you want to unsubscribe from? ")
        ArrayList<String> topics = System.in.newReader().readLine().split(',')

        senderProcess = new SubscriberSender(UNSUBSCRIBE, topics)
        Thread thread = new Thread(senderProcess)
        thread.start()
    }

    private void addBroker() {
        boolean notValid = true
        String broker
        while(notValid) {
            print("Enter a broker you want to add (in the format X.X.X.X:X): ")
            broker = System.in.newReader().readLine()

            if(broker.matches("(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}):(\\d{1,5})")) notValid = false
            else println("That was not a valid ip")
        }

        Subscriber.brokers.add(broker)
        Subscriber.brokers.unique()
    }

    private void viewMessages() {
        print("What topic do you want to see the messages for? ")
        String topic = System.in.newReader().readLine()

        print("Messages:")
        Subscriber.messagesReceived.forEach { BrokerContent brokerContent ->
            if (brokerContent.topics.contains(topic)) printMessage(brokerContent)
        }
        print("Press enter to return to menu... ")
        System.in.newReader().readLine()
    }

    void printMessage(BrokerContent brokerContent) {
        println("\nBatch No. $brokerContent.batchNo" +
                "\n______________________\nCONTENT:" +
                "\n______________________" +
                "\nTopics: $brokerContent.topics" +
                "\nMessage: $brokerContent.message" +
                "\n______________________" +
                "\n")
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J")
        System.out.flush()
    }
}
