<template>
   <v-card>
    <v-toolbar dark color="primary">
      <v-icon>swap_horiz</v-icon>

      <v-toolbar-title>Traspaso de medidor</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items>
        <v-btn dark flat @click="emiteClose()" icon>
          <v-icon>close</v-icon>
        </v-btn>
      </v-toolbar-items>
    </v-toolbar>
    <v-container class="pa-0">
      <form @submit.prevent="save">
        <v-layout row>
          <v-flex xs6>
            <FrameWidget smallTitle padding title="Propietario actual">
              <template slot="content">
                <FindBeneficiary solo clearable return="id" @selected="findAssigned"/>
                <InfoBeneficiary :basic="true" :beneficiary="currentAssigned.beneficiary"/>
                <v-list subheader two-line>
                  <v-list-tile v-for="item in currentAssigned.assigneds" :key="item.id">
                    <v-list-tile-content>
                      <v-list-tile-title>{{item.measurer.number}}</v-list-tile-title>
                      <v-list-tile-sub-title>{{item.measurer.sap.name}}</v-list-tile-sub-title>
                    </v-list-tile-content>
                    <v-list-tile-action>
                      <v-btn icon @click="toNew(item)">
                        <v-icon color="grey">send</v-icon>
                      </v-btn>
                    </v-list-tile-action>
                  </v-list-tile>
                </v-list>
              </template>
            </FrameWidget>
          </v-flex>

          <v-flex xs6>
            <FrameWidget smallTitle padding title="Propietario nuevo">
              <template slot="content">
                <FindBeneficiary solo readonly :show="newAssigned.beneficiary"/>
                <InfoBeneficiary :basic="true" :beneficiary="newAssigned.beneficiary"/>
                <v-list subheader two-line>
                  <v-list-tile v-for="item in newAssigned.assigneds" :key="item.id">
                    <v-list-tile-content>
                      <v-list-tile-title>{{item.measurer.number}}</v-list-tile-title>
                      <v-list-tile-sub-title>{{item.measurer.sap.name}}</v-list-tile-sub-title>
                    </v-list-tile-content>
                    <v-list-tile-action>
                      <v-btn icon @click="toCurrent(item)">
                        <v-icon color="grey">delete</v-icon>
                      </v-btn>
                    </v-list-tile-action>
                  </v-list-tile>
                </v-list>
              </template>
            </FrameWidget>
          </v-flex>
        </v-layout>
        <v-layout row>
          <v-flex xs12>
            <v-card-actions>
              <v-spacer/>
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
            </v-card-actions>
          </v-flex>
        </v-layout>
      </form>
    </v-container>
</v-card>
</template>
<script lang="ts">
import TransferMeasurer from '@/components/measurer/TransferMeasurer.ts'
export default TransferMeasurer
</script>
