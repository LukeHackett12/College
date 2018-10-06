package main.Structures

class BrokerContent implements Serializable{
    String batchNo
    ArrayList<String> topics
    String message

    BrokerContent(String batchNo, ArrayList<String> topics, String message){
        this.batchNo = batchNo
        this.topics = topics
        this.message = message
    }
}
