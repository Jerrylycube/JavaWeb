<template>
  <div>
    <!-- <span>{{message}}</span><br><br>
    <input type="button" value="点击我" v-on:click="on()"> -->
    <!-- <element-view>asa</element-view> -->
    <!-- <emp-view></emp-view> -->
    <router-view></router-view>
  </div>
</template>

<script>
// import EmpView from './views/tlias/EmpView.vue'
// import ElementView from './views/ElementView.vue';
export default {
  components: { /* EmpView  */},
  data() {
    return {
    }
  },
  methods: {
  },
}
</script>

<style >
/* span{
  color: aqua;
} */

</style>