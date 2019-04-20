import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'login',
      component: () => import('@/views/login/LoginView.vue')
    },
    {
      path: '/admin',
      name: 'admin',
      component: () => import('@/views/admin/AdminView.vue'),
      children: [
        { path: '', redirect: 'home' },
        {
          path: 'home',
          name: 'home',
          component: () => import('@/views/admin/home/HomeView.vue')
        },
        {
          path: 'dashboard',
          name: 'dashboard'
          // component: () => import('@/views/admin/DashboardComponent.vue')
        },
        {
          path: 'accounts',
          name: 'accounts'
          // component: () => import('@/views/admin/AccountsComponent.vue')
        },
        {
          path: 'beneficiaries',
          name: 'beneficiaries'
          // component: () => import('@/views/admin/BeneficiaryComponent.vue')
        },
        {
          path: 'users',
          name: 'users'
          // component: () => import('@/views/admin/UserComponent.vue')
        },
        {
          path: 'measurers/:beneficiaryId',
          name: 'measurers'
          // component: () => import('@/views/admin/MeasurerComponent.vue')
        },
        {
          path: 'services',
          name: 'services'
          // component: () => import('@/views/admin/ServiceComponent.vue')
        },
        {
          path: 'reports',
          name: 'reports'
          // component: () => import('@/views/admin/MainReports.vue')
        },
        {
          path: 'settings',
          name: 'adminsettings'
          // component: () => import('@/views/admin/AdminSettingsComponent.vue')
        },
        {
          path: 'help',
          name: 'adminhelp'
          // component: () => import('@/views/admin/AdminHelpComponent.vue')
        },
        {
          path: '*',
          name: 'notfound'
          // component: () => import('@/views/admin/NotFound.vue')
        }
      ]
    }
  ]
})
