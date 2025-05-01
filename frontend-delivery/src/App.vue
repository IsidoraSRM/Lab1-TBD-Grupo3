<script>
import NavBar from './components/NavBar.vue'

export default {
  name: 'App',
  components: {
    NavBar
  }
}
</script>


<template>
  <div id="app">
    <NavBar />
    <router-view />
  </div>
</template>



<style>

html, body {
  margin: 0;
  padding: 0;
  height: 100%;
}

#app {
  background-color:#E2DCD2 ;
  height: 100vh;
  width:100%;
  font-family: Arial, sans-serif;
}

/*
#F4F5F5 blanco
#77B8B9 celeste
#CA5833 rojo
#C98F51 naranja
#3A3A4B gris oscuro

*/
</style>
