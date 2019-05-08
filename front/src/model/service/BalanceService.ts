import Axios from 'axios'
import Service from '@/model/service/Service'

export default class BalanceService extends Service {
  constructor (userId?: number) {
    super('/invoice/balance', userId)
  }

  public getByDate (date: string) {
    return Axios.get(`${this.url}?date=${date}`)
  }
}