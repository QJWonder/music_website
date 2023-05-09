<template>
  <ul class="xing-header-nav">
    <li :class="{ active: item.name === activeName }" v-for="item in styleList" :key="item.path"
      @click="handleChangeView(item)">
      {{ item.name }}
    </li>
  </ul>
</template>

<script lang="ts">
import { defineComponent, getCurrentInstance, PropType } from "vue";
interface HeaderNavList {
  name: string;   // string 是一种原始数据类型，表示一个字符序列，而 String 是 JavaScript 中的构造函数，表示字符串对象
  path: string;
}
export default defineComponent({
  props: {
    styleList: {
      type: Array as PropType<HeaderNavList[]>,
      required: true,
    },
    activeName: String,
  },
  emits: ["click"],
  setup() {
    const { proxy } = getCurrentInstance();

    function handleChangeView(item) {
      proxy.$emit("click", item.path, item.name);
    }
    return {
      handleChangeView,
    };
  },
});
</script>

<style lang="scss" scoped>
@import "@/assets/css/var.scss";

li {
  margin: $header-nav-margin;
  padding: $header-nav-padding;
  line-height: 3.3rem;
  color: $color-grey;
  border-bottom: none;
  cursor: pointer;
}

li.active {
  color: $color-black;
  font-weight: 600;
  border-bottom: 5px solid $color-black;
}
</style>
