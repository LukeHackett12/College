class Broker {
    public static final String PUBLISHER = 'Publisher'
    public static final String SUBSCRIBER = 'Subscriber'

    static int DEFAULT_PUBLISHER_PORT = 5050
    static int DEFAULT_SUBSCRIBER_PORT = 5151

    static ArrayList<Subscriber> subscribersList

    Broker(){
        subscribersList = new ArrayList<>()

        Receiver publisherReceiver = new Receiver(PUBLISHER, DEFAULT_PUBLISHER_PORT)
        createThread(publisherReceiver)
        Receiver subscriberReceiver = new Receiver(SUBSCRIBER, DEFAULT_SUBSCRIBER_PORT)
        createThread(subscriberReceiver)
    }

    void createThread(Runnable runnable){
        Thread thread = new Thread(runnable)
        thread.start()
    }

    static void main(String[] args){
        new Broker()
    }
}
