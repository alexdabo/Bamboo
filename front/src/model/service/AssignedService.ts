import Axios from 'axios'
import Service from '@/model/service/Service'

export default class AssignedService extends Service {
  constructor (userId?: number) {
    super('/assigned', userId)
  }

  public getAll () {
    return Axios.get(this.url)
  }

  public getById (id: number) {
    return Axios.get(`${this.url}/${id}`)
  }
  public getByBeneficiary (beneficiaryIdd: number) {
    return Axios.get(`${this.url}/beneficiary/${beneficiaryIdd}`)
  }
}
