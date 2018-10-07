package main.Structures

class IdentificationContent implements Serializable{
    int uniqueId
    int publisherPort

    IdentificationContent(int uniqueId, int publisherPort){
        this.uniqueId = uniqueId
        this.publisherPort = publisherPort
    }
}
