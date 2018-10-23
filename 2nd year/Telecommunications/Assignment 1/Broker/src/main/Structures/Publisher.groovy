package main.Structures

class Publisher extends Connection {
    String uniqueId

    Publisher(InetAddress address, int port, String uniqueId) {
        this.address = address
        this.port = port
        this.uniqueId = uniqueId
    }
}
