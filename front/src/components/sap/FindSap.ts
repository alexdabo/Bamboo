import Page from '@/components/widget/page/Page'
import Component from 'vue-class-component'
import { Prop, Watch, Emit } from 'vue-property-decorator'

import Sap from '@/model/entity/Sap'
import SapService from '@/model/service/SapService'

@Component({ name: 'find-sap' })

export default class FindSap extends Page {
  @Prop({ default: 'object' }) public return!: string;
  @Prop({ default: 'Buscar SAP' }) public label!: string;
  @Prop() public code!: number;
  @Prop() public show!: Sap;
  @Prop({ default: false }) public readonly!: boolean;
  @Prop({ default: false }) public initialized!: boolean;
  public isLoading: boolean = false;
  public search: any = null;
  public selected: any = null;
  public saps: Sap[] = [];

  public created (): void {
    if ((this.code !== undefined) && this.code > 0) {
      this.findById()
    }
    if (this.initialized === true) {
      this.find()
    }
  }

  @Watch('show')
  public showSap (): void {
    if (this.show !== undefined) {
      this.saps = []
      this.saps.push(this.show)
      this.selected = this.show
    }
  }

  public filter (item: any, queryText: any): any {
    const result =
      item.name.toLowerCase().indexOf(queryText.toLowerCase()) > -1
    return result
  }

  public findById (): void {
    this.isLoading = true
    const service: SapService = new SapService()
    service.getById(this.code).then((res: any) => {
      this.saps.push(res.data)
      this.selected = res.data
    }).catch((err) => {
      this.error('Error al buscar beneficiarios', err.response.data.error)
    })
      .finally(() => (this.isLoading = false))
  }

  @Watch('search')
  public find (): void {
    if (this.saps.length === 1) {
      this.saps = []
    }
    if (this.saps.length > 0) { return }

    this.isLoading = true
    const service: SapService = new SapService(1)
    service.getAll().then((res) => {
      this.saps = res.data
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

        default:
          element = new Sap()
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
