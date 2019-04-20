<template>
  <div id="app">
    <v-app id="inspire">
      <!--SIDEBAR-->
      <v-navigation-drawer fixed v-model="drawer" app dark>
        <v-toolbar dark @click="changeView('home')" class="home_logo">
          <img src="@/assets/logo.svg" width="53" alt>
          <v-toolbar-title class="white--text">Bamboo</v-toolbar-title>
        </v-toolbar>
        <v-list>
          <v-list-tile
            v-for="(item,index) in sideBarItems"
            :key="index"
            @click="changeView(item.name)"
          >
            <v-list-tile-action>
              <v-icon>{{item.icon}}</v-icon>
            </v-list-tile-action>
            <v-list-tile-content>
              <v-list-tile-title>{{item.title}}</v-list-tile-title>
            </v-list-tile-content>
          </v-list-tile>
        </v-list>
      </v-navigation-drawer>

      <!--TOOLBAR-->
      <v-toolbar fixed app class="white content_toolbar">
        <v-toolbar-side-icon @click.stop="drawer = !drawer"></v-toolbar-side-icon>
        <v-toolbar-title>Gestión JAP</v-toolbar-title>
        <v-spacer></v-spacer>

        <v-menu offset-y origin="top bottom" :nudge-bottom="10" transition="scale-transition">
          <v-btn icon large flat slot="activator">
            <v-icon>account_circle</v-icon>
          </v-btn>
          <v-card class="elevation-0">
            <v-toolbar card dense color="transparent">
              <v-toolbar-title>
                <!--h5>{{this.$store.state.user.lastName}} {{this.$store.state.user.firstName}}</h5-->
              </v-toolbar-title>
            </v-toolbar>
            <v-divider></v-divider>
            <v-card-text class="pa-0">
              <v-list dense>
                <v-list-tile
                  v-for="(item,index) in optionItems"
                  :key="index"
                  @click="changeView(item.name)"
                >
                  <v-list-tile-action>
                    <v-icon>{{item.icon}}</v-icon>
                  </v-list-tile-action>
                  <v-list-tile-content>
                    <v-list-tile-title>{{item.title}}</v-list-tile-title>
                  </v-list-tile-content>
                </v-list-tile>
              </v-list>
            </v-card-text>
          </v-card>
        </v-menu>
      </v-toolbar>

      <!--CONTAINER-->
      <v-content>
        <router-view tag="v-container" fluid fill-height></router-view>
      </v-content>
    </v-app>
  </div>
</template>

<script lang="ts">
import AdminView from '@/views/admin/AdminView.ts'
export default AdminView
</script>
