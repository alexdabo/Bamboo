import Measurer from '@/model/entity/Measurer';

export default class AssignedSimple {
    // eslint-disable-next-line
    constructor(
        public id?: number,
        public assignmentDate?: string,
        public status?: string,
        public debt?: number,
        public measurer: Measurer = new Measurer()
    ) { }
}