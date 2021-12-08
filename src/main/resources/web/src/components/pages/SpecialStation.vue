<template>
  <el-container style="height: 700px; border: 1px solid #eee">
    <el-aside width="200px">
      <Navigation></Navigation>
    </el-aside>
    <el-container>
      <el-header style="text-align: center; font-size: 36px">
        {{ headmsg }}
      </el-header>

      <el-main style="text-align: left; font-size: 18px">
        <div class="HomeTableData" style="text-align: center">
          <el-descriptions title="地铁站查询结果">
            <el-descriptions-item label="站点类型">{{ tableData.subway.type }}</el-descriptions-item>
            <el-descriptions-item label="站点数量">{{ tableData.subway.amount }}</el-descriptions-item>
          </el-descriptions>

          <el-table :data="tableData.subway.stations" stripe="true" :header-cell-style="{textAlign: 'center'}"
                    :cell-style="{textAlign: 'center'}">
            <el-table-column prop="station" label="站点名称" width="980">
            </el-table-column>
          </el-table>

          <el-descriptions title="始发站查询结果">
            <el-descriptions-item label="站点类型">{{ tableData.start.type }}</el-descriptions-item>
            <el-descriptions-item label="站点数量">{{ tableData.start.amount }}</el-descriptions-item>
          </el-descriptions>

          <el-table :data="tableData.start.stations" stripe="true" :header-cell-style="{textAlign: 'center'}"
                    :cell-style="{textAlign: 'center'}">
            <el-table-column prop="station" label="站点名称" width="980">
            </el-table-column>
          </el-table>

          <el-descriptions title="终点站查询结果">
            <el-descriptions-item label="站点类型">{{ tableData.end.type }}</el-descriptions-item>
            <el-descriptions-item label="站点数量">{{ tableData.end.amount }}</el-descriptions-item>
          </el-descriptions>

          <el-table :data="tableData.end.stations" stripe="true" :header-cell-style="{textAlign: 'center'}"
                    :cell-style="{textAlign: 'center'}">
            <el-table-column prop="station" label="站点名称" width="980">
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
  data() {
    return {
      headmsg: '统计特殊站台',
      tableData: {
        subway: {
          type: "",
          amount: 0,
          stations: []
        },
        start: {
          type: "",
          amount: 0,
          stations: []
        },
        end: {
          type: "",
          amount: 0,
          stations: []
        }
      }
    }
  },
  components: {
    Navigation,
    Copyright
  },
  created: function () {
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
