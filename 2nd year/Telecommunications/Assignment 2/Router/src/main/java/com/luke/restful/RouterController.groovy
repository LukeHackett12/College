package com.luke.restful


import com.luke.backend.FlowPath
import com.luke.backend.Router
import com.luke.backend.RouterDispatcher
import groovy.json.JsonBuilder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RouterController {
  @GetMapping("/info")
  Map getRouterInfo() {
    Router.features
  }

  @PostMapping(path = "/send", consumes = "application/json", produces = "application/json")
  ResponseEntity sendPacket(@RequestBody Map packet) {
    int finalPort = (packet.port != null) ? packet.port : Router.PACKET_DEFAULT_PORT
    String addressString = packet.address
    InetAddress finalAddress = InetAddress.getByName(addressString)
    String content = packet.content

    Map toSend = ['destPort'    : finalPort,
                  'destAddress' : finalAddress,
                  'destRouterID': finalRouterID,
                  'content'     : content]

    ArrayList<FlowPath> path = Router.flowTable.get(addressString)
    toSend.put("flowPath", path)

    if (path != null) {
      RouterDispatcher.forwardPacket(toSend)
      return ResponseEntity.ok(new JsonBuilder(toSend).toPrettyString())
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body('Invalid destination')
    }
  }
}
