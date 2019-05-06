import Axios from 'axios'
import Service from '@/model/service/Service'
import InvoiceSap from '../entity/InvoiceSap'

export default class InvoiceSapService extends Service {
  constructor (userId?: number) {
    super('/invoice/sap', userId)
  }

  public getAll () {
    return Axios.get(this.url)
  }

  public getById (invoiceId: number) {
    return Axios.get(`${this.url}/${invoiceId}`)
  }

  public getByBeneficiary (beneficiaryId: number) {
    return Axios.get(`${this.url}/beneficiary/${beneficiaryId}`)
  }
  public post (invoice: InvoiceSap) {
    return Axios.post(this.url, invoice, { headers: this.headers })
  }
}
