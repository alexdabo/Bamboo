<template>
  <div>
    <v-card :class="[shadow ? 'no-box-shadow' : '']">
      <v-toolbar :class="colorBar" class="lighten-3" flat dense card v-if="enableHeader">
        <v-icon v-if="icon" :class="colorText">{{icon}}</v-icon>
        <v-toolbar-title>
          <h4 v-if="smallTitle===false" :class="colorText">{{title}}</h4>
          <h5 v-else :class="colorText">{{title}}</h5>
        </v-toolbar-title>
        <v-spacer></v-spacer>
        <slot name="header-action"></slot>
      </v-toolbar>
      <v-card-text class="white" :class="[padding ? 'pa-0' : 'pa-3']">
        <slot name="content"></slot>
      </v-card-text>
      <v-card-actions>
        <v-spacer/>
        <slot name="footer"></slot>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script lang="ts">
import FrameWidget from '@/components/widget/frame/FrameWidget.ts'
export default FrameWidget
</script>
<style scoped lang="sass">
  @import '@/components/widget/frame/FrameWidget.sass';
</style>
