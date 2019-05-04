<template>
  <div>
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
    >
      <v-text-field
        slot="activator"
        solo
        clearable
        v-model="date"
        label="Fecha de instalación"
        readonly
      ></v-text-field>
      <v-date-picker v-model="date" locale="es-mx" no-title scrollable>
        <v-spacer></v-spacer>
        <v-btn flat color="primary" @click="menu = false">Cancel</v-btn>
        <v-btn flat color="primary" @click="$refs.menu.save(date);listenerDate ()">OK</v-btn>
      </v-date-picker>
    </v-menu>
    {{date}}
  </div>
</template>
<script lang="ts">
import DateWidget from '@/components/widget/date/DateWidget.ts'
export default DateWidget
</script>
