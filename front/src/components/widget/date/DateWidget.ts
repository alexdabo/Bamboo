import Vue from 'vue'
import Component from 'vue-class-component'
import { Emit, Watch } from 'vue-property-decorator'

@Component({ name: 'widget-date' })
export default class DateWidget extends Vue {
    menu: boolean = false
    date: any = new Date().toISOString().substr(0, 10)

    public created(): void {
        this.returnDate()
    }
    @Watch('date')
    public listenerDate(newValue: any): void {
        if (newValue !== null) {
            this.returnDate()
        }
    }

    public returnDate(): void {
        this.$emit('input', this.date)
    }
}
