<template>
  <div>
    <v-form @submit.prevent="save()">
      <v-container>
        <v-text-field v-model="entity.province" label="Provincia" required></v-text-field>
        <v-text-field v-model="entity.canton" label="Cantón" required></v-text-field>
        <v-text-field v-model="entity.community" label="Comunidad" required></v-text-field>
        <v-text-field v-model="entity.address" label="Dirección"></v-text-field>
        <v-text-field v-model="entity.telephone" label="Teléfono" required></v-text-field>
        <v-text-field v-model="entity.email" label="Correo Electrónico" required></v-text-field>
        <v-text-field v-model="entity.ruc" label="RUC"></v-text-field>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn type="submit" color="primary">
            <v-icon left>save</v-icon>Guardar
          </v-btn>
        </v-card-actions>
      </v-container>
    </v-form>
  </div>
</template>
<script lang="ts">
import EntityView from '@/views/admin/entity/EntityView.ts'
export default EntityView
</script>
