import Vue from 'vue'
import Component from 'vue-class-component'
import { Prop } from 'vue-property-decorator'
import Beneficiary from '@/model/entity/Beneficiary'

@Component({ name: 'info-beneficiary' })

export default class InfoBeneficiary extends Vue {
    @Prop({}) public beneficiary!: Beneficiary;
    @Prop({ default: false }) public basic!: boolean;
}
