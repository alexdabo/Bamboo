import Axios from 'axios'
import Service from '@/model/service/Service'

export default class RoleService extends Service {
  constructor (userId?: number) {
    super('/role', userId)
  }

  public getAll () {
    return Axios.get(this.url)
  }

  public getById (id: number) {
    return Axios.get(`${this.url}/${id}`)
  }
}
