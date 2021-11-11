<template>
  <div>
    <el-container style="height: 500px; border: 1px solid #eee">
      <el-aside width="300px">
        <Navigation></Navigation>
      </el-aside>
      <el-container>
        <el-header style="text-align: center; font-size: 36px">
          {{msg}}
        </el-header>

        <el-main>
          <HelloWorld></HelloWorld>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import Navigation from '../../components/navi/Navigation'
import HelloWorld from '../../components/HelloWorld.vue'

export default {
  name: "testpage",
  data(){
    return{
      msg:'Test Page'
    }
  },
  components:{
    Navigation,
    HelloWorld
  }
}
</script>

<style scoped>

</style>
