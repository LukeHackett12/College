class TopicSubscription implements Runnable {
    SenderProcess senderProcess
    int port

    TopicSubscription(int port){
        this.port = port
    }

    @Override
    void run(){
        while(true) {
            println("What do you want to subscribe to? ")
            ArrayList<String> topics = System.in.newReader().readLine().split(',')

            senderProcess = new SenderProcess(port, topics)
            Thread thread = new Thread(senderProcess)
            thread.start()
        }
    }
}
