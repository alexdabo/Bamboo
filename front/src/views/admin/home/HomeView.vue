<template>
  <v-container fluid padding>
    <v-layout row wrap>
      <v-flex v-for="(item,index) in viewItems" :key="index" xs12 sm3 md3 class="pa-2">
        <v-card @click="changeView(item.routerName)" style="cursor:pointer;">
          <v-card-title>
            <v-spacer/>
            <img :src="getImgUrl(item.imageName)" alt>
            <v-spacer/>
          </v-card-title>
          <v-divider light></v-divider>
          <v-card-actions class="pa-3 center">
            <v-spacer></v-spacer>
            <h3>{{item.title}}</h3>
            <v-spacer></v-spacer>
          </v-card-actions>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>
<script lang="ts">
import HomeView from '@/views/admin/home/HomeView.ts'
export default HomeView
</script>
