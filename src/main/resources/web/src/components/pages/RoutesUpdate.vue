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

          <!--
          <el-tab-pane label="线路创建">
            线路创建
            <el-divider></el-divider>

            <div class="forminlineCreate" style="text-align: center">
              <el-form :inline="true" :model="formInlineCreate" ref="formInlineCreate" class="demo-form-inline">
                <el-form-item label="路线">
                  <el-input v-model="formInlineCreate.route" placeholder="路线"></el-input>
                </el-form-item>
              </el-form>
              <el-form :inline="true" @submit.native.prevent :model="formInlineCreate" class="demo-form-inline">
                <el-form-item label="行驶时间">
                  <el-input v-model="formInlineCreate.oneWayTime" placeholder="行驶时间"></el-input>
                </el-form-item>
              </el-form>
              <el-form :inline="true" @submit.native.prevent :model="formInlineCreate" class="demo-form-inline">
                <el-form-item label="方向">
                  <el-radio-group v-model="formInlineCreate.directional">
                    <el-radio label="上行" value="up"></el-radio>
                    <el-radio label="下行" value="down"></el-radio>
                    <el-radio label="环线" value="circle"></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-form>
              <el-form :inline="true" @submit.native.prevent :model="formInlineCreate" class="demo-form-inline">
                <el-form-item label="路线长度">
                  <el-input v-model="formInlineCreate.kilometer" placeholder="路线长度"></el-input>
                </el-form-item>
              </el-form>
              <el-form :inline="true" @submit.native.prevent :model="formInlineCreate" class="demo-form-inline">
                <el-form-item label="线路名称">
                  <el-input v-model="formInlineCreate.name" placeholder="线路名称"></el-input>
                </el-form-item>
              </el-form>
              <el-form :inline="true" @submit.native.prevent :model="formInlineCreate" class="demo-form-inline">
                <el-form-item label="运行时间">
                  <el-input v-model="formInlineCreate.runTime" placeholder="运行时间"></el-input>
                </el-form-item>
              </el-form>
              <el-form :inline="true" @submit.native.prevent :model="formInlineCreate" class="demo-form-inline">
                <el-form-item label="类型">
                  <el-input v-model="formInlineCreate.type" placeholder="类型"></el-input>
                </el-form-item>
              </el-form>

              <el-form :inline="true" @submit.native.prevent :model="formInlineCreate" class="demo-form-inline"
                       v-for="(interval, index) in formInlineCreate.intervals" :key="formInlineCreate.intervals.key">
                <el-form-item
                  :label="'站点' + (index + 1) + ' 名称'"
                  :prop="'intervals.' + index + '.name'"
                >
                  <el-input v-model="interval.name"></el-input>
                </el-form-item>
                <el-form-item
                  :label="'站点' + (index + 1) + ' 英语'"
                  :prop="'intervals.' + index + '.english'"
                >
                  <el-input v-model="interval.english"></el-input>
                </el-form-item>
                <el-form-item
                  :label="'站点' + (index + 1) + ' id'"
                  :prop="'intervals.' + index + '.id'"
                  :rules="{
                    required: true, message: '站点id不能为空', trigger: 'blur'
                  }"
                >
                  <el-input v-model="interval.id"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button @click.prevent="removeStation(interval)">删除</el-button>
                </el-form-item>
              </el-form>

              <el-form :inline="true" @submit.native.prevent :model="formInlineCreate" class="demo-form-inline"
                       v-for="(timeTable, index) in formInlineCreate.timeTables" :key="formInlineCreate.timeTables.key">
                <el-form-item
                  :label="'时刻' + (index + 1) + ''"
                  :prop="'timeTables.' + index + '.time'"
                  :rules="{
                    required: true, message: '时刻不能为空', trigger: 'blur'
                  }"
                >
                  <el-input v-model="timeTable.time"></el-input>
                </el-form-item>

                <el-form-item>
                  <el-button @click.prevent="removeTime(timeTable)">删除</el-button>
                </el-form-item>
              </el-form>

              <el-form :inline="true" @submit.native.prevent :model="formInlineCreate" class="demo-form-inline">
                <el-form-item>
                  <el-button type="primary" @click="submitRoute">提交</el-button>
                </el-form-item>

                <el-form-item>
                  <el-button @click="addStation">新增站点</el-button>
                </el-form-item>

                <el-form-item>
                  <el-button @click="addTime">新增时刻</el-button>
                </el-form-item>
              </el-form>
            </div>
          </el-tab-pane>
          -->

          <el-tab-pane label="删除整条线路">
            删除整条线路
            <el-divider></el-divider>

            <div class="deleteWhole" style="text-align: center">
              <el-form :inline="true" @submit.native.prevent :model="deleteWhole" class="demo-form-inline">
                <el-form-item label="线路">
                  <el-input v-model="deleteWhole.route" placeholder="线路">
                    <template slot="append">路</template>
                  </el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="deleteRoute" native-type="submit">删除</el-button>
                </el-form-item>
              </el-form>
            </div>
          </el-tab-pane>

          <el-tab-pane label="替换线路站点">
            替换线路站点
            <el-divider></el-divider>

            <div class="substituteStation" style="text-align: center">
              <el-form :inline="true" @submit.native.prevent :model="substituteStation" class="demo-form-inline">
                <el-form-item label="线路">
                  <el-input v-model="substituteStation.route" placeholder="线路">
                    <template slot="append">路</template>
                  </el-input>
                </el-form-item>
                <el-form-item label="被替换站点 Id">
                  <el-input v-model="substituteStation.originStation" placeholder="被替换站点 Id">
                  </el-input>
                </el-form-item>
                <el-form-item label="新站点 Id">
                  <el-input v-model="substituteStation.newStation" placeholder="新站点 Id">
                  </el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="substitute" native-type="submit">替换</el-button>
                </el-form-item>
              </el-form>
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
  name: "RoutesUpdate",
  data(){
    return{
      headmsg:'线路更新',
      formInlineCreate: {
        route: '',
        oneWayTime: '',
        directional: '',
        kilometer: '',
        name: '',
        runTime: '',
        type: '',

        intervals: [{
          name: '',
          english: '',
          id: '',
          key: Date.now()
        }],
        timeTables: [{
          time: '',
          key: Date.now()
        }]
      },
      deleteWhole: {
        route: ''
      },
      substituteStation: {
        route: '',
        originStation: '',
        newStation: ''
      }
    }
  },
  components: {
    Navigation,
    Copyright
  },
  methods: {
    addStation(){
        this.formInlineCreate.intervals.push({
          name: '',
          english: '',
          id: '',
          key: Date.now()
        });
    },
    removeStation(interval) {
      var index = this.formInlineCreate.intervals.indexOf(interval)
      if (index !== -1) {
        this.formInlineCreate.intervals.splice(index, 1)
      }
    },
    addTime(){
      this.formInlineCreate.timeTables.push({
        time: '',
        key: Date.now()
      });
    },
    removeTime(time) {
      var index = this.formInlineCreate.timeTables.indexOf(time)
      if (index !== -1) {
        this.formInlineCreate.timeTables.splice(index, 1)
      }
    },
    submitRoute(){
      this.$confirm('此操作将提交路线, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        beforeClose: (action, instance, done) => {
          if (action === 'confirm') {
            instance.confirmButtonLoading = true;
            instance.confirmButtonText = '执行中...';
            setTimeout(() => {
              done();
              setTimeout(() => {
                instance.confirmButtonLoading = false;
              }, 300);
            }, 3000);

            // 这里写与后端对接的新建操作
          }
          else {
            done();
          }
        }
      }).then(() => {
        this.$message({
          type: 'success',
          message: '提交成功!'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消提交'
        });
      });
    },
    deleteRoute(){
      this.$confirm('此操作将删除路线, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        beforeClose: (action, instance, done) => {
          if (action === 'confirm') {
            instance.confirmButtonLoading = true;
            instance.confirmButtonText = '执行中...';
            setTimeout(() => {
              done();
              setTimeout(() => {
                instance.confirmButtonLoading = false;
              }, 300);
            }, 3000);

            // 这里写与后端对接的删除操作
            this.$axios.post('/route/delete_line', {
              lineId: this.deleteWhole.route
            });
          }
          else {
            done();
          }
        }
      }).then(() => {
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    substitute(){
      this.$confirm('此操作将替换站点, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        beforeClose: (action, instance, done) => {
          if (action === 'confirm') {
            instance.confirmButtonLoading = true;
            instance.confirmButtonText = '执行中...';
            setTimeout(() => {
              done();
              setTimeout(() => {
                instance.confirmButtonLoading = false;
              }, 300);
            }, 3000);

            // 这里写与后端对接的替换操作
            this.$axios.post('/route/change_line', {
              lineId: this.substituteStation.route,
              stationId: this.substituteStation.originStation,
              newStationId: this.substituteStation.newStation
            });
          }
          else {
            done();
          }
        }
      }).then(() => {
        this.$message({
          type: 'success',
          message: '替换成功!'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消替换'
        });
      });
    }
  }
}
</script>

<style scoped>

</style>
