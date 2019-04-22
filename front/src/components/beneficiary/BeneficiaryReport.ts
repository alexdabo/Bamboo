import Page from '@/components/widget/page/Page'
import Component from 'vue-class-component'
import Village from '@/model/entity/Village'
import DefinitionReport from '@/util/DefinitionReport'
import VillageService from '@/model/service/VillageService'
import Beneficiary from '@/model/entity/Beneficiary'
import BeneficiaryService from '@/model/service/BeneficiaryService'

import FrameWidget from '@/components/widget/frame/FrameWidget.vue'
import Entity from '@/model/entity/Entity'
import EntityService from '@/model/service/EntityService'

const pdfmake = require('pdfmake/build/pdfmake.js')
const pdffont = require('pdfmake/build/vfs_fonts.js')
pdfmake.vfs = pdffont.pdfMake.vfs

@Component({
  name: 'beneficiary-report',
  components: { FrameWidget }
})

export default class BeneficiaryReport extends Page {
  public loadingOpen: boolean = false
  public loadingDownload: boolean = false
  public orientation: any = [
    {
      name: 'Horizontal',
      value: 'landscape'
    },
    {
      name: 'Vertical',
      value: 'portrait'
    }
  ];
  public config: any = {
    data: '',
    village: new Village(0, 'Todos'),
    orientation: 'portrait'
  };

  public villages: Village[] = [new Village(0, 'Todos')];
  public beneficiaries: Beneficiary[] = [];
  public entity: Entity = new Entity();

  public created(): void {
    this.findVillages()
    this.getEntity()
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

  public findVillages(): void {
    const service: VillageService = new VillageService()
    service.getAll().then((res: any) => {
      for (const item of res.data) {
        this.villages.push(item)
      }
    }).catch((err) => {
      this.error('Error al buscar comunidades', err.response.data.error)
    })
  }

  public createPdf(option: string = 'open'): void {
    const service: BeneficiaryService = new BeneficiaryService()
    service.getByData(this.config.data, this.config.village.id).then((res: any) => {
      const content: any[] = []

      // header of the table
      content.push([
        { text: '#', style: 'thead' },
        { text: 'Cédula', style: 'thead' },
        { text: 'Nombres', style: 'thead' },
        { text: 'Comunidad', style: 'thead' },
        { text: 'Teléfono', style: 'thead' },
        { text: 'Dirección', style: 'thead' },
        { text: 'Referencia', style: 'thead' }
      ])

      for (let i = 0; i < res.data.length; i++) {
        let beneficiary: Beneficiary = new Beneficiary()
        beneficiary = res.data[i]
        content.push([
          { text: (i + 1), style: 'tcell' },
          { text: beneficiary.dni, style: 'tcell' },
          { text: beneficiary.lastName + ' ' + beneficiary.firstName, style: 'tcell' },
          { text: beneficiary.village.name, style: 'tcell' },
          { text: beneficiary.telephone, style: 'tcell' },
          { text: beneficiary.address, style: 'tcell' },
          { text: beneficiary.placeReference, style: 'tcell' }
        ])
      }

      const table: any = {
        widths: ['auto', 'auto', '*', 'auto', 'auto', 'auto', 'auto'],
        body: content
      }
      const definition: DefinitionReport = new DefinitionReport(this.entity)
      definition.orientation = this.config.orientation
      definition.title = 'Lista de Beneficiarios'
      definition.setTableDefinition(table)

      switch (option.toLowerCase()) {
        case 'open': {
          pdfmake.createPdf(definition.getDefinition()).open()
          break
        }
        case 'download': {
          pdfmake.createPdf(definition.getDefinition()).download('Lista de beneficiarios.pdf')
          break
        }
      }
    }).catch((err: any) => {
      this.error('Error al buscar beneficiarios', err.response.statusText)
    }).finally(() => {
      this.loadingOpen = false
      this.loadingDownload = false
    })
  }

  public createPdfByVillage(option: string = 'open'): void {
    const service: BeneficiaryService = new BeneficiaryService()
    service.getByData(this.config.data, this.config.village.id).then((res: any) => {
      const content: any[] = []

      // header of the table
      content.push([
        { text: '#', style: 'thead' },
        { text: 'Cédula', style: 'thead' },
        { text: 'Nombres', style: 'thead' },
        { text: 'Teléfono', style: 'thead' },
        { text: 'Dirección', style: 'thead' },
        { text: 'Referencia', style: 'thead' }
      ])

      for (let i = 0; i < res.data.length; i++) {
        let beneficiary: Beneficiary = new Beneficiary()
        beneficiary = res.data[i]
        content.push([
          { text: (i + 1), style: 'tcell' },
          { text: beneficiary.dni, style: 'tcell' },
          { text: beneficiary.lastName + ' ' + beneficiary.firstName, style: 'tcell' },
          { text: beneficiary.telephone, style: 'tcell' },
          { text: beneficiary.address, style: 'tcell' },
          { text: beneficiary.placeReference, style: 'tcell' }
        ])
      }

      const table: any = {
        widths: ['auto', 'auto', '*', 'auto', 'auto', 'auto'],
        body: content
      }
      const definition: DefinitionReport = new DefinitionReport(this.entity)
      definition.orientation = this.config.orientation
      definition.title = `Beneneficiarios de ${this.config.village.name}`
      definition.setTableDefinition(table)
      switch (option.toLowerCase()) {
        case 'open': {
          pdfmake.createPdf(definition.getDefinition()).open()
          break
        }
        case 'download': {
          pdfmake.createPdf(definition.getDefinition())
            .download(`Beneficiarios de ${this.config.village.name}.pdf`)
          break
        }
      }
    }).catch((err) => {
      this.error('Error al buscar beneficiarios', err.response.statusText)
    })
      .finally(() => {
        this.loadingOpen = false
        this.loadingDownload = false
      })
  }

  public openPdf(): void {
    this.loadingOpen = true;
    if (this.config.village.id === 0) {
      this.createPdf()
    } else {
      this.createPdfByVillage()
    }
  }

  public downloadPdf(): void {
    this.loadingDownload = true
    if (this.config.village.id === 0) {
      this.createPdf('download')
    } else {
      this.createPdfByVillage('download')
    }
  }
}
