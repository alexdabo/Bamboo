import Axios from 'axios'
import Service from '@/model/service/Service'
import Entity from '@/model/entity/Entity'

export default class EntityService extends Service {
  constructor (userId?: number) {
    super('/entity', userId)
  }

  public get () {
    return Axios.get(this.url)
  }

  public put (entity: Entity) {
    return Axios.put(this.url, entity, { headers: this.headers })
  }
}
