import Vuex from 'vuex'
export default new Vuex.Store({
  state: {
    notify: {
      show: false,
      title: null,
      msg: null,
      type: null
    },
    loader: false
  },
  mutations: {
    showSuccess (state: any, conf: { title: string, msg: string }): void {
      state.notify.show = true
      state.notify.title = conf.title
      state.notify.msg = conf.msg
      state.notify.type = 'success'
    },
    showError (state: any, conf: { title: string, msg: string }): void {
      state.notify.show = true
      state.notify.title = conf.title
      state.notify.msg = conf.msg
      state.notify.type = 'error'
    },
    closeNotify (state: any): void {
      state.notify.show = false
      state.notify.title = null
      state.notify.msg = null
      state.notify.type = null
    },

    // Loader
    loaderStart (state: any) {
      state.loader = true
    },
    loaderEnd (state: any) {
      state.loader = false
    }
  },
  actions: {

  }
})
