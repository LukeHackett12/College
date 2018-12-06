package com.luke.backend


import static com.luke.backend.Router.contactedController

class RouterReceiver implements Runnable {
  int port

  RouterReceiver(int port, boolean passive) {
    this.port = port
    if (!contactedController && !passive) {
      RouterDispatcher.phoneHome()
    }
  }

  @Override
  void run() {
    DatagramSocket socket = new DatagramSocket(port)
    while (true) {
      byte[] buffer = new byte[65508]
      DatagramPacket packet = new DatagramPacket(buffer, buffer.length)

      socket.receive(packet)

      PacketHandler handler = new PacketHandler(packet)
      Thread t = new Thread(handler)
      t.start()
    }
  }
}
