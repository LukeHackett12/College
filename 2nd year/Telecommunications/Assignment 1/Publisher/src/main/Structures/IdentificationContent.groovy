package main.Structures

class IdentificationContent implements Serializable {
    String uniqueId
    int publisherPort

    IdentificationContent(String uniqueId, int publisherPort) {
        this.uniqueId = uniqueId
        this.publisherPort = publisherPort
    }
}
