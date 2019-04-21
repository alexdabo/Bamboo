<template>
  <div>
    <v-toolbar flat color="white">
      <v-toolbar-title>Otros</v-toolbar-title>
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
        @shortkey="findAnothers()"
        color="primary"
        class="mb-2"
        icon
        @click="findAnothers()"
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
                    label="Precio del sercicio"
                    required
                    prefix="$"
                    v-model="editedItem.price"
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
      :items="anothers"
      :search="search"
      hide-actions
      class="elevation-1"
    >
      <template slot="items" slot-scope="props">
        <td>{{ props.item.name }}</td>
        <td>{{ props.item.price }}</td>
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
import AnotherView from '@/views/admin/another/AnotherView.ts'
export default AnotherView
</script>
