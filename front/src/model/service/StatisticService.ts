import Axios from 'axios'
import Service from '@/model/service/Service'

export default class StatisticService extends Service {
  constructor (userId?: number) {
    super('/statistic', userId)
  }

  public get () {
    return Axios.get(this.url/* 'https://api.myjson.com/bins/pe95y' */)
  }
}
