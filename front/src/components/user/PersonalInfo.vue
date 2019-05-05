<template>
  <div>
    <v-form @submit.prevent="save()">
      <v-container>
        <v-text-field v-model="user.firstName" label="Apellidos" required></v-text-field>
        <v-text-field v-model="user.lastName" label="Nombres" required></v-text-field>
        <v-text-field v-model="user.dni" label="Cédula" required></v-text-field>
        <v-text-field v-model="user.userName" label="Nombre de usuario" required></v-text-field>
        <v-text-field v-model="user.telephone" label="Teléfono" required></v-text-field>
        <v-text-field v-model="user.email" label="Correo Electrónico" required></v-text-field>
        <v-text-field v-model="user.address" label="Dirección"></v-text-field>
        <v-card-actions>
          <v-btn v-if="goback" @click="$router.replace(goback)" class="red" dark>
            <v-icon left>keyboard_backspace</v-icon>Regresar
          </v-btn>
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
import PersonalInfo from '@/components/user/PersonalInfo.ts'
export default PersonalInfo
</script>
