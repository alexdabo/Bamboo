import Measurer from '@/model/entity/Measurer';
import AssignedSimple from './AssignedSimple';

export default class Assigned {
    constructor(
        public id?: number,
        public assigneds: AssignedSimple = new AssignedSimple()
    ) { }
}