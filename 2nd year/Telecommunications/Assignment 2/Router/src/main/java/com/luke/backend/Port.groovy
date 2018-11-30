package com.luke.backend

class Port {
  Map port
  DatagramSocket socket
  RouterReceiver receiver

  Port(int id = 0,
       String name = '',
       int config = 0,
       int recPort = -1,
       int sendPort = -1,
       String hw_addr = NetworkInterface.networkInterfaces?.collect{
         if (it.hardwareAddress?.encodeHex()?.toString() != null)
           it.hardwareAddress?.encodeHex()?.toString()
       }?.first()
  ) {
    receiver = new RouterReceiver(recPort, true)
    if (config == 1){
      socket = new DatagramSocket(sendPort, InetAddress.localHost)
    }
    port = ['id'     : id,
            'hw_addr': hw_addr,
            'name'   : name,
            'config' : config,
            'intakePort': recPort,
            'outgoingPort':sendPort]

  }
}
