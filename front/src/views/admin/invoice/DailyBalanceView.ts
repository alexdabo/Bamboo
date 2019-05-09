import Page from '@/components/widget/page/Page'
import Component from 'vue-class-component'
import { Watch } from 'vue-property-decorator'
import DateWidget from '@/components/widget/date/DateWidget'
import InvoiceService from '@/model/service/BalanceService'

@Component({
  name: 'daily-balance-view',
  components: { DateWidget }
})
export default class DailyBalanceView extends Page {
  public date: any = null
  public invoices: any = [];
  public dialog: boolean = false;
  public tipo: string='Servicio de Agua Potable'

  public headers: any[] = [
    { text: 'Número de Factura', value: 'invoiceId', align: 'left' },
    { text: 'Beneficiario', value: 'beneficiary.lastName' },
    { text: 'Responsable', value: 'debtcollector.firstName' },
    { text: 'Monto', value: 'totalToPay' }
  ];

  @Watch('date')
  public findInvoices (): void {
    const invoiceService = new InvoiceService(this.getUser().id)
    this.invoices = []
    invoiceService.getByDate(this.date).then((res: any) => {
      this.invoices = res.data
    }).catch(() => {
      this.error('Error al buscar Facturas')
    })
  }

  public get dialyTotal (): number {
    let total:number = 0
    for (let item of this.invoices) {
      total += item.totalToPay
    }
    return total
  }
}
