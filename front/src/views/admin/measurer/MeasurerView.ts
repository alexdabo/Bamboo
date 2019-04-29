import Vue from 'vue'
import Component from 'vue-class-component'
import FindBeneficiary from '@/components/beneficiary/FindBeneficiary.vue'
import InfoBeneficiary from '@/components/beneficiary/InfoBeneficiary.vue'
import Assigned from '@/model/entity/Assigned'
import AssignedService from '@/model/service/AssignedService'
import NewMeasurer from '@/components/measurer/NewMeasurer.vue'

@Component({
  name: 'view-measurer',
  components: {
    FindBeneficiary,
    InfoBeneficiary,
    NewMeasurer
  }
})

export default class MeasurerView extends Vue {
  public dialogTransferMeasurer: boolean = false;
  public dialogNewMeasurer: boolean = false;
  public dialogEditMeasurer: boolean = false;
  public assigned: Assigned = new Assigned()
  headers: any[] = [
    { text: 'Número', value: 'measurer.number' },
    { text: 'Servicio', value: 'measurer.sap.name' },
    { text: 'Fecha de instalación', value: 'measurer.installationDate' },
    { text: 'Estado', value: 'measurer.status.name' },
    { text: 'Deuda', value: 'debt', align: 'center' },
    { text: 'Acciones', sortable: false, align: 'center' }
  ]

  public findAssignedByBeneficiary (beneficiaryId: number) {
    if (beneficiaryId === undefined) {
      this.assigned = new Assigned()
      return
    }
    const assignedService: AssignedService = new AssignedService()
    assignedService.getByBeneficiary(beneficiaryId)
      .then((res: any) => { this.assigned = res.data })
      .catch((err: any) => { console.log(err.response.data) })
  }
}
