import Page from '@/components/widget/page/Page'
import Component from 'vue-class-component'
import sweetalert from 'sweetalert'
import DateWidget from '@/components/widget/date/DateWidget'
import Invoice from '@/model/entity/InvoiceSap'
import InvoiceService from '@/model/service/InvoiceSapService'

@Component({
  name: 'daily-balance-view',
  components: { DateWidget }
})
export default class DailyBalanceView extends Page {
  public date: any = null
  public invoices: Invoice[] = [];
  public dialog: boolean = false;
  public tipo: string='Servicio de Agua Potable'

  public headers: any[] = [
    { text: 'Número de Factura', value: 'invoiceId', align: 'left' },
    { text: 'Beneficiario', value: 'beneficiary.lastName' },
    { text: 'Responsable', value: 'debtcollector.firstName' },
    { text: 'Tipo de Factura', value: this.tipo },
    { text: 'Monto', value: 'totalToPay' }
  ];

  public created (): void {
    this.findInvoices()
  }

  public findInvoices (): void {
    const invoiceService = new InvoiceService(this.getUser().id)
    this.invoices = []
    // invoiceService.getByDate(this.date).then((res: any) => {
    invoiceService.getById(1).then((res: any) => {
      console.log(this.invoices)
      // this.invoices=res.data
      this.invoices.push(res.data)
    }).catch(() => {
      this.error('Error al buscar Facturas')
      console.log('no se pudo encontrar facturas' + this.invoices)
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
