<template>
  <div>
    <v-tabs grow>
      <v-tab ripple>
        <b>Entidad</b>
      </v-tab>
      <v-tab-item class="pr-5 pl-5">
        <EntityView/>
      </v-tab-item>

      <v-tab ripple>
        <b>Información Personal</b>
      </v-tab>
      <v-tab-item class="pr-5 pl-5">
        <PersonalInfo/>
      </v-tab-item>

      <v-tab ripple>
        <b>Cambiar Contraseña</b>
      </v-tab>
      <v-tab-item class="pa-5">
        <ChangePassword/>
      </v-tab-item>
    </v-tabs>
  </div>
</template>

<script lang="ts">
import AdminSettingsView from '@/views/admin/settings/AdminSettingsView.ts'
export default AdminSettingsView
</script>
