package com.luke.backend

import groovy.json.JsonSlurper

import static com.luke.backend.Codes.*
import static com.luke.backend.DefaultAddresses.CONTROLLER_DEFAULT_ADDRESS
import static com.luke.backend.DefaultAddresses.CONTROLLER_DEFAULT_PORT

class PacketHandler implements Runnable {

  DatagramPacket packet

  PacketHandler(DatagramPacket packet) {
    this.packet = packet
  }

  @Override
  void run() {
    switch (packet.data[0]) {
      case HELLO:
        helloReceived()
        println("Hello(Code $HELLO) exchanged with controller")
        break
      case FEATURE_REQ:
        if (Router.contactedController) {
          println("Received FEATURE_REQ($FEATURE_REQ) from controller")
          sendFeatures()
        }
        break
      case TABLE_UPDATE:
        if (Router.contactedController && Router.sentFeatures) {
          updateTable()
        }
        break
      case FORWARD_MESSAGE:
        forwardMessage()
      case ACK_RES:
        ackRead()
        break
      default:
        //Return not yet implemented
        break
    }
    Thread.currentThread().interrupt()
  }

  void helloReceived() {
    Router.contactedController = true
  }

  void sendFeatures() {
    RouterDispatcher.sendFeatures(Router.features)
    println("Sent features to controller at $CONTROLLER_DEFAULT_ADDRESS:$CONTROLLER_DEFAULT_PORT")
  }

  void updateTable() {
    ByteArrayInputStream data = new ByteArrayInputStream(Arrays.copyOfRange(packet.data, 1, packet.data.length - 1))
    ObjectInputStream stream = new ObjectInputStream(data)
    String flowString = stream.readUTF()
    ArrayList<Map> flow = new JsonSlurper().parseText(flowString) as ArrayList<Map>
    ArrayList<FlowPath> convertedFlow = flow.collect { Map map ->
      new FlowPath(destination: map.destination,
        nextRouter: map.nextRouter,
        nextRouterPort: map.nextRouterPort,
        nextRouterID: map.nextRouterID,
        isFinal: map.isFinal)
    }

    Router.flowTable.put(convertedFlow.get(0).destination, convertedFlow)
    Router.destinations.add(convertedFlow.get(0).destination)
    convertedFlow.each{ FlowPath entry ->
      Router.routers.add(InetAddress.getByName(entry.nextRouter))
    }
  }

  void forwardMessage(){
    ByteArrayInputStream data = new ByteArrayInputStream(Arrays.copyOfRange(packet.data, 1, packet.data.length - 1))
    ObjectInputStream stream = new ObjectInputStream(data)
    String message = stream.readUTF()
    Map messageMap = new JsonSlurper().parseText(message) as Map

    RouterDispatcher.forwardPacket(messageMap)
  }

  void ackRead() {
    ByteArrayInputStream data = new ByteArrayInputStream(Arrays.copyOfRange(packet.data, 1, packet.data.length - 1))
    ObjectInputStream stream = new ObjectInputStream(data)
    int code = stream.readInt()

    switch (code) {
      case FEATURE_REQ:
        println("Received FEATURE_RES($FEATURE_RES) ack from controller")
        Router.sentFeatures = true
        break
    }
  }
}
