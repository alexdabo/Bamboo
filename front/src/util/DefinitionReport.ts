import Entity from '@/model/entity/Entity'

export default class DefinitionReport {
  public title: string = '';
  public orientation: string = '';
  public pageSize: string = '';
  private definition: any = {};
  // eslint-disable-next-line
  constructor(private entity: any) {
  }

  public setTableDefinition (definition: any): void {
    this.entityUpperCase()
    this.definition = {
      pageOrientation: this.orientation,
      pageSize: this.pageSize,
      content: [
        {
          layout: 'noBorders',
          table: {
            headerRows: 1,
            widths: ['*'],
            body: [
              [{
                text: `JUNTA ADMINISTRADORA DEL AGUA POTABLE DE ${this.entity.community}
                            PROVINCIA DE ${this.entity.province} \t CANTON: ${this.entity.canton}
                             ${this.title}`,
                style: 'docHeader'
              }]
            ]
          }
        },
        {
          layout: {
            hLineWidth (i: number, node: any) {
              return (i === 0 || i === node.table.body.length) ? 1 : 1
            },
            vLineWidth (i: number, node: any) {
              return (i === 0 || i === node.table.widths.length) ? 1 : 1
            },
            hLineColor (i: number, node: any) {
              return (i === 0 || i === node.table.body.length) ? '#bdbdbd' : '#bdbdbd'
            },
            vLineColor (i: number, node: any) {
              return (i === 0 || i === node.table.widths.length) ? '#bdbdbd' : '#bdbdbd'
            }
          },
          style: 'infoLabel',
          margin: [0, 25],
          table: definition
        }
      ],
      styles: this.getStyle()
    }
  }

  public setInvoiceDefinition (info: any, detail: any, debtCollector: string, isPayed: boolean): void {
    this.entityUpperCase()
    this.definition = {
      pageOrientation: this.orientation,
      pageSize: this.pageSize,
      content: [
        {
          layout: 'noBorders',
          margin: [-20, -20, -20, 0],
          table: {
            headerRows: 1,
            widths: ['*'],
            body: [
              [{
                text: `JUNTA ADMINISTRADORA DEL AGUA POTABLE DE ${this.entity.community}
                            PROVINCIA DE ${this.entity.province} \t CANTON: ${this.entity.canton}
                             ${this.title}`,
                style: 'invoiceHeader'
              }]
            ]
          }
        },
        {
          style: 'infoLabel',
          margin: [0, 6],
          layout: 'noBorders',
          table: info
        },
        {
          style: 'infoLabel',
          margin: [0, 6],
          layout: {
            hLineWidth (i: number, node: any) {
              return (i === 0 || i === node.table.body.length) ? 0.5 : 0.5
            },
            vLineWidth (i: number, node: any) {
              return (i === 0 || i === node.table.widths.length) ? 0.5 : 0.5
            },
            hLineColor (i: number, node: any) {
              return (i === 0 || i === node.table.body.length) ? '#bdbdbd' : '#bdbdbd'
            },
            vLineColor (i: number, node: any) {
              return (i === 0 || i === node.table.widths.length) ? '#bdbdbd' : '#bdbdbd'
            }
          },
          table: detail
        }
      ],
      footer: {
        margin: [0, -20],
        style: 'footer',
        columns: [
          {
            width: '*',
            text: 'Válido como comprobante de pago\nsi tiene el sello de la junta.'
          },
          {
            width: '*',
            text: 'Emitido por: \n' + debtCollector
          },
          {
            width: '*',
            text: (isPayed) ? 'Pagada' : 'Sin Pagar' // this.invoice.payed?''

          }

        ]
      },
      styles: this.getStyle()
    }
  }

  public getDefinition (): any {
    return this.definition
  }

  private entityUpperCase (): void {
    this.entity.province = this.entity.province.toLocaleUpperCase()
    this.entity.canton = this.entity.canton.toLocaleUpperCase()
    this.entity.community = this.entity.community.toLocaleUpperCase()
  }

  private getStyle (): any {
    return {
      invoiceHeader: {
        fontSize: 14,
        color: '#000000',
        alignment: 'center',
        bold: true,
        margin: 2
      },
      docHeader: {
        fontSize: 14,
        color: 'white',
        fillColor: '#00B9D1',
        alignment: 'center',
        bold: true,
        margin: 2
      },
      titleLabel: {
        bold: true,
        color: '#666666'
      },
      footer: {
        fontSize: 10,
        color: '#000000',
        alignment: 'center'
      },
      infoLabel: {
        fontSize: 10,
        margin: [0, 5]
      },
      thead: {
        alignment: 'center',
        color: '#000000',
        bold: true,
        margin: [0, 2],
        fontSize: 10
      },
      tcell: {
        margin: [0, 3],
        fontSize: 10
      }
    }
  }
}
