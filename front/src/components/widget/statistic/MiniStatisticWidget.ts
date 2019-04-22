import Vue from 'vue'
import Component from 'vue-class-component'
import { Prop } from 'vue-property-decorator'

@Component({ name: 'v-mini-statistic' })
export default class MiniStatisticWidget extends Vue {
    @Prop({}) public icon!: string;
    @Prop({}) public amount!: number;
    @Prop({}) public title!: string;
    @Prop({}) public color!: string;
}
