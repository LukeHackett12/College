<template>
    <div>
        <h3>{{message}}</h3>
        <form v-on:submit.prevent="sendData">
            <div class="form-group">
                <label for="destination">Destination</label>
                <input id="destination" type="text" name="destination" v-model="destination" autocomplete="destination"/>
            </div>
            <div class="form-group">
                <label for="content">Message Content</label>
                <input id="content" type="text" name="content" v-model="content" autocomplete="content"/>
            </div>
            <input type="submit"/>
        </form>
        <h3>Result:</h3>
        <pre>{{result}}</pre>
    </div>
</template>

<script>
export default {
  name: 'send',
  data: function() {
            return {
                message: "What message would you like to send?",
                result: {},
                error: ""
            }
        },
        methods: {
            sendData: function() {
                const data = {
                    port: '9000',
                    destination: this.destination,
                    content: this.content,
                }
                axios.post("/send", data)
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
