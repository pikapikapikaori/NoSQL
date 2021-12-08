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
        <div class="spacehd" style="text-align: center">
          {{ '\xa0' }}
        </div>

        <div class="forminline" style="text-align: center">
          <el-form :inline="true" @submit.native.prevent :model="formInline" class="demo-form-inline">
            <el-form-item label="线路">
              <el-input v-model="formInline.route" placeholder="线路">
                <template slot="append">路</template>
              </el-input>
            </el-form-item>
            <el-form-item label="线路方向">
              <el-select v-model="formInline.direction" placeholder="线路1方向">
                <el-option label="上行" value="up"></el-option>
                <el-option label="下行" value="down"></el-option>
                <el-option label="环线" value="circle"></el-option>
              </el-select>
            </el-form-item>

            <el-form-item>
              <el-button type="primary" @click="searchRoutes" native-type="submit">查询</el-button>
              <el-button type="primary" @click="clearAll" native-type="submit">清空</el-button>
            </el-form-item>
          </el-form>

          <div>
            <div v-if="isClear">
              <el-table :data="searchResult" stripe="true" :header-cell-style="{textAlign: 'center'}"
                        :cell-style="{textAlign: 'center'}" empty-text="暂无班次信息">
                <el-table-column prop="time" label="班次" width="980">
                </el-table-column>
              </el-table>
            </div>
          </div>
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
  name: "RoutesTimetable",
  data() {
    return {
      headmsg: '线路班次',
      isClear: false,
      formInline: {
        route: '',
        direction: ''
      },
      searchResult: []
    }
  },
  components: {
    Navigation,
    Copyright
  },
  methods: {
    searchRoutes() {
      this.isClear = true;

    },
    clearAll() {
      this.isClear = false;
    }
  }
}
</script>

<style scoped>

</style>
