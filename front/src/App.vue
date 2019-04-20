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
    console.log('App created')
  }
}
</script>
