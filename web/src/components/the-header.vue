<template>
  <a-layout-header class="header">
    <div class="logo" />
    <div style="float: right;color: white">
      您好:{{member.mobile}} &nbsp;&nbsp;
      <router-link to="/login">
        退出登录
      </router-link>
    </div>
    <a-menu
        v-model:selectedKeys="selectedKeys1"
        theme="dark"
        mode="horizontal"
        :style="{ lineHeight: '64px' }"
    >
      <a-menu-item key="1">nav 1</a-menu-item>
      <a-menu-item key="2">nav 2</a-menu-item>
      <a-menu-item key="3">nav 3</a-menu-item>
    </a-menu>
  </a-layout-header>
</template>
<script>
import {defineComponent, ref} from "vue";
import store from "@/store";
export default defineComponent({
  name:"the-header-view",
  setup(){
    let member=store.state.member;//因为header只是显示，不会修改member，所以声明成普通变量就可以，不需要响应式变量

    return {
      selectedKeys1 : ref(['2']),
      member
    };
  },
});
</script>
<style scoped>

</style>