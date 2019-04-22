import Page from '@/components/widget/page/Page'
import Component from 'vue-class-component'
import { Prop } from 'vue-property-decorator'
import User from '@/model/entity/User'
import UserService from '@/model/service/UserService'

@Component({ name: 'change-password' })

export default class ChangePassword extends Page {
  @Prop({}) public goback!: string;
  public user: any = this.getUser();

  public currentPass: string = '';
  public newPass: string = '';
  public repeatedPass: string = '';

  public save (): void {
    const userService: UserService = new UserService(this.user.id)
    if (this.currentPass !== '') {
      if (this.newPass === this.repeatedPass) {
        if (this.newPass.length > 10) {
          userService.changePassword(this.currentPass, this.newPass, this.user.userName)
            .then((res: any) => {
              if (res.data.updated === true) {
                this.success('Contraseña actualizada')
              } else {
                this.error('Contraseña no actualizada', res.data.msg)
              }
            })
            .catch((err: any) => {
              this.error('Contraseña no actualizada', err.response.data.error)
            })
        } else {
          this.error('Contraseña es muy débil','La nueva contraseña debe tener al menos 10 caracteres')
          this.repeatedPass = ''
          this.newPass = ''
        }
      } else {
        this.error('Las contraseñas no coinciden')
        this.repeatedPass = ''
      }
    }
  }
}
