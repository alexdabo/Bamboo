import Vue from 'vue'
import Component from 'vue-class-component'
import DateWidget from '@/components/widget/date/DateWidget'

@Component({
  name: 'daily-balance-view',
  components: { DateWidget }
})
export default class DailyBalanceView extends Vue {
  public date:any = null
}
