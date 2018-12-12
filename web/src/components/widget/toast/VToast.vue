<template lang="html">
  <div>

    <v-snackbar v-if="toast.status === 'success'" v-model="toast.show" :color="toast.status" :timeout="3000" top right auto-height style="padding:0px!important">
      {{toast.msg}}
      <v-spacer></v-spacer>
      <v-btn icon @click="show = false">
        <v-icon >cancel</v-icon>
      </v-btn>
    </v-snackbar>
    <v-snackbar v-if="toast.status === 'error'" v-model="toast.show" :color="toast.status" :timeout="15000" vertical  top right auto-height>

      <div>
        {{toast.msg}}
        <v-btn icon @click="toast.show = false" class="right mt-0">
          <v-icon >cancel</v-icon>
        </v-btn>
      </div>
      <v-expansion-panel  >
        <v-expansion-panel-content :class="toast.status" class="lighten-2">
        <div slot="header">Detalle</div>
        <v-card>
          <v-card-text :class="toast.status" class=" lighten-5">{{toast.detail}}</v-card-text>
        </v-card>
      </v-expansion-panel-content>
    </v-expansion-panel>

  </v-snackbar>
</div>
</template>

<script lang="ts">
import VToast from '@/components/widget/toast/VToast.ts'
export default VToast
</script>
