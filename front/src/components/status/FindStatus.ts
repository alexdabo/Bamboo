import Page from '@/components/widget/page/Page'
import Component from 'vue-class-component'
import { Prop, Watch, Emit } from 'vue-property-decorator'

import Status from '@/model/entity/Status'
import StatusService from '@/model/service/StatusService'

@Component({ name: 'find-status' })

export default class FindStatus extends Page {
  @Prop({ default: 'object' }) public return!: string;
  @Prop({ default: 'Buscar estado de medidor' }) public label!: string;
  @Prop() public code!: number;
  @Prop() public show!: Status;
  @Prop({ default: false }) public readonly!: boolean;
  @Prop({ default: false }) public initialized!: boolean;
  @Prop({ default: false }) public solo!: boolean;
  public isLoading: boolean = false;
  public search: any = null;
  public selected: any = null;
  public statusList: Status[] = [];

  public created (): void {
    if ((this.code !== undefined) && this.code > 0) {
      this.findById()
    }
    if (this.initialized === true) {
      this.find()
    }
  }

  @Watch('show')
  public showStatus (): void {
    if (this.show !== undefined) {
      this.statusList = []
      this.statusList.push(this.show)
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
    const service: StatusService = new StatusService()
    service.getById(this.code).then((res: any) => {
      this.statusList.push(res.data)
      this.selected = res.data
    }).catch((err) => {
      this.error('Error al buscar beneficiarios', err.response.data.error)
    })
      .finally(() => (this.isLoading = false))
  }

  @Watch('search')
  public find (): void {
    if (this.statusList.length === 1) {
      this.statusList = []
    }
    if (this.statusList.length > 0) { return }

    this.isLoading = true
    const service: StatusService = new StatusService(1)
    service.getAll().then((res) => {
      this.statusList = res.data
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
          element = new Status()
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
