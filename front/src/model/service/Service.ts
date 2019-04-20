
export default class Service {
    public url: string;
    public headers: any;
    constructor (url: string, userId?: number) {
      this.url = process.env.NODE_ENV === 'development'
        ? `http://localhost:8080/bamboo/api${url}`
        : `/bamboo/api${url}`

      this.headers = {
        'Accept': 'application/json',
        'Content-Type': 'application/json; charset=utf-8',
        'user': userId
      }
    }
}
