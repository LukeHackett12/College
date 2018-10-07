package main.Handlers

import main.Publisher
import main.Receivers.PublisherReceiver
import main.Senders.PublisherContentSender
import main.Senders.PublisherIdentificationSender
import main.Structures.Broker
import main.Structures.PublisherContent

class PublisherHandler implements Runnable {

    PublisherContentSender senderProcess
    ArrayList<PublisherContentSender> senders

    @Override
    void run() {
        while (true) {
            println("Do you want to message a topic(1) or add a broker(2)?")
            int answer = System.in.newReader().readLine().toInteger()

            switch (answer) {
                case 1:
                    messageSubscribers()
                    break
                case 2:
                    addBroker()
            }

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

        PublisherContent content = new PublisherContent("blank", topics, message)

        for (Broker broker : Publisher.brokers) {
             content.batchNo = "$Broker.id.$Publisher.batchNo"
            senderProcess = new PublisherContentSender(broker, content)
            Thread thread = new Thread(senderProcess)
            thread.start()
            senders.add(senderProcess)
        }

        while (senders.any { (!it.received) }) {
            println("Waiting for acknowledgement")
            sleep(200)
        }

        Publisher.trailingMessages.add(content)
        if (Publisher.trailingMessages.size() > 5) {
            Publisher.trailingMessages.remove(0)
        }
    }

    private void addBroker() {

        print("Enter a broker you want to add (in the format X.X.X.X:X): ")
        String broker = System.in.newReader().readLine()

        println("What is your brokers identification port?")
        int answer = System.in.newReader().readLine().toInteger()
        PublisherIdentificationSender publisherIdentificationSender = new PublisherIdentificationSender(answer)
        createThread(publisherIdentificationSender)

        Publisher.brokers.add(new Broker(id: -1, location: broker))
        Publisher.brokers.unique{it.location}
    }
}
