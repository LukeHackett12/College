package main.Handlers

import groovy.time.TimeCategory
import groovy.time.TimeDuration
import main.Publisher
import main.Senders.PublisherContentSender
import main.Senders.PublisherIdentificationSender
import main.Structures.Broker
import main.Structures.MessageTime
import main.Structures.PublisherContent

import static main.Publisher.awaitingAck
import static main.Publisher.brokers
import static main.Publisher.terminal

class PublisherHandler implements Runnable {

    static final TimeDuration FIVE_SECONDS = new TimeDuration(0, 0, 5, 0)

    PublisherContentSender senderProcess
    ArrayList<PublisherContentSender> senders
    MessageTime waiting

    PublisherHandler() {
        senders = new ArrayList<>()
    }

    @Override
    void run() {
        while (true) {
            terminal.print("Do you want to message a topic(1) or add a broker(2)? ")

            String line = terminal.takeInput()
            int answer
            try {
                answer = line.toInteger()
            } catch (NumberFormatException ignored) {
                println("Cannot recognise choice")
            }

            switch (answer) {
                case 1:
                    messageSubscribers()
                    break
                case 2:
                    addBroker()
                    break
                default:
                    terminal.clear()
            }
        }
    }

    void createThread(Runnable runnable) {
        Thread thread = new Thread(runnable)
        thread.start()
    }

    private void messageSubscribers() {
        terminal.print("What topics does this message have(separated by ,)? ")
        ArrayList<String> topics = terminal.takeInput().split(',')

        terminal.print("What is the message? ")
        String message = terminal.takeInput()

        if(!brokers.empty) {
            PublisherContent content = new PublisherContent('-1', Publisher.batchNo, topics, message)

            sendMessage(content)
            waitForAck(content)

            content.batchNo = Publisher.batchNo
        }
    }

    private void waitForAck(PublisherContent content) {
        int retries = 0
        while (awaitingAck.size() != 0 && retries < 5) {
            if (TimeCategory.minus(new Date(), waiting.timeStart) > FIVE_SECONDS) {
                awaitingAck.remove(0)
                println("Retrying message...")
                sendMessage(content)
                retries++
            }
        }

        if (retries < 5) {
            println("Ack received for $waiting.messageNo")
        } else {
            println("Ack was not received for received for $waiting.messageNo")
            awaitingAck.clear()
        }
    }

    private void sendMessage(PublisherContent content) {
        Publisher.brokers.each { Broker broker ->
            content.uniqueId = broker.id
            senderProcess = new PublisherContentSender(broker, content)
            Thread thread = new Thread(senderProcess)
            thread.start()
            while (!senderProcess.sent) {
                //wait
            }
        }
        waiting = awaitingAck.first()
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

        PublisherIdentificationSender publisherIdentificationSender = new PublisherIdentificationSender(broker)
        createThread(publisherIdentificationSender)

        brokers.add(new Broker(id: '-1', location: broker))
        brokers.unique { it.location }
    }
}
