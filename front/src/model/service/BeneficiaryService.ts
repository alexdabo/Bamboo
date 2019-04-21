import Axios from 'axios'
import Beneficiary from '@/model/entity/Beneficiary'
import Service from '@/model/service/Service'

export default class BeneficiaryService extends Service {
  constructor (userId?: number) {
    super('/beneficiary', userId)
  }

  public post (beneficiary: Beneficiary) {
    return Axios.post(this.url, beneficiary, { headers: this.headers })
  }

  public put (beneficiary: Beneficiary) {
    return Axios.put(this.url, beneficiary, { headers: this.headers })
  }

  public getAll () {
    return Axios.get(this.url)
  }

  public getById (id: number) {
    return Axios.get(`${this.url}/${id}`)
  }

  public getByData (data: string, villageId: number) {
    return Axios.get(`${this.url}?data=${data}&villageId=${villageId}`)
  }

  public getFromVillage () {
    return Axios.get(this.url)
  }

  public delete (beneficiary: Beneficiary) {
    return Axios.delete(`${this.url}/${beneficiary.id}`, { headers: this.headers })
  }
}
