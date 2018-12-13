import Vue from 'vue';
import Component from 'vue-class-component';
import {Prop} from 'vue-property-decorator';

@Component({
  name: 'side-bar-item'
})
export default class SideBarItem extends Vue {
  @Prop(String) icon!: string;
  @Prop(String) title!: string;
  @Prop(String) link!: string;
}
