import Page from '@/components/widget/page/Page'
import Component from 'vue-class-component'
import { Prop, Watch, Emit } from 'vue-property-decorator'

import Beneficiary from '@/model/entity/Beneficiary'
import Sap from '@/model/entity/Sap'
import Assigned from '@/model/entity/Assigned'
import SapService from '@/model/service/SapService'

import FrameWidget from '@/components/widget/frame/FrameWidget.vue'
import AssignedSimple from '@/model/entity/AssignedSimple'
import AssignedService from '@/model/service/AssignedService'
import FindSap from '@/components/sap/FindSap'

@Component({
  name: 'new-measurer',
  components: {
    FrameWidget,
    FindSap
  }
})

export default class NewMeasurer extends Page {
    @Prop({}) beneficiary!: Beneficiary;
    sapList: Sap[] = [];
    assigned: Assigned = new Assigned();
    selected: Sap = new Sap()
    menu: boolean = false
    date: any = new Date().toISOString().substr(0, 10)

    @Watch('beneficiary')
    onChangeBeneficiary () {
      this.assigned.beneficiary = this.beneficiary
    }

    @Emit('close')
    emiteClose (): void { }

    @Emit('saved')
    emiteSaved (): number {
      return this.assigned.beneficiary.id
    }

    setSap (sap: Sap) {
      this.selected = sap
    }

    save (): void {
      let service: AssignedService = new AssignedService(this.getUser().id)
      let assignedSimple: AssignedSimple = new AssignedSimple()
      assignedSimple.measurer.sap = this.selected
      assignedSimple.assignmentDate = new Date().toISOString().substr(0, 10)
      assignedSimple.measurer.installationDate = this.date
      this.assigned.assigneds = []
      this.assigned.assigneds.push(assignedSimple)

      service.postNew(this.assigned)
        .then((res: any) => {
          if (res.data.saved === true) {
            this.success('Medidor asignado')
            this.emiteSaved()
            this.emiteClose()
          }
        })
        .catch((err: any) => {
          this.error('Medidor sin assignar')
          console.log(err)
        })
    }
}
