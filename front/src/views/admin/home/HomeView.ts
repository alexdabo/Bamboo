import Vue from 'vue'
import Component from 'vue-class-component'

interface Item {
    imageName: string;
    title: string;
    routerName: string;
}

@Component({ name: 'view-home' })

export default class HomeView extends Vue {
    public viewItems: Item[] = [
      { imageName: 'dashboard', title: 'Dashboard', routerName: 'dashboard' },
      { imageName: 'invoice', title: 'Cuentas', routerName: 'balance' },
      { imageName: 'user', title: 'Usuarios', routerName: 'users' },
      { imageName: 'beneficiaries', title: 'Beneficiarios', routerName: 'beneficiaries' },
      { imageName: 'measurer', title: 'Medidores', routerName: 'measurers' },
      { imageName: 'services', title: 'Servicios', routerName: 'serviceSap' },
      { imageName: 'reports', title: 'Reportes', routerName: 'reports' }
    ];

    public changeView (routerName: string): void {
      if (routerName === 'measurers') {
        this.$router.push({ name: routerName, params: { beneficiaryId: '0' } })
      }
      this.$router.push({ name: routerName })
    }

    public getImgUrl (name: string): any {
      if (name === undefined) return null
      var images = require.context(
        '@/assets/image/home/',
        false,
        /\.png$/
      )
      return images('./' + name + '.png')
    }
}
