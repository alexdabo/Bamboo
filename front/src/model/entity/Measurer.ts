import Status from '@/model/entity/Status'
import Sap from '@/model/entity/Sap'
import Uptake from './Uptake';

export default class Measurer {
  // eslint-disable-next-line
  constructor (
    public id: number = 0,
    public number?: string,
    public installationDate?: string,
    public sap: Sap = new Sap(),
    public status: Status = new Status(),
    public uptakes?: Uptake[]
  ) {}
}
