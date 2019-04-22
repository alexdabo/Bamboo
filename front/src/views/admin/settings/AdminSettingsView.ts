import Vue from 'vue'
import Component from 'vue-class-component'
import { Prop } from 'vue-property-decorator'
import EntityView from '@/views/admin/entity/EntityView.vue'
import PersonalInfo from '@/components/beneficiary/PersonalInfo.vue'
import ChangePassword from '@/components/beneficiary/ChangePassword.vue'

@Component({
  name: 'v-admin-settings',
  components: {
    EntityView,
    PersonalInfo,
    ChangePassword
  }
})

export default class AdminSettingsView extends Vue {
    @Prop({}) public goback!: string;
}
