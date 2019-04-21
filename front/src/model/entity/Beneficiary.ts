import Village from '@/model/entity/Village'

export default class Beneficiary {
    public id?: number
    public lastName?: string
    public firstName?: string
    public dni?: string
    public village: Village = new Village()
    public telephone?: string
    public address?: string
    public placeReference?: string
}
