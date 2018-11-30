<template>
  <div class="tab-panel">
    Documents!!!
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      activeTab: 0
    }
  },
  methods: {
    isActiveTab: function (id) { return this.activeTab === id }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
