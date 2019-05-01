<template>
  <v-autocomplete
    v-model="selected"
    :items="statusList"
    :loading="isLoading"
    :search-input.sync="search"
    :filter="filter"
    :label="label"
    :solo="solo"
    :readonly="readonly"
    clearable
    hide-details
    hide-selected
    return-object
  >
    <template slot="no-data">
      <v-list-tile>
        <v-list-tile-title>
          Buscar <strong>estado</strong> de un medidir
        </v-list-tile-title>
      </v-list-tile>
    </template>
    <template slot="selection" slot-scope="{ item  }">
      <span v-text="`${item.name}`"></span>
    </template>
    <template slot="item" slot-scope="{ item}">
      <v-list-tile-avatar
        color="primary"
        class="headline font-weight-light white--text"
      >{{ item.name.charAt(0) }}</v-list-tile-avatar>
      <v-list-tile-content>
        <v-list-tile-title v-text="item.name"></v-list-tile-title>
      </v-list-tile-content>
    </template>
  </v-autocomplete>
</template>
<script lang="ts">
import FindStatus from '@/components/status/FindStatus.ts'
export default FindStatus
</script>
