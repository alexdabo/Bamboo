<template>
  <form @submit.prevent="save()">
    <FrameWidget icon="add" padding title="Nuevo Medidor">
      <template slot="content">
        <!--v-autocomplete
          :loading="isLoading"
          :items="sapList"
          :search-input.sync="search"
          clearable
          v-model="selected"
          return-object
          item-text="name"
          required
          cache-items
          class="pa-3"
          hide-no-data
          hide-details
          label="Tipo de servicio"
        /-->
<FindSap class="pa-3" :initialized="true" label="Tipo de servicio" @selected="setSap"/>
        <v-menu
          ref="menu"
          v-model="menu"
          :close-on-content-click="false"
          :nudge-right="40"
          :return-value.sync="date"
          lazy
          transition="scale-transition"
          offset-y
          full-width
          min-width="290px"
        >
          <v-text-field
            slot="activator"
            v-model="date"
            class="pa-3"
            label="Fecha de instalación"
            readonly
          ></v-text-field>
          <v-date-picker v-model="date" locale="es-mx" no-title scrollable>
            <v-spacer></v-spacer>
            <v-btn flat color="primary" @click="menu = false">Cancel</v-btn>
            <v-btn flat color="primary" @click="$refs.menu.save(date)">OK</v-btn>
          </v-date-picker>
        </v-menu>
      </template>
      <template slot="footer">
        <v-btn
          v-shortkey="['esc']"
          @shortkey="emiteClose()"
          color="error"
          @click.native="emiteClose()"
        >
          <v-icon left>cancel</v-icon>Cancelar
        </v-btn>
        <v-btn color="primary" type="submit">
          <v-icon left>save</v-icon>Guardar
        </v-btn>
      </template>
    </FrameWidget>
  </form>
</template>
<script lang="ts">
import NewMeasurer from '@/components/measurer/NewMeasurer.ts'
export default NewMeasurer
</script>
