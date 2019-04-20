import Page from '@/components/widget/page/Page'
import Component from 'vue-class-component'
import Entity from '@/model/entity/Entity'
import EntityService from '@/model/service/EntityService'

@Component({ name: 'entity-component' })

export default class EntityComponent extends Page {
  public entity: Entity = new Entity();

  created() {
    this.getEntity()
  }
  public getEntity(): void {
    const entityService: EntityService = new EntityService()
    entityService.get()
      .then((res: any) => {
        this.entity = res.data
      })
      .catch(() => {
        this.error('Error al cargar información')
      })
  }
  public save(): void {
    const entityService: EntityService = new EntityService(this.getUser().id)
    entityService.put(this.entity)
      .then((res: any) => {
        if (res.data.updated === true) {
          this.success('Información actualizada')
        }
      })
      .catch((err: any) => {
        this.error('Información sin guardar', err.response.data.errorText)
      })
  }
}
