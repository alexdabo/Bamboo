<template>
  <div>
    <v-btn style="display:none" v-shortkey="['alt', 'l']" @shortkey="selected=0"/>
    <v-toolbar color="white">
      <v-toolbar-title>MEDIDORES</v-toolbar-title>
      <v-divider class="mx-2" inset vertical></v-divider>
      <v-spacer></v-spacer>
      <FindBeneficiary
        return="id"
        @selected="findAssignedByBeneficiary"
        :code="$route.params.beneficiaryId"
        solo
      />

      <v-spacer></v-spacer>
      <v-btn
        v-shortkey="[ 'alt', 'r']"
        @shortkey="findAssignedByBeneficiary(assigned.beneficiary.id)"
        color="primary"
        class="mb-2"
        icon
        :disabled="assigned.beneficiary.id===0"
        @click="findAssignedByBeneficiary(assigned.beneficiary.id)"
      >
        <v-icon>refresh</v-icon>
      </v-btn>

      <v-menu
        :disabled="assigned.beneficiary.id===0"
        offset-y
        origin="top bottom"
        transition="scale-transition"
      >
        <v-btn :disabled="assigned.beneficiary.id===0" slot="activator" color="primary" icon>
          <v-icon>more_vert</v-icon>
        </v-btn>
        <v-list>
          <v-list-tile @click="dialogNewMeasurer=true">
            <v-list-tile-avatar v-shortkey="['alt', 'n']" @shortkey="dialogNewMeasurer=true">
              <v-icon>add</v-icon>
            </v-list-tile-avatar>
            <v-list-tile-content>
              <v-list-tile-title>Nuevo Medidor</v-list-tile-title>
            </v-list-tile-content>
          </v-list-tile>

          <v-list-tile @click="dialogTransferMeasurer=true">
            <v-list-tile-avatar v-shortkey="['alt', 't']" @shortkey="dialogTransferMeasurer=true">
              <v-icon>swap_horiz</v-icon>
            </v-list-tile-avatar>
            <v-list-tile-content>
              <v-list-tile-title>Traspaso de medidor</v-list-tile-title>
            </v-list-tile-content>
          </v-list-tile>
        </v-list>
      </v-menu>
      <v-dialog
        v-model="dialogTransferMeasurer"
        fullscreen
        hide-overlay
        persistent
        transition="dialog-bottom-transition"
      >
        <TransferMeasurer
          :beneficiary="assigned.beneficiary"
          @close="dialogTransferMeasurer=false"
          @saved="findAssignedByBeneficiary"
        />
      </v-dialog>

      <v-dialog v-model="dialogNewMeasurer" persistent max-width="400px">
        <NewMeasurer
          :beneficiary="assigned.beneficiary"
          @close="dialogNewMeasurer=false"
          @saved="findAssignedByBeneficiary"
        />
      </v-dialog>
      <v-dialog v-model="dialogEditMeasurer" persistent max-width="400px">
        <EditMeasurer
          :assigned="editAssigned"
          @close="dialogEditMeasurer=false"
          @saved="findAssignedByBeneficiary"
        />
      </v-dialog>
    </v-toolbar>
    <v-card>
      <InfoBeneficiary :beneficiary="assigned.beneficiary"/>
      <v-data-table :headers="headers" :items="assigned.assigneds" hide-actions>
        <template slot="items" slot-scope="props">
          <td>{{ props.item.measurer.number }}</td>
          <td>{{ props.item.measurer.sap.name }}</td>
          <td>{{ props.item.measurer.installationDate }}</td>
          <td class="text-xs-center" v-if="props.item.status==='disable'" >
            <span class="red">Sin servicio</span>
          </td>
          <td v-else class="text-xs-center">
            <span
              v-if="props.item.measurer.status.id===1"
              class="success"
            >{{ props.item.measurer.status.name }}</span>
            <span
              v-if="props.item.measurer.status.id===2"
              class="warning"
            >{{ props.item.measurer.status.name }}</span>
            <span
              v-if="props.item.measurer.status.id===3"
              class="warning"
            >{{ props.item.measurer.status.name }}</span>
            <span
              v-if="props.item.measurer.status.id===4 && props.item.status==='disable'"
              class="orange"
            >{{ props.item.measurer.status.name }}</span>
          </td>
          <td class="text-xs-right">${{ props.item.debt }}</td>
          <td class="text-xs-center">
            <v-btn icon dark @click="toEdit(props.item)">
              <v-icon color="grey">edit</v-icon>
            </v-btn>
          </td>
        </template>
      </v-data-table>
    </v-card>
  </div>
</template>
<script lang="ts">
import MeasurerView from '@/views/admin/measurer/MeasurerView.ts'

export default MeasurerView
</script>
<style lang="scss" scoped>
td span {
  border-radius: 5px;
  padding: 2px 5px;
  color: white;
}
</style>
