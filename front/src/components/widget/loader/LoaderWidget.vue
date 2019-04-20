<template>
  <v-dialog v-model="$store.state.loader" persistent max-width="200">
    <v-toolbar>
      <v-btn icon>
        <v-progress-circular indeterminate color="primary"/>
      </v-btn>
      <v-toolbar-title class="grey--text">Cargando</v-toolbar-title>
    </v-toolbar>
  </v-dialog>
</template>
<script lang="ts">
import LoaderWidget from '@/components/widget/loader/LoaderWidget.ts'
export default LoaderWidget
</script>
