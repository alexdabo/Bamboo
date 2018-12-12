import Vue from 'vue'
import Router from 'vue-router'

import LoginRouter from '@/router/LoginRouter'
import AdminRouter from '@/router/AdminRouter'


Vue.use(Router)

export default new Router({
  routes: [
    {path: '/', redirect: '/login'},
    LoginRouter,
    AdminRouter
  ]
})
