package main.Structures

class PublisherContent implements Serializable {
    int uniqueId
    int batchNo
    ArrayList<String> topics
    String message

    PublisherContent(int uniqueId, int batchNo, ArrayList<String> topics, String message) {
        this.uniqueId = uniqueId
        this.batchNo = batchNo
        this.topics = topics
        this.message = message
    }
}
