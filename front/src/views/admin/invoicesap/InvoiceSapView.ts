import Page from '@/components/widget/page/Page'
import Component from 'vue-class-component'
import swal from 'sweetalert'
import Assigned from '@/model/entity/Assigned'
import Uptake from '@/model/entity/Uptake'
import FindBeneficiary from '@/components/beneficiary/FindBeneficiary.vue'
import InfoBeneficiary from '@/components/beneficiary/InfoBeneficiary.vue'
import InvoiceSap from '@/model/entity/InvoiceSap'
import Beneficiary from '@/model/entity/Beneficiary'
import MeasurerService from '@/model/service/MeasurerService'
import Measurer from '@/model/entity/Measurer'
import InvoiceSapService from '@/model/service/InvoiceSapService'
import InvoiceSapReport from '@/components/invoice/InvoiceSapReport'

@Component({
  name: 'invoice-sap-view',
  components: {
    FindBeneficiary,
    InfoBeneficiary,
    InvoiceSapReport
  }
})
export default class InvoiceSapView extends Page {
  dialogTransferMeasurer: boolean = false
  dialogNewMeasurer: boolean = false
  public invoice: InvoiceSap = new InvoiceSap()
  public invoices: InvoiceSap[] = []
  public measurers: Measurer[] = []
  public measurer: Measurer = new Measurer()
  public drawer: boolean = false

  headers: any[] = [
    { text: 'Fecha de medición', value: 'dateTaked' },
    { text: 'Valor marcado', value: 'measurer.sap.name' },
    { text: 'Volume base', value: 'measurer.installationDate' },
    { text: 'Precio base', value: 'measurer.status.name' },
    { text: 'Precio extra', value: 'debt', align: 'center' },
    { text: 'Volumen mensual', sortable: false, align: 'center' },
    { text: 'Excedente', sortable: false, align: 'center' },
    { text: 'Total', sortable: false, align: 'center' }
  ]

  public setInvoice (beneficiary: Beneficiary): void {
    if (beneficiary === undefined) {
      this.invoice = new InvoiceSap()
      this.invoices = []
      this.measurer = new Measurer()
      this.measurers = []
      this.drawer = false
      return
    }
    const measurerService: MeasurerService = new MeasurerService()
    measurerService.getByBeneficiaryUnbilledUptakes(beneficiary.id)
      .then((res: any) => {
        if (res.data.length === 1) {
          this.invoice.detail = res.data[0].uptakes
        } else {
          this.drawer = true
        }
        this.measurers = res.data
        this.invoice.beneficiary = beneficiary
        this.findInvoices()
      })
      .catch(() => {
        this.error('Error al cargar.')
      })
  }

  public findInvoices (): void {
    const invoiceService: InvoiceSapService = new InvoiceSapService()
    invoiceService.getByBeneficiary(this.invoice.beneficiary.id)
      .then((res: any) => {
        this.invoices = res.data
      })
      .catch((err: any) => { this.error('Error al cargar', err.response.data.error) })
  }

  public setDetail (uptakes: Uptake[]): void {
    this.invoice.detail = []
    for (let uptake of uptakes) {
      this.invoice.detail.push(uptake)
    }
    this.drawer = false
  }

  public chargeInvoice (): void {
    if (this.invoice.payed === true) {
      this.warning('Error al cobrar', 'La factura ya existe.')
      return
    }
    if (this.invoice.detail.length < 1) {
      this.warning('Error al cobrar', 'La factura no poseé detalle.')
      return
    }
    const invoiceService: InvoiceSapService = new InvoiceSapService(this.getUser().id)
    this.invoice.debtcollector = this.getUser()
    this.invoice.totalToPay = this.totalToPay()
    this.invoice.payed = true
    invoiceService.post(this.invoice)
      .then((res: any) => {
        this.success('Factura creada')
        this.invoice = res.data.invoice
        this.invoices.push(res.data.invoice)
      })
      .catch((err: any) => {
        this.error('Error al crear la factura', err.response.data.error)
      })
  }

  public deleteUptake (): void {
    if (this.invoice.detail.length > 1) {
      this.warning('Suspención de pago', 'El pago será supendido temporalmente. ')
      this.invoice.detail.pop()
    } else {
      this.error('Error al suspender pago', 'No se puede crear una factura en blanco.')
    }
  }

  public totalToPay (): number {
    let total: number = 0
    for (let item of this.invoice.detail) {
      total = total + item.totalPrice
    }
    return total
  }

  public get getTotal () {
    return this.totalToPay()
  }
}
