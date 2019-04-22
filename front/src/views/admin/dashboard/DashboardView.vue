<template>
  <div>
    <v-toolbar flat color="transparent">
      <v-toolbar-title>Dashboard</v-toolbar-title>
      <v-divider class="mx-2" inset vertical></v-divider>
      <v-spacer></v-spacer>
      <v-btn
        v-shortkey="['alt', 'r']"
        @shortkey="loadStatistic()"
        color="primary"
        class="mb-2"
        icon
        @click="loadStatistic()"
      >
        <v-icon>refresh</v-icon>
      </v-btn>
    </v-toolbar>
    <v-container grid-list-xl fluid>
      <v-layout row wrap>
        <v-flex lg3 sm6 xs12>
          <MiniStatisticWidget
            icon="people"
            :amount="beneficiaries"
            title="Beneficiarios"
            color="primary"
          />
        </v-flex>
        <v-flex lg3 sm6 xs12>
          <MiniStatisticWidget icon="timer" :amount="measurers" title="Medidores" color="indigo"/>
        </v-flex>
        <v-flex lg3 sm6 xs12></v-flex>
        <v-flex lg3 sm6 xs12></v-flex>
      </v-layout>

      <v-layout row wrap>
        <v-flex md6 xs12>
          <ChartWidget
            :data="peopleFromVillage"
            title="Beneficiarios por comunidad"
            xlabel="Comunidad"
            ylabel="Beneficiarios"
          />
        </v-flex>
        <v-flex md6 xs12>
          <ChartWidget
            :data="measurerPerService"
            title="Medidores por servicio"
            xlabel="Tipo de servicio"
            ylabel="Medidores"
          />
        </v-flex>
      </v-layout>

      <v-layout row wrap>
        <v-flex md6 xs12>
          <ChartWidget
            :data="measurerPerStatus"
            title="Estado de medidores"
            xlabel="Estado"
            ylabel="Medidores"
          />
        </v-flex>
        <v-flex md6 xs12></v-flex>
      </v-layout>
    </v-container>
  </div>
</template>
<script lang="ts">
import DashboardView from '@/views/admin/dashboard/DashboardView.ts'
export default DashboardView
</script>
