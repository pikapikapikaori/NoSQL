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
        <div class="HomeTableData" style="text-align: center">
          <el-table :data="tableData" stripe="true" :header-cell-style="{textAlign: 'center'}" :cell-style="{textAlign: 'center'}">
            <el-table-column type="index" prop="序号" width="150">
            </el-table-column>
            <el-table-column prop="station1" label="站点1" width="340">
            </el-table-column>
            <el-table-column prop="station2" label="站点2" width="340">
            </el-table-column>
            <el-table-column prop="num" label="线路数量" width="150">
            </el-table-column>
          </el-table>
        </div>
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
  name: "MostRoutesBetweenStations",
  data(){
    return{
      headmsg:'线路最多站台',
      tableData: []
    }
  },
  components: {
    Navigation,
    Copyright
  },
  created: function(){
    //这里写与后端对接的将tableData初始化的代码
  }
}
</script>

<style scoped>

</style>
