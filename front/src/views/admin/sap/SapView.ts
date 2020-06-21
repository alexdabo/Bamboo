import Page from '@/components/widget/page/Page'
import Component from 'vue-class-component'
// @ts-ignore
import sweetalert from 'sweetalert'
import Sap from '@/model/entity/Sap'
import SapService from '@/model/service/SapService'

@Component({ name: 'view-sap' })

export default class SapView extends Page {
    public saps: Sap[] = [];
    public search: string = '';
    public dialog: boolean = false;
    public editedIndex: number = -1;
    public editedItem: Sap = new Sap();
    public headers: any[] = [
      { text: 'Nombre del servicio', value: 'name', align: 'left' },
      { text: 'Volume Base', value: 'baseVolume', align: 'left' },
      { text: 'Precio Base', value: 'basePrice' },
      { text: 'Precio extra', value: 'extraPrice' },
      { text: 'Acciones', sortable: false }
    ];

    public created (): void {
      this.findSaps()
    }

    public findSaps (): void {
      const sapService: SapService = new SapService()
      this.saps = []
      sapService.getAll().then((res: any) => {
        this.saps = res.data
      }).catch((err: any) => {
        this.error('Error al buscar Servicios', err.response.data.error)
      })
    }

    public save (): void {
      const sapService: SapService = new SapService(this.getUser().id)
      sapService.post(this.editedItem).then((res: any) => {
        if (res.data.saved === true) {
          this.saps.push(res.data.sap)
          this.success('Servicio guardado')
          this.close()
        }
      }).catch((err: any) => {
        this.error('Servicio sin guardar', err.response.data.error)
      })
    }

    public update (): void {
      const sapService: SapService = new SapService(this.getUser().id)
      sapService.put(this.editedItem).then((res: any) => {
        if (res.data.updated === true) {
          this.saps[this.editedIndex] = this.editedItem
          this.success('Servicio actualizado')
          this.close()
        }
      }).catch((err: any) => {
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

    public remove (sap: Sap): void {
      const sapService: SapService = new SapService(this.getUser().id)
      const conf: any = {
        buttons: true,
        title: '¿Está seguro?',
        text: `Una vez que se elimine, ¡no podrá recuperar el registro de ${sap.name}!`,
        icon: 'warning',
        dangerMode: true
      }

      sweetalert(conf).then((willDelete:any) => {
        if (willDelete) {
          sapService.delete(sap).then((res: any) => {
            if (res.data.deleted === true) {
              const index = this.saps.indexOf(sap)
              this.saps.splice(index, 1)
              this.success('Servicio eliminado')
            }
          }).catch((err: any) => {
            this.error('Servicio sin eliminar', err.response.data.error)
          })
        }
      })
    }

    public editItem (sap: Sap): void {
      this.editedIndex = this.saps.indexOf(sap)
      this.editedItem = sap
      this.dialog = true
    }

    public close (): void {
      this.dialog = false
      setTimeout(() => {
        this.editedItem = new Sap()
        this.editedIndex = -1
      }, 300)
    }
}
