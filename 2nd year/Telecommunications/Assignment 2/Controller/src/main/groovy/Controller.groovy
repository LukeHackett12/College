class Controller {
  public static final int ROUTER_HELLO = 0
  public static final int CLIENT_HELLO = 100
  public static final int FEATURE_REQ = 5
  public static final int FEATURE_RES = 6
  public static final int TABLE_UPDATE = 8
  public static final int ACK_CODE = 10
  public static final int ROUTER_DEFAULT_PORT = 4141

  static ArrayList<Router> routers
  static ArrayList<Router> destinations
  static int port

  Controller() {
    //TODO make the controller and router find each other without this shit
    port = 5151
    routers = new ArrayList<>()
    destinations = new ArrayList<>()

    ControllerReceiver receiver = new ControllerReceiver()
    createThread(receiver)

    println('Controller started on port ' + port)
  }

  void createThread(Runnable runnable) {
    Thread thread = new Thread(runnable)
    thread.start()
  }

  static void main(String[] args) {
    new Controller()
  }
}
