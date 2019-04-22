import Page from '@/components/widget/page/Page'
import Component from 'vue-class-component'
import { Prop } from 'vue-property-decorator'
import User from '@/model/entity/User'
import UserService from '@/model/service/UserService'

@Component({ name: 'personal-info' })

export default class PersonalInfo extends Page {
    @Prop({}) public goback!: string;
    public user: User = this.getUser();

    public save (): void {
      const userService: UserService = new UserService(this.user.id)
      userService.put(this.user)
        .then((res: any) => {
          if (res.data.updated === true) {
            this.success('Información personal actualizada')
          }
        })
        .catch((err: any) => {
          this.error('Información sin guardar', err.response.data.error)
        })
    }
}
