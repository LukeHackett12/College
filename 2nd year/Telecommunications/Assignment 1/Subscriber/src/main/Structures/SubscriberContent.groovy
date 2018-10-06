package main.Structures

class SubscriberContent implements Serializable{
    ArrayList<String> topics
    String type
    int receivingPort

    SubscriberContent(ArrayList<String> topics, String type, int receivingPort){
        this.topics = topics
        this.type = type
        this.receivingPort = receivingPort
    }
}
