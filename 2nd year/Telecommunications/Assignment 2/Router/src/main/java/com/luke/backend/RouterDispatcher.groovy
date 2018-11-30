package com.luke.backend

import groovy.json.JsonBuilder

import static com.luke.backend.Codes.*
import static com.luke.backend.Config.MAX_PACKETS
import static com.luke.backend.DefaultAddresses.CONTROLLER_DEFAULT_ADDRESS
import static com.luke.backend.DefaultAddresses.CONTROLLER_DEFAULT_PORT

class RouterDispatcher {

  static void phoneHome() {
    byte[] flag = [(byte) HELLO]
    sendPacket(flag, CONTROLLER_DEFAULT_ADDRESS, CONTROLLER_DEFAULT_PORT)
  }

  static void sendFeatures(Map features) {
    if (features == null) {
      features = ['type'       : 'router',
                  'datapath_id': Router.datapath_id,
                  'n_buffers'  : MAX_PACKETS,
                  'ports'      : Router.port]

      Router.features = features
    }
    byte[] content = buildJSONByteArray(features, FEATURE_RES)
    sendPacket(content, CONTROLLER_DEFAULT_ADDRESS, CONTROLLER_DEFAULT_PORT)
  }

  static void forwardPacket(Map toSend) {
    ArrayList<FlowPath> flowPath = toSend.flowPath
    if (flowPath.first().isFinal) {
      sendPacketToClient(toSend)
    } else {
      FlowPath path = flowPath.first()
      toSend.flowPath = flowPath.tail()
      InetAddress interumAddress = path.nextRouter
      int interumPort = path.nextRouterPort

      byte[] bytes = buildJSONByteArray(toSend, FORWARD_MESSAGE)
      sendPacket(bytes, interumAddress, interumPort)
    }
  }

  static void sendPacketToClient(Map toSend) {
    URL baseUrl = new URL('http://localhost/8080/receive')
    String queryString = new JsonBuilder(toSend).toPrettyString()
    URLConnection connection = baseUrl.openConnection()
    connection.with {
      doOutput = true
      requestMethod = 'POST'
      outputStream.withWriter { writer ->
        writer << queryString
      }
      println content.text
    }
  }

  private static byte[] buildJSONByteArray(Map toSend, int code) {
    ByteArrayOutputStream packetBytes = new ByteArrayOutputStream()
    ObjectOutputStream output = new ObjectOutputStream(packetBytes)
    String jsonPacket = new JsonBuilder(toSend).toString()
    output.writeUTF(jsonPacket)
    output.flush()

    byte[] flag = [(byte) code]
    byte[] finalArray = new byte[flag.length + packetBytes.toByteArray().length]
    System.arraycopy(flag, 0, finalArray, 0, flag.length)
    System.arraycopy(packetBytes.toByteArray(), 0, finalArray, flag.length, packetBytes.toByteArray().length)
    finalArray
  }

  private static void sendPacket(byte[] bytes, InetAddress interumAddress, int interumPort) {
    DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, interumAddress, interumPort)
    DatagramSocket socket = new DatagramSocket()
    socket.send(datagramPacket)
  }
}

