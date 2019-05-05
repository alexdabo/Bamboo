import Axios from 'axios'
import Service from '@/model/service/Service'
import Uptake from '../entity/Uptake'

export default class UptakeService extends Service {
  constructor (userId?: number) {
    super('/uptake', userId)
  }

  public post (measurerId: number, uptake: Uptake) {
    return Axios.post(`${this.url}/${measurerId}`, uptake, { headers: this.headers })
  }
}
