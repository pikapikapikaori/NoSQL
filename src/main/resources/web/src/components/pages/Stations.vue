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

              <div class="searchStationRoute" style="text-align: center">
                <el-form :inline="true" @submit.native.prevent :model="searchStationRoute" class="demo-form-inline">
                  <el-form-item label="站点">
                    <el-input v-model="searchStationRoute.station" placeholder="站点">
                    </el-input>
                  </el-form-item>
                  <el-form-item>
                    <el-button type="primary" @click="searchStationForRoute" native-type="submit">查询</el-button>
                    <el-button type="primary" @click="clearAllRoute" native-type="submit">清空</el-button>
                  </el-form-item>
                </el-form>
                <div v-show="isClearAllRoute">
                  <el-table :data="searchStationRouteResult" stripe="true" :header-cell-style="{textAlign: 'center'}" :cell-style="{textAlign: 'center'}" empty-text="暂无线路">
                    <el-table-column prop="id" label="站点id" width="320">
                    </el-table-column>
                    <el-table-column prop="routes" label="线路" width="660">
                    </el-table-column>
                  </el-table>
                </div>
              </div>
            </el-tab-pane>
            <!--

            <el-tab-pane label="站点某时线路">
              站点某时线路
              <el-divider></el-divider>

              <div class="searchStationSoon" style="text-align: center">
                <el-form :inline="true" @submit.native.prevent :model="searchStationSoon" class="demo-form-inline">
                  <el-form-item label="站点Id">
                    <el-input v-model="searchStationSoon.station" placeholder="站点 Id">
                    </el-input>
                  </el-form-item>
                  <el-form-item label="基准时间">
                    <el-input v-model="searchStationSoon.curTime" placeholder="基准时间">
                    </el-input>
                  </el-form-item>
                  <el-form-item label="预期时间">
                    <el-input v-model="searchStationSoon.preTime" placeholder="预期时间">
                    </el-input>
                  </el-form-item>
                  <el-form-item>
                    <el-button type="primary" @click="searchStationForSoon" native-type="submit">查询</el-button>
                    <el-button type="primary" @click="clearAllSoon" native-type="submit">清空</el-button>
                  </el-form-item>
                </el-form>
                <div v-show="isClearAllSoon">
                  <el-table :data="searchStationSoonResult" stripe="true" :header-cell-style="{textAlign: 'center'}" :cell-style="{textAlign: 'center'}" empty-text="暂无线路">
                    <el-table-column prop="routes" label="线路" width="980">
                    </el-table-column>
                  </el-table>
                </div>
              </div>
            </el-tab-pane>

            <el-tab-pane label="站点某时某线">
              站点某时某线
              <el-divider></el-divider>

              <div class="searchStationTime" style="text-align: center">
                <el-form :inline="true" @submit.native.prevent :model="searchStationTime" class="demo-form-inline">
                  <el-form-item label="站点Id">
                    <el-input v-model="searchStationTime.station" placeholder="站点 Id">
                    </el-input>
                  </el-form-item>
                  <el-form-item label="基准时间">
                    <el-input v-model="searchStationTime.curTime" placeholder="基准时间">
                    </el-input>
                  </el-form-item>
                  <el-form-item>
                    <el-button type="primary" @click="searchStationForTime" native-type="submit">查询</el-button>
                    <el-button type="primary" @click="clearAllTime" native-type="submit">清空</el-button>
                  </el-form-item>
                </el-form>
                <div v-show="isClearAllTime">
                  <el-table :data="searchStationTimeResult" stripe="true" :header-cell-style="{textAlign: 'center'}" :cell-style="{textAlign: 'center'}" empty-text="暂无线路">
                    <el-table-column prop="timeTable" label="班次" width="980">
                    </el-table-column>
                  </el-table>
                </div>
              </div>
            </el-tab-pane>
            -->
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
      headmsg:'站点信息查询',
      isClearAllRoute: false,
      searchStationRoute: {
        station: ''
      },
      searchStationRouteResult: [],
      isClearAllSoon: false,
      searchStationSoon: {
        station: '',
        curTime: '',
        preTime: ''
      },
      searchStationSoonResult: [],
      isClearAllTime: false,
      searchStationTime: {
        station: '',
        curTime: ''
      },
      searchStationTimeResult: []
    }
  },
  components: {
    Navigation,
    Copyright
  },
  methods: {
    searchStationForRoute(){
      this.isClearAllRoute = true;

      this.$axios.get('/station/find_stationName_routeName', {
        params: {
          stationName: this.searchStationRoute.station
        }
      }).then((res) => {
        this.searchStationRouteResult = res.data;
      }).catch((err) => {
        this.searchStationRouteResult = [];
      });
    },
    clearAllRoute(){
      this.isClearAllRoute = false;
    },
    searchStationForSoon(){
      this.isClearAllSoon= true;

      this.$axios.get('/station/find_station_time_line', {
        params: {
          stationId: this.searchStationSoon.station,
          baseTime: this.searchStationSoon.curTime,
          lasttime: this.searchStationSoon.preTime
        }
      }).then((rses) => {
        this.searchStationSoonResult = res.data;
      }).catch((err) => {
        this.searchStationSoonResult = [];
      })
    },
    clearAllSoon(){
      this.isClearAllSoon = false;
    },
    searchStationForTime(){
      this.isClearAllTime= true;

      this.$axios.get('/station/find_station_time_nearest3', {
        params: {
          stationId: this.searchStationTime.station,
          baseTime: this.searchStationTime.curTime
        }
      }).then((res) => {
        this.searchStationTimeResult = res.data;
      }).catch((err) => {
        this.searchStationTimeResult = [];
      })
    },
    clearAllTime(){
      this.isClearAllTime = false;
    }
  }
}
</script>

<style scoped>

</style>
