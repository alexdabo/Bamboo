import Vue from 'vue'
import User from '@/model/entity/User'

export default class Page extends Vue {
  public success (title: string, msg?: string): void {
    this.$store.commit('showSuccess', { title, msg })
  }

  public warning (title: string, msg?: string): void {
    this.$store.commit('showWarning', { title, msg })
  }

  public error (title: string, msg?: string): void {
    this.$store.commit('showError', { title, msg })
  }
  public closeNotify (): void {
    this.$store.commit('closeNotify')
  }
  public loaderStart (): void {
    this.$store.commit('loaderStart')
  }
  public loaderFinish (): void {
    this.$store.commit('loaderFinish')
  }
  public getUser (): User {
    const user: User = JSON.parse(localStorage.user)
    return user
  }
}
