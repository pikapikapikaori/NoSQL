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
            <el-table-column prop="stationId" label="站点 Id" width="320">
            </el-table-column>
            <el-table-column prop="station" label="站点" width="320">
            </el-table-column>
            <el-table-column prop="num" label="线路数量" width="220">
            </el-table-column>
            <el-table-column prop="route" label="线路" width="440">
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
  name: "MostRoutesByStations",
  data(){
    return{
      headmsg:'路线最多站点',
      tableData: []
    }
  },
  components: {
    Navigation,
    Copyright
  },
  created: function(){
    const axios = require('axios');
    //这里写与后端对接的将tableData初始化为最多路线站点的代码
    axios.get(
      'http://localhost:8080/statistics/most_line_station'
    ).then((res) => {
      this.tableData = res.data;
    }).catch((err) => {
      this.tableData = [];
    });
  }
}
</script>

<style scoped>

</style>
