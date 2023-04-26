<template>
  <xing-header></xing-header>
  <xing-aside></xing-aside>
  <div class="content-box" :class="{ 'content-collapse': collapse }">
    <router-view></router-view>
  </div>
  <!-- <xing-audio></xing-audio> -->
</template>

<script lang="ts" setup>
import { ref } from "vue";
import XingHeader from "@/components/layouts/XingHeader.vue";
// import XingAudio from "@/components/layouts/XingAudio.vue";
import XingAside from "@/components/layouts/XingAside.vue";
import emitter from "@/utils/emitter";

const collapse = ref(false);
emitter.on("collapse", (msg) => {
  collapse.value = msg as boolean;
});
</script>

<style scoped>
.content-box {
  position: absolute;
  left: 150px;
  right: 0;
  top: 60px;
  bottom: 0;
  overflow-y: scroll;
  transition: left 0.3s ease-in-out;
  padding: 20px;
}

.content-collapse {
  left: 65px;
}
</style>
