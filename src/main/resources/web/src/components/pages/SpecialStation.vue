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
            <el-table-column prop="station" label="地铁站" width="320">
            </el-table-column>
            <el-table-column prop="start" label="起点站" width="330">
            </el-table-column>
            <el-table-column prop="end" label="终点站" width="330">
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
  name: "SpecialStation",
  data(){
    return{
      headmsg:'统计特殊站台',
      tableData: []
    }
  },
  components: {
    Navigation,
    Copyright
  },
  created: function(){
    const axios = require('axios');
    //这里写与后端对接的将tableData初始化的代码
    axios.get(
      'http://localhost:8080/statistics/special_station'
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
