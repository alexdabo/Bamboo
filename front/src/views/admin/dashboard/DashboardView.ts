import Page from '@/components/widget/page/Page'
import Component from 'vue-class-component'
import StatisticService from '@/model/service/StatisticService'
import MiniStatisticWidget from '@/components/widget/statistic/MiniStatisticWidget.vue'
import ChartWidget from '@/components/widget/chart/ChartWidget.vue'

@Component({
  name: 'view-dashboard',
  components: {
    MiniStatisticWidget,
    ChartWidget
  }
})

export default class DashboardView extends Page {
    public peopleFromVillage: any = {};
    public beneficiaries: number = 0;

    public measurerPerService: any = {};
    public measurerPerStatus: any = {};
    public measurers: number = 0;

    public created (): void {
      this.loadStatistic()
    }

    public loadStatistic (): void {
      const statisticService: StatisticService = new StatisticService()
      statisticService.get()
        .then((res: any) => {
          this.measurers = 0
          this.beneficiaries = 0
          this.setPeopleFromVillage(res.data.peopleFromVillage)
          this.setMeasurerPerService(res.data.measurerPerService)
          this.setMeasurerPerStatus(res.data.measurerPerStatus)
        })
        .catch((err: any) => {
          this.error('Error al cargar los datos', err.response.data.error)
        })
    }

    public setPeopleFromVillage (list: any): void {
      let fromVillage: string = '{'
      for (let i = 0; i < list.length; i++) {
        const item: any = list[i]
        if (i === 0) {
          fromVillage = `${fromVillage} "${item.village}": ${item.amount}`
        } else {
          fromVillage = `${fromVillage}, "${item.village}": ${item.amount}`
        }
        this.beneficiaries += item.amount
      }
      fromVillage = `${fromVillage}}`
      this.peopleFromVillage = JSON.parse(fromVillage)
    }

    public setMeasurerPerService (list: any): void {
      let measurer: string = '{'
      for (let i = 0; i < list.length; i++) {
        const item: any = list[i]
        if (i === 0) {
          measurer = `${measurer} "${item.service}": ${item.amount}`
        } else {
          measurer = `${measurer}, "${item.service}": ${item.amount}`
        }
      }
      measurer = `${measurer}}`
      this.measurerPerService = JSON.parse(measurer)
    }

    public setMeasurerPerStatus (list: any): void {
      let measurer: string = '{'
      for (let i = 0; i < list.length; i++) {
        const item: any = list[i]
        if (i === 0) {
          measurer = `${measurer} "${item.status}": ${item.amount}`
        } else {
          measurer = `${measurer}, "${item.status}": ${item.amount}`
        }
        this.measurers += item.amount
      }
      measurer = `${measurer}}`
      this.measurerPerStatus = JSON.parse(measurer)
    }
}
