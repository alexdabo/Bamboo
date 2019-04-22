<template>
  <div>
    <v-form @submit.prevent="save()" ref="form">
      <v-container>
        <v-text-field v-model="currentPass" required label="Contraseña Actual" type="password"></v-text-field>
        <v-text-field v-model="newPass" required label="Nueva Contraseña" type="password"></v-text-field>
        <v-text-field v-model="repeatedPass" required label="Repita la contraseña" type="password"></v-text-field>
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
import ChangePassword from '@/components/beneficiary/ChangePassword.ts'
export default ChangePassword
</script>
