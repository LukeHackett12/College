class SubscriberContent implements Serializable{
    ArrayList<String> topics
    int receivingPort

    SubscriberContent(ArrayList<String> topics, int receivingPort){
        this.topics = topics
        this.receivingPort = receivingPort
    }
}
