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
            <el-table-column type="index" prop="序号" width="200">
            </el-table-column>
            <el-table-column prop="type" label="线路类型" width="580">
            </el-table-column>
            <el-table-column prop="num" label="线路数量" width="200">
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
  name: "RoutesType",
  data(){
    return{
      headmsg:'路线类型统计',
      tableData: []
    }
  },
  components:{
    Navigation,
    Copyright
  },
  created: function(){
    const axios = require('axios');
    //这里写与后端对接的将tableData初始化的代码
    axios.get(
      'http://localhost:8080/statistics/count_type'
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
