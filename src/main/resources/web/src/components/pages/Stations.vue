<template>
  <div>
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
            <el-tab-pane label="站点停靠线路">
              站点停靠线路
              <el-divider></el-divider>
            </el-tab-pane>

            <el-tab-pane label="站点某时线路">
              站点某时线路
              <el-divider></el-divider>
            </el-tab-pane>

            <el-tab-pane label="站点某时某线">
              站点某时某线
              <el-divider></el-divider>
            </el-tab-pane>
          </el-tabs>
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
  name: "Stations",
  data(){
    return{
      headmsg:'站点信息查询'
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
