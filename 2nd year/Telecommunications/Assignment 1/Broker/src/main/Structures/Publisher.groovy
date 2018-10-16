package main.Structures

class Publisher extends Connection{
    int uniqueId

    Publisher(InetAddress address, int port, int uniqueId) {
        this.address = address
        this.port = port
        this.uniqueId = uniqueId
    }
}
