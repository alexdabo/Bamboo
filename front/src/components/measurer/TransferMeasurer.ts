import Page from '@/components/widget/page/Page'
import Component from 'vue-class-component'
import Assigned from '@/model/entity/Assigned'
import AssignedService from '@/model/service/AssignedService'
import Beneficiary from '@/model/entity/Beneficiary'
import { Prop, Watch, Emit } from 'vue-property-decorator'
import Swal from 'sweetalert'
import AssignedSimple from '@/model/entity/AssignedSimple'

import FrameWidget from '@/components/widget/frame/FrameWidget.vue'
import FindBeneficiary from '@/components/beneficiary/FindBeneficiary.vue'
import InfoBeneficiary from '@/components/beneficiary/InfoBeneficiary.vue'

@Component({
  name: 'transfer-measurer',
  components: {
    FrameWidget,
    FindBeneficiary,
    InfoBeneficiary
  }
})

export default class TransferMeasurer extends Page {
    @Prop({}) public beneficiary!: Beneficiary;
    public currentAssigned: Assigned = new Assigned();
    public newAssigned: Assigned = new Assigned();

    @Watch('beneficiary')
    public setBeneficiary () {
      this.newAssigned.beneficiary = this.beneficiary
    }

    @Emit('close')
    public emiteClose () { return true }

    @Emit('saved')
    public emiteSaved () { return this.newAssigned.beneficiary.id }

    public findAssigned (beneficiaryId: number): void {
      if (beneficiaryId === undefined) {
        this.currentAssigned = new Assigned()
        this.newAssigned.assigneds = []
        return
      }
      const service: AssignedService = new AssignedService(1)
      service.getByBeneficiary(beneficiaryId)
        .then((res: any) => {
          this.currentAssigned = res.data
        }).catch((err: any) => {
          this.error('Error al cargar los datos', err.response.data.error)
        })
    }

    public toCurrent (element: AssignedSimple): void {
      this.currentAssigned.assigneds.push(element)
      const index = this.newAssigned.assigneds.indexOf(element)
      this.newAssigned.assigneds.splice(index, 1)
    }
    public toNew (element: any): void {
      if (!(element.debt > 0)) {
        if (element.status === 'disable') {
          const swal: any = {
            title: '¿Está seguro?',
            text: `El medidor con número ${element.measurer.number} ` +
                        `está sin servicio, puede pertenecer a otro beneficiario.`,
            icon: 'warning',
            buttons: true,
            dangerMode: true
          }

          Swal(swal)
            .then((willDelete) => {
              if (willDelete) {
                this.newAssigned.assigneds.push(element)
                const index = this.currentAssigned.assigneds.indexOf(element)
                this.currentAssigned.assigneds.splice(index, 1)
              }
            })
        } else {
          this.newAssigned.assigneds.push(element)
          const index = this.currentAssigned.assigneds.indexOf(element)
          this.currentAssigned.assigneds.splice(index, 1)
        }
      } else {
        const swalConf: any = {
          title: 'Error!',
          text: `El medidor con número ${element.measurer.number} tiene una deuda pendiente de $${element.debt}.`,
          icon: 'warning',
          button: 'Aceptar',
          dangerMode: true
        }
        Swal(swalConf)
      }
    }

    public save (): void {
      const service: AssignedService = new AssignedService(this.getUser().id)
      if (this.currentAssigned.beneficiary.id !== undefined) {
        if (this.newAssigned.beneficiary.id !== undefined) {
          if (this.currentAssigned.beneficiary.id !== this.newAssigned.beneficiary.id) {
            const swal: any = {
              title: '¿Está seguro?',
              text: 'El medidor será transferido del beneficiario ' +
                            `${this.currentAssigned.beneficiary.lastName} ` +
                            `${this.currentAssigned.beneficiary.firstName} ` +
                            `al beneficiario ` +
                            `${this.newAssigned.beneficiary.lastName}` +
                            ` ${this.newAssigned.beneficiary.firstName}`,
              icon: 'warning',
              buttons: true,
              dangerMode: true
            }

            Swal(swal)
              .then((willDelete) => {
                if (willDelete) {
                  for(let item of this.newAssigned.assigneds){
                    item.id=undefined
                  }
                  service.postTransfer(this.newAssigned)
                    .then((res: any) => {
                      console.log('res')
                      console.log(res.data)
                      this.success('Transferencia exitosa.')
                      this.emiteSaved()
                      this.emiteClose()
                    })
                    .catch((err: any) => {
                      this.error('Medidor sin assignar', err.response.data.error)
                    })
                }
              })
          } else {
            this.error('Medidor sin assignar', 'No se puede asignar el medidor al mismo beneficiario.')
          }
        } else {
          this.error('Nuevo propietario indefinido')

          this.emiteClose()
        }
      } else {
        this.error('Propietario actual indefinido')
      }
    }
}
