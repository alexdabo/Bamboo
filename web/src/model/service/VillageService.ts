import Axios from 'axios';
import Village from '@/model/entity/Village';
import Service from '@/model/service/Service';



export default class VillageService extends Service {
  constructor(userId: number){
    super('/village',userId);
  }

  post(village: Village){
    return Axios.post(this.url, village,{headers: this.headers});
  }

  put(village: Village){
    return Axios.put(this.url, village,{headers: this.headers});
  }

  getAll(){
    return Axios.get(this.url);
  }

  getById(id: number){
    return Axios.get(`${this.url}/id=${id}`);
  }

  delete(village: Village){
    return Axios.post(this.url, village,{headers: this.headers});
  }

}
