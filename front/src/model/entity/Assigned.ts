import AssignedSimple from '@/model/entity/AssignedSimple'
import Beneficiary from './Beneficiary'

export default class Assigned {
  // eslint-disable-next-line
  constructor (
        public beneficiary:Beneficiary = new Beneficiary(),
        public assigneds: AssignedSimple[] = []
  ) { }
}
