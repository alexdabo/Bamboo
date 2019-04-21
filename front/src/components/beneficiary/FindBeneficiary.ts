import Page from '@/components/widget/page/Page'
import Component from 'vue-class-component'
import { Prop, Watch, Emit } from 'vue-property-decorator'

import Beneficiary from '@/model/entity/Beneficiary'
import BeneficiaryService from '@/model/service/BeneficiaryService'

@Component({ name: 'find-beneficiary' })

export default class FindBeneficiary extends Page {
  @Prop({ default: 'object' }) public return!: string;
  @Prop({ default: 'Buscar beneficiario' }) public label!: string;
  @Prop() public code!: number;
  @Prop() public show!: Beneficiary;
  @Prop({ default: false }) public clearable!: boolean;
  @Prop({ default: false }) public readonly!: boolean;
  public isLoading: boolean = false;
  public search: any = null;
  public selected: any = null;
  public beneficiaries: Beneficiary[] = [];

  public created (): void {
    if ((this.code !== undefined) && this.code > 0) {
      this.findById()
    }
  }

  @Watch('show')
  public showBeneficiary (): void {
    if (this.show !== undefined) {
      this.beneficiaries = []
      this.beneficiaries.push(this.show)
      this.selected = this.show
    }
  }

  public filter (item: any, queryText: any): any {
    const result =
      item.dni.toLowerCase().indexOf(queryText.toLowerCase()) > -1 ||
      item.lastName.toLowerCase().indexOf(queryText.toLowerCase()) > -1 ||
      item.firstName.toLowerCase().indexOf(queryText.toLowerCase()) > -1
    return result
  }

  public findById (): void {
    this.isLoading = true
    const service: BeneficiaryService = new BeneficiaryService()
    service.getById(this.code).then((res: any) => {
      this.beneficiaries.push(res.data)
      this.selected = res.data
    }).catch((err) => {
      this.error('Error al buscar beneficiarios', err.response.data.error)
    })
      .finally(() => (this.isLoading = false))
  }

  @Watch('search')
  public find (): void {
    if (this.beneficiaries.length === 1) {
      this.beneficiaries = []
    }
    if (this.beneficiaries.length > 0) { return }

    this.isLoading = true
    const service: BeneficiaryService = new BeneficiaryService(1)
    service.getAll().then((res) => {
      this.beneficiaries = res.data
    }).catch((err) => {
      this.error('Error al buscar beneficiarios', err.response.data.error)
    })
      .finally(() => (this.isLoading = false))
  }
  @Emit('selected')
  public returnSelected () {
    let element: any
    if (this.selected !== undefined) {
      switch (this.return) {
        case 'object':
          element = this.selected
          break
        case 'id':
          element = this.selected.id
          break
        case 'dni':
          element = this.selected.dni
          break

        default:
          element = new Beneficiary()
          break
      }
    }
    return element
  }

  @Watch('selected')
  public onChangeSelected () {
    this.returnSelected()
  }
}
