package main.Structures

class PublisherContent implements Serializable {
    String uniqueId
    int batchNo
    ArrayList<String> topics
    String message

    PublisherContent(String uniqueId, int batchNo, ArrayList<String> topics, String message) {
        this.uniqueId = uniqueId
        this.batchNo = batchNo
        this.topics = topics
        this.message = message
    }
}
