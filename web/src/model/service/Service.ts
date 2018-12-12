
export default class Service{
  public url: string;
  public headers: any;
  constructor(url: String, userId: number){
    this.url = `/api${url}`
    this.headers = {
      'Accept': 'application/json',
      'Content-Type': 'application/json; charset=utf-8',
      'user': userId
    };
  }

}
