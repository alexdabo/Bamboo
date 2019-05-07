<template>
    <div>
        <v-toolbar flat color="white">
      <v-toolbar-title>BALANCE DIARIO</v-toolbar-title>
      <v-divider class="mx-2" inset vertical></v-divider>
      <v-spacer></v-spacer>
      <DateWidget v-model="date"/>
      <v-spacer></v-spacer>
      <v-btn
        v-shortkey="['alt', 'r']"
        @shortkey="findInvoices()"
        color="primary"
        class="mb-2"
        icon
        @click="findInvoices()"
      >
        <v-icon>refresh</v-icon>
      </v-btn>
        <v-btn
          v-shortkey="['alt', 'n']"
          @shortkey="dialog=true"
          slot="activator"
          color="primary"
          icon
          class="mb-2"
        >
          <v-icon>open_in_browser</v-icon>
        </v-btn>
    </v-toolbar>
    <v-data-table
      :headers="headers"
      :items="invoices"
      :search="date"
      hide-actions
      class="elevation-1"
    >
      <template slot="items" slot-scope="props">
        <td>{{ props.item.invoiceId }}</td>
        <td class="text-xs-left">{{ props.item.beneficiary.lastName }}</td>
        <td class="text-xs-left">{{ props.item.debtcollector.firstName }}</td>
        <td class="text-xs-left">{{ tipo }}</td>
        <td class="text-xs-left">{{ props.item.totalToPay }}</td>
      </template>
      <template slot="footer">
              <td colspan="3"></td>
              <td class="primary white--text">
                <b>TOTAL</b>
              </td>
              <td>
                <span class="grey--text">
                  <b>$</b>
                </span>
                {{dialyTotal}}
              </td>
            </template>
      <v-alert
        slot="no-results"
        :value="true"
        color="error"
        icon="warning"
      >No se encontro resultados para "{{ date }}".</v-alert>
    </v-data-table>
    </div>
</template>
<script lang="ts">
import DailyBalanceView from '@/views/admin/invoice/DailyBalanceView.ts'
export default DailyBalanceView
</script>
