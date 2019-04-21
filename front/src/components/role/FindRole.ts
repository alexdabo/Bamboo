import Page from '@/components/widget/page/Page'
import Component from 'vue-class-component'
import { Prop, Watch, Emit } from 'vue-property-decorator'

import Role from '@/model/entity/Role'
import RoleService from '@/model/service/RoleService'

@Component({ name: 'find-role' })

export default class FindRole extends Page {
  @Prop({ default: 'object' }) public return!: string;
  @Prop({ default: 'Buscar rol de usuario' }) public label!: string;
  @Prop() public code!: number;
  @Prop() public show!: Role;
  @Prop({ default: false }) public readonly!: boolean;
  @Prop({ default: false }) public initialized!: boolean;
  @Prop({ default: false }) public solo!: boolean;
  public isLoading: boolean = false;
  public search: any = null;
  public selected: any = null;
  public roles: Role[] = [];

  public created (): void {
    if ((this.code !== undefined) && this.code > 0) {
      this.findById()
    }
    if (this.initialized === true) {
      this.find()
    }
  }

  @Watch('show')
  public showRole (): void {
    if (this.show !== undefined) {
      this.roles = []
      this.roles.push(this.show)
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
    const service: RoleService = new RoleService()
    service.getById(this.code).then((res: any) => {
      this.roles.push(res.data)
      this.selected = res.data
    }).catch((err) => {
      this.error('Error al buscar beneficiarios', err.response.data.error)
    })
      .finally(() => (this.isLoading = false))
  }

  @Watch('search')
  public find (): void {
    if (this.roles.length === 1) {
      this.roles = []
    }
    if (this.roles.length > 0) { return }

    this.isLoading = true
    const service: RoleService = new RoleService(1)
    service.getAll().then((res) => {
      this.roles = res.data
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
          element = new Role()
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
