import Vue from 'vue'
import Component from 'vue-class-component'

@Component({ name: 'widget-notify' })
export default class NotifyWidget extends Vue {
    public timeout: number = 6000;

    public get getColor () {
      let color: string | undefined
      switch (this.$store.state.notify.type) {
        case 'success':
          color = 'green'
          break
        case 'error':
          color = 'red'
          break
        default:
          break
      }
      return color
    }
    public get getIcon () {
      let icon: string | undefined
      switch (this.$store.state.notify.type) {
        case 'success':
          icon = 'check_circle'
          break
        case 'error':
          icon = 'error'
          break
        default:
          break
      }
      return icon
    }

    public close (): void {
      this.$store.commit('closeNotify')
    }
}
