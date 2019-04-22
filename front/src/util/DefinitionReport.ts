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
                text: `Junta administradora del agua potable de ${this.entity.community}
                            Provincia de ${this.entity.province} \t Cantón: ${this.entity.canton}
                             ${this.title}`,
                style: 'header'
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

  public setInvoiceDefinition (definition: any): void {
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
                text: `Junta administradora del agua potable de ${this.entity.community}
                            Provincia de ${this.entity.province} \t Cantón: ${this.entity.canton}
                             ${this.title}`,
                style: 'header'
              }]
            ]
          }
        }
      ],
      styles: this.getStyle
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
      header: {
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
        fontSize: 8,
        color: '#666666',
        alignment: 'center'
      },
      infoLabel: {
        fontSize: 10,
        margin: [0, 5]
      },
      thead: {
        alignment: 'center',
        color: 'white',
        bold: true,
        margin: [0, 5],
        fontSize: 10,
        fillColor: '#00B9D1'
      },
      tcell: {
        margin: [0, 5],
        fontSize: 10
      }
    }
  }
}
