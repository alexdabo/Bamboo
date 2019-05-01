import Axios from 'axios'
import Service from '@/model/service/Service'

export default class StatusService extends Service {
  constructor (userId?: number) {
    super('/status', userId)
  }

  public getAll () {
    return Axios.get(this.url)
  }

  public getById (id: number) {
    return Axios.get(`${this.url}/${id}`)
  }
}
