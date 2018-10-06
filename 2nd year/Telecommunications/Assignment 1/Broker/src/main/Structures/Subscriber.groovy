package main.Structures

class Subscriber extends Connection{
    ArrayList<String> subscribedTopics

    Subscriber(InetAddress address, int port, ArrayList<String> topics = new ArrayList<>()){
        this.address = address
        this.port = port

        this.subscribedTopics = topics
    }
}
