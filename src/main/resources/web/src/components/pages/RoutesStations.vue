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

            <div class="forminlineStation" style="text-align: center">
              <el-form :inline="true" @submit.native.prevent :model="formInlineStation" class="demo-form-inline">
                <el-form-item label="线路">
                  <el-input v-model="formInlineStation.station" placeholder="线路">
                    <template slot="append">路</template>
                  </el-input>
                </el-form-item>
                <el-form-item label="起点">
                  <el-input v-model="formInlineStation.begin" placeholder="起点">
                    起点
                  </el-input>
                </el-form-item>
                <el-form-item label="终点">
                  <el-input v-model="formInlineStation.end" placeholder="终点">
                    终点
                  </el-input>
                </el-form-item>

                <el-form-item>
                  <el-button type="primary" @click="searchRouteStation" native-type="submit">查询</el-button>
                  <el-button type="primary" @click="clearAllStation" native-type="submit">清空</el-button>
                </el-form-item>
              </el-form>

              <div v-if="isClearStation">
                <div v-if="haveResultStation">
                  <el-descriptions title="查询结果">
                    <el-descriptions-item label="运行方向">{{routeToSearchStationResult.lineName}}</el-descriptions-item>
                    <el-descriptions-item label="运行时长">{{routeToSearchStationResult.time}}</el-descriptions-item>
                  </el-descriptions>

                  <el-table :data="routeToSearchStationResult.station" stripe="true" :header-cell-style="{textAlign: 'center'}" :cell-style="{textAlign: 'center'}" empty-text="暂无线路">
                    <el-table-column prop="id" label="站点id" width="320">
                    </el-table-column>
                    <el-table-column prop="name" label="站点名称" width="330">
                    </el-table-column>
                    <el-table-column prop="english" label="站点英语" width="330">
                    </el-table-column>
                  </el-table>
                </div>
                <div v-else>
                  <el-descriptions title="暂无线路信息">
                  </el-descriptions>
                </div>
              </div>
            </div>
          </el-tab-pane>

          <el-tab-pane label="查询重复站点">
            查询重复站点
            <el-divider></el-divider>

            <div class="forminlineRepeat" style="text-align: center">
              <el-form :inline="true" @submit.native.prevent :model="formInlineRepeat" class="demo-form-inline">
                <el-form-item label="线路1">
                  <el-input v-model="formInlineRepeat.route1" placeholder="线路1">
                    <template slot="append">路</template>
                  </el-input>
                </el-form-item>
                <el-form-item label="线路1方向">
                  <el-select v-model="formInlineRepeat.route1direction" placeholder="线路1方向">
                    <el-option label="上行" value="up"></el-option>
                    <el-option label="下行" value="down"></el-option>
                    <el-option label="环线" value="circle"></el-option>
                  </el-select>
                </el-form-item>
              </el-form>

              <el-form :inline="true" @submit.native.prevent :model="formInlineRepeat" class="demo-form-inline">
                <el-form-item label="线路2">
                  <el-input v-model="formInlineRepeat.route2" placeholder="线路2">
                    <template slot="append">路</template>
                  </el-input>
                </el-form-item>
                <el-form-item label="线路2方向">
                  <el-select v-model="formInlineRepeat.route2direction" placeholder="线路2方向">
                    <el-option label="上行" value="up"></el-option>
                    <el-option label="下行" value="down"></el-option>
                  </el-select>
                </el-form-item>
              </el-form>

              <el-form :inline="true" @submit.native.prevent :model="formInlineRepeat" class="demo-form-inline">
                <el-form-item>
                  <el-button type="primary" @click="searchRouteRepeat" native-type="submit">查询</el-button>
                  <el-button type="primary" @click="clearAllRepeat" native-type="submit">清空</el-button>
                </el-form-item>
              </el-form>

              <div v-show="isClearRepeat">
                <el-table :data="routeToSearchRepeatResult" stripe="true" :header-cell-style="{textAlign: 'center'}" :cell-style="{textAlign: 'center'}" empty-text="暂无重复站点">
                  <el-table-column prop="station_id" label="站点ID" width="320">
                  </el-table-column>
                  <el-table-column prop="station_name" label="站点名称" width="330">
                  </el-table-column>
                  <el-table-column prop="station_english" label="站点英语名称" width="330">
                  </el-table-column>
                </el-table>
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
  name: "RoutesStations",
  data(){
    return{
      headmsg:'沿线站点',
      isClearStation: false,
      haveResultStation: false,
      formInlineStation: {
        station: '',
        begin: '',
        end: ''
      },
      routeToSearchStationResult: {
        lineName: '',
        time: '',
        station: []
      },
      isClearRepeat: false,
      formInlineRepeat: {
        route1: '',
        route1direction: '',
        route2: '',
        route2direction: ''
      },
      routeToSearchRepeatResult: []
    }
  },
  components: {
    Navigation,
    Copyright
  },
  methods: {
    searchRouteStation(){
      const axios = require('axios');

      this.isClearStation = true;
      this.haveResultStation = true;

      axios.get('http://localhost:8080/route/find_lineId_stationName_path', {
        params: {
          lineId: this.formInlineStation.station,
          stationName1: this.formInlineStation.begin,
          stationName2: this.formInlineStation.end
        }
      }).then((res) => {
        if(res.data === {}){
          this.haveResultStation = false;
        }
        else{
          this.routeToSearchStationResult = JSON.parse(res);
        }
      }).catch((err) => {
        this.haveResultStation = false;
      })
    },
    clearAllStation(){
      this.isClearStation = false;
      this.haveResultStation = false;
    },
    searchRouteRepeat(){
      const axios = require('axios');

      this.isClearRepeat = true;

      axios.get('http://localhost:8080/route/find_sameStations', {
        params: {
          id1: this.formInlineRepeat.route1,
          direction1: this.formInlineRepeat.route1direction,
          id2: this.formInlineRepeat.route2,
          direction2: this.formInlineRepeat.route2direction
        }
      }).then((res) => {
        this.routeToSearchRepeatResult = res.data;
      }).catch((err) => {
        this.routeToSearchRepeatResult = [];
      });
    },
    clearAllRepeat(){
      this.isClearRepeat = false;
    }
  }
}
</script>

<style scoped>

</style>
