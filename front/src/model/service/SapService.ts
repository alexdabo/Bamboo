import Axios from 'axios'
import Sap from '@/model/entity/Sap'
import Service from '@/model/service/Service'

export default class SapService extends Service {
  constructor (userId?: number) {
    super('/sap', userId)
  }

  public post (sap: Sap) {
    return Axios.post(this.url, sap, { headers: this.headers })
  }

  public put (sap: Sap) {
    return Axios.put(this.url, sap, { headers: this.headers })
  }

  public getAll () {
    return Axios.get(this.url)
  }

  public getById (id: number) {
    return Axios.get(`${this.url}/${id}`)
  }

  public delete (sap: Sap) {
    return Axios.delete(`${this.url}/${sap.id}`, { headers: this.headers })
  }
}
