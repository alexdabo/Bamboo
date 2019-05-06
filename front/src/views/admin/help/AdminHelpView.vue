<template>
     <v-container class="pa-0 ma-0" style="min-width:100%">
    <v-layout row fill-height wrap>
      <v-flex class=" white v-navigation-drawer--fixed  v-navigation-drawer--right v-navigation-drawer v-navigation-drawer--open"
      style="height: 100%; margin-top: 64px; max-height: calc(100% - 58px); transform: translateX(0px); z-index: 2;">
        <div class="layaout pa-3"> <center><h2><v-icon >info</v-icon>Ayuda</h2></center>
        </div>
        <v-list>
          <v-list-group
            v-for="item in items"
            :key="item.name"
            :prepend-icon="item.action"
            no-action
          >
            <v-list-tile slot="activator">
              <v-list-tile-action v-if="item.icon !== ''">
                <v-icon>{{ item.icon }}</v-icon>
              </v-list-tile-action>
              <v-list-tile-content>
                <v-list-tile-title>{{ item.name }}</v-list-tile-title>
              </v-list-tile-content>
            </v-list-tile>

            <v-list-tile
              v-for="subItem in item.subitems"
              :key="subItem.name"
              @click="goTo(subItem.id)"
            >
              <v-list-tile-action v-if="subItem.icon !== ''">
                <v-icon>{{ subItem.icon }}</v-icon>
              </v-list-tile-action>
              <v-list-tile-content>
                <v-list-tile-title>{{ subItem.name }}</v-list-tile-title>
              </v-list-tile-content>
            </v-list-tile>
          </v-list-group>
        </v-list>
      </v-flex>

      <v-flex xs11 class="pa-2 container page">
          <br>
          <b>(Nota: Para una mejor visualización de esta ventana haga clic en <img src="@/assets/docs/menu.png" alt> para
          ocultar la bara de menu lateral izquierda)  </b>
        <!-- DATOS DE PANTALLA PRINCIPAL-->
        <div id="principal1">
          <h1>Administración</h1>
          El sistema cuenta con 4 secciones principales en el diseño mostrado:
          Menú  --  Título  --  Contenido Dinámico  --  Usuario <br>
            <v-spacer/>
              <img src="@/assets/docs/1.png" alt>
            <v-spacer/>
          <ul>
            <li>
              <b>Sección de menú:</b> Permite elegir las actividades rápidamente en cualquier pestaña en la que se encuentre solo con un click.
            </li>
            <li>
              <b>Sección de título:</b> Permite regresar a la página principal con un click en el Icono, y tambien contiene a la sección del usuario.
            </li>
            <li>
              <b>Sección de contenido dinámico:</b> Es la sección en la cual se describe con una imagen representativa los principales módulos que se encuentran en el sistema.
              <br>Puede acceder a estos con un click en la imagen.
            </li>
            <li>
              <b>Sección de usuario:</b> Es un botón que contiene un menú desplegable que permite cambiar la información de la cuenta, salir y acceder a la sección de Ayuda.
            </li>
          </ul>
        </div>
        <div id="principal2">
          <h1>Iconos de la sección Menú</h1>
            <center>
              <img src="@/assets/docs/2.png" alt>
            </center>
          <ul>
            <li>
              Icono de la página de inicio.
            </li>
            <li>
              Icono de la lista de los dashboard
            </li>
            <li>
              Icono de la lista de cuentas y cobros realizados en el día.
              <ul>
                <li>
                    Balance Diario
                </li>
                <li>
                    Factura SAP (<b>S</b>ervicio de <b>A</b>gua <b>P</b>otable)
                </li>
                <li>
                    Factura de Otros Servicios
                </li>
              </ul>
            </li>
            <li>
              Icono de la lista de los usuarios del sistema.
            </li>
            <li>
              Icono de la lista de los beneficiarios del sistema.
            </li>
            <li>
              Icono de los distintos tipos de medidores del sistema.
            </li>
            <li>
              Icono de la lista de los servicios de agua potable del sistema.
              <ul>
                <li>
                    SAP (<b>S</b>ervicio de <b>A</b>gua <b>P</b>otable)
                </li>
                <li>
                    Otros Servicios
                </li>
              </ul>
            </li>
            <li>
              Icono de la lista de las comunidades del sistema.
            </li>
            <li>
              Icono de los reportes.
            </li>
          </ul>
        </div>
        <!-- DATOS DEL DASHBOARD "white v-navigation-drawer--fixed  v-navigation-drawer--right v-navigation-drawer" -->
        <div id="dashboard">
          <h1>DashBoard</h1>
              <img src="@/assets/docs/dashboar.png" alt>
            <br>
            En esta pantalla se mostrara la información estadística que se encuentra en la base de datos
            del sistema. <br>Podemos cambiar el tipo de gráfico que queremos visualizar haciendo clic en
            <img src="@/assets/docs/more.png" alt> se mostrara la siguiente ventana en donde elegiremos una opción.
            <br><center><img src="@/assets/docs/moreopt.png" alt></center>
        </div>
        <!-- DATOS DEL DASHBOARD -->

        <!-- DATOS DE PANTALLA PRINCIPAL -->

        <!-- DATOS DE PANTALLA USUARIO -->
        <div id="usuario1" align="justify">
          <h1>Lista de Usuarios</h1>
          <img src="@/assets/docs/userlist.png" alt>
          <br>
          Se muestra el registro de los trabajadores obtenidos de la Base de Datos,
          se cargan los campos mostrando sus datos personales como: Apellido, Nombre, Cédula,
          Rol, Email, Teléfono <br> y Dirección ademas de las acciones que podemos realizar como:
          modificar <img src="@/assets/docs/btn_edit.png" alt>, eliminar <img src="@/assets/docs/btn_delete.png" alt> y buscar. <b>(Si pasamos el cursor
          sobre estas palabras aparecera una flecha junto a la palabra <br>como se muestra en "Apellidos↑", y si hacemos clic sobre ella la lista se ordenara
           de manera ascendente o descendente dependiendo de cual elijamos).</b>
           Se puede realizar una busqueda <br>por cualquier campo de los datos personales anteriormente mencionados digitandolos en el buscador.
           Tambien existen 2 botones los cuales son actualizar <img src="@/assets/docs/btn_update.png" alt> y nuevo <img src="@/assets/docs/btn_new.png" alt>.
        </div>
        <div id="usuario2" align="justify">
          <h1>Nuevo Usuario</h1>
          <center>
          <img src="@/assets/docs/usernew.png" alt>
          </center>
          Podemos visualizar los datos requeridos de los trabajadores para un nuevo ingreso, los datos del personal a llenar son: Apellidos, Nombres, Cédula, Teléfono
          Correo, Rol de usuario <br>el cual puede ser "Administrador, Cobros o Recolector" finalmente y Dirección. <b>(Es necesario llenar todos los campos sin
          excepciones).</b> Una vez que todos los campos se hayan <br>llenado correctamente, se hara clic en <img src="@/assets/docs/btn_guardar.png" alt>
          para almacenar los datos, caso contrario en <img src="@/assets/docs/btn_cancelar.png" alt>
        </div>

        <div id="usuario3">
          <h1>Modificar Usuario</h1>
          <center>
          <img src="@/assets/docs/useredit.png" alt>
          </center>
          Luego de dar clic en el boton de editar usuario <img src="@/assets/docs/btn_edit.png" alt> podremos visualizar una ventana en
          donde podremos editar la información de aquella persona
          como: Apellidos, Nombres, Cédula, <br>Teléfono
          Correo, Rol de usuario el cual puede ser "Administrador, Cobros o Recolector"
          y la Dirección.
          <b>(Es necesario llenar todos los campos sin excepciones).</b><br>
          Una vez que todos los campos se hayan llenado correctamente, se hara clic en
          <img src="@/assets/docs/btn_guardar.png" alt> para almacenar los datos, caso
          contrario en <img src="@/assets/docs/btn_cancelar.png" alt>
        </div>

        <div id="usuario4">
          <h1>Eliminar Usuario</h1>
          <center>
          <img src="@/assets/docs/userdel.png" alt>
          </center>
          Luego de dar clic en el boton de eliminar usuario <img src="@/assets/docs/btn_delete.png" alt> nos mostrara un cuadro de dialogo
          preguntandonos si estamos seguros de eliminar el usuario seleccionado. <br>
          Si queremos continuar con la eliminación daremos clic en
          <img src="@/assets/docs/btn_ok.png" alt>, caso
          contrario en <img src="@/assets/docs/btn_cancelarg.png" alt>
        </div>
        <!-- DATOS DE PANTALLA USUARIO-->

        <!-- DATOS DE PANTALLA BENEFICIARIO -->
        <div id="benef1" align="justify">
          <h1>Lista de Beneficiarios</h1>
              <img src="@/assets/docs/beneflist.png" alt><br>
          Se muestra el registro de los clientes obtenidos de la Base de Datos,
          se cargan los campos mostrando sus datos personales como: Apellidos, Nombres, Cédula,
          Comunidad, Teléfono, <br> Dirección y Referencia <b>(Si pasamos el cursor sobre estas palabras aparecera
          una flecha junto a la palabra como se muestra en "Apellidos↑", y si hacemos clic sobre ella la lista se <br>ordenara
           de manera ascendente o descendente dependiendo de cual elijamos).</b>  Podemos realizar acciones
          como: asignar un medidor al beneficiario <img src="@/assets/docs/btn_med.png" alt>, modificar <img src="@/assets/docs/btn_edit.png" alt>,
          eliminar <img src="@/assets/docs/btn_delete.png" alt> y buscar. <br>
          Se puede realizar una busqueda por cualquier campo de los datos personales anteriormente mencionados digitandolos en el buscador.
          Tambien existen 2 botones los cuales son: <br>actualizar <img src="@/assets/docs/btn_update.png" alt> y nuevo <img src="@/assets/docs/btn_new.png" alt>.
        </div>
        <div id="benef2">
          <h1>Nuevo Beneficiario</h1>
          <center>
          <img src="@/assets/docs/benefnew.png" alt>
          </center>
          Podemos visualizar los datos requeridos de los clientes para un nuevo
          ingreso, los datos a llenar son: Apellidos, Nombres, Cédula, Teléfono,
          Comunidad, Dirección y Lugar de Referencia. <br><b>(Es necesario llenar todos los campos sin excepciones).</b> <br>
          Una vez que todos los campos se hayan llenado correctamente, se hara clic en
          <img src="@/assets/docs/btn_guardar.png" alt> para almacenar los datos, caso
          contrario en <img src="@/assets/docs/btn_cancelar.png" alt>
        </div>

        <div id="benef3">
          <h1>Modificar Beneficiario</h1>
          <center>
          <img src="@/assets/docs/benefedit.png" alt>
          </center>
          Luego de dar clic en el boton de editar beneficiario <img src="@/assets/docs/btn_edit.png" alt> podremos visualizar una ventana en
          donde podremos editar la información de aquella persona
          como: Apellidos, Nombres, Cédula,  <br>Teléfono, Comunidad, Dirección y Lugar de Referencia.
          <b>(Es necesario llenar todos los campos sin excepciones).</b><br>
          Una vez que todos los campos se hayan llenado correctamente, se hara clic en
          <img src="@/assets/docs/btn_guardar.png" alt> para almacenar los datos, caso
          contrario en <img src="@/assets/docs/btn_cancelar.png" alt>
        </div>

        <div id="benef4">
          <h1>Eliminar Beneficiario</h1>
          <center>
          <img src="@/assets/docs/benefdelet.png" alt>
          </center>
          Luego de dar clic en el boton de eliminar beneficiario <img src="@/assets/docs/btn_delete.png" alt> nos mostrara un cuadro de dialogo
          preguntandonos si estamos seguros de eliminar aquel beneficiario. <br>
          Si queremos continuar con la eliminación daremos clic en
          <img src="@/assets/docs/btn_ok.png" alt>, caso
          contrario en <img src="@/assets/docs/btn_cancelarg.png" alt>
        </div>

        <div id="benef5">
          <h1>Asignar medidor al Beneficiario</h1>
          <img src="@/assets/docs/asigmed.png" alt> <br>
          Despues de hacer clic en el boton de asignar medidor <img src="@/assets/docs/btn_med.png" alt> del beneficiario
          seleccionado, nos redireccionara a la siguiente ventana: ,
          Todos los campos se llenaran automaticamente,  <br>y estara listo para hacer la asignacion del medidor, daremos clic en <img src="@/assets/docs/more.png" alt>
          y nos mostrara la siguiente ventana: <br> <center><img src="@/assets/docs/moremed.png" alt></center><br>
          En donde elegiremos <b>"Nuevo Medidor"</b>, aparecera una ventana en donde llenaremos los datos del nuevo medidor
          <center>
          <img src="@/assets/docs/mednew.png" alt>
          </center>
          Escogeremos el tipo de servicio que poseera el medidor.
        <b>(Es necesario llenar todos los campos sin excepciones).</b>
          Una vez que todos los campos se hayan llenado correctamente  <br>se hara clic en
          <img src="@/assets/docs/btn_guardar.png" alt> para almacenar los datos, caso
          contrario en <img src="@/assets/docs/btn_cancelar.png" alt>
        </div>
        <!-- DATOS DE PANTALLA BENEFICIARIO -->

        <!-- DATOS DE PANTALLA MEDIDORES -->
        <div id="med1">
          <h1>Lista de Medidores</h1>
              <img src="@/assets/docs/medlist2.png" alt><br>
          Lo primero que debemos hacer es buscar el beneficiario por su nombre o por el número de cédula
          para que todos los campos de la tabla inferior se llenen con la información  <br>automáticamente, si
          el beneficiario posee algún medidor este aparecera en la parte inferior de la tabla la cual detallara
          : Número, Servicio, Fecha de Instalación, Estado y Deuda  <br>además de las acciones que podemos
          realizar como: modificar <img src="@/assets/docs/btn_edit.png" alt>.
        </div>
        <div id="med2">
          <h1>Nuevo Medidor</h1>
          Luego de buscar el beneficiario y que todos los datos se carguen automáticamente
          daremos clic en <img src="@/assets/docs/more.png" alt>
          y nos mostrara la siguiente ventana:
          en donde elegiremos <b>"Nuevo Medidor"</b>.
          <br> <center><img src="@/assets/docs/moremed.png" alt></center><br>
          Aparecera una ventana en donde llenaremos los datos del nuevo medidor.
          <center>
          <img src="@/assets/docs/mednew.png" alt>
          </center>
          Escogeremos el tipo de servicio que poseera el medidor.
          <b>(Es necesario llenar todos los campos sin excepciones).</b>
          Una vez que todos los campos se hayan llenado correctamente  <br>se hara clic en
          <img src="@/assets/docs/btn_guardar.png" alt> para almacenar los datos, caso
          contrario en <img src="@/assets/docs/btn_cancelar.png" alt>
        </div>
        <div id="med3">
          <h1>Traspaso de Medidor</h1>
          Luego de buscar el beneficiario al cual queremos hacer el traspaso y que todos los datos se carguen
           automáticamente, daremos clic en <img src="@/assets/docs/more.png" alt>
          y nos mostrara la siguiente ventana: <br>
          en donde elegiremos <b>"Traspaso de medidor"</b>.
          <br> <center><img src="@/assets/docs/moremed.png" alt></center><br>
          Nos direccionará a la siguiente página en donde buscaremos al beneficiario desde el cual queremos
          hacer el traspaso del medidor, en el apartado de <b>"Buscar beneficiario"</b>
          <br>
          Los campos del propietario actual se llenaran automáticamente junto con los medidores que posee.
          Para hacer el traspaso daremos clic en la flecha <b>"→"</b> que se encuentra  <br>junto al medidor que se descendente
          hacer el traspaso.
          <img src="@/assets/docs/medtrasp1.png" alt>

          <br><br>
          Una vez hecho clic en la flecha el medidor seleccionado se asignara automáticamente a la otra persona <br>
          <img src="@/assets/docs/medtrasp2.png" alt>
           <br>
          Si es el medidor que queremos traspasar de beneficiario es correcta, daremos clic en
          <img src="@/assets/docs/btn_guardar.png" alt> para almacenar los datos, caso
          contrario en el icono de eliminar medidor  <br>traspasado <img src="@/assets/docs/btn_delete.png" alt>
          o en su defecto en el boton <img src="@/assets/docs/btn_cancelar.png" alt> para salir de la ventana.

          <br><br>
          <b>Nota: este procesos se podrá realizar si el medidor que deseamos traspasar no posee ninguna deuda,
            de ser así nos aparecera el siguiente mensaje de error, que hasta que se  <br>realice el pago no se podrá realizar la acción. </b><br>
          <center>
          <img src="@/assets/docs/errordeuda.png" alt>
          </center>
        </div>
        <!-- DATOS DE PANTALLA MEDIDORES -->

        <!-- DATOS DE PANTALLA SERVICIOS -->
        <div id="serv1">
          <h1>Lista de Servicios (SAP)</h1>
          <br><img src="@/assets/docs/servsaplist.png" alt> <br>
          Se muestra el registro de los servicios obtenidos de la Base de Datos,
          se cargan los campos mostrando sus datos tales como: Nombre del Servicio,
           Volumen Base, Precio Base y  <br>Precio Extra ademas  <b>(Si pasamos el cursor sobre estas palabras aparecera
           una flecha junto a la palabra como se muestra en "Nombre del servicio↑", y si hacemos clic sobre ella <br>la lista se
           ordenara de manera ascendente o descendente dependiendo de cual elijamos)</b>.
           Ademas de las acciones que podemos realizar como: modificar <img src="@/assets/docs/btn_edit.png" alt>, eliminar
           <img src="@/assets/docs/btn_delete.png" alt> y buscar. <br>
            Se puede realizar una busqueda por cualquier campo de los datos anteriormente mencionados digitandolos en el buscador.
            Tambien existen 2 botones los cuales son: actualizar <img src="@/assets/docs/btn_update.png" alt>  <br>y nuevo
             <img src="@/assets/docs/btn_new.png" alt>.
        </div>
        <div id="serv2">
          <h1>Nuevo Servicio (SAP)</h1> <br>
          <center>
              <img src="@/assets/docs/servsapnew.png" alt>
          </center> <br>
           Podemos visualizar los datos requeridos de los servicios para un nuevo
          ingreso, los datos del servicio a llenar son: Nombre del Servicio,
          Volumen Base, Precio Base y Precio extra. <br>
          Una vez que todos los campos se hayan llenado correctamente, se hara clic en
          <img src="@/assets/docs/btn_guardar.png" alt> para almacenar los datos, caso
          contrario en <img src="@/assets/docs/btn_cancelar.png" alt>
        </div>
        <!-- DATOS DE PANTALLA OTROS SERVICIOS -->
        <div id="otserv1">
          <h1>Lista de Servicios (Otros)</h1> <br>
            <img src="@/assets/docs/othserv.png" alt> <br>
          Se muestra el registro de otros servicios obtenidos de la Base de Datos,
          se cargan los campos mostrando sus datos tales como: Nombre del Servicio y
          Precio <b>(Si pasamos el cursor  <br>sobre estas palabras aparecera
          una flecha junto a la palabra como se muestra en "Nombre del servicio↑", y si hacemos clic sobre ella la lista se
          ordenara de manera ascendente o <br>descendente dependiendo de cual elijamos)</b>.
          Ademas de las acciones que podemos realizar como: modificar <img src="@/assets/docs/btn_edit.png" alt>, eliminar
          <img src="@/assets/docs/btn_delete.png" alt> y buscar.
           Se puede realizar una busqueda por cualquier <br>campo de los datos anteriormente mencionados digitandolos en el buscador.
           Tambien existen 2 botones los cuales son actualizar <img src="@/assets/docs/btn_update.png" alt> y nuevo
          <img src="@/assets/docs/btn_new.png" alt>.
        </div>
        <div id="otserv2">
          <h1>Nuevo Servicio (Otros)</h1>
          <center>
              <img src="@/assets/docs/servotnew.png" alt>
          </center>
           Podemos generar el reporte de todos los beneficiarios dejando en blanco el apartador de <b>"Buscar"</b>
           , podemos elegir tambien si queremos generar el reporte de todas las comunidades <br>o de una en especifico
           seleccionandola en la pestana, por ultimo se puede generar el reporte de manera vertical u horizontal
           en el apartado de <b>"Orientación"</b>.
           Una vez que todos los <br>campos se hayan seleccionado correctamente se hara clic en
          <img src="@/assets/docs/btn_guardar.png" alt> para abrir el pdf o en <img src="@/assets/docs/btn_cancelar.png" alt>
          para descargarlo.
        </div>
        <!-- DATOS DE PANTALLA SERVICIOS -->

        <!-- DATOS DE PANTALLA DE COMUNIDADES -->
        <div id="com">
          <h1>Lista de Comunidades</h1> <br>
          <img src="@/assets/docs/village.png" alt><br>
           Se muestra el registro de las comunidades obtenidas de la Base de Datos, se cargan
           los campos mostrando sus datos tales como: Código, Nombre de la Comunidad y las acciones <br>
          que se pueden realizar como modificar <img src="@/assets/docs/btn_edit.png" alt>y eliminar <img src="@/assets/docs/btn_delete.png" alt>,
           <b>(Si hacemos clic sobre estas palabras, la lista se ordenara
           de manera ascendente o descendente dependiendo de cual elijamos).</b> <br>
           Se puede realizar una busqueda por código, o por nombre de la comunidad digitandola en el buscador.
           Tambien existen 2 botones los cuales son actualizar <img src="@/assets/docs/btn_update.png" alt> y nuevo <img src="@/assets/docs/btn_new.png" alt>.
        </div>
        <!-- DATOS DE PANTALLA REPORTES -->

        <!-- DATOS DE PANTALLA DE NUEVA COMUNIDADES -->
        <div id="comnew">
          <h1>Nueva Comunidad</h1>
          <center>
          <img src="@/assets/docs/newcom.png" alt>
          </center>
          Podemos visualizar el dato requerido de un nuevo
          ingreso de comunidad, el dato es: Nombre de la Comunidad.
          Una vez que el campo se hayan llenado correctamente, se hara clic <br>en
          <img src="@/assets/docs/btn_guardar.png" alt> para almacenar los datos, caso
          contrario en <img src="@/assets/docs/btn_cancelar.png" alt>
           </div>
        <!-- DATOS DE PANTALLA REPORTES -->

        <!-- DATOS DE PANTALLA REPORTES -->
        <div id="rep1">
          <h1>Generar Reportes</h1> <br>
          <img src="@/assets/docs/replist.png" alt> <br>
           Podemos visualizar los datos requeridos de los reportes para un nuevo
          ingreso, los datos del reporte a llenar son: Código, Nombre de la Comunidad y las acciones
          que se pueden <br>realizar como modificar y eliminar.
          Una vez que todos los campos se hayan llenado correctamente, se hará clic en
          <img src="@/assets/docs/btn_abrir.png" alt> para almacenar los datos, caso
          contrario en <img src="@/assets/docs/btn_descargar.png" alt>
        </div>
        <!-- DATOS DE PANTALLA REPORTES -->

        <!-- CONFIGURACION -->
        <div id="confu1">
          <h1>Editar Entidad</h1> <br>
          <img src="@/assets/docs/conf.png" alt> <br>
           Haciendo clic en el icono <img src="@/assets/docs/btn_conf.png" alt> aparecera una ventana en donde
           elegiremos la opcion de <b>"Configuraciones"</b> que inmediatamente nos redireccionara a la pagina que se muestra
           <br> en la imagenrealizar como modificar y eliminar.
          Una vez que todos los campos se hayan llenado correctamente, se hará clic en
          <img src="@/assets/docs/btn_guardar.png" alt> para almacenar los datos
        </div>

        <div id="confu2">
          <h1>Información  Personal</h1> <br>
          <img src="@/assets/docs/editinfper.png" alt> <br>
           En este apartado podremos modificar la informacion de la persona que actualmente ha iniciado secion.
          Una vez que todos los campos se hayan llenado correctamente, se <br>hará clic en
          <img src="@/assets/docs/btn_guardar.png" alt> para almacenar los datos
        </div>

        <div id="confu3">
          <h1>Cambiar Contraseña</h1> <br>
          <img src="@/assets/docs/passedit.png" alt> <br>
           En este apartado podremos modificar la ontraseña de la persona que actualmente ha iniciado secion.
          Una vez que todos los campos se hayan llenado correctamente, se hará clic <br>en
          <img src="@/assets/docs/btn_guardar.png" alt> para almacenar los datos
        </div>
        <!-- CONFIGURACION -->

      </v-flex>

    </v-layout>
  </v-container>
</template>
<script lang="ts">
import AdminHelpView from '@/views/admin/help/AdminHelpView.ts'
export default AdminHelpView
</script>
