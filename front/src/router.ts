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
          path: 'invoice/sap',
          name: 'invoiceSap',
          component: () => import('@/views/admin/invoicesap/InvoiceSapView.vue')
        },
        {
          path: 'invoice/another',
          name: 'invoiceAnother',
          component: () => import('@/views/admin/invoiceanother/InvoiceAnotherView.vue')
        },
        {
          path: 'invoice/balance',
          name: 'balance',
          component: () => import('@/views/admin/invoice/DailyBalanceView.vue')
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
          path: 'uptake',
          name: 'adminUptake',
          component: () => import('@/views/admin/uptake/UptakeView.vue')
        },
        {
          path: 'service/sap',
          name: 'serviceSap',
          component: () => import('@/views/admin/sap/SapView.vue')
        },
        {
          path: 'service/another',
          name: 'serviceAnother',
          component: () => import('@/views/admin/another/AnotherView.vue')
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
          path: 'help',
          name: 'adminhelp',
          component: () => import('@/views/admin/help/AdminHelpView.vue')
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
