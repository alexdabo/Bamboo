<template>
  <div>
    <Notify/>
    <router-view/>
  </div>
</template>

<script lang="ts">
import Vue from 'vue'
import Component from 'vue-class-component'
import Notify from '@/components/notify/NotifyWidget.vue'

@Component({
  name: 'App',
  components: {
    Notify
  }
})
export default class LoginView extends Vue {
  public created (): void {
    console.log('App created')
  }
}
</script>
