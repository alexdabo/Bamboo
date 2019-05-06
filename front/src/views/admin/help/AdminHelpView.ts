import Vue from 'vue'
import Component from 'vue-class-component'
import MenuItems from '@/views/admin/help/AdminItems'

@Component({
  name: 'admin-help-view',
  components: {}
})
export default class AdminHelpView extends Vue {
  items: any = MenuItems;

  goTo (id: string): void {
    this.$vuetify.goTo(`#${id}`, { duration: 500, easing: 'easeInOutCubic' })
  }
}
