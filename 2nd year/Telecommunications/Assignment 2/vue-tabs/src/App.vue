<template>
  <div id="app">
    <div class="section">
      <div class="container">
        <div class="tabs">
          <ul>
            <li :class="{'is-active' : isActiveTab(0)}"><a @click="activeTab = 0">Pictures</a></li>
            <li :class="{'is-active' : isActiveTab(1)}"><a @click="activeTab = 1">Music</a></li>
            <li :class="{'is-active' : isActiveTab(2)}"><a @click="activeTab = 2">Videos</a></li>
            <li :class="{'is-active' : isActiveTab(3)}"><a @click="activeTab = 3">Documents</a></li>
          </ul>
        </div>
        <div class="tab-panels">
          <div class="tab-panel" v-if="isActiveTab(0)">
            Pictures!!!
          </div>
          <div class="tab-panel" v-if="isActiveTab(1)">
            Music!!!
          </div>
          <div class="tab-panel" v-if="isActiveTab(2)">
            Videos!!!
          </div>
          <documents v-if="isActiveTab(3)"></documents>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import documents from './components/documents'

export default {
  name: 'App',
  data () {
    return {
      activeTab: 0
    }
  },
  methods: {
    isActiveTab: function (id) { return this.activeTab === id }
  },
  components: {
    'documents': documents
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
