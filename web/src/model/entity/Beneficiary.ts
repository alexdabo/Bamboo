import Village from '@/model/entity/Village'

export default class Beneficiary {
  public id: number;
  public lastName: string;
  public firstName: string;
  public dni: string;
  public village: Village;
  public telephone: string;
  public address: string;
  public placeReference: string;

  constructor (id: number = 0,
    lastName: string = '',
    firstName: string = '',
    dni: string = '',
    village: Village = new Village(),
    telephone: string = '',
    address: string = '',
    placeReference: string = ''
  ) {
    this.id = id
    this.lastName = lastName
    this.firstName = firstName
    this.dni = dni
    this.village = village
    this.telephone = telephone
    this.address = address
    this.placeReference = placeReference
  }

  setJson (data: any): void{
    this.id = data.id
    this.lastName = data.lastName
    this.firstName = data.firstName
    this.dni = data.dni
    this.village = data.village
    this.telephone = data.telephone
    this.address = data.address
    this.placeReference = data.placeReference
  }
}
