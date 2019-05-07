import Page from '@/components/widget/page/Page'
import Component from 'vue-class-component'
import { Prop, Watch, Emit } from 'vue-property-decorator'
import InvoiceSap from '@/model/entity/InvoiceSap'
import DefinitionReport from '@/util/DefinitionReport'
import Entity from '@/model/entity/Entity'
import EntityService from '@/model/service/EntityService'
import Uptake from '@/model/entity/Uptake'
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
                  [{ text: 'Fecha de emisión ', style: 'titleLabel' }, this.invoice.dateOfIssue],
                  [{ text: 'Fatura N° ', style: 'titleLabel' }, this.invoice.number]
                  // [{ text: 'Medidor', style: 'titleLabel' }, 'medidor 1'],
                  // [{ text: 'Servicio', style: 'titleLabel' }, 'SAP comercial']

                ]
              }
            }
          ]
        ]
      }
      // solo funciona con un detalle por el momento
      // https://github.com/AlexanderDa/Senagua/blob/master/web/js/report/sapinvoice.js
      let detail: any[] = []
      for (let uptake of this.invoice.detail) {
        detail = [
          { text: [uptake.lastValueTaken, { text: 'm³', color: '#ddd' }], alignment: 'right', style: 'tcell' },
          { text: [uptake.currentValueTaken, { text: 'm³', color: '#ddd' }], alignment: 'right', style: 'tcell' },
          { text: [uptake.volumeConsumed, { text: 'm³', color: '#ddd' }], alignment: 'right', style: 'tcell' },
          { text: ['12', { text: 'm³', color: '#ddd' }], alignment: 'right', style: 'tcell' },
          { text: [uptake['volumeExceeded'], { text: 'm³', color: '#ddd' }], alignment: 'right', style: 'tcell' },
          { text: [{ text: '$', color: '#ddd' }, '1'], alignment: 'right', style: 'tcell' },
          { text: [{ text: '$', color: '#ddd' }, '1'], alignment: 'right', style: 'tcell' },
          { text: [{ text: '$', color: '#ddd' }, '1'], alignment: 'right', style: 'tcell' },
          { text: [{ text: '$', color: '#ddd' }, '1'], alignment: 'right', style: 'tcell' }
        ]
      }
      const detailDefinition = {
        headerRows: 1,
        widths: ['*', '*', '*', '*', '*', '*', '*', '*', '*'],
        body: [
          [
            { text: 'LECTURAS', style: 'thead', colSpan: 2 },
            { text: '', style: 'thead' },
            { text: 'CONSUMO', style: 'thead', colSpan: 3 },
            { text: '', style: 'thead' },
            { text: '', style: 'thead' },
            { text: 'TARIFA', style: 'thead', colSpan: 3 },
            { text: '', style: 'thead' },
            { text: '', style: 'thead' },
            { text: 'MONTO', style: 'thead' }
          ],
          [
            { text: 'Anterior', style: 'thead' },
            { text: 'Actual', style: 'thead' },
            { text: 'Mensual', style: 'thead' },
            { text: 'Básico', style: 'thead' },
            { text: 'Exceso', style: 'thead' },
            { text: 'Básico', style: 'thead' },
            { text: 'Exceso', style: 'thead' },
            { text: 'Subtotal', style: 'thead' },
            { text: 'Total', style: 'thead' }
          ],
          detail,
          [
            {
              text: '',
              alignment: 'center',
              colSpan: 7,
              style: 'tcell',
              border: [false, false, false, false]
            }, '', '', '', '', '', '', { text: 'Total', style: 'thead' }, { text: [{ text: '$', color: '#ddd' }, this.invoice.totalToPay], alignment: 'right', style: 'tcell' }
          ]
        ]
      }
      const definition: DefinitionReport = new DefinitionReport(this.entity)
      definition.orientation = 'landscape'
      definition.pageSize = 'A5'
      definition.title = 'FACTURA DE SERVICIOS'
      let beneficiary: string
      beneficiary = `${this.invoice.debtcollector.lastName} ${this.invoice.debtcollector.firstName}`
      definition.setInvoiceDefinition(infoDefinition, detailDefinition, beneficiary, this.invoice.payed)
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
}
