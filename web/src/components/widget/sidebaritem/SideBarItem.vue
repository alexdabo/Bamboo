<template lang="html">
  <v-list-tile @click="$router.push(link)">
    <v-list-tile-action>
      <v-icon>{{icon}}</v-icon>
    </v-list-tile-action>
    <v-list-tile-content>
      <v-list-tile-title>{{title}}</v-list-tile-title>
    </v-list-tile-content>
  </v-list-tile>
</template>
<script lang="ts">
import SideBarItem from '@/components/widget/sidebaritem/SideBarItem.ts'
export default SideBarItem
</script>
