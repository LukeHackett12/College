import groovy.json.JsonSlurper

import static Controller.*

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
        routers.add(new Router(packet.address))
        routers.unique()
        sendSimpleCode(ROUTER_HELLO)
        sleep(100)
        sendSimpleCode(FEATURE_REQ)
        computeNewFlow()
        break
      case FEATURE_RES:
        addFeaturesToRouter()
        ackCode(FEATURE_REQ)
        break
      case CLIENT_HELLO:
        destinations.add(new Router(packet.address))
        destinations.unique()
        sendSimpleCode(CLIENT_HELLO)
        sleep(100)
        computeNewFlow()
        break
    }
    Thread.currentThread().interrupt()
  }

  void sendSimpleCode(int code) {
    InetAddress routerAddress = packet.address
    ControllerDispatcher.sendSimpleCode(code, routerAddress, ROUTER_DEFAULT_PORT)
  }

  void computeNewFlow() {
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
        flowPath.nextRouter = "localhost"
        flowPath.nextRouterPort = 0
        flowPath.nextRouterID = "localhost"
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
