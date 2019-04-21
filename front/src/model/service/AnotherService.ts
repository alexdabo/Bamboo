import Axios from 'axios'
import Another from '@/model/entity/Another'
import Service from '@/model/service/Service'

export default class AnotherService extends Service {
  constructor (userId?: number) {
    super('/anotherservice', userId)
  }

  public post (another: Another) {
    return Axios.post(this.url, another, { headers: this.headers })
  }

  public put (another: Another) {
    return Axios.put(this.url, another, { headers: this.headers })
  }

  public getAll () {
    return Axios.get(this.url)
  }

  public getById (id: number) {
    return Axios.get(`${this.url}?id=${id}`)
  }

  public delete (another: Another) {
    return Axios.delete(`${this.url}/${another.id}`, { headers: this.headers })
  }
}
