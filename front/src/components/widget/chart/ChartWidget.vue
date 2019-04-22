<template>
    <FrameWidget shadow :title="title" padding >
        <div slot="header-action">

             <div class="text-xs-center">
    <v-menu offset-y>
      <template v-slot:activator="{ on }">
        <v-btn dark icon v-on="on" >
            <v-icon>more_vert</v-icon>
        </v-btn>
      </template>
      <v-list>
        <v-list-tile v-for="(item, index) in items" :key="index"  @click="type=item.type" >
          <v-list-tile-title>{{ item.title }}</v-list-tile-title>
        </v-list-tile>
      </v-list>
    </v-menu>
  </div>
        </div>
        <div slot="content">
            <column-chart  v-if="type==='column'" :data="data" :messages="{empty: 'Sin datos'}" :xtitle="xlabel" :ytitle="ylabel"></column-chart>
            <line-chart  v-if="type==='line'" :data="data"  :messages="{empty: 'Sin datos'}"  :xtitle="xlabel" :ytitle="ylabel"></line-chart>
            <pie-chart  v-if="type==='pie'" :data="data" :messages="{empty: 'Sin datos'}" ></pie-chart>
            <bar-chart  v-if="type==='bar'" :data="data" :messages="{empty: 'Sin datos'}"  :xtitle="ylabel" :ytitle="xlabel"></bar-chart>
        </div>
    </FrameWidget>
</template>

<script>
import ChartWidget from '@/components/widget/chart/ChartWidget.ts'
export default ChartWidget
</script>
