import Vue from 'vue'
import Component from 'vue-class-component'
import UserService from '@/model/service/UserService'

@Component({ name: 'view-login' })
export default class LoginView extends Vue {
  public username: string = '';
  public password: string = '';
  public created (): void {
    console.log('login')
  }
  public login (): void {
    const userService: UserService = new UserService()
    userService.getLogged(this.username, this.password)
      .then((res: any) => {
        if (res.data.logged === true) {
          localStorage.logged = true
          localStorage.user = JSON.stringify(res.data.user)
          switch (res.data.user.role.id) {
            case 1:
              this.$router.push({ name: 'admin' })
              break
            case 2:
              this.$router.push({ name: 'collections' })
              break
            case 3:
              this.$router.push({ name: 'measurements' })
              break
          }
        } else {
          this.$store.commit('showError', { msg: 'Usuario o contraseña incorrectos' })
        }
      })
      .catch(() => {
        this.$store.commit('showError', { msg: 'Un error se ha generado' })
      })
  }
}
