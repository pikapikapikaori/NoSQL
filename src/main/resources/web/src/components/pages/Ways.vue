<template>
  <div>
    <el-container style="height: 700px; border: 1px solid #eee">
      <el-aside width="300px">
        <Navigation></Navigation>
      </el-aside>
      <el-container>
        <el-header style="text-align: center; font-size: 36px">
          {{headmsg}}
        </el-header>

        <el-main style="text-align: left; font-size: 18px">

        </el-main>

        <el-footer>
          <Copyright></Copyright>
        </el-footer>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import Navigation from '../../components/navi/Navigation'
import Copyright from '../../components/copyright/Copyright'

export default {
  name: "Wayss",
  data(){
    return{
      headmsg:'直达路线查询'
    }
  },
  components:{
    Navigation,
    Copyright
  }
}
</script>

<style scoped>

</style>
