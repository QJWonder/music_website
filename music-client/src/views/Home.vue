<template>
  <!--轮播图-->
  <el-carousel class="swiper-container" type="card" height="20vw" :interval="4000">
    <el-carousel-item v-for="item in swiperList" :key="item">
      <img :src="item.picImg" />
    </el-carousel-item>
  </el-carousel>
  <!--热门歌单-->
  <play-list class="play-list-container" title="歌单" path="song-sheet-detail" :playList="songList"></play-list>
  <!--热门歌手-->
  <play-list class="play-list-container" title="歌手" path="singer-detail" :playList="singerList"></play-list>
</template>

<script lang="ts" setup>
import { ref, onMounted } from "vue";
import PlayList from "@/components/PlayList.vue";
import { swiperList, NavName } from "@/enums";
import { HttpManager } from "@/api";
import mixin from "@/mixins/mixin";

const songList = ref([]); // 歌单列表
const singerList = ref([]); // 歌手列表
const { changeIndex } = mixin();
try {
  HttpManager.getSongList().then((res) => {
    songList.value = (res as ResponseBody).data.sort(() => Math.random() - 0.5).slice(0, 10);
    // 随机获取10个
  });
  /*
    从服务器返回的数据（res.data）进行排序，取前10个，
    最后将结果赋值给songList的变量的value属性
  */
  HttpManager.getAllSinger().then((res) => {
    singerList.value = (res as ResponseBody).data.sort(() => Math.random() - 0.5).slice(0, 10);
  });

  onMounted(() => {
    changeIndex(NavName.Home);
  });
} catch (error) {
  console.error(error);
}
</script>

<style lang="scss" scoped>
@import "@/assets/css/var.scss";

/*轮播图*/
.swiper-container {
  width: 90%;
  margin: auto;
  padding-top: 20px;

  img {
    width: 100%;
  }
}

.swiper-container:deep(.el-carousel__indicators.el-carousel__indicators--outside) {
  display: inline-block;
  transform: translate(35vw);
}

.el-slider__runway {
  background-color: $color-blue;
}
</style>
