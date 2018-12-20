package com.luke.restful

import com.luke.backend.FlowPath
import com.luke.backend.Router
import com.luke.backend.RouterDispatcher
import groovy.json.JsonBuilder
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class RouterController {
    @GetMapping("/info")
    Map getRouterInfo() {
        Router.features
    }

    @GetMapping("/routers")
    String getRouters() {
        new JsonBuilder(Router.routers.collect { it.toString() }).toPrettyString()
    }

    @GetMapping("/dests")
    String getDests() {
        new JsonBuilder(Router.destinations).toPrettyString()
    }

    @PostMapping(path = "/send", consumes = "application/json", produces = "application/json")
    ResponseEntity sendPacket(@RequestBody Map packet, @RequestHeader HttpHeaders headers) {
        int finalPort = (packet.port != null) ? packet.port : Router.PACKET_DEFAULT_PORT
        String addressString = packet.destination
        InetAddress finalAddress = InetAddress.getByName(addressString.replaceAll('/', ''))
        String content = packet.content
        String source = headers.getOrigin()

        Map toSend = ['source'     : source,
                      'destPort'   : finalPort,
                      'destAddress': finalAddress,
                      'content'    : content]

        HashSet<FlowPath> path = Router.flowTable.get(addressString)
        toSend.put("flowPath", path)

        if (path != null) {
            RouterDispatcher.forwardPacket(toSend)
            return ResponseEntity.ok(new JsonBuilder(toSend).toPrettyString())
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body('Invalid destination')
        }
    }

    @PostMapping(path = "/add/destination", consumes = "application/json", produces = "application/json")
    ResponseEntity addDestination(@RequestBody Map packet, @RequestHeader HttpHeaders headers) {
        String originIP = headers.getOrigin().split(":")[1].replaceAll('/', '')
        String originPort = headers.getOrigin().split(":").last()


        Map toSend = ['destPort'   : originPort,
                      'destAddress': originIP]

        RouterDispatcher.addDestination(toSend)
        return ResponseEntity.ok(new JsonBuilder(toSend).toPrettyString())
    }

}
