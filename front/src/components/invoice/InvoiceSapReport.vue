<template>
    <div><v-btn :loading="loading" @click="createReport()">saluda</v-btn></div>
</template>
<script lang="ts">
import InvoiceSapReport from '@/components/invoice/InvoiceSapReport.ts'
export default InvoiceSapReport
</script>
