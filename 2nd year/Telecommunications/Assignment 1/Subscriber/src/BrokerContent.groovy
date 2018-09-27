class BrokerContent implements Serializable{
    ArrayList<String> topics
    String message

    BrokerContent(ArrayList<String> topics, String message){
        this.topics = topics
        this.message = message
    }
}
