<template>
  <a-row class="login">
    <a-col :span="8" :offset="8" class="login-main">
      <h1 style="text-align: center"><rocket-two-tone />&nbsp;逃跑裤子12306售票系统</h1>
      <a-form
          :model="loginForm"
          name="basic"
          autocomplete="off"
      >
        <a-form-item
            label=""
            name="mobile"
            :rules="[{ required: true, message: '请输入手机号!' }]"
        >
          <a-input v-model:value="loginForm.mobile" placeholder="手机号"/>
        </a-form-item>

        <a-form-item
            label=""
            name="code"
            :rules="[{ required: true, message: '请输入验证码!' }]"
        >
          <a-input v-model:value="loginForm.code">
            <template #addonAfter>
              <a @click="sendCode">获取验证码</a>
            </template>
          </a-input>
          <!--<a-input v-model:value="loginForm.code" placeholder="验证码"/>-->
        </a-form-item>

        <a-form-item>
          <a-button type="primary" block @click="login">登录</a-button>
        </a-form-item>

      </a-form>
    </a-col>
  </a-row>
</template>

<script>
import { defineComponent, reactive } from 'vue';
import axios from 'axios';
import { notification } from 'ant-design-vue';
import {useRouter} from 'vue-router'
import store from "@/store"; //useRouter是管理全局的路由，useRoute用来获取当前页面的地址参数

export default defineComponent({
  name: "login-view",
  setup() {
    const router =useRouter();

    const loginForm = reactive({
      mobile: '13000000000',
      code: '',
    });

    const sendCode = () => {
      axios.post("/member/member/send-code", {//请求
        mobile: loginForm.mobile//传递参数
      }).then(response => {//调用结束后的回调函数，得到一个结果
        let data = response.data;//response.data==后端的CommonResp，得到结果的data对应后端的CommonResp
        if (data.success) {//判断是否成功，成功什么处理，失败什么处理
          notification.success({ description: '发送验证码成功！' });
          loginForm.code = "8888";//双向绑定
        } else {
          notification.error({ description: data.message });
        }
      });
    };

    const login = () => {
      axios.post("/member/member/login", loginForm).then((response) => {//传递实体的写法
        let data = response.data;
        if (data.success) {
          notification.success({ description: '登录成功！' });
          //登陆成功，跳到控制台主页
          router.push("/");
          store.commit("setMember",data.content);
        } else {
          notification.error({ description: data.message });
        }
      })
    };

    return {//return出去说明这个方法要被html调用到
      loginForm,
      sendCode,
      login
    };
  },
});
</script>

<style>
.login-main h1 {
  font-size: 25px;
  font-weight: bold;
}
.login-main {
  margin-top: 100px;
  padding: 30px 30px 20px;
  border: 2px solid grey;
  border-radius: 10px;
  background-color: #fcfcfc;
}
</style>