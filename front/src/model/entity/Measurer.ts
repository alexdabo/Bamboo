import Status from '@/model/entity/Status'
import Sap from '@/model/entity/Sap'

export default class Measurer {
  // eslint-disable-next-line
  constructor (
    public id: number = 0,
    public number?: string,
    public installationDate?: string,
    public sap: Sap = new Sap(),
    public status: Status = new Status()
  ) {}
}
