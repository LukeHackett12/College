class Publisher {

    final int DEFAULT_BROKER_PORT = 5050

    Publisher(){
        MessagePublisher messagePublisher = new MessagePublisher(DEFAULT_BROKER_PORT)
        createThread(messagePublisher)
    }

    void createThread(Runnable runnable) {
        Thread thread = new Thread(runnable)
        thread.start()
    }

    static void main(String[] args){
        new Publisher()
    }
}
