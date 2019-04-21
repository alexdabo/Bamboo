import Page from '@/components/widget/page/Page'
import Component from 'vue-class-component'
import sweetalert from 'sweetalert'
import Another from '@/model/entity/Another'
import AnotherService from '@/model/service/AnotherService'
@Component({ name: 'view-another' })

export default class AnotherView extends Page {
  public anothers: Another[] = [];
  public search: string = '';
  public dialog: boolean = false;
  public editedIndex: number = -1;
  public editedItem: Another = new Another();
  public headers: any[] = [
    { text: 'Nombre del servicio', value: 'name', align: 'left' },
    { text: 'Precio', value: 'price', align: 'left' },
    { text: 'Acciones', align: 'center', sortable: false }
  ];

  public created (): void {
    this.findAnothers()
  }

  public findAnothers (): void {
    const anotherService: AnotherService = new AnotherService()
    this.anothers = []
    anotherService.getAll().then((res: any) => {
      this.anothers = res.data
    }).catch((err) => {
      this.error('Error al buscar los servicios', err.response.data.error)
    })
  }

  public save (): void {
    const anotherService: AnotherService = new AnotherService(this.getUser().id)
    anotherService.post(this.editedItem).then((res) => {
      if (res.data.saved === true) {
        this.anothers.push(res.data.anotherService)
        this.success('Servicio guardado')
        this.close()
      }
    }).catch((err) => {
      this.error('Servicio sin guardar', err.response.data.error)
    })
  }

  public update (): void {
    const anotherService: AnotherService = new AnotherService(this.getUser().id)
    anotherService.put(this.editedItem).then((res) => {
      if (res.data.updated === true) {
        this.anothers[this.editedIndex] = this.editedItem
        this.success('Servicio actualizado')
        this.close()
      }
    }).catch((err) => {
      this.error('Servicio sin actualizar', err.response.data.error)
    })
  }

  public submit (): void {
    if (this.editedIndex > -1) {
      this.update()
    } else {
      this.save()
    }
  }

  public remove (another: Another): void {
    const anotherService: AnotherService = new AnotherService(this.getUser().id)
    const conf: any = {
      buttons: true,
      title: '¿Está seguro?',
      text: `Una vez que se elimine, ¡no podrá recuperar el registro de ${another.name}!`,
      icon: 'warning',
      dangerMode: true
    }

    sweetalert(conf).then((willDelete) => {
      if (willDelete) {
        anotherService.delete(another).then((res) => {
          if (res.data.deleted === true) {
            const index = this.anothers.indexOf(another)
            this.anothers.splice(index, 1)
            this.success('Servicio eliminado')
          }
        }).catch((err) => {
          this.error('Servicio sin eliminar', err.response.data.error)
        })
      }
    })
  }

  public editItem (another: Another): void {
    this.editedIndex = this.anothers.indexOf(another)
    this.editedItem = another
    this.dialog = true
  }

  public close (): void {
    this.dialog = false
    setTimeout(() => {
      this.editedItem = new Another()
      this.editedIndex = -1
    }, 300)
  }
}
