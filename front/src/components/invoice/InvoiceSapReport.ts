import Page from '@/components/widget/page/Page'
import Component from 'vue-class-component'
import { Prop } from 'vue-property-decorator'
import InvoiceSap from '@/model/entity/InvoiceSap'
import DefinitionReport from '@/util/DefinitionReport'
import Entity from '@/model/entity/Entity'
import EntityService from '@/model/service/EntityService'
const pdfmake = require('pdfmake/build/pdfmake.js')
const pdffont = require('pdfmake/build/vfs_fonts.js')
pdfmake.vfs = pdffont.pdfMake.vfs

@Component({
  name: 'invoice-sap-report'
})

export default class InvoiceSapReport extends Page {
  @Prop({}) public invoice!: InvoiceSap
  public entity: Entity = new Entity();
  public loading: boolean = false
  public created (): void {
    this.getEntity()
  }

  public createReport (): void {
    if (!this.invoice.payed) {
      this.invoice.number = 0
      this.invoice.debtcollector = this.getUser()
      this.invoice.dateOfIssue = ''
    }
    const infoDefinition = {
      widths: ['*', '*'],
      body: [
        [
          {
            style: 'infoLabel',
            layout: 'noBorders',
            table: {
              widths: ['auto', '*'],
              body: [
                [{ text: 'Nombre', style: 'titleLabel' }, this.invoice.beneficiary.lastName + ' ' + this.invoice.beneficiary.firstName],
                [{ text: 'Cédula', style: 'titleLabel' }, this.invoice.beneficiary.dni],
                [{ text: 'Comunidad', style: 'titleLabel' }, this.invoice.beneficiary.village.name],
                [{ text: 'Dirección', style: 'titleLabel' }, this.invoice.beneficiary.address]
              ]
            }
          },
          {
            style: 'infoLabel',
            layout: 'noBorders',
            table: {
              widths: ['auto', '*'],
              body: [
                // eslint-disable-next-line
                [{ text: 'RUC', style: 'titleLabel' }, this.entity.ruc !== '' ? this.entity.ruc : 'SIN RUC' ],
                [{ text: 'Fecha de emisión ', style: 'titleLabel' }, this.invoice.dateOfIssue],
                [{ text: 'Fatura N° ', style: 'titleLabel' }, this.invoice.number]
              ]

            }
          }
        ]
      ]
    }

    let detail: any[] = [
      [
        { text: 'MES', style: 'thead' },
        { text: 'LECTURAS', style: 'thead', colSpan: 2 },
        { text: '', style: 'thead' },
        { text: 'CONSUMO', style: 'thead', colSpan: 3 },
        { text: '', style: 'thead' },
        { text: '', style: 'thead' },
        { text: 'TARIFA', style: 'thead', colSpan: 2 },
        { text: '', style: 'thead' },
        { text: 'MONTO', style: 'thead' }
      ],
      [
        { text: 'Fecha', style: 'thead' },
        { text: 'Anterior', style: 'thead' },
        { text: 'Actual', style: 'thead' },
        { text: 'Mensual', style: 'thead' },
        { text: 'Básico', style: 'thead' },
        { text: 'Exceso', style: 'thead' },
        { text: 'Básico', style: 'thead' },
        { text: 'Exceso', style: 'thead' },
        { text: 'Total', style: 'thead' }
      ]
    ]
    for (let uptake of this.invoice.detail) {
      detail.push([
        { text: [this.getDate(uptake.dateTaked)], alignment: 'right', style: 'tcell' },
        { text: [this.round(uptake.lastValueTaken), { text: 'm³', color: '#ddd' }], alignment: 'right', style: 'tcell' },
        { text: [this.round(uptake.currentValueTaken), { text: 'm³', color: '#ddd' }], alignment: 'right', style: 'tcell' },
        { text: [this.round(uptake.volumeConsumed), { text: 'm³', color: '#ddd' }], alignment: 'right', style: 'tcell' },
        { text: [this.round(uptake.baseVolume), { text: 'm³', color: '#ddd' }], alignment: 'right', style: 'tcell' },
        { text: [this.round(uptake.volumeExceeded), { text: 'm³', color: '#ddd' }], alignment: 'right', style: 'tcell' },
        { text: [{ text: '$', color: '#ddd' }, this.round(uptake.basePrice)], alignment: 'right', style: 'tcell' },
        { text: [{ text: '$', color: '#ddd' }, this.round(uptake.extraPrice)], alignment: 'right', style: 'tcell' },
        { text: [{ text: '$', color: '#ddd' }, this.round(uptake.totalPrice)], alignment: 'right', style: 'tcell' }
      ])
    }
    detail.push([
      {
        text: '',
        alignment: 'center',
        colSpan: 7,
        style: 'tcell',
        border: [false, false, false, false]
      }, '', '', '', '', '', '', { text: 'Total', style: 'thead' }, { text: [{ text: '$', color: '#ddd' }, this.round(this.invoice.totalToPay)], alignment: 'right', style: 'tcell' }
    ])
    const detailDefinition = {
      headerRows: 1,
      widths: ['auto', '*', '*', '*', '*', '*', '*', '*', '*'],
      body: detail
    }
    const definition: DefinitionReport = new DefinitionReport(this.entity)
    definition.orientation = 'landscape'
    definition.pageSize = 'A5'
    definition.title = 'FACTURA DE SERVICIOS'
    let debtcollector: string
    debtcollector = `${this.invoice.debtcollector.lastName} ${this.invoice.debtcollector.firstName}`
    definition.setInvoiceDefinition(infoDefinition, detailDefinition, debtcollector, this.invoice.payed)
    pdfmake.createPdf(definition.getDefinition()).open()
    this.loading = false
  }

  public getEntity (): void {
    const entityService: EntityService = new EntityService()
    entityService.get()
      .then((res: any) => {
        this.entity = res.data
      })
      .catch((err: any) => {
        this.error('Error al cargar los datos', err.response.data.error)
      })
  }

  public round (value: any) {
    return parseFloat(value.toFixed(2))
  }
  public getDate (date: any): string {
    date = date.substr(0, 7)
    switch (date.substr(5, 6)) {
      case '01':
        date = `Enero ${date.substr(0, 4)}`
        break
      case '02':
        date = `Febrero ${date.substr(0, 4)}`
        break
      case '03':
        date = `Marzo ${date.substr(0, 4)}`
        break
      case '04':
        date = `Abril ${date.substr(0, 4)}`
        break
      case '05':
        date = `Mayo ${date.substr(0, 4)}`
        break
      case '06':
        date = `Junio ${date.substr(0, 4)}`
        break
      case '07':
        date = `Julio ${date.substr(0, 4)}`
        break
      case '08':
        date = `Agosto ${date.substr(0, 4)}`
        break
      case '09':
        date = `Septiembre ${date.substr(0, 4)}`
        break
      case '10':
        date = `Octubre ${date.substr(0, 4)}`
        break
      case '11':
        date = `Noviembre ${date.substr(0, 4)}`
        break
      case '12':
        date = `Diciembre ${date.substr(0, 4)}`
        break
    }
    return date
  }
}
