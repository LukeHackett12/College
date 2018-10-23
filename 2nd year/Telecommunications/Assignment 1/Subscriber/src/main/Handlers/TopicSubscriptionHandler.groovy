package main.Handlers

import main.Senders.SubscriberSender
import main.Structures.BrokerContent
import main.Subscriber

import static main.Subscriber.brokers
import static main.Subscriber.terminal
import static main.Subscriber.terminal
import static main.Subscriber.terminal
import static main.Subscriber.terminal
import static main.Subscriber.terminal

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
            terminal.print("Do you want to subscribe to a topic(1), unsubscribe from a topic(2), view messages(3), or add a broker(4)? ")
            String line = terminal.takeInput()

            try {
                int answer = line.toInteger()
                switch (answer) {
                    case 1:
                        subscribeToTopic()
                        terminal.clear()
                        break
                    case 2:
                        unsubscribeFromTopic()
                        terminal.clear()
                        break
                    case 3:
                        viewMessages()
                        break
                    case 4:
                        addBroker()
                        terminal.clear()
                        break
                    default:
                        terminal.clear()
                        break
                }
            } catch (NumberFormatException ignored) {
                terminal.clear()
            }
        }
    }

    private void subscribeToTopic() {
        terminal.print('What do you want to subscribe to? ')
        ArrayList<String> topics = terminal.takeInput().split(',')

        senderProcess = new SubscriberSender(SUBSCRIBE, topics)
        Thread thread = new Thread(senderProcess)
        thread.start()
    }

    private void unsubscribeFromTopic() {
        terminal.print("What do you want to unsubscribe from? ")
        ArrayList<String> topics = terminal.takeInput().split(',')

        senderProcess = new SubscriberSender(UNSUBSCRIBE, topics)
        Thread thread = new Thread(senderProcess)
        thread.start()
    }

    private void addBroker() {
        boolean notValid = true
        String broker
        while (notValid) {
            terminal.print("Enter a broker you want to add (in the format X.X.X.X:X): ")
            broker = terminal.takeInput()

            if (broker.matches("(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}):(\\d{1,5})")) notValid = false
            else terminal.println("That was not a valid ip")
        }

        brokers.add(broker)
        brokers.unique()
    }

    private void viewMessages() {
        terminal.print("What topic do you want to see the messages for? ")
        String topic = terminal.takeInput()

        terminal.clear()
        terminal.print("Messages:")
        Subscriber.messagesReceived.forEach { BrokerContent brokerContent ->
            if (brokerContent.topics.contains(topic)) printMessage(brokerContent, 'r' as char, 'b' as char)
        }
    }

    void printMessage(BrokerContent brokerContent, char foreground, char background) {
        terminal.setTextColor(foreground, background)
        terminal.print("\nBatch No. $brokerContent.batchNo" +
                "\n______________________\nCONTENT:" +
                "\n______________________" +
                "\nTopics: $brokerContent.topics" +
                "\nMessage: $brokerContent.message" +
                "\n______________________" +
                "\n")
        terminal.setDefaultColors()
    }
}
