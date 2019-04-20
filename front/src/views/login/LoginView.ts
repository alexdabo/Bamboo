import Vue from 'vue'
import Component from 'vue-class-component'

@Component({ name: 'view-login' })
export default class LoginView extends Vue {
  public username: string = '';
  public password: string = '';
  public created (): void {
    console.log('login')
  }
  public login (): void {
    console.log('logged')
    if (this.username === 'admin' && this.password === 'admin') { this.$store.commit('showSuccess', { title: 'Bienvenido admin' }) } else { this.$store.commit('showError', { title: 'Credenciales incorrectos' }) }
  }
}
