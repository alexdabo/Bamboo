import Vue from 'vue'
import Component from 'vue-class-component'
import { Prop } from 'vue-property-decorator'
import EntityView from '@/views/admin/entity/EntityView.vue'
import PersonalInfo from '@/components/user/PersonalInfo.vue'
import ChangePassword from '@/components/user/ChangePassword.vue'

@Component({
  name: 'admin-settings',
  components: {
    EntityView,
    PersonalInfo,
    ChangePassword
  }
})

export default class AdminSettingsView extends Vue {
    @Prop({}) public goback!: string;
}
