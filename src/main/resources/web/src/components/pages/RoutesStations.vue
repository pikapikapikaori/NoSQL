<template>
  <el-container style="height: 700px; border: 1px solid #eee">
    <el-aside width="200px">
      <Navigation></Navigation>
    </el-aside>
    <el-container>
      <el-header style="text-align: center; font-size: 36px">
        {{headmsg}}
      </el-header>

      <el-main style="text-align: left; font-size: 18px">
        <el-tabs type="border-card">
          <el-tab-pane label="起止沿线站点">
            起止沿线站点
            <el-divider></el-divider>
          </el-tab-pane>

          <el-tab-pane label="查询重复站点">
            查询重复站点
            <el-divider></el-divider>
          </el-tab-pane>
        </el-tabs>
      </el-main>

      <el-footer>
        <Copyright></Copyright>
      </el-footer>
    </el-container>
  </el-container>
</template>

<script>
import Navigation from '../../components/navi/Navigation'
import Copyright from '../../components/copyright/Copyright'

export default {
  name: "RoutesStations",
  data(){
    return{
      headmsg:'沿线站点'
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
