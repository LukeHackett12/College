<template>
    <div>
        <div class="tile is-ancestor">
            <div class="tile is-parent is-half">
                <div class="tile is-child box">
                    <button v-on:click="getInfo" class="button">Get Local Router Info</button>
                    <div>
                        <p class="content is-info">Type: {{infores.type}}</p>
                        <p class="content is-info">ID: {{infores.datapath_id}}</p>
                        <p class="content is-info">Buffers: {{infores.n_buffers}}</p>
                        <p class="content is-info">Port: {{infores.ports}}</p>
                    </div>
                </div>
            </div>
            <div class="tile is-parent is-vertical">
                <div class="tile is-child box">
                    <button v-on:click="getRouters" class="button">Get Routers</button>
                    <p>{{routerRes}}</p>
                </div>
                <div class="tile is-child box">
                    <button v-on:click="getDests" class="button">Get Destinations</button>
                    <p>{{destRes}}</p>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    const infoURL = "http://localhost:8080/info"
    const routersURL = "http://localhost:8080/routers"
    const destsURL = "http://localhost:8080/dests"

    import axios from 'axios'

    export default {
        name: 'send',
        data: function () {
            return {
                infores: {},
                routerRes: {},
                destRes: {},
                error: ""
            }
        },
        methods: {
            getInfo: function () {
                axios.get(infoURL
                    , {
                        headers: {'Access-Control-Allow-Origin': '*'},
                        responseType: 'json',
                        crossdomain: true
                    })
                    .then(response => {
                        this.infores = response.data;
                    })
                    .catch(error => {
                        this.error = error.data;
                    })
            },
            getRouters: function () {
                axios.get(routersURL, {
                    headers: {'Access-Control-Allow-Origin': '*'},
                    responseType: 'json',
                    crossdomain: true
                })
                    .then(result => {
                        this.routerRes = result.data;
                        this.$emit('routerRes', this.routerRes);
                    })
                    .catch(error => {
                        this.error = error.data;
                    })
            },
            getDests: function () {
                axios.get(destsURL, {
                    headers: {'Access-Control-Allow-Origin': '*'},
                    responseType: 'json',
                    crossdomain: true
                })
                    .then(result => {
                        this.destRes = result.data;
                        this.$emit('destRes', this.destRes)
                    })
                    .catch(error => {
                        this.error = error.data;
                    })
            }
        }
    }
</script>
