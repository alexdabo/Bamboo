<template>
  <div>
    <v-tabs grow>
      <v-tab ripple>
        <b>Sap</b>
      </v-tab>
      <v-tab-item>
        <SapView/>
      </v-tab-item>

      <v-tab ripple>
        <b>Otros</b>
      </v-tab>
      <v-tab-item>
        <AnotherView/>
      </v-tab-item>
    </v-tabs>
  </div>
</template>

<script lang="ts">
import ServiceView from '@/views/admin/service/ServiceView.ts'
export default ServiceView
</script>
