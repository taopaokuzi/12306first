import { createStore } from 'vuex'
const MEMBER="MEMBER";
export default createStore({

  state: {
    member:window.SessionStorage.get(MEMBER)||{}
  },
  getters: {
  },
  mutations: {   //所有状态修改
    setMember(state,_member){
      state.member=_member;
      window.SessionStorage.set(MEMBER,_member);
    }
  },
  actions: {          //异步任务
  },
  modules: {          //模块化
  }
})
