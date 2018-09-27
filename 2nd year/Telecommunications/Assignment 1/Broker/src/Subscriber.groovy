class Subscriber {
    InetAddress address
    int port

    ArrayList<String> subscribedTopics

    Subscriber(InetAddress address, int port, ArrayList<String> topics = new ArrayList<>()){
        this.address = address
        this.port = port

        this.subscribedTopics = topics
    }
}
