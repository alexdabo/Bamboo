import Axios from 'axios'
import Village from '@/model/entity/Village'
import Service from '@/model/service/Service'

export default class VillageService extends Service {
  constructor (userId?: number) {
    super('/village', userId)
  }

  public post (village: Village) {
    return Axios.post(this.url, village, { headers: this.headers })
  }

  public put (village: Village) {
    return Axios.put(this.url, village, { headers: this.headers })
  }

  public getAll () {
    return Axios.get(this.url)
  }

  public getById (id: number) {
    return Axios.get(`${this.url}/${id}`)
  }

  public delete (village: Village) {
    return Axios.delete(this.url, { data: village, headers: this.headers })
  }
}
