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
          <div class="spacehd" style="text-align: center">
            {{'\xa0'}}
          </div>

          <div class="forminline" style="text-align: center">
            <el-form :inline="true" @submit.native.prevent :model="formInline" class="demo-form-inline">
              <el-form-item label="站点1">
                <el-input v-model="formInline.station1" placeholder="站点1"></el-input>
              </el-form-item>

              <el-form-item label="站点2">
                <el-input v-model="formInline.station2" placeholder="站点2"></el-input>
              </el-form-item>

              <el-form-item>
                <el-button type="primary" @click="searchRoutes" native-type="submit" :loading="loading">查询</el-button>
              </el-form-item>
            </el-form>

            <el-table :data="searchWayResult" stripe="true">
              <el-table-column type="index" width="400">
              </el-table-column>
              <el-table-column prop="route" label="路线" width="600">
              </el-table-column>
            </el-table>
          </div>
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
  name: "Wayss",
  data(){
    return{
      headmsg:'直达路线查询',
      formInline: {
        station1: '',
        station2: ''
      },
      searchWayResult: []
    }
  },
  components:{
    Navigation,
    Copyright
  },
  methods: {
    searchRoutes() {
      if (this.formInline.station1 === '荷花池' && this.formInline.station2 == '环球中心'){
        this.searchWayResult = [{route: '30路上行'}];
      }
      else{
        this.searchWayResult = [];
      }
    }
  }
}
</script>

<style scoped>

</style>
