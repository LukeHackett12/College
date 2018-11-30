class ControllerReceiver implements Runnable {
  @Override
  void run() {
    DatagramSocket socket = new DatagramSocket(Controller.port)

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
