<template>
  <v-btn @click="createReport()" color="primary">
    <v-icon left>print</v-icon>Imprimir
  </v-btn>
</template>
<script lang="ts">
import InvoiceSapReport from '@/components/invoice/InvoiceSapReport.ts'
export default InvoiceSapReport
</script>
