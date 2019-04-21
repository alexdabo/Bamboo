<template>
  <div>
    <v-toolbar flat color="white">
      <v-toolbar-title>SAP</v-toolbar-title>
      <v-divider class="mx-2" inset vertical></v-divider>
      <v-spacer></v-spacer>
      <v-text-field
        solo
        v-model="search"
        label="Buscar"
        append-icon="search"
        hide-details
        clearable
      ></v-text-field>
      <v-spacer></v-spacer>
      <v-btn
        v-shortkey="['alt', 'r']"
        @shortkey="findSaps()"
        color="primary"
        class="mb-2"
        icon
        @click="findSaps()"
      >
        <v-icon>refresh</v-icon>
      </v-btn>
      <v-dialog v-model="dialog" persistent max-width="400px">
        <v-btn
          v-shortkey="['alt', 'n']"
          @shortkey="dialog=true"
          slot="activator"
          color="primary"
          icon
          class="mb-2"
        >
          <v-icon>add</v-icon>
        </v-btn>
        <v-card>
          <v-card-title class="primary lighten-3 white--text">
            <span class="headline" v-if="editedIndex === -1">Nuevo Servicio</span>
            <span class="headline" v-else>Editar Servicio</span>
          </v-card-title>
          <form @submit.prevent="submit">
            <v-container grid-list-sm class="pa-4">
              <v-layout row wrap>
                <v-flex xs12>
                  <v-text-field label="Nombre del servicio" required v-model="editedItem.name"/>
                </v-flex>
                <v-flex xs12>
                  <v-text-field
                    label="Volume base"
                    required
                    suffix="m³"
                    v-model="editedItem.baseVolume"
                    type="number"
                    min="0"
                    step="0.01"
                  />
                </v-flex>
                <v-flex xs12>
                  <v-text-field
                    label="Precio base"
                    required
                    prefix="$"
                    v-model="editedItem.basePrice"
                    type="number"
                    min="0"
                    step="0.01"
                  />
                </v-flex>
                <v-flex xs12>
                  <v-text-field
                    label="Precio extra"
                    required
                    prefix="$"
                    v-model="editedItem.extraPrice"
                    type="number"
                    min="0"
                    step="0.01"
                  />
                </v-flex>
              </v-layout>
            </v-container>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn v-shortkey="['esc']" @shortkey="close()" color="error" @click.native="close()">
                <v-icon left>cancel</v-icon>Cancelar
              </v-btn>
              <v-btn color="primary" type="submit">
                <v-icon left>save</v-icon>Guardar
              </v-btn>
            </v-card-actions>
          </form>
        </v-card>
      </v-dialog>
    </v-toolbar>
    <v-data-table
      :headers="headers"
      :items="saps"
      :search="search"
      hide-actions
      class="elevation-1"
    >
      <template slot="items" slot-scope="props">
        <td>{{ props.item.name }}</td>
        <td>{{ props.item.baseVolume }}</td>
        <td class="text-xs-left">{{ props.item.basePrice }}</td>
        <td class="text-xs-left">{{ props.item.extraPrice }}</td>
        <td class="justify-center layout px-0">
          <v-icon small class="mr-2" @click="editItem(props.item)">edit</v-icon>
          <v-icon small @click="remove(props.item)">delete</v-icon>
        </td>
      </template>
      <v-alert
        slot="no-results"
        :value="true"
        color="error"
        icon="warning"
      >No se encontro resultados para "{{ search }}".</v-alert>
    </v-data-table>
  </div>
</template>
<script lang="ts">
import SapView from '@/views/admin/sap/SapView.ts'
export default SapView
</script>
<style lang="sass">
  @import '@/views/admin/sap/SapView.sass';
</style>
