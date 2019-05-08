<template>
  <div>
    <v-layout fluid fill-height row wrap>
      <v-flex md12>
        <v-toolbar color="white">
          <v-toolbar-title>Factura</v-toolbar-title>
          <v-divider class="mx-2" inset vertical></v-divider>
          <v-spacer></v-spacer>
          <FindBeneficiary solo @selected="findUptakes" clearable :code="$route.params.id"/>
          <v-spacer></v-spacer>
          <v-btn
            v-shortkey="[ 'alt', 't']"
            @shortkey="()=>{this.drawer =!this.drawer}"
            color="primary"
            class="mb-2"
            icon
            @click="drawer = !drawer"
          >
            <v-icon>vertical_split</v-icon>
          </v-btn>
        </v-toolbar>
        <v-card>
          <InfoBeneficiary :beneficiary="invoice.beneficiary"/>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn @click="chargeInvoice()" :disabled="invoice.payed" color="primary">
              <v-icon left>monetization_on</v-icon>Cobrar
            </v-btn>
            <v-btn @click="deleteUptake()" :disabled="invoice.payed" color="primary">
              <v-icon left>delete_sweep</v-icon>Eliminar
            </v-btn>
           <InvoiceSapReport :invoice="invoice"/>
          </v-card-actions>
          <v-data-table :headers="headers" :items="invoice.detail" hide-actions>
            <template slot="items" slot-scope="props">
              <td>{{ getDate(props.item.dateTaked) }}</td>
              <td>
                {{ round(props.item.currentValueTaken)}}
                <span class="grey--text">
                  <b>m³</b>
                </span>
              </td>
              <td>
                {{ round(props.item.baseVolume) }}
                <span class="grey--text">
                  <b>m³</b>
                </span>
              </td>
              <td>
                <span class="grey--text">
                  <b>$</b>
                </span>
                {{ round(props.item.basePrice) }}
              </td>
              <td>
                <span class="grey--text">
                  <b>$</b>
                </span>
                {{ round(props.item.extraPrice) }}
              </td>
              <td>
                {{ round(props.item.volumeConsumed) }}
                <span class="grey--text">
                  <b>m³</b>
                </span>
              </td>
              <td>
                {{ round(props.item.volumeExceeded) }}
                <span class="grey--text">
                  <b>m³</b>
                </span>
              </td>
              <td>
                <span class="grey--text">
                  <b>$</b>
                </span>
                {{ round( props.item.totalPrice )}}
              </td>
            </template>
            <template slot="footer">
              <td colspan="6"></td>
              <td class="primary white--text">
                <b>TOTAL</b>
              </td>
              <td>
                <span class="grey--text">
                  <b>$</b>
                </span>
                {{getTotal}}
              </td>
            </template>
          </v-data-table>
        </v-card>
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
              <v-list-tile @click="setDetail(item.uptakes)">
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
        <v-tab-item>
          <v-list>
            <div v-for="item in invoices" :key="item.id">
              <v-list-tile @click="setInvoice(item)">
                <v-list-tile-content>
                  <v-list-tile-title>
                    Factura N°
                    <span>{{item.number}}</span>
                    <v-icon v-if="item.payed" class="right" color="green">done</v-icon>
                  </v-list-tile-title>
                  <v-list-tile-sub-title>
                    <b>Emitida en</b>
                    {{item.dateOfIssue}}
                  </v-list-tile-sub-title>
                </v-list-tile-content>
              </v-list-tile>
              <v-divider></v-divider>
            </div>
          </v-list>
        </v-tab-item>
      </v-tabs>
    </v-navigation-drawer>
  </div>
</template>
<script lang="ts">
import InvoiceSapView from '@/views/admin/invoicesap/InvoiceSapView.ts'
export default InvoiceSapView
</script>
