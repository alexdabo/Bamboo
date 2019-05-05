import Axios from 'axios'
import Service from '@/model/service/Service'

export default class MeasurerService extends Service {
  constructor (userId?: number) {
    super('/measurer', userId)
  }

  public getAll () {
    return Axios.get(`${this.url}/simple`)
  }
  public getById (measurerId: any) {
    return Axios.get(`${this.url}/simple/by/number/${measurerId}`)
  }

  public getByBeneficiary (beneficiaryId: number) {
    return Axios.get(`${this.url}/api/measurer/simple/from/beneficiary/${beneficiaryId}`)
  }
  public getByBeneficiaryUnbilledUptakes (beneficiaryId: number) {
    return Axios.get(`${this.url}/with/unbilled/uptakes/from/beneficiary/${beneficiaryId}`)
  }
}
