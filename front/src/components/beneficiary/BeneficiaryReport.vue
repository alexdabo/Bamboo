<template>
  <v-container grid-list-xl fluid>
    <v-layout row wrap>
      <v-flex xm12>
        <FrameWidget shadow title="Beneficiarios" padding icon="people">
          <div slot="content">
            <v-container fluid class="pa-0">
              <v-layout row wrap>
                <v-flex xs12 sm4 md4>
                  <v-text-field
                  v-model="config.data"
                  label="Buscar"
                  single-line
                  hide-details
                  clearable/>
                </v-flex>
                <v-flex xs12 sm4 md4>
                  <v-select
                    :items="villages"
                    v-model="config.village"
                    label="Comunidad"
                    item-text="name"
                    item-value="id"
                    return-object
                  />
                </v-flex>
                <v-flex xs12 sm4 md4>
                  <v-select
                    :items="orientation"
                    v-model="config.orientation"
                    label="Orientación"
                    item-text="name"
                    item-value="value"
                  />
                </v-flex>
              </v-layout>
            </v-container>
          </div>
          <div slot="footer">
            <v-btn color="primary" :loading="loadingOpen" @click="openPdf()">
              <v-icon left>open_in_browser</v-icon>Abrir
            </v-btn>
            <v-btn color="primary" :loading="loadingDownload" @click="downloadPdf()">
              <v-icon left>cloud_download</v-icon>Descargar
            </v-btn>
          </div>
        </FrameWidget>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script lang="ts">
import BeneficiaryReport from '@/components/beneficiary/BeneficiaryReport.ts'
export default BeneficiaryReport
</script>
