class MessagePublisher implements Runnable{

    SenderProcess senderProcess
    int port

    MessagePublisher(int port){
        this.port = port
    }

    @Override
    void run() {
        while(true) {
            println("What topics does this message have? ")
            ArrayList<String> topics = System.in.newReader().readLine().split(',')

            println("What is the message? ")
            String message = System.in.newReader().readLine()

            PublisherContent content = new PublisherContent(topics, message)

            senderProcess = new SenderProcess(port, content)
            Thread thread = new Thread(senderProcess)
            thread.start()
        }
    }
}
