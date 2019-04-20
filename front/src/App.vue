<template>
  <div>
    <Notify/>
    <Loader/>
    <router-view/>
  </div>
</template>

<script lang="ts">
import Vue from 'vue'
import { Watch } from 'vue-property-decorator'
import Component from 'vue-class-component'
import Notify from '@/components/widget/notify/NotifyWidget.vue'
import Loader from '@/components/widget/loader/LoaderWidget.vue'

@Component({
  name: 'App',
  components: {
    Notify,
    Loader
  }
})
export default class LoginView extends Vue {
  public created (): void {
    if (localStorage.logged) {
      switch (JSON.parse(localStorage.user).role.id) {
        case 1:
          this.$router.push({ name: 'admin' })
          break
        case 2:
          this.$router.push({ name: 'collections' })
          break
        case 3:
          this.$router.push({ name: 'measurements' })
          break
      }
    }
  }

  @Watch('$route.name')
  public onChangeRoute (newName: string): void {
    console.log(`new: ${newName}`)
    if (newName !== 'admin') {
      localStorage.lastRouteName = newName
    }
    // if(performance.navigation.type===1){
    // localStorage.lastRouteName = this.$route.name
    // }
    /* switch (performance.navigation.type) {
      case 0:
        console.info("TYPE_NAVIGATE");
        break;
      case 1:
        console.info("TYPE_RELOAD");

        break;
      case 2:
        console.info("TYPE_BACK_FORWARD");
        break;
      case 255:
        console.info("255");
        break;
    } */
  }
}
</script>
