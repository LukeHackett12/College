import groovy.json.JsonBuilder

import static Controller.ACK_CODE
import static Controller.TABLE_UPDATE

class ControllerDispatcher {
  static void sendSimpleCode(int code, InetAddress address, int port) {
    byte[] flag = [(byte) code]
    DatagramPacket packet = new DatagramPacket(flag, flag.length, address, port)
    DatagramSocket socket = new DatagramSocket()
    socket.send(packet)
    println("Sent code $code to $packet.port")
  }

  static void sendAck(int code, InetAddress address, int port) {
    ByteArrayOutputStream packetBytes = new ByteArrayOutputStream()
    ObjectOutputStream output = new ObjectOutputStream(packetBytes)
    output.writeInt(code)
    output.flush()

    sendPacket(ACK_CODE, packetBytes, address, port)
  }

  static void sendFlows(ArrayList<FlowPath> flows, InetAddress address, int port){
    ArrayList<Map> convertedFlows = flows.collect{it.asMap()}
    String prettyString = new JsonBuilder(convertedFlows).toPrettyString()

    ByteArrayOutputStream packetBytes = new ByteArrayOutputStream()
    ObjectOutputStream output = new ObjectOutputStream(packetBytes)
    output.writeUTF(prettyString)
    output.flush()

    sendPacket(TABLE_UPDATE, packetBytes, address, port)
  }

  private static void sendPacket(int code, ByteArrayOutputStream packetBytes, InetAddress address, int port) {
    byte[] flag = [(byte) code]
    byte[] finalArray = new byte[flag.length + packetBytes.toByteArray().length]
    System.arraycopy(flag, 0, finalArray, 0, flag.length)
    System.arraycopy(packetBytes.toByteArray(), 0, finalArray, flag.length, packetBytes.toByteArray().length)
    DatagramPacket packet = new DatagramPacket(finalArray, finalArray.length, address, port)
    DatagramSocket socket = new DatagramSocket()
    socket.send(packet)
    println("Sent code $code with info to $packet.port")
  }
}
