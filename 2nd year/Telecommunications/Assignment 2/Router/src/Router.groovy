class Router {
    static ArrayList<FlowPath> flowTable
    static InetAddress controllerAddress
    static int controllerPort

    /*
        Step 1. Hello messages exchanged
        Step 2. Feature request from controller
        Step 3. Wait for flow table from controller
                or from outside the network..?
     */

    Router() {
        flowTable = new ArrayList<>()

        //TODO make the controller and router find each other without this shit
        controllerAddress = InetAddress.localHost
        controllerPort = 5151

        RouterReceiver receiver = new RouterReceiver()
        createThread(receiver)
    }

    private void createThread(Runnable runnable) {
        Thread thread = new Thread(runnable)
        thread.start()
    }

    static void main(String[] args) {
        new Router()
    }
}
