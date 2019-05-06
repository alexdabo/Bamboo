interface Subitem {
    name: string;
    id: string;
}

interface Item {
    name: string;
    icon: string;
    subitems: Subitem[];
}

interface Tittle{
    name: string;
    icon: string;
}

const MenuItems: Item[] = [
  {
    icon: 'home',
    name: 'Principal',
    subitems: [
      { name: 'Pantalla', id: 'principal1' },
      { name: 'Iconos', id: 'principal2' }
    ]
  },
  {
    icon: 'dashboard',
    name: 'Dashboard',
    subitems: [
      { name: 'Principal', id: 'dashboard' }
    ]
  },
  {
    icon: 'account_balance_wallet',
    name: 'Cuentas',
    subitems: [
      { name: 'Balance Diario', id: 'baldi' },
      { name: 'Factura SAP', id: 'factsap' },
      { name: 'Factura Otros Servicios', id: 'factot' }
    ]
  },
  {
    icon: 'person',
    name: 'Usuario',
    subitems: [
      { name: 'Listar', id: 'usuario1' },
      { name: 'Nuevo', id: 'usuario2' },
      { name: 'Modificar', id: 'usuario3' },
      { name: 'Eliminar', id: 'usuario4' }
    ]
  },
  {
    icon: 'people',
    name: 'Beneficiario',
    subitems: [
      { name: 'Listar', id: 'benef1' },
      { name: 'Nuevo', id: 'benef2' },
      { name: 'Modificar', id: 'benef3' },
      { name: 'Eliminar', id: 'benef4' },
      { name: 'Asignar Medidor', id: 'benef5' }
    ]
  },
  {
    icon: 'timer',
    name: 'Medidor',
    subitems: [
      { name: 'Listar', id: 'med1' },
      { name: 'Nuevo', id: 'med2' },
      { name: 'Traspaso', id: 'med3' }
    ]
  },
  {
    icon: 'format_list_bulleted',
    name: 'Servicios(SAP)',
    subitems: [
      { name: 'Listar', id: 'serv1' },
      { name: 'Nuevo', id: 'serv2' }
    ]
  },
  {
    icon: 'format_list_bulleted',
    name: 'Servicios(Otros)',
    subitems: [
      { name: 'Listar', id: 'otserv1' },
      { name: 'Nuevo', id: 'otserv2' }
    ]
  },
  {
    icon: 'map',
    name: 'Comunidades',
    subitems: [
      { name: 'Principal', id: 'com' },
      { name: 'Nuevo', id: 'comnew' }
    ]
  },
  {
    icon: 'description',
    name: 'Reportes',
    subitems: [
      { name: 'Generar Reportes', id: 'rep1' }
    ]
  },
  {
    icon: 'person',
    name: 'Configurar Usuario',
    subitems: [
      { name: 'Editar Entidad', id: 'confu1' },
      { name: 'Información Personal', id: 'confu2' },
      { name: 'Cambiar Contraseña', id: 'confu3' }
    ]
  }

]

export default MenuItems
