<template>
  <div>
    <v-layout fluid fill-height row wrap>
      <v-flex md12>
        <v-toolbar color="white">
          <v-toolbar-title>Factura</v-toolbar-title>
          <v-divider class="mx-2" inset vertical></v-divider>
          <v-spacer></v-spacer>
          <FindBeneficiary solo @selected="setInvoice" clearable :code="$route.params.id"/>
          <v-spacer></v-spacer>
          <v-btn
            v-shortkey="[ 'alt', 'r']"
            @shortkey="findUsers()"
            color="primary"
            class="mb-2"
            icon
            @click="findUsers()"
          >
            <v-icon>refresh</v-icon>
          </v-btn>

          <v-menu offset-y origin="top bottom" transition="scale-transition">
            <v-btn slot="activator" color="primary" icon>
              <v-icon>more_vert</v-icon>
            </v-btn>
            <v-list>
              <v-list-tile @click="dialogNewMeasurer=true">
                <v-list-tile-avatar v-shortkey="['alt', 'n']" @shortkey="dialogNewMeasurer=true">
                  <v-icon>add</v-icon>
                </v-list-tile-avatar>
                <v-list-tile-content>
                  <v-list-tile-title>Factura de SAP</v-list-tile-title>
                </v-list-tile-content>
              </v-list-tile>

              <v-list-tile @click="dialogTransferMeasurer=true">
                <v-list-tile-avatar
                  v-shortkey="['alt', 't']"
                  @shortkey="dialogTransferMeasurer=true"
                >
                  <v-icon>add</v-icon>
                </v-list-tile-avatar>
                <v-list-tile-content>
                  <v-list-tile-title>Factura de proceso</v-list-tile-title>
                </v-list-tile-content>
              </v-list-tile>
            </v-list>
          </v-menu>
        </v-toolbar>
        <InfoBeneficiary :beneficiary="invoice.beneficiary"/>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn @click="createInvoice()" color="primary">
            <v-icon left>monetization_on</v-icon>Cobrar
          </v-btn>
          <v-btn @click="printInvoice()" color="primary">
            <v-icon left>print</v-icon>Imprimir
          </v-btn>
          <v-btn @click="deleteUptake()" color="primary">
            <v-icon left>delete_sweep</v-icon>Eliminar
          </v-btn>
        </v-card-actions>
        <v-data-table :headers="headers" :items="invoice.detail" hide-actions>
          <template slot="items" slot-scope="props">
            <td>{{ props.item.dateTaked }}</td>
            <td>{{ props.item.currentValueTaken}}</td>
            <td>{{ props.item.baseVolume }}</td>
            <td>{{ props.item.basePrice }}</td>
            <td>{{ props.item.extraPrice }}</td>
            <td>{{ props.item.volumeConsumed }}</td>
            <td>{{ props.item.volumeExceeded }}</td>
            <td>{{ props.item.totalPrice }}</td>
          </template>
          <template slot="footer">
              <td colspan="6"></td>
              <td class="primary white--text"><b>TOTAL</b></td>
              <td>{{getTotal}}</td>
          </template>
        </v-data-table>
      </v-flex>
    </v-layout>
    <v-navigation-drawer fixed v-model="drawer" right clipped app>
      <v-tabs color="primary" dark slider-color="blue-grey darken-2" grow>
        <v-tab ripple>
          <b>Medidores</b>
        </v-tab>
        <v-tab-item>
          <v-list>
            <div v-for="item in measurers" :key="item.id">
              <v-list-tile @click="invoice.detail = item.uptakes;drawer=false">
                <v-list-tile-content>
                  <v-list-tile-title>Medidor número:{{item.number}}</v-list-tile-title>
                  <v-list-tile-sub-title>{{item.sap.name}}</v-list-tile-sub-title>
                </v-list-tile-content>
              </v-list-tile>
              <v-divider></v-divider>
            </div>
          </v-list>
        </v-tab-item>

        <v-tab ripple>
          <b>Facturas</b>
        </v-tab>
        <v-tab-item>Factura de servicios</v-tab-item>
      </v-tabs>
    </v-navigation-drawer>
  </div>
</template>
<script lang="ts">
import InvoiceSapView from '@/views/admin/invoicesap/InvoiceSapView.ts'
export default InvoiceSapView
</script>
