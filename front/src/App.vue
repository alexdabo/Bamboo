<template>
  <div>
    <Notify/>
    <Loader/>
    <router-view/>
  </div>
</template>

<script lang="ts">
import Vue from 'vue'
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
}
</script>
