import Page from '@/components/widget/page/Page'
import Component from 'vue-class-component'
import Assigned from '@/model/entity/Assigned'
import Uptake from '@/model/entity/Uptake'
import FindBeneficiary from '@/components/beneficiary/FindBeneficiary.vue'
import InfoBeneficiary from '@/components/beneficiary/InfoBeneficiary.vue'
import InvoiceSap from '@/model/entity/InvoiceSap'
import Beneficiary from '@/model/entity/Beneficiary'
import MeasurerService from '@/model/service/MeasurerService'
import Measurer from '@/model/entity/Measurer'

@Component({
  name: 'invoice-sap-view',
  components: {
    FindBeneficiary,
    InfoBeneficiary
  }
})
export default class InvoiceSapView extends Page {
  dialogTransferMeasurer: boolean = false
  dialogNewMeasurer: boolean = false
  public invoice: InvoiceSap = new InvoiceSap()
  public measurers: Measurer[] = []
  public measurer: Measurer = new Measurer()
  drawer: boolean = false
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
  assigned: Assigned = new Assigned()
  uptakes: Uptake[] = []

  public setInvoice (beneficiary: Beneficiary): void {
    if (beneficiary === undefined) {
      this.invoice = new InvoiceSap()
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
          this.measurers = res.data
          this.drawer = true
        }

        this.invoice.beneficiary = beneficiary
      })
      .catch(() => {
        this.error('Error al cargar.')
      })
  }
  public get getTotal () {
    let total: number = 0
    for (let item of this.invoice.detail) {
      total = total + item.totalPrice
    }
    return total
  }
}
