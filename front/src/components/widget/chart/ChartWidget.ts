import Vue from 'vue'
import Component from 'vue-class-component'
import { Prop } from 'vue-property-decorator'
import FrameWidget from '@/components/widget/frame/FrameWidget'

@Component({ name: 'v-chart-widget', components: { FrameWidget } })
export default class ChartWidget extends Vue {
    @Prop() public data!: any;
    @Prop() public title!: string;
    @Prop() public xlabel!: string;
    @Prop() public ylabel!: string;
    public type: string = 'column';
    public items: any[] = [
      { title: 'Columnas ', type: 'column' },
      { title: 'Lineas', type: 'line' },
      { title: 'Pastel', type: 'pie' },
      { title: 'Barras', type: 'bar' }
    ];
}
