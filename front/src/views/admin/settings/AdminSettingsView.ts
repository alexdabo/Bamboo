import Vue from 'vue'
import Component from 'vue-class-component'
import { Prop } from 'vue-property-decorator'
import EntityView from '@/views/admin/entity/EntityView.vue'

@Component({
  name: 'v-admin-settings',
  components: {
    EntityView
  }
})

export default class AdminSettingsView extends Vue {
    @Prop({}) public goback!: string;
}
