import Page from '@/components/widget/page/Page'
import Component from 'vue-class-component'
// @ts-ignore
import sweetalert from 'sweetalert'
import User from '@/model/entity/User'
import UserService from '@/model/service/UserService'
import FindRole from '@/components/role/FindRole.vue'
import Role from '@/model/entity/Role'

@Component({
  name: 'view-user',
  components: {
    FindRole
  }
})
export default class UserView extends Page {
  public users: User[] = [];
  public search: string = '';
  public dialog: boolean = false;
  public editedIndex: number = -1;
  public editedItem: User = new User();
  public headers: any[] = [{ text: 'Apellidos', value: 'lastName', align: 'left' },
    { text: 'Nombres', value: 'firstName', align: 'left' },
    { text: 'Cédula', value: 'dni', sortable: false },
    { text: 'Rol', value: 'role.name' },
    { text: 'Email', value: 'email', sortable: false },
    { text: 'Teléfono', value: 'telephone', sortable: false },
    { text: 'Dirección', value: 'address', sortable: false },
    { text: 'Acciones', sortable: false }];

  public created (): void {
    this.findUsers()
  }

  public setRole (role: Role): void {
    this.editedItem.role = role
  }
  public findUsers (): void {
    const userService = new UserService()
    this.users = []
    userService.getAll().then((res: any) => {
      this.users = res.data
    }).catch((err: any) => {
      this.error('Error al buscar los usuario', err.response.data.error)
    })
  }

  public save (): void {
    const userService = new UserService(this.getUser().id)
    userService.post(this.editedItem).then((res: any) => {
      if (res.data.saved === true) {
        this.users.push(res.data.user)
        this.success('Usuario guardado')
        this.close()
      }
    }).catch((err: any) => {
      console.log(err)
      this.error('Usuario sin guardar', err.response.data.error)
    })
  }

  public update (): void {
    const userService = new UserService(this.getUser().id)
    userService.put(this.editedItem).then((res) => {
      if (res.data.updated === true) {
        this.users[this.editedIndex] = this.editedItem
        this.success('Usuario actualizado')
        this.close()
      }
    }).catch((err: any) => {
      this.error('Usuario sin actualizar', err.response.data.error)
    })
  }

  public submit (): void {
    if (this.editedIndex > -1) {
      this.update()
    } else {
      this.save()
    }
  }

  public remove (user: User): void {
    const userService = new UserService(this.getUser().id)
    const conf: any = {
      buttons: true,
      title: '¿Está seguro?',
      text: `Una vez que se elimine, ¡no podrá recuperar el registro de ${user.lastName} ${user.firstName}!`,
      icon: 'warning',
      dangerMode: true
    }

    sweetalert(conf)
      .then((willDelete: any) => {
        if (willDelete) {
          userService.delete(user).then((res: any) => {
            if (res.data.deleted === true) {
              const index = this.users.indexOf(user)
              this.users.splice(index, 1)
              this.success('Usuario eliminado')
            }
          }).catch((err: any) => {
            this.error('Usuario sin eliminar', err.response.data.error)
          })
        }
      })
  }

  public editItem (user: User): void {
    this.editedIndex = this.users.indexOf(user)
    this.editedItem = user
    this.dialog = true
  }

  public close (): void {
    this.dialog = false
    setTimeout(() => {
      this.editedItem = new User()
      this.editedIndex = -1
    }, 300)
  }
}
