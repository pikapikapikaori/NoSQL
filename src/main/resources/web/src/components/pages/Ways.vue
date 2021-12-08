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
            <el-tab-pane label="直达路线查询">
              直达路线查询
              <el-divider></el-divider>

              <div class="forminline" style="text-align: center">
                <el-form :inline="true" @submit.native.prevent :model="formInline" class="demo-form-inline">
                  <el-form-item label="站点1">
                    <el-input v-model="formInline.station1" placeholder="站点1"></el-input>
                  </el-form-item>

                  <el-form-item label="站点2">
                    <el-input v-model="formInline.station2" placeholder="站点2"></el-input>
                  </el-form-item>

                  <el-form-item>
                    <el-button type="primary" @click="searchRoutes" native-type="submit">查询</el-button>
                    <el-button type="primary" @click="clearAll" native-type="submit">清空</el-button>
                  </el-form-item>
                </el-form>

                <div>
                  <div v-if="isClear">
                    <el-table :data="searchWayResult" stripe="true" :header-cell-style="{textAlign: 'center'}" :cell-style="{textAlign: 'center'}" empty-text="暂无直达路线">
                      <el-table-column prop="route" label="路线" width="980">
                      </el-table-column>
                    </el-table>
                  </div>
                </div>
              </div>
            </el-tab-pane>

            <el-tab-pane label="最短路线查询（基于Id）">
              最短路线查询（基于Id）
              <el-divider></el-divider>

              <div class="idShorrtedRouteForm" style="text-align: center">
                <el-form :inline="true" @submit.native.prevent :model="idShortedRouteForm" class="demo-form-inline">
                  <el-form-item label="站点1 Id">
                    <el-input v-model="idShortedRouteForm.station1" placeholder="站点1 Id"></el-input>
                  </el-form-item>

                  <el-form-item label="站点2 Id">
                    <el-input v-model="idShortedRouteForm.station2" placeholder="站点2 Id"></el-input>
                  </el-form-item>

                  <el-form-item>
                    <el-button type="primary" @click="searchRoutesId" native-type="submit">查询</el-button>
                    <el-button type="primary" @click="clearAllId" native-type="submit">清空</el-button>
                  </el-form-item>
                </el-form>

                <div>
                  <div v-if="isClearId">
                    <el-table :data="searchIdResult" stripe="true" :header-cell-style="{textAlign: 'center'}" :cell-style="{textAlign: 'center'}" empty-text="暂无直达路线">
                      <el-table-column prop="id" label="站点id" width="320">
                      </el-table-column>
                      <el-table-column prop="name" label="站点名称" width="330">
                      </el-table-column>
                      <el-table-column prop="english" label="站点英语" width="330">
                      </el-table-column>
                    </el-table>
                  </div>
                </div>
              </div>
            </el-tab-pane>



            <el-tab-pane label="最短路线查询（基于名称）">
              最短路线查询（基于名称）
              <el-divider></el-divider>
              <div class="nameShorrtedRouteForm" style="text-align: center">
                <el-form :inline="true" @submit.native.prevent :model="nameShortedRouteForm" class="demo-form-inline">
                  <el-form-item label="站点1 名称">
                    <el-input v-model="nameShortedRouteForm.station1" placeholder="站点1 名称"></el-input>
                  </el-form-item>

                  <el-form-item label="站点2 名称">
                    <el-input v-model="nameShortedRouteForm.station2" placeholder="站点2 名称"></el-input>
                  </el-form-item>

                  <el-form-item>
                    <el-button type="primary" @click="searchRoutesName" native-type="submit">查询</el-button>
                    <el-button type="primary" @click="clearAllName" native-type="submit">清空</el-button>
                  </el-form-item>
                </el-form>

                <div>
                  <div v-if="isClearName">
                    <el-table :data="searchNameResult" stripe="true" :header-cell-style="{textAlign: 'center'}" :cell-style="{textAlign: 'center'}" empty-text="暂无直达路线">
                      <el-table-column prop="id" label="站点id" width="320">
                      </el-table-column>
                      <el-table-column prop="name" label="站点名称" width="330">
                      </el-table-column>
                      <el-table-column prop="english" label="站点英语" width="330">
                      </el-table-column>
                    </el-table>
                  </div>
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
  </div>
</template>

<script>
import Navigation from '../../components/navi/Navigation'
import Copyright from '../../components/copyright/Copyright'

export default {
  name: "Ways",
  data(){
    return{
      headmsg: '路线查询',
      isClear: false,
      formInline: {
        station1: '',
        station2: ''
      },
      searchWayResult: [],
      isClearId: false,
      idShortedRouteForm: {
        station1: '',
        station2: ''
      },
      searchIdResult: [],
      isClearName: false,
      nameShortedRouteForm: {
        station1: '',
        station2: ''
      },
      searchNameResult: []
    }
  },
  components:{
    Navigation,
    Copyright
  },
  methods: {
    searchRoutes() {
      const axios = require('axios');

      this.isClear = true;

      axios.get('http://localhost:8080/line/find_directRoute', {
        params: {
          station1: this.formInline.station1,
          station2: this.formInline.station2
        }
      }).then((res) => {
        this.searchWayResult = res.data;
      }).catch((err) => {
        this.searchWayResult = [];
      });
    },
    clearAll(){
      this.isClear = false;
    },
    searchRoutesId() {
      const axios = require('axios');

      this.isClearId = true;

      axios.get('http://localhost:8080/line/find_shortestRoute_id', {
        params: {
          station1: this.idShortedRouteForm.station1,
          station2: this.idShortedRouteForm.station2
        }
      }).then((res) => {
        this.searchIdResult = res.data;
      }).catch((err) => {
        this.searchIdResult = [];
      });
    },
    clearAllId(){
      this.isClearId = false;
    },
    searchRoutesName() {
      const axios = require('axios');

      this.isClearName = true;

      axios.get('http://localhost:8080/line/find_shortestRoute_name', {
        params: {
          station1: this.nameShortedRouteForm.station1,
          station2: this.nameShortedRouteForm.station2
        }
      }).then((res) => {
        this.searchNameResult = res.data;
      }).catch((err) => {
        this.searchNameResult = [];
      });
    },
    clearAllName(){
      this.isClearName = false;
    }
  }
}
</script>

<style scoped>

</style>
