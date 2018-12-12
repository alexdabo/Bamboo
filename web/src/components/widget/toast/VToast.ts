import Vue from 'vue';
import Component from 'vue-class-component';
import {Prop} from 'vue-property-decorator';
import Toast from '@/components/widget/toast/Toast'

@Component({name: 'v-toast'})
export default class VToast extends Vue {
	@Prop({
		default: null
	})
	toast!: Toast;
}
