class PublisherContent implements Serializable{
    ArrayList<String> topics
    String message

    PublisherContent(ArrayList<String> topics, String message){
        this.topics = topics
        this.message = message
    }
}
