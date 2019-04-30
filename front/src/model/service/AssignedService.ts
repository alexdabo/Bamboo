import Axios from 'axios'
import Service from '@/model/service/Service'
import Assigned from '@/model/entity/Assigned'

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
  public getByBeneficiary (beneficiaryId: number) {
    return Axios.get(`${this.url}/beneficiary/${beneficiaryId}`)
  }

  public postNew (assigned: Assigned) {
    return Axios.post(`${this.url}/new`, assigned, { headers: this.headers })
  }

  public postTransfer (assigned: Assigned) {
    return Axios.post(`${this.url}/transfer`, assigned, { headers: this.headers })
  }
}
