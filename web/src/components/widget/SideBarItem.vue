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

<script>
export default {
	name: 'side-bar-item',
	props: {
		icon: String,
		title: String,
		link: String
	}
}
</script>

<style lang="css">
</style>
