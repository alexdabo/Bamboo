import Page from '@/components/widget/page/Page'
import Component from 'vue-class-component'
import { Prop, Watch, Emit } from 'vue-property-decorator'
import InvoiceSap from '@/model/entity/InvoiceSap'
import DefinitionReport from '@/util/DefinitionReport';
import Entity from '@/model/entity/Entity';
import EntityService from '@/model/service/EntityService';
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
    public created(): void {
        this.getEntity()
    }

    public createReport(): void {


        const table = {
        
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
                                [{ text: 'Fatura N° ', style: 'titleLabel' }, this.invoice.number],
                                //[{ text: 'Medidor', style: 'titleLabel' }, 'medidor 1'],
                                //[{ text: 'Servicio', style: 'titleLabel' }, 'SAP comercial']

                            ]
                        }
                    }
                ]
            ]
        }
        const definition: DefinitionReport = new DefinitionReport(this.entity)
        definition.orientation = 'landscape'
        definition.pageSize = 'A5'
        definition.title = 'FACTURA DE SERVICIOS'
        let beneficiary: string
        beneficiary = `${this.invoice.debtcollector.lastName} ${this.invoice.debtcollector.firstName}`
        definition.setInvoiceDefinition(table,beneficiary, this.invoice.payed)
        pdfmake.createPdf(definition.getDefinition()).open()
        this.loading = false
    }

    public getEntity(): void {
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
