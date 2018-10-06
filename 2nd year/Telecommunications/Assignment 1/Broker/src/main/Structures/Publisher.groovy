package main.Structures

class Publisher extends Connection{
    int uniqueId
    int currentMessage

    Publisher(InetAddress address, int port, int uniqueId) {
        this.address = address
        this.port = port
        this.uniqueId = uniqueId

        this.currentMessage = 0
    }
}
