import Axios from 'axios'
import User from '@/model/entity/User'
import Service from '@/model/service/Service'

export default class UserService extends Service {
  constructor (userId?: number) {
    super('/user', userId)
  }

  public post (user: User) {
    return Axios.post(this.url, user, { headers: this.headers })
  }

  public getLogged (username: string, password: string) {
    return Axios.post(`${this.url}/login?credential=${username}&password=${password}`)
  }

  public put (user: User) {
    return Axios.put(this.url, user, { headers: this.headers })
  }

  public putPassword (currentPass: string, newPass: string, username: string) {
    return Axios.put(
      `${this.url}?currentPass=${currentPass}&newPass=${newPass}&username=${username}`,
      { headers: this.headers }
    )
  }

  public getAll () {
    return Axios.get(this.url)
  }

  public getById (id: number) {
    return Axios.get(`${this.url}?id=${id}`)
  }

  public delete (user: User) {
    return Axios.delete(this.url, { data: user, headers: this.headers })
  }
}
