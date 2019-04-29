import Village from '@/model/entity/Village'

export default class Beneficiary {
  // eslint-disable-next-line
    constructor(
        public id: number = 0,
        public lastName?: string,
        public firstName?: string,
        public dni?: string,
        public village: Village = new Village(),
        public telephone?: string,
        public address?: string,
        public placeReference?: string
  ) { }
}
