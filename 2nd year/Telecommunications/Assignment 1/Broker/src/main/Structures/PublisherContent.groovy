package main.Structures

class PublisherContent implements Serializable{
    String batchNo
    ArrayList<String> topics
    String message

    PublisherContent(String batchNo, ArrayList<String> topics, String message){
        this.batchNo = batchNo
        this.topics = topics
        this.message = message
    }
}
