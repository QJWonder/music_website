<template>
  <el-container>
    <el-header>
      <xing-header></xing-header>
    </el-header>
    <el-main>
      <router-view />
      <xing-current-play></xing-current-play>
      <xing-play-bar></xing-play-bar>
      <xing-scroll-top></xing-scroll-top>
      <xing-audio></xing-audio>
    </el-main>
    <el-footer>
      <xing-footer></xing-footer>
    </el-footer>
  </el-container>
</template>

<script lang="ts" setup>
import { getCurrentInstance } from "vue";
import XingHeader from "@/components/layouts/XingHeader.vue";
import XingCurrentPlay from "@/components/layouts/XingCurrentPlay.vue";
import XingPlayBar from "@/components/layouts/XingPlayBar.vue";
import XingScrollTop from "@/components/layouts/XingScrollTop.vue";
import XingFooter from "@/components/layouts/XingFooter.vue";
import XingAudio from "@/components/layouts/XingAudio.vue";

const { proxy } = getCurrentInstance();

if (sessionStorage.getItem("dataStore")) {
  proxy.$store.replaceState(Object.assign({}, proxy.$store.state, JSON.parse(sessionStorage.getItem("dataStore"))));
}

window.addEventListener("beforeunload", () => {
  sessionStorage.setItem("dataStore", JSON.stringify(proxy.$store.state));
});
</script>

<style lang="scss" scoped>
@import "@/assets/css/var.scss";
@import "@/assets/css/global.scss";


.el-container {
  background-color: rgba(173, 216, 230, 0.4);
  min-height: calc(100% - 60px);

}

.el-header {
  padding: 0;
}

.el-main {
  padding-left: 0;
  padding-right: 0;
}
</style>
