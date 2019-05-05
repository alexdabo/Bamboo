import Page from '@/components/widget/page/Page'
import Component from 'vue-class-component'
import { Watch, Emit } from 'vue-property-decorator'
import Uptake from '@/model/entity/Uptake'
import Measurer from '@/model/entity/Measurer'
import InfoBeneficiary from '@/components/beneficiary/InfoBeneficiary.vue'
import MeasurerService from '@/model/service/MeasurerService'
import Beneficiary from '@/model/entity/Beneficiary'
import UptakeService from '@/model/service/UptakeService'

@Component({
  name: 'uptake-view',
  components: {
    InfoBeneficiary
  }
})
export default class UptakeView extends Page {
  search: string = ''
  modal: boolean = false
  drawerHistory: boolean = false
  date: string = new Date().toISOString().substr(0, 10)

  measurer: Measurer = new Measurer()
  beneficiary: Beneficiary = new Beneficiary()
  history: any[] = []
  uptake: Uptake = new Uptake()

  @Watch('search')
  public reset (newValue?: string): void {
    if (newValue === '' || newValue === null || newValue === undefined) {
      this.beneficiary = new Beneficiary()
      this.measurer = new Measurer()
      this.history = []
      this.uptake = new Uptake()
      this.drawerHistory = false
    }
  }

  public findMeasurer (): void {
    if (this.search === '') return
    this.reset()
    const measurerService: MeasurerService = new MeasurerService()
    measurerService.getById(this.search)
      .then((res: any) => {
        this.beneficiary = res.data.beneficiary
        this.measurer = res.data.measurer
        this.history = res.data.history
        if (this.history.length > 0) {
          this.drawerHistory = true
        }
      })
      .catch(() => {
        this.error(`Error la cargar`, `Verifique si el medidor N° ${this.search}  existe.`)
      })
  }
  public get lastValue () : number {
    let value: number = 0
    for (let item of this.history) {
      if (value < item.currentValueTaken) {
        value = item.currentValueTaken
      }
    }
    return value
  }
  public disableValue (statusId:number) {
    let isDisabled:boolean = false
    if (statusId === 3) {
      isDisabled = true
      this.uptake.currentValueTaken = this.lastValue
      this.warning('Medidor dañado', 'Solo se puede generar la cuota base.')
    }
    if (statusId === 2) {
      isDisabled = true
      this.error('Servicio suspendido', 'No se puede reguistrar medida.')
    }
    if (statusId === 4) {
      isDisabled = true
      this.error('Sin servicio')
    }
    return isDisabled
  }
  @Emit('measurer')
  returnUptakes (measurerId: number): number {
    return measurerId
  }

  save (): void {
    const uptakeService: UptakeService = new UptakeService(this.getUser().id)
    this.uptake.dateTaked = this.date
    uptakeService.post(this.measurer.id, this.uptake)
      .then((res: any) => {
        this.history.push(res.data.uptake)
        this.drawerHistory = true
        this.success('Medida almacenada')
      })
      .catch((err: any) => {
        console.log(err)
        this.error('Medida no almacenada', err.response.data.error)
      })
  }
}
