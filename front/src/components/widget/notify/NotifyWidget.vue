<template>
  <div>
    <v-snackbar
      :color="getColor"
      v-model="$store.state.notify.show"
      :timeout="timeout"
      top
      right
      auto-height
    >
      <v-container fluid>
        <v-layout row>
          <v-flex xs2 text-xs-center style="background-color:rgba(0,0,0,0.15)">
            <v-icon x-large dark>{{getIcon}}</v-icon>
          </v-flex>
          <v-flex xs8>
            <v-card-title primary-title class="pt-3">
              <div>
                <div>
                  <b>{{$store.state.notify.title}}</b>
                </div>
                <span>{{$store.state.notify.msg}}</span>
              </div>
            </v-card-title>
          </v-flex>
          <v-flex xs2 text-xs-center>
            <v-btn v-if="!$store.state.notify.msg" class="ma-0 pt-3" flat @click="close()">
              <v-icon>close</v-icon>
            </v-btn>
            <v-btn v-else class="ma-0" flat @click="close()">
              <v-icon>close</v-icon>
            </v-btn>
          </v-flex>
        </v-layout>
      </v-container>
    </v-snackbar>
  </div>
</template>

<script lang="ts">
import NotifyWidget from '@/components/widget/notify/NotifyWidget.ts'
export default NotifyWidget
</script>
<style lang="sass">
    @import "@/components/widget/notify/NotifyWidget.sass";
</style>
