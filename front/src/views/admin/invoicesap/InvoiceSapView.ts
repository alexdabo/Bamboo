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
    { text: 'Mes', value: 'dateTaked' },
    { text: 'Valor marcado', value: 'measurer.sap.name' },
    { text: 'Volume base', value: 'measurer.installationDate' },
    { text: 'Precio base', value: 'measurer.status.name' },
    { text: 'Precio extra', value: 'debt', align: 'center' },
    { text: 'Volumen mensual', sortable: false, align: 'center' },
    { text: 'Excedente', sortable: false, align: 'center' },
    { text: 'Total', sortable: false, align: 'center' }
  ]

  public findUptakes (beneficiary: Beneficiary): void {
    if (beneficiary === undefined) {
      this.invoice = new InvoiceSap()
      this.invoices = []
      this.measurer = new Measurer()
      this.measurers = []
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

  public setInvoice (invoice: InvoiceSap): void {
    this.invoice.beneficiary = invoice.beneficiary
    this.invoice.dateOfIssue = invoice.dateOfIssue
    this.invoice.debtcollector = invoice.debtcollector
    this.invoice.detail = invoice.detail
    this.invoice.id = invoice.id
    this.invoice.invoiceId = invoice.invoiceId
    this.invoice.number = invoice.number
    this.invoice.payed = invoice.payed
    this.invoice.totalToPay = invoice.totalToPay
  }

  public setDetail (uptakes: Uptake[]): void {
    this.invoice.detail = []
    for (let uptake of uptakes) {
      this.invoice.detail.push(uptake)
    }
    this.invoice.payed = false
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
    // en caso de que se carge una factura antes
    this.invoice.id = undefined
    this.invoice.number = undefined
    this.invoice.invoiceId = undefined

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
        this.invoice.payed = false
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
    return this.round(total)
  }

  public get getTotal () {
    this.invoice.totalToPay = this.totalToPay()
    return this.totalToPay()
  }
  public round (value: any) {
    return parseFloat(value.toFixed(2))
  }

  public getDate (date: string): string {
    date = date.substr(0, 7)
    switch (date.substr(5, 6)) {
      case '01':
        date = `Enero ${date.substr(0, 4)}`
        break
      case '02':
        date = `Febrero ${date.substr(0, 4)}`
        break
      case '03':
        date = `Marzo ${date.substr(0, 4)}`
        break
      case '04':
        date = `Abril ${date.substr(0, 4)}`
        break
      case '05':
        date = `Mayo ${date.substr(0, 4)}`
        break
      case '06':
        date = `Junio ${date.substr(0, 4)}`
        break
      case '07':
        date = `Julio ${date.substr(0, 4)}`
        break
      case '08':
        date = `Agosto ${date.substr(0, 4)}`
        break
      case '09':
        date = `Septiembre ${date.substr(0, 4)}`
        break
      case '10':
        date = `Octubre ${date.substr(0, 4)}`
        break
      case '11':
        date = `Noviembre ${date.substr(0, 4)}`
        break
      case '12':
        date = `Diciembre ${date.substr(0, 4)}`
        break
    }
    return date
  }
}
