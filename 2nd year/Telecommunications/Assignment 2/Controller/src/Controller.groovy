import java.lang.reflect.Array

class Controller {
    static ArrayList<InetAddress> routers
    static int port

    Controller() {
        routers = new ArrayList<>()
        //TODO make the controller and router find each other without this shit
        port = 5151

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
