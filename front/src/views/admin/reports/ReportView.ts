import Vue from 'vue'
import Component from 'vue-class-component'

import BeneficiaryReport from '@/components/beneficiary/BeneficiaryReport.vue'

@Component({
  name: 'v-main-report',
  components: {
    BeneficiaryReport
  }
})
export default class ReportView extends Vue {
}
