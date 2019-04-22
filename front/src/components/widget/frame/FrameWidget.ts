import Vue from 'vue'
import Component from 'vue-class-component'
import { Prop } from 'vue-property-decorator'

@Component({ name: 'v-frame' })

export default class FrameWidget extends Vue {
    @Prop() public title!: string;
    @Prop() public icon!: string;
    @Prop({ default: 'primary' }) public colorBar!: string;
    @Prop({ default: 'white' }) public colorTitle!: string;
    @Prop({ default: true }) public enableHeader!: boolean;
    @Prop({ default: false }) public smallTitle!: boolean;
    @Prop({ default: true }) public shadow!: boolean;
    @Prop({ default: true }) public padding!: boolean;
    get colorText (): string {
      return `${this.colorTitle}--text`
    }
}
