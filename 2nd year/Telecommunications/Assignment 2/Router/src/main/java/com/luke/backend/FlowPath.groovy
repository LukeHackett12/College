package com.luke.backend

class FlowPath {
  String destination
  String nextRouter
  int nextRouterPort
  String nextRouterID
  boolean isFinal

  Map asMap() {
    this.class.declaredFields.findAll { !it.synthetic }.collectEntries {
      [ (it.name):this."$it.name" ]
    }
  }
}

