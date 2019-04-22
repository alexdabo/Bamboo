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
        {
          path: 'home',
          name: 'home',
          component: () => import('@/views/admin/home/HomeView.vue')
        },
        {
          path: 'dashboard',
          name: 'dashboard',
          component: () => import('@/views/admin/dashboard/DashboardView.vue')
        },
        {
          path: 'accounts',
          name: 'accounts'
          // component: () => import('@/views/admin/AccountsComponent.vue')
        },
        {
          path: 'beneficiaries',
          name: 'beneficiaries',
          component: () => import('@/views/admin/beneficiary/BeneficiaryView.vue')
        },
        {
          path: 'users',
          name: 'users',
          component: () => import('@/views/admin/user/UserView.vue')
        },
        {
          path: 'measurers/:beneficiaryId',
          name: 'measurers',
          component: () => import('@/views/admin/measurer/MeasurerView.vue')
        },
        {
          path: 'services',
          name: 'services',
          component: () => import('@/views/admin/service/ServiceView.vue')
        },
        {
          path: 'reports',
          name: 'reports',
          component: () => import('@/views/admin/reports/ReportView.vue')
        },
        {
          path: 'villages',
          name: 'villages',
          component: () => import('@/views/admin/village/VillageView.vue')
        },
        {
          path: 'settings',
          name: 'adminsettings',
          component: () => import('@/views/admin/settings/AdminSettingsView.vue')
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
