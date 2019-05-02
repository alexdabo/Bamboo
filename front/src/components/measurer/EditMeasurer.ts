import Page from '@/components/widget/page/Page'
import Component from 'vue-class-component'
import Assigned from '@/model/entity/Assigned'
import AssignedService from '@/model/service/AssignedService'
import Beneficiary from '@/model/entity/Beneficiary'
import { Prop, Watch, Emit } from 'vue-property-decorator'

import AssignedSimple from '@/model/entity/AssignedSimple'

import FrameWidget from '@/components/widget/frame/FrameWidget.vue'
import FindSap from '../sap/FindSap.vue'
import FindStatus from '../status/FindStatus.vue'
import Sap from '@/model/entity/Sap'
import Status from '@/model/entity/Status'

@Component({
  name: 'edit-measurer',
  components: {
    FrameWidget,
    FindSap,
    FindStatus
  }
})

export default class EditMeasurer extends Page {
    @Prop({}) public assigned!: Assigned;
    public assignedSimple: AssignedSimple = new AssignedSimple();
    public menu: boolean = false;
    public date: any = new Date().toISOString().substr(0, 10);

    @Watch('assigned.beneficiary.id')
    public changeAssigned (): void {
      if (this.assigned.beneficiary.id !== undefined) {
        for (const item of this.assigned.assigneds) {
          this.assignedSimple = item
        }
        if (this.assignedSimple.status === 'enable') {
          // this.selectedStatus = this.assignedSimple.measurer.status;
        } else {
          // this.selectedStatus = new Status(0, 'Sin servicio');
        }
      }
    }

    @Emit('close')
    public emiteClose (): void {
      this.assigned.beneficiary = new Beneficiary()
    }

    @Emit('saved')
    public emiteSaved (): number {
      return this.assigned.beneficiary.id
    }

    public setSap (sap:Sap): void{
      this.assignedSimple.measurer.sap = sap
    }

    public setStatus (status:Status): void {
      this.assignedSimple.measurer.status = status
    }

    public save (): void {
      this.assigned.assigneds = [this.assignedSimple]
      const service: AssignedService = new AssignedService(this.getUser().id)
      service.put(this.assigned)
        .then((res: any) => {
          this.success('Medidor actualizado')
          this.emiteSaved()
          this.emiteClose()
        })
        .catch((err: any) => {
          this.error('Error al editar medidor', err.response.data.error)
        })
    }
}
