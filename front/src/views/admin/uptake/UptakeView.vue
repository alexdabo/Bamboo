<template>
  <div>
    <v-card>
      <v-toolbar flat color="white">
        <v-toolbar-title>MEDIDAS</v-toolbar-title>
        <v-divider class="mx-2" inset vertical></v-divider>
        <v-spacer></v-spacer>

        <v-text-field
          solo
          v-model="search"
          label="Buscar medidor"
          append-icon="search"
          hide-details
          clearable
        />
        <button v-shortkey="['enter']" @shortkey="findMeasurer()"/>

        <v-spacer></v-spacer>
        <v-btn
          color="primary"
          class="mb-2"
          icon
          v-shortkey="['alt' + 'l']"
          :disabled="!history.length>0"
          @shortkey="search=''"
          @click="drawerHistory=!drawerHistory"
        >
          <v-icon>vertical_split</v-icon>
        </v-btn>
      </v-toolbar>

      <InfoBeneficiary :beneficiary="beneficiary"/>
      <form @submit.prevent="save()">
        <v-container>
          <v-layout row wrap>
            <v-flex xs12 sm6>
              <v-text-field
                box
                color="primary"
                disabled
                v-model="measurer.number"
                background-color="white"
                label="Número de medidor"
                prepend-inner-icon="code"
              ></v-text-field>
            </v-flex>

            <v-flex xs12 sm6>
              <v-text-field
                box
                color="primary"
                disabled
                v-model="measurer.status.name"
                background-color="white"
                label="Estado del medidor"
                prepend-inner-icon="settings_applications"
              ></v-text-field>
            </v-flex>
          </v-layout>
          <v-layout row wrap>
            <v-flex xs12 sm6>
              <v-dialog
                ref="dialog"
                v-model="modal"
                :return-value.sync="date"
                persistent
                lazy
                full-width
                width="290px"
              >
                <v-text-field
                  slot="activator"
                  v-model="date"
                  box
                  label="Fecha de reguistro"
                  background-color="white"
                  prepend-inner-icon="today"
                  readonly
                ></v-text-field>
                <v-date-picker v-model="date" locale="es-mx">
                  <v-spacer></v-spacer>
                  <v-btn flat color="primary" @click="modal = false">Cancel</v-btn>
                  <v-btn flat color="primary" @click="$refs.dialog.save(date)">OK</v-btn>
                </v-date-picker>
              </v-dialog>
            </v-flex>

            <v-flex xs12 sm6>
              <v-text-field
                box
                v-model="uptake.currentValueTaken"
                color="primary"
                background-color="white"
                label="Valor marcado"
                prepend-inner-icon="bookmark"
                :disabled="disableValue(measurer.status.id)"
                type="number"
                step="0.01"
                :min="lastValue"
              ></v-text-field>
            </v-flex>
          </v-layout>
        </v-container>
        <v-card-actions>
          <v-spacer/>

          <v-btn
            type="submit"
            :disabled="measurer.status.id === 2 && measurer.status.id ===4"
            color="primary"
            class="white--text"
          >
            Guardar
            <v-icon right dark>cloud_upload</v-icon>
          </v-btn>
        </v-card-actions>
      </form>
    </v-card>
    <v-navigation-drawer fixed v-model="drawerHistory" right clipped app>
      <v-tabs color="primary" dark slider-color="blue-grey darken-2" grow>
        <v-tab ripple>
          <b>Historial de medidas</b>
        </v-tab>
        <v-tab-item>
          <v-list>
            <div v-for="item in history" :key="item.id">
              <v-list-tile>
                <v-list-tile-content>
                  <v-list-tile-title>
                    Fecha:
                    <span>{{item.dateTaked}}</span>
                    <v-icon v-if="item.billed" class="right" color="green">done</v-icon>
                  </v-list-tile-title>
                  <v-list-tile-sub-title>
                    Valor marcado
                    {{item.currentValueTaken}}
                    <span>m³</span>
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
import UptakeView from '@/views/admin/uptake/UptakeView.ts'
export default UptakeView
</script>
