import groovy.json.JsonSlurper

import static Controller.ROUTER_HELLO
import static Controller.CLIENT_HELLO
import static Controller.FEATURE_REQ
import static Controller.FEATURE_RES
import static Controller.ROUTER_DEFAULT_PORT
import static Controller.destinations
import static Controller.routers

class PacketHandler implements Runnable {
  DatagramPacket packet

  PacketHandler(DatagramPacket packet) {
    this.packet = packet
  }

  //TODO add a code for ID request of router closest to the destination
  // Probably request each router to ping the final destination or some
  @Override
  void run() {
    switch (packet.data[0]) {
      case ROUTER_HELLO:
        if (!routers.contains { it.address = packet.address }) {
          routers.add(new Router(packet.address))
          sendSimpleCode(ROUTER_HELLO)
          sleep(100)
          sendSimpleCode(FEATURE_REQ)
          computeNewFlow()
        }
        break
      case FEATURE_RES:
        addFeaturesToRouter()
        ackCode(FEATURE_REQ)
        break
      case CLIENT_HELLO:
        if (!destinations.contains { it.address = packet.address }) {
          destinations.add(new Router(packet.address))
          sendSimpleCode(CLIENT_HELLO)
          sleep(100)
          computeNewFlow()
        }
        break
      default:
        //Return not yet implemented
        break
    }
    Thread.currentThread().interrupt()
  }

  void sendSimpleCode(int code) {
    InetAddress routerAddress = packet.address
    ControllerDispatcher.sendSimpleCode(code, routerAddress, ROUTER_DEFAULT_PORT)
  }

  void computeNewFlow() {
    //First implementation is hardcoded
    /*
    Flow table example: flow table is per destination and will be added to packet
   {
    FlowPath {
      dest = 2
      next = 127.0.0.1
      nextPort = 5211
      isFinal = 0
    },
    FlowPath {
      dest = 2
      next = 0
      nextPort = 0
      isFinal = 1
    }
   }
     */
    int test = 0
    destinations.forEach { Router dest ->
      routers.eachWithIndex { Router router, int i ->
        ArrayList<FlowPath> flowPaths = new ArrayList<>()
        for (int j = 0; j < routers.size() - 1; j++) {
          FlowPath flowPath = new FlowPath()
          flowPath.destination = dest.address.toString()
          Router nextRouter = routers.get((i + j + 1) % routers.size())
          flowPath.nextRouter = nextRouter.address.toString()
          flowPath.nextRouterPort = ROUTER_DEFAULT_PORT
          flowPath.nextRouterID = nextRouter.features.datapath_id
          flowPath.isFinal = false
          flowPaths.add(flowPath)
        }

        FlowPath flowPath = new FlowPath()
        flowPath.destination = dest.address
        flowPath.nextRouter = "na"
        flowPath.nextRouterPort = 0
        flowPath.nextRouterID = "na"
        flowPath.isFinal = true

        flowPaths.add(flowPath)
        ControllerDispatcher.sendFlows(flowPaths, router.address, ROUTER_DEFAULT_PORT)
      }
    }
  }

  void addFeaturesToRouter() {
    ByteArrayInputStream featureData = new ByteArrayInputStream(Arrays.copyOfRange(packet.data, 1, packet.data.length - 1))
    ObjectInputStream featureStream = new ObjectInputStream(featureData)
    String featureString = featureStream.readUTF()
    Map features = (new JsonSlurper().parseText(featureString)) as Map
    routers.forEach { Router router ->
      if (router.address == packet.address) {
        router.features = features
      }
    }
  }

  void ackCode(int code) {
    InetAddress routerAddress = packet.address
    ControllerDispatcher.sendAck(code, routerAddress, ROUTER_DEFAULT_PORT)
  }
}
