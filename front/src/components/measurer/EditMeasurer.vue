<template>
  <form @submit.prevent="save()">
    <FrameWidget icon="edit" padding title="Editar Medidor">
      <template slot="content">
        <v-container fluid>
          <v-layout row wrap>
            <v-flex xs12>
              <v-text-field label="Número de medidor" v-model="assignedSimple.measurer.number"/>
            </v-flex>
            <v-flex xs12>
              <FindSap
                :show="assignedSimple.measurer.sap"
                @selected="setSap"
                label="Tipo de servicio"
              />
            </v-flex>
            <v-flex xs12>
              <FindStatus
                class="mt-4"
                :show="assignedSimple.measurer.status"
                @selected="setStatus"
                label="Estado del medidor"
              />
            </v-flex>
            <v-flex xs12>
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
                  v-model="assignedSimple.measurer.installationDate"
                  class="mt-4"
                  label="Fecha de instalación"
                  readonly
                ></v-text-field>
                <v-date-picker
                  v-model="assignedSimple.measurer.installationDate"
                  locale="es-mx"
                  no-title
                  scrollable
                >
                  <v-spacer></v-spacer>
                  <v-btn flat color="primary" @click="menu = false">Cancel</v-btn>
                  <v-btn flat color="primary" @click="$refs.menu.save(date)">OK</v-btn>
                </v-date-picker>
              </v-menu>
            </v-flex>
          </v-layout>
          {{assigned}}
        </v-container>
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
import EditMeasurer from '@/components/measurer/EditMeasurer.ts'
export default EditMeasurer
</script>
