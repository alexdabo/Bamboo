
import Vue from 'vue';
import Component from 'vue-class-component';
import swal from 'sweetalert';
import Toast from '@/components/widget/toast/Toast'
import Village from '@/model/entity/Village';
import Beneficiary from '@/model/entity/Beneficiary';
import VillageService from '@/model/service/VillageService';
import BeneficiaryService from '@/model/service/BeneficiaryService';

import VToast from '@/components/widget/toast/VToast.vue'
@Component({components:{VToast}})
export default class EditBeneficiary extends Vue {

  service: BeneficiaryService = new BeneficiaryService(1);
  toast: Toast;
  villages: Village[];
  beneficiaries: Beneficiary[];
  search: string;
  dialog: boolean;
  editedIndex: number;
  editedItem: Beneficiary;
  headers: any[];

  constructor(){
    super();
    this.toast = new Toast();
    this.villages = [];
    this.beneficiaries = [];
    this.search = '';
    this.dialog = false;
    this.editedIndex = -1,
    this.editedItem =  new Beneficiary();
    this.headers = [
      {text: 'Apellidos', value: 'lastName',        align: 'left'},
      {text: 'Nombres',   value: 'firstName',       align: 'left'},
      {text: 'Cédula',    value: 'dni',             sortable: false},
      {text: 'Comunidad', value: 'village.name'    },
      {text: 'Teléfono',  value: 'telephone',       sortable: false},
      {text: 'Dirección', value: 'address',         sortable: false},
      {text: 'Referencia',value: 'placeReference',  sortable: false},
      {text: 'Actions',   sortable: false    }
    ];
  }

  created(): void {
    this.findVillages();
    this.findBeneficiaries();
  }


  findVillages(): void {
    let service: VillageService = new VillageService(1);
    service.getAll().then(res =>{
      for (let i = 0; i < res.data.length; i++){
        let village: Village = new Village(res.data[i]['id'],res.data[i]['name']);
        this.villages.push(village);
      }
    }).catch((err)=>{
      console.log(err.response.data.errorText);
    });
  }

  findBeneficiaries(): void{
    this.beneficiaries= [];
    this.service.getAll().then((res)=>{
      for (let i = 0; i < res.data.length; i++){
        let beneficiary: Beneficiary = new Beneficiary();
        beneficiary.setJson(res.data[i]);
        this.beneficiaries.push(beneficiary);
      }
    }).catch((err)=>{
      console.log(err.response.data.errorText);
    });
  }


  save(): void{
    this.service.post(this.editedItem).then((res) =>{
      if(res.data.saved===true){
        this.beneficiaries.push(this.editedItem);
        this.toast.success('Beneficiario guardado');
      }
    }).catch((err)=>{
      this.toast.error('Beneficiario sin guardar', err.response.data.errorText)
      console.log(err.response.data.errorText);
    });
  }

  update(): void{
    this.service.put(this.editedItem).then((res) =>{
      if(res.data.updated===true){
        this.beneficiaries[this.editedIndex] = this.editedItem;
        this.toast = new Toast(true,'success','Beneficiario actualizado','');
        this.toast.success('Beneficiario actualizado');
      }
    }).catch((err)=>{
      this.toast.error('Beneficiario sin actualizar', err.response.data.errorText)
    });
  }


  submit(): void {
    if (this.editedIndex > -1) {
      this.update();
    } else {
      this.save();
    }
    this.close()
  }

  remove(beneficiary: Beneficiary): void {
    const conf: any = {
      buttons: true,
      title: "¿Está seguro?",
      text: `Una vez que se elimine, ¡no podrá recuperar el registro de ${beneficiary.lastName} ${beneficiary.firstName}!`,
      icon: "warning",
      dangerMode: true
    };

    swal(conf)
    .then((willDelete) => {
      if (willDelete) {
        this.service.delete(beneficiary).then((res) =>{
          if(res.data.deleted===true){
            const index = this.beneficiaries.indexOf(beneficiary);
            this.beneficiaries.splice(index, 1);
            this.toast.success('Beneficiario eliminado');
          }
        }).catch((err)=>{
          this.toast.error('Beneficiario sin eliminar', err.response.data.errorText)
        });
      }
    });
  }

  editItem(beneficiary: Beneficiary): void {
    this.editedIndex = this.beneficiaries.indexOf(beneficiary)
    this.editedItem = beneficiary;
    this.dialog = true
  }


  close(): void{
    this.dialog = false
    setTimeout(() => {
      this.editedItem = new Beneficiary();
      this.editedIndex = -1;
    }, 300)
  }
}
