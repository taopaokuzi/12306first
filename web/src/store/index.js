import { createStore } from 'vuex'

export default createStore({

  state: {
    member:{}
  },
  getters: {
  },
  mutations: {   //所有状态修改
    setMember(state,_member){
      state.member=_member;
    }
  },
  actions: {          //异步任务
  },
  modules: {          //模块化
  }
})
