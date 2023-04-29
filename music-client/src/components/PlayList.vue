<template>
  <div class="play-list">
    <div class="play-title" v-if="title">{{ title }}</div>
    <ul class="play-body">
      <li class="card-frame" v-for="(item, index) of playList" :key="index">
        <div class="card" @click="goAblum(item)">
          <el-image class="card-img" fit="contain" :src="attachImageUrl(item.pic)" />
          <div class="mask" @click="goAblum(item)">
            <xing-icon class="mask-icon" :icon="BOFANG"></xing-icon>
          </div>
        </div>
        <p class="card-name">{{ item.name || item.title }}</p>
      </li>
    </ul>
  </div>
</template>

<script lang="ts">
import { defineComponent, getCurrentInstance, toRefs, PropType } from "vue";
import XingIcon from "@/components/layouts/XingIcon.vue";
import mixin from "@/mixins/mixin";
import { Icon } from "@/enums";
import { HttpManager } from "@/api";

interface Song {
  pic: string;
  name: string;
  title: string;
  // 其他属性
}
/*
  通过 props 接收到了名为 playList 的数组，
  其中的每个元素就是 songList 数组中的一个对象，
  所以在子组件中可以通过 item.attribute 来获取每个对象的 attribute 属性
*/
export default defineComponent({
  components: {
    XingIcon,
  },
  props: {
    playList: {
      type: Array as PropType<Song[]>,
      required: true,
    },
    title: String,
    path: String,
  },
  // props: {
  //   title: String,
  //   playList: Array,
  //   path: String,
  // },
  setup(props) {
    const { proxy } = getCurrentInstance();
    const { routerManager } = mixin();

    const { path } = toRefs(props);

    function goAblum(item) {
      proxy.$store.commit("setSongDetails", item);
      routerManager(path.value, { path: `/${path.value}/${item.id}` });
    }

    return {
      BOFANG: Icon.BOFANG,
      goAblum,
      attachImageUrl: HttpManager.attachImageUrl,
    };
  },
});
</script>

<style lang="scss" scoped>
@import "@/assets/css/var.scss";
@import "@/assets/css/global.scss";

.play-list {
  padding: 0 1rem;

  .play-title {
    height: 60px;
    line-height: 60px;
    font-size: 28px;
    font-weight: 500;
    text-align: center;
    color: $color-black;
    box-sizing: border-box;
  }

  .play-body {
    @include layout(flex-start, stretch, row, wrap);
  }
}

.card-frame {
  .card {
    position: relative;
    height: 0;
    padding-bottom: 100%;
    overflow: hidden;
    border-radius: 5px;

    .card-img {
      width: 100%;
      transition: all 0.4s ease;
    }
  }

  .card-name {
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2;
    margin: 0.5rem 0;
  }

  &:hover .card-img {
    transform: scale(1.2);
  }
}

.mask {
  position: absolute;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: hidden;
  border-radius: 5px;
  background-color: rgba(52, 47, 41, 0.4);
  @include layout(center, center);
  transition: all 0.3s ease-in-out;
  opacity: 0;

  .mask-icon {
    @include icon(2em, rgba(240, 240, 240, 1));
  }

  &:hover {
    opacity: 1;
    cursor: pointer;
  }
}

@media screen and (min-width: $sm) {
  .card-frame {
    width: 18%;
    margin: 0.5rem 1%;
  }
}

@media screen and (max-width: $sm) {
  .card-frame {
    width: 46%;
    margin: 0.5rem 2%;
  }
}
</style>