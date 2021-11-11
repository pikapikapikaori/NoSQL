<template>
  <div>
    <el-radio-group v-model="isCollapse" style="margin-bottom: 20px;">
      <el-radio-button :label="false">展开</el-radio-button>
      <el-radio-button :label="true">收起</el-radio-button>
    </el-radio-group>
    <el-menu default-active="$route.path" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" :collapse="isCollapse" router="true">
      <el-menu-item index="/">
        <i class="el-icon-menu"></i>
        <span slot="title">主页</span>
      </el-menu-item>

      <el-submenu index="1">
        <template slot="title">
          <i class="el-icon-info"></i>
          <span slot="title">线路</span>
        </template>

        <el-menu-item index="/testpage">线路信息</el-menu-item>
        <el-menu-item index="1-2">沿线站点</el-menu-item>
        <el-menu-item index="1-2">线路班次</el-menu-item>
        <el-menu-item index="1-2">线路更新</el-menu-item>

      </el-submenu>

      <el-menu-item index="2">
        <i class="el-icon-location"></i>
        <span slot="title">站点</span>
      </el-menu-item>

      <el-submenu index="2">
        <template slot="title">
          <i class="el-icon-document"></i>
          <span slot="title">统计</span>
        </template>

        <el-menu-item-group>
          <span slot="title">排序</span>
          <el-menu-item index="1-1">路线最多站点</el-menu-item>
          <el-menu-item index="1-2">线路最多站台</el-menu-item>
          <el-menu-item index="1-2">最多站点线路</el-menu-item>
          <el-menu-item index="1-2">运行时间线路</el-menu-item>
        </el-menu-item-group>

        <el-menu-item-group>
          <span slot="title">数量</span>
          <el-menu-item index="1-1">路线类型统计</el-menu-item>
        </el-menu-item-group>
      </el-submenu>

      <el-menu-item index="2">
        <i class="el-icon-rank"></i>
        <span slot="title">路线</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "Navigation",
  data() {
    return {
      isCollapse: true
    };
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  }
}
</script>

<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
</style>
