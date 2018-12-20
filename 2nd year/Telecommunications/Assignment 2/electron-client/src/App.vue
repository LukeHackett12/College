<template>
    <div id="app" class="wrapper">
        <link rel="stylesheet" href="https://unpkg.com/buefy/dist/buefy.min.css" type="text/css" media="screen">
        <div style="height:100vh;">
            <div class="tile is-ancestor" style="height: 80vh;">
                <div class="tile is-parent">
                    <div class="tile is-child is-half">
                        <div class="tabs">
                            <ul>
                                <li :class="{'is-active' : isActiveTab(1)}"><a @click="activeTab = 1">Join Network</a>
                                </li>
                                <li :class="{'is-active' : isActiveTab(2)}"><a @click="activeTab = 2">Network
                                    Information</a></li>
                                <li :class="{'is-active' : isActiveTab(3)}"><a @click="activeTab = 3">Send</a></li>
                            </ul>
                        </div>
                        <div class="tab-panels">
                            <router v-if="isActiveTab(1)" @routerConnection="addRouterConnection"></router>
                            <receive v-if="isActiveTab(2)" @routerRes="addRouterRes" @destRes="addDestRes"></receive>
                            <send v-if="isActiveTab(3)" :routerRes="routerRes" :destRes="destRes"></send>
                        </div>
                    </div>
                    <div class="tile is-child notification is-primary is-4">
                        <p class="title">Messages</p>
                        <ul>
                            <li v-for="item in messages">
                                Message received from {{item.source}} : {{ item.content }}
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <footer class="footer is-clipped" style="height: 20vh;">
                <div class="content has-text-centered">
                    <p>
                        <strong>OpenFlow Client</strong> by <a href="https://lukehackett.me">Luke Hackett</a>
                        <br/> Incoming messages will be written to <strong>temp.txt</strong>
                    </p>
                </div>
            </footer>
        </div>
    </div>
</template>

<script>
    import send from './components/send.vue'
    import receive from './components/receive.vue'
    import router from './components/router.vue'
    import vueify from 'vueify'

    const express = require('express')
    const bodyParser = require('body-parser')
    const jsonParser = bodyParser.json()
    const app = express()
    const port = 9000

    export default {
        name: 'App',
        created() {
            var self = this;
            app.get('/', (req, res) => {
                console.log(req.body)
                res.json(self.messages)
            });
            app.post('/', jsonParser, function (req, res) {
                    self.messages.push(req.body)
                    console.log(req.body)
                    res.json(self.messages)
                }
            )
            app.listen(port, () => console.log(`Example app listening on port ${port}!`))
        },
        data() {
            return {
                activeTab: 1,
                routerRes: {},
                destRes: {},
                routerconnection: "",
                messages: []
            }
        },
        methods: {
            isActiveTab: function (id) {
                return this.activeTab === id
            },
            addRouterRes: function (routerRes) {
                this.routerRes = routerRes;
            },
            addDestRes: function (destRes) {
                this.destRes = destRes;
            },
            addRouterConnection: function (routerCon) {
                this.routerconnection = routerCon;
            }
        },
        components: {
            'send': send,
            'receive': receive,
            'router': router,
        }
    }
</script>

<style>
    {
        height: 100vh
    ;
    }
</style>
