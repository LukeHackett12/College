package main.Handlers

import groovy.time.TimeCategory
import groovy.time.TimeDuration
import main.Publisher
import main.Senders.PublisherContentSender
import main.Senders.PublisherIdentificationSender
import main.Structures.Broker
import main.Structures.MessageTime
import main.Structures.PublisherContent

class PublisherHandler implements Runnable {

    PublisherContentSender senderProcess
    ArrayList<PublisherContentSender> senders
    MessageTime waiting

    PublisherHandler() {
        senders = new ArrayList<>()
    }

    @Override
    void run() {
        while (true) {
            println("Do you want to message a topic(1) or add a broker(2)?")

            String line = System.in.newReader().readLine()
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
            }

            clearScreen()
        }
    }

    void createThread(Runnable runnable) {
        Thread thread = new Thread(runnable)
        thread.start()
    }

    private void messageSubscribers() {
        println("What topics does this message have? ")
        ArrayList<String> topics = System.in.newReader().readLine().split(',')

        println("What is the message? ")
        String message = System.in.newReader().readLine()

        PublisherContent content = new PublisherContent(-1, Publisher.batchNo, topics, message)

        sendMessage(content)
        waitForAck(content)

        content.batchNo = Publisher.batchNo
        Publisher.trailingMessages.add(content)
        if (Publisher.trailingMessages.size() > 5) {
            Publisher.trailingMessages.removeLast()
        }
    }

    private void waitForAck(PublisherContent content) {
        int retries = 0
        while (Publisher.awaitingAck.size() != 0 && retries < 5) {
            if (TimeCategory.minus(new Date(), waiting.timeStart) > new TimeDuration(0, 0, 5, 0)) {
                Publisher.awaitingAck.remove(0)
                println("Retrying message...")
                sendMessage(content)
                retries++
            }
        }

        if(retries < 5) {
            println("Ack received for $waiting.messageNo")
        } else {
            println("Ack was not received for received for $waiting.messageNo")
            Publisher.awaitingAck.clear()
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
            waiting = Publisher.awaitingAck.first()
        }
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

        PublisherIdentificationSender publisherIdentificationSender = new PublisherIdentificationSender(broker)
        createThread(publisherIdentificationSender)

        Publisher.brokers.add(new Broker(id: -1, location: broker))
        Publisher.brokers.unique { it.location }
    }

    void clearScreen() {
        System.out.print("\033[H\033[2J")
        System.out.flush()
    }
}
