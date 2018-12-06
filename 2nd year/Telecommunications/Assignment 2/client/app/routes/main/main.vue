<template>
    <div id="app">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.4.4/css/bulma.css" type="text/css" media="screen">
      <div class="title">OpenFlow Client</div>
      <div class="tile is-ancestor">
        <div class="tile is-parent">
          <div class="tile is-child box">
            <div class="tabs">
              <ul>
                <li :class="{'is-active' : isActiveTab(1)}"><a @click="activeTab = 1">Join Network</a></li>
                <li :class="{'is-active' : isActiveTab(2)}"><a @click="activeTab = 2">Network Information</a></li>
                <li :class="{'is-active' : isActiveTab(3)}"><a @click="activeTab = 3">Send</a></li>           
              </ul>
            </div>
            <div class="tab-panels">
                  <router v-if="isActiveTab(1)" @routerConnection="addRouterConnection"></router>
                  <receive v-if="isActiveTab(2)" @routerRes="addRouterRes" @destRes="addDestRes"></receive>
                  <send v-if="isActiveTab(3)" :routerRes="routerRes" :destRes="destRes"></send>
            </div>
          </div>
        </div>
      </div>
      <footer class="footer">
        <div class="content has-text-centered">
          <p>
            <strong>OpenFlow Client</strong> by <a href="https://lukehackett.me">Luke Hackett</a>
            <br/> Incoming messages will be written to <strong>temp.txt</strong>
          </p>
          
        </div>
      </footer>
    </div>
</template>

<script>
import send from './send/send.vue'
import receive from './receive/receive.vue'
import router from './router/router.vue'

export default {
  name: 'App',
  data () {
    return {
      activeTab: 1,
      routerRes: {},
      destRes: {},
      messages: "",
      routerconnection: "",
    }
  },
  methods: {
    isActiveTab: function (id) { return this.activeTab === id },
    addRouterRes: function (routerRes) {
      this.routerRes = routerRes;
    },
    addDestRes: function (destRes) {
      this.destRes = destRes;
    },
    addRouterConnection: function(routerCon) {
      this.routerconnection = routerCon;
    }
  },
  components: {
      'send' : send,
      'receive' : receive,
      'router' : router,
  }
}
</script>