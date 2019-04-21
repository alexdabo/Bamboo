import Page from '@/components/widget/page/Page'
import Component from 'vue-class-component'
import sweetalert from 'sweetalert'
import Village from '@/model/entity/Village'
import VillageService from '@/model/service/VillageService'

@Component({ name: 'view-village' })

export default class VillageView extends Page {
  public villages: Village[] = [];
  public search: string = '';
  public dialog: boolean = false;
  public editedIndex: number = -1;
  public editedItem: Village = new Village();
  public headers: any[] = [
    { text: 'Código', value: 'id', align: 'left' },
    { text: 'Nombre del servicio', value: 'name', align: 'left' },
    { text: 'Acciones', align: 'center', sortable: false }
  ];

  public created (): void {
    this.findVillages()
  }

  public findVillages (): void {
    const villageService: VillageService = new VillageService()
    this.villages = []
    villageService.getAll().then((res: any) => {
      this.villages = res.data
    }).catch((err) => {
      this.error('Error al buscar los servicios', err.response.data.error)
    })
  }

  public save (): void {
    const villageService: VillageService = new VillageService(this.getUser().id)
    villageService.post(this.editedItem).then((res) => {
      if (res.data.saved === true) {
        this.villages.push(res.data.village)
        this.success('Comunidad guardada')
        this.close()
      }
    }).catch((err: any) => {
      this.error('Comunidad sin guardar', err.response.data.error)
    })
  }

  public update (): void {
    const villageService: VillageService = new VillageService(this.getUser().id)
    villageService.put(this.editedItem).then((res) => {
      if (res.data.updated === true) {
        this.villages[this.editedIndex] = this.editedItem
        this.success('Comunidad actualizada')
        this.close()
      }
    }).catch((err) => {
      this.error('Comunidad sin actualizar', err.response.data.error)
    })
  }

  public submit (): void {
    if (this.editedIndex > -1) {
      this.update()
    } else {
      this.save()
    }
  }

  public remove (village: Village): void {
    const villageService: VillageService = new VillageService(this.getUser().id)
    const conf: any = {
      buttons: true,
      title: '¿Está seguro?',
      text: `Una vez que se elimine, ¡no podrá recuperar el registro de ${village.name}!`,
      icon: 'warning',
      dangerMode: true
    }

    sweetalert(conf).then((willDelete) => {
      if (willDelete) {
        villageService.delete(village)
          .then((res: any) => {
            if (res.data.deleted === true) {
              const index = this.villages.indexOf(village)
              this.villages.splice(index, 1)
              this.success('Comunidad eliminada')
            }
          }).catch((err) => {
            this.error('Comunidad sin eliminar', err.response.data.error)
          })
      }
    })
  }

  public editItem (village: Village): void {
    this.editedIndex = this.villages.indexOf(village)
    this.editedItem = village
    this.dialog = true
  }

  public close (): void {
    this.dialog = false
    setTimeout(() => {
      this.editedItem = new Village()
      this.editedIndex = -1
    }, 300)
  }
}
