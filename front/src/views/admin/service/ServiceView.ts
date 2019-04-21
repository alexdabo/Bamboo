import Vue from 'vue'
import Component from 'vue-class-component'
import SapView from '@/views/admin/sap/SapView.vue'
import AnotherView from '@/views/admin/another/AnotherView.vue'

@Component({
  name: 'view-service',
  components: {
    SapView,
    AnotherView
  }
})

export default class ServiceView extends Vue {

}
