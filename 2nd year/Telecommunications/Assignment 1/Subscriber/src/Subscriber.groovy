class Subscriber{

    final int DEFAULT_BROKER_PORT = 5151

    static int portNumber

    Subscriber(){
        portNumber = findFreePort()

        TopicSubscription topicSubscription = new TopicSubscription(DEFAULT_BROKER_PORT)
        createThread(topicSubscription)
        Receiver receiver = new Receiver(portNumber)
        createThread(receiver)
    }

    int findFreePort(){
        ServerSocket ss = new ServerSocket()
        ss.bind(new InetSocketAddress(0))
        int port = ss.localPort
        ss.close()
        port
    }

    void createThread(Runnable runnable) {
        Thread thread = new Thread(runnable)
        thread.start()
    }

    static void main(String[] args){
        new Subscriber()
    }
}