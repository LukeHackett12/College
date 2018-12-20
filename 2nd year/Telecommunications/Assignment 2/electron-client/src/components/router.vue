<template>
    <div>
        <div class="control is-8">
            <div class="field">

                <label class="label">Router Address</label>
                <div class="control">
                    <input class="input" type="text" placeholder="e.g. 127.0.0.1" v-model="address">
                </div>
            </div>
            <div class="field">
                <label class="label">Router Port</label>
                <div class="control">
                    <input class="input" type="text" placeholder="e.g. 8080" v-model="port">
                </div>
            </div>

            <button class="button is-primary" v-on:click="sendData">Submit</button>
        </div>
        <div class="tile is-parent is-vertical">
            <div class="tile is-child">
                <p class="title">Result</p>
            </div>
            <div class="tile is-child box">
                <p class="content">{{ result }}</p>
            </div>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'

    export default {
        name: 'router',
        data: function () {
            return {
                address: "",
                port: "",
                result: "",
                error: "",
            }
        },
        methods: {
            sendData: function () {
                var url = "http://" + this.address + ":" + this.port;
                this.$emit('routerConnection', url)

                axios.post(url + "/add/destination", {})
                    .then(result => {
                        this.result = result.data;
                    })
                    .catch(error => {
                        this.error = error.data;
                    })
            }
        }
    }
</script>

<style>

</style>
