package com.luke.backend

import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths

class Router {
  static HashMap<String, ArrayList<FlowPath>> flowTable
  static int port
  static int num_tables
  static String datapath_id
  static Map features

  public static final int PACKET_DEFAULT_PORT = 9000

  static HashSet<InetAddress> routers
  static HashSet<String> destinations

  static boolean contactedController
  static boolean sentFeatures

  Router() {
    flowTable = new HashMap<>()

    routers = new HashSet<>()
    routers.add(InetAddress.localHost)
    destinations = new HashSet<>()
    
    //TODO make the controller and router find each other without this shit
    port = 4141
    num_tables = 0
    datapath_id = UUID.randomUUID().toString()
    contactedController = false

    RouterReceiver receiver = new RouterReceiver(port, false)
    createThread(receiver)
  }

  private void createThread(Runnable runnable) {
    Thread thread = new Thread(runnable)
    thread.start()
  }

  String readFile(String path, Charset encoding = Charset.defaultCharset())
    throws IOException {
    byte[] encoded = Files.readAllBytes(Paths.get(path))
    return new String(encoded, encoding)
  }

  static void initRouter() {
    new Router()
  }
}
