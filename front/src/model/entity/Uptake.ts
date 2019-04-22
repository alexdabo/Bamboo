import Measurer from '@/model/entity/Measurer'

export default class Uptake {
  // eslint-disable-next-line
  constructor(
    public id?: number,
    public dateTaked?: string,
    public lastValueTaken?: number,
    public currentValueTaken?: number,
    public baseVolume?: number,
    public basePrice?: number,
    public extraPrice?: number,
    public volumeExceeded?: number,
    public volumeConsumed?: number,
    public totalPrice: number = 0,
    public billed?: boolean,
    public measurer: Measurer = new Measurer()
  ) { }
}
