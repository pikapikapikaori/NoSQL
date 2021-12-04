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
          <el-tab-pane label="线路基本信息">
            线路基本信息
            <el-divider></el-divider>

            <div class="forminlineInfo" style="text-align: center">
              <el-form :inline="true" @submit.native.prevent :model="formInlineInfo" class="demo-form-inline">
                <el-form-item label="线路">
                  <el-input v-model="formInlineInfo.routeToSearchInfo" placeholder="线路">
                    <template slot="append">路</template>
                  </el-input>
                </el-form-item>

                <el-form-item>
                  <el-button type="primary" @click="searchRouteInfo" native-type="submit">查询</el-button>
                  <el-button type="primary" @click="clearAllInfo" native-type="submit">清空</el-button>
                </el-form-item>
              </el-form>

              <div v-if="isClearInfo">
                <div v-if="haveResult">
                  <el-descriptions title="线路信息">
                    <el-descriptions-item label="路线">{{routeToSearchInfoResult.route}}</el-descriptions-item>
                    <el-descriptions-item label="单向行驶时间">{{routeToSearchInfoResult.oneWayTime}}</el-descriptions-item>
                    <el-descriptions-item label="是否为单向线">{{routeToSearchInfoResult.directional}}</el-descriptions-item>
                    <el-descriptions-item label="行驶距离（千米）">{{routeToSearchInfoResult.length}}</el-descriptions-item>
                    <el-descriptions-item label="线路名称">{{routeToSearchInfoResult.lineId}}</el-descriptions-item>
                    <el-descriptions-item label="运行时间">{{routeToSearchInfoResult.runtime}}</el-descriptions-item>
                    <el-descriptions-item label="途经站点">{{routeToSearchInfoResult.interval}}</el-descriptions-item>
                    <el-descriptions-item label="类型">{{routeToSearchInfoResult.type}}</el-descriptions-item>
                  </el-descriptions>
                </div>
                <div v-else>
                  <el-descriptions title="暂无线路信息">
                  </el-descriptions>
                </div>
              </div>
            </div>
          </el-tab-pane>

          <el-tab-pane label="线路站点信息">
            线路站点信息
            <el-divider></el-divider>

            <div class="forminlineStation" style="text-align: center">
              <el-form :inline="true" @submit.native.prevent :model="formInlineStation" class="demo-form-inline">
                <el-form-item label="线路">
                  <el-input v-model="formInlineStation.routeToSearchStation" placeholder="线路">
                    <template slot="append">路</template>
                  </el-input>
                </el-form-item>

                <el-form-item label="线路方向">
                  <el-select v-model="formInlineStation.direction" placeholder="线路1方向">
                    <el-option label="上行" value="up"></el-option>
                    <el-option label="下行" value="down"></el-option>
                    <el-option label="环线" value="circle"></el-option>
                  </el-select>
                </el-form-item>

                <el-form-item>
                  <el-button type="primary" @click="searchRouteStation" native-type="submit">查询</el-button>
                  <el-button type="primary" @click="clearAllStation" native-type="submit">清空</el-button>
                </el-form-item>
              </el-form>

              <div v-show="isClearStation">
                <el-table :data="routeToSearchStationResult" stripe="true" :header-cell-style="{textAlign: 'center'}" :cell-style="{textAlign: 'center'}" empty-text="暂无线路信息">
                  <el-table-column prop="id" label="站点ID" width="320">
                  </el-table-column>
                  <el-table-column prop="name" label="站点名称" width="330">
                  </el-table-column>
                  <el-table-column prop="english" label="站点英语名称" width="330">
                  </el-table-column>
                </el-table>
                <p>
                  共{{routeToSearchStationResultInterval}}个站点
                </p>
              </div>
            </div>
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
  name: "RoutesInformation",
  data(){
    return{
      headmsg: '线路信息',
      isClearInfo: false,
      haveResult: false,
      formInlineInfo: {
        routeToSearchInfo: ''
      },
      routeToSearchInfoResult: {
        route: '',
        directional: '',
        length: 0.0,
        lineId: '',
        runtime: '',
        interval: 0,
        type: '',
        oneWayTime: 0
      },
      isClearStation: false,
      formInlineStation: {
        routeToSearchStation: '',
        direction: ''
      },
      routeToSearchStationResult: [],
      routeToSearchStationResultInterval: 0
    }
  },
  components: {
    Navigation,
    Copyright
  },
  methods: {
    searchRouteInfo() {
      const axios = require('axios');

      this.isClearInfo = true;
      this.haveResult = true;

      axios.get('http://localhost:8080/route/find_lineId_line', {
        params: {
          lineId: this.formInlineInfo.routeToSearchInfo
        }
      }).then((res) => {
        if(res.data === {}){
          this.haveResult = false;
        }
        else{
          this.routeToSearchInfoResult = res.data;
        }
      }).catch((err) => {this.haveResult = false;});
    },
    clearAllInfo(){
      this.isClearInfo = false;
      this.haveResult = false;
    },
    searchRouteStation() {
      const axios = require('axios');

      this.isClearStation = true;

      axios.get('http://localhost:8080/route/find_route_station', {
        params: {
          line_id: this.formInlineStation.routeToSearchStation,
          direction: this.formInlineStation.direction
        }
      }).then((res) => {
        this.routeToSearchStationResult = res.data;
        this.routeToSearchStationResultInterval = res.data.length;
      }).catch((err) => {
        this.routeToSearchStationResult = [];
        this.routeToSearchStationResultInterval = 0;
      });
    },
    clearAllStation(){
      this.isClearStation = false;
    }
  }
}
</script>

<style scoped>

</style>
