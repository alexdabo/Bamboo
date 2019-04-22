<template>
  <v-container fluid>
    <v-layout v-if="!basic" row wrap>
      <v-flex xs12 sm6>
        <v-layout row class="ma-2">
          <v-flex xs4>
            <b>Apellidos</b>
          </v-flex>
          <v-flex xs8>{{ beneficiary.lastName }}</v-flex>
        </v-layout>
        <v-layout row class="ma-2">
          <v-flex xs4>
            <b>Nombres</b>
          </v-flex>
          <v-flex xs8>{{ beneficiary.firstName }}</v-flex>
        </v-layout>
        <v-layout row class="ma-2">
          <v-flex xs4>
            <b>Cédula</b>
          </v-flex>
          <v-flex xs8>{{ beneficiary.dni }}</v-flex>
        </v-layout>
      </v-flex>
      <v-flex xs12 sm6>
        <v-layout row class="ma-2">
          <v-flex xs4>
            <b>Comunidad</b>
          </v-flex>
          <v-flex xs8>{{ beneficiary.village.name }}</v-flex>
        </v-layout>
        <v-layout row class="ma-2">
          <v-flex xs4>
            <b>Dirección</b>
          </v-flex>
          <v-flex xs8>{{ beneficiary.address }}</v-flex>
        </v-layout>
      </v-flex>
    </v-layout>

    <v-layout v-else row wrap>
      <v-flex xs12>
        <v-layout row class="ma-2">
          <v-flex xs4>
            <b>Apellidos</b>
          </v-flex>
          <v-flex xs8>{{ beneficiary.lastName }}</v-flex>
        </v-layout>
        <v-layout row class="ma-2">
          <v-flex xs4>
            <b>Nombres</b>
          </v-flex>
          <v-flex xs8>{{ beneficiary.firstName }}</v-flex>
        </v-layout>
        <v-layout row class="ma-2">
          <v-flex xs4>
            <b>Cédula</b>
          </v-flex>
          <v-flex xs8>{{ beneficiary.dni }}</v-flex>
        </v-layout>
      </v-flex>
    </v-layout>

  </v-container>
</template>
<script lang="ts">
import InfoBeneficiary from '@/components/beneficiary/InfoBeneficiary.ts'

export default InfoBeneficiary
</script>
