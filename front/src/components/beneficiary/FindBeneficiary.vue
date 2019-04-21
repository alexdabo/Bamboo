<template>
  <v-autocomplete
    v-model="selected"
    :items="beneficiaries"
    :loading="isLoading"
    :search-input.sync="search"
    :clearable="clearable"
    :filter="filter"
    :label="label"
    hide-details
    hide-selected
    return-object
    solo
    :readonly="readonly"
  >
    <template slot="no-data">
      <v-list-tile>
        <v-list-tile-title>
          Buscar por <strong>cédula</strong> o por <strong>nombres</strong>
        </v-list-tile-title>
      </v-list-tile>
    </template>
    <template slot="selection" slot-scope="{ item  }">
      <span v-text="`${item.lastName}  ${item.firstName}`"></span>
    </template>
    <template slot="item" slot-scope="{ item}">
      <v-list-tile-avatar
        color="primary"
        class="headline font-weight-light white--text"
      >{{ item.lastName.charAt(0) }}</v-list-tile-avatar>
      <v-list-tile-content>
        <v-list-tile-title v-text="item.dni"></v-list-tile-title>
        <v-list-tile-sub-title v-text="`${item.lastName}  ${item.firstName}`"></v-list-tile-sub-title>
      </v-list-tile-content>
    </template>
  </v-autocomplete>
</template>
<script lang="ts">
import FindBeneficiary from '@/components/beneficiary/FindBeneficiary.ts'
export default FindBeneficiary
</script>
