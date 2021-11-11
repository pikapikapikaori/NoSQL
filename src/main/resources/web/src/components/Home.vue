<template>
  <div>
    <el-container style="height: 700px; border: 1px solid #eee">
      <el-aside width="300px">
        <Navigation></Navigation>
      </el-aside>
      <el-container>
        <el-header style="text-align: center; font-size: 36px">
          {{msg}}
        </el-header>

        <el-main style="text-align: left; font-size: 18px">
          <el-divider content-position="left">金河市介绍</el-divider>
          {{intromsg}}
          <el-divider content-position="left">具体功能</el-divider>
          <el-table :data="tableData" stripe="true ">
            <el-table-column type="index" :index="indexMethod1" width="150">
            </el-table-column>
            <el-table-column prop="func" label="功能" width="250">
            </el-table-column>
            <el-table-column type="index" :index="indexMethod2" width="150">
            </el-table-column>
            <el-table-column prop="funcp" label="功能" width="250">
            </el-table-column>
          </el-table>
        </el-main>

        <el-footer>
          <Copyright></Copyright>
        </el-footer>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import Navigation from './navi/Navigation'
import Copyright from './copyright/Copyright'

export default {
  name: "",
  data(){
    return{
      tableData: [
        {
          func: '线路基本信息',
          funcp: '线路站点信息'
        },
        {
          func: '站点停靠线路',
          funcp: '起止沿线站点'
        },
        {
          func: '直达路线判断',
          funcp: '线路班次信息'
        },
        {
          func: '站点某时线路',
          funcp: '站点某时某线'
        },
        {
          func: '统计停靠路线',
          funcp: '统计路线类型'
        },
        {
          func: '查询重复站点',
          funcp: '统计站台连接'
        },
        {
          func: '统计路线站点',
          funcp: '统计运行时间'
        },
        {
          func: '线路创建',
          funcp: '线路删除更新'
        }
      ],
      msg: '金河市公交线路系统',
      intromsg: '\xa0\xa0\xa0\xa0\xa0\xa0\xa0金河市(虚拟城市)是一座⻛景优美的城市，包括信息技术产业专区高新区、历史悠久的老城区、人口流动较多的 天河区和有高速公路联通的合江县。有两座火⻋站(火⻋⻄站和金河南站)和一座快铁站(普光快铁)，有两座客 运站(金河客运站和北客站)。其公交网络也比较发达，一共有93条公交线路，包括常规线路、快速公交、高峰公 交、夜班公交等多种类型。'
    }
  },
  methods: {
    indexMethod1(index) {
      return index * 2;
    },
    indexMethod2(index) {
      return index * 2 + 1;
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
