import Axios from 'axios';
import Beneficiary from '@/model/entity/Beneficiary';
import Service from '@/model/service/Service';



export default class BeneficiaryService extends Service {
  constructor(userId: number){
    super('/beneficiary',userId);
  }

  post(beneficiary: Beneficiary){
    return Axios.post(this.url, beneficiary,{headers: this.headers});
  }

  put(beneficiary: Beneficiary){
    return Axios.put(this.url, beneficiary,{headers: this.headers});
  }

  getAll(){
    return Axios.get(this.url);
  }

  getById(id: number){
    return Axios.get(`${this.url}/id=${id}`);
  }

  getFromVillage(){
    return Axios.get(this.url);
  }

  delete(beneficiary: Beneficiary){
    return Axios.delete(this.url,{data:beneficiary,headers: this.headers});
  }

}
