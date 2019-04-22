import Vue from 'vue'
import Component from 'vue-class-component'
import FindBeneficiary from '@/components/beneficiary/FindBeneficiary.vue'
import InfoBeneficiary from '@/components/beneficiary/InfoBeneficiary.vue'
import Beneficiary from '@/model/entity/Beneficiary'

@Component({
  name: 'view-measurer',
  components: {
    FindBeneficiary,
    InfoBeneficiary
  }
})

export default class MeasurerView extends Vue {
  public dialogTransferMeasurer: boolean = false;
  public dialogNewMeasurer: boolean = false;
  public dialogEditMeasurer: boolean = false;
  public beneficiary: Beneficiary = new Beneficiary()

  public setBeneficiary (beneficiary:Beneficiary): void{
    this.beneficiary = beneficiary
  }
}
