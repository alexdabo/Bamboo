import Vue from 'vue'
import Component from 'vue-class-component'

interface Item {
  icon: string;
  title: string;
  name: string;
}

@Component({ name: 'admin-view' })
export default class AdminView extends Vue {
  public drawer: boolean = true;
  public sideBarItems: Item[] = [
    { icon: 'home', title: 'Principal', name: 'home' },
    { icon: 'dashboard', title: 'Dashboard', name: 'dashboard' },
    { icon: 'account_balance_wallet', title: 'Cuentas', name: 'accounts' },
    { icon: 'person', title: 'Usuarios', name: 'users' },
    { icon: 'people', title: 'Beneficiarios', name: 'beneficiaries' },
    { icon: 'timer', title: 'Medidores', name: 'measurers' },
    { icon: 'format_list_bulleted', title: 'Servicios', name: 'services' },
    { icon: 'description', title: 'Reportes', name: 'reports' }
  ];
  public optionItems: Item[] = [
    { icon: 'settings', title: 'Configuraciones', name: 'adminsettings' },
    { icon: 'info', title: 'Ayuda', name: 'adminhelp' },
    { icon: 'logout', title: 'Salir', name: 'login' }
  ];

  public changeView(name: string): void {
    if (name === 'measurers') {
      this.$router.push({ name: name, params: { beneficiaryId: '0' } });
    }
    this.$router.push({ name })
  }
}
