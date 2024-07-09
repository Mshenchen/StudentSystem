<template>
  <div>
    <el-container>
      <el-main>
        <h1>teacher home</h1>
        <info-card></info-card>
        <el-card style="width: 100%; margin-top: 10px">
          <p><i class="el-icon-s-home" style="margin-right: 18px"></i>项目名：教务管理系统</p>
          <p><i class="el-icon-s-custom" style="margin-right: 18px"></i>开发人员：马沈晨 杨子康 李锦琨</p>
          <p><i class="el-icon-location-outline" style="margin-right: 18px"></i>学校：中南民族大学</p>
          <p><i class="el-icon-chat-round" style="margin-right: 18px"></i>开发小组：爱敲代码</p>
        </el-card>
        <logout></logout>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import Logout from "@/components/logout";
import InfoCard from "@/components/infoCard";
export default {
  name: "home",
  components: {InfoCard, Logout},
}
</script>

<style scoped>

</style>
