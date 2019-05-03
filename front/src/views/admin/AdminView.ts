import Vue from 'vue'
import Component from 'vue-class-component'

interface Route {
  icon?: string;
  title: string;
  routerName?: string;
}

interface Item {
  icon: string;
  title: string;
  routerName?: string;
  children?: Route[]
}

@Component({ name: 'admin-view' })
export default class AdminView extends Vue {
  public drawer: boolean = true;
  public sideBarItems: Item[] = [
    { icon: 'home', title: 'Principal', routerName: 'home' },
    { icon: 'dashboard', title: 'Dashboard', routerName: 'dashboard' },
    {
      icon: 'account_balance_wallet',
      title: 'Cuentas',
      children: [
        { title: 'Balance Diario', routerName: 'balance' },
        { title: 'Factura SAP', routerName: 'invoiceSap' },
        { title: 'Factura Otos Servicios', routerName: 'invoiceAnother' }
      ]
    },
    { icon: 'person', title: 'Usuarios', routerName: 'users' },
    { icon: 'people', title: 'Beneficiarios', routerName: 'beneficiaries' },
    { icon: 'timer', title: 'Medidores', routerName: 'measurers' },
    {
      icon: 'format_list_bulleted',
      title: 'Servicios',
      children: [
        { title: 'SAP', routerName: 'serviceSap' },
        { title: 'Otros', routerName: 'serviceAnother' }
      ]
    },
    { icon: 'map', title: 'Comunidades', routerName: 'villages' },
    { icon: 'description', title: 'Reportes', routerName: 'reports' }
  ];

  public optionItems: Item[] = [
    { icon: 'settings', title: 'Configuraciones', routerName: 'adminsettings' },
    { icon: 'info', title: 'Ayuda', routerName: 'adminhelp' },
    { icon: 'logout', title: 'Salir', routerName: 'login' }
  ];

  created () {
    switch (localStorage.lastRouteName) {
      case 'measurers':
        this.$router.push({ name: localStorage.lastRouteName, params: { beneficiaryId: '0' } })
        break
      default:
        this.$router.push({ name: localStorage.lastRouteName })
        break
    }
  }

  public changeView (routerName: string): void {
    switch (routerName) {
      case 'measurers':
        this.$router.push({ name: routerName, params: { beneficiaryId: '0' } })
        break

      case 'login':
        localStorage.removeItem('user')
        localStorage.logged = false
        this.$router.push({ name: routerName })

        break

      default:
        this.$router.push({ name: routerName })
        break
    }
  }
}
