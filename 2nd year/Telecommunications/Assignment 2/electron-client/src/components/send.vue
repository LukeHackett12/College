<template>
    <div>
        <div class="control is-8">
            <div class="select">
                <select v-model="selectedRouter">
                    <option disabled value="">Please select a Router</option>
                    <option v-for="router in routerRes">{{ router }}</option>
                </select>
            </div>

            <div class="select">
                <select v-model="selectedDestination">
                    <option disabled value="">Please select a Dest</option>
                    <option v-for="dest in destRes">{{ dest }}</option>
                </select>
            </div>

            <div class="field">
                <label class="label">Content</label>
                <div class="control">
                    <input class="input" type="text" placeholder="e.g. Hello, World!" v-model="content">
                </div>
            </div>

            <button class="button is-primary" v-on:click="sendData">Submit</button>
        </div>
        <div class="tile is-parent is-vertical">
            <div class="tile is-child">
                <p class="title">Result</p>
            </div>
            <div class="tile is-child box">
                <p class="content">{{result}}</p>
            </div>
        </div>
    </div>
</template>

<script>
    /* eslint-disable no-console */
    import axios from 'axios'

    export default {
        name: 'send',
        props: ['routerRes', 'destRes'],
        data: function () {
            return {
                message: "What message would you like to send?",
                result: {},
                selectedDestination: "",
                selectedRouter: "",
                content: "",
                error: ""
            }
        },
        methods: {
            sendData: function () {
                var DATA = {
                    destination: this.selectedDestination,
                    content: this.content,
                }

                var routerURL = "http://" + this.selectedRouter.split("/")[1] + ":8080/send"

                console.log(DATA)
                console.log("selected " + this.selectedRouter + " to " + routerURL)

                axios.post(routerURL, DATA
                )
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
