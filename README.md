# Bamboo

Bamboo es un sistema para la automatización de las tarifas de servicio de agua potable.


### Base de datos

1.- El sistema usa postgresql como SGBD para lo cual creamos una base de datos con el nombre "bamboo".

``` shell
createdb -U postgres bamboo
```

2.- En el directorio "sql" están los scripts para la implementación de la base de datos, estos deben ejecutarse en orden numérico, esto puede hacerse manualmente o con la ayuda de un script desarrollado con python.

#### Con python

Para utilizar el script, debe seguir los pasos que se muestran a continuación.

* Instale la biblioteca para conectarse a la base de datos.

``` shell
pip instalar psycopg2
```


* Ejecutar el script y rellenar todos los campos requeridos.

``` shell
python sql/startDB.py
```

### Descragar el código

Bamboo esta dividido en BackEnd y FrontEnd, por lo cual se los debe descargar y compilar por separado.

* Descargar el backend
``` shell
git clone https://github.com/alexanderda/bamboo.git
```

* Descargar el frontend 
``` shell
git clone https://github.com/alexanderda/bamboofront.git
```
con el código fuente descargado procedemos con la instalación de sus dependencias.
``` shell
cd bamboofront
```
``` shell
yarn install
```
 

### Compilar el frontend

Bamboo FrontEnd está desarrollado con vue, por lo que debe compilar por separado para lo cual accedemos a la direccion del código del frontend e iniciamos la compilación.
```sh
yarn vue-cli-service build  --dest ruta_absoluta_del_backend/src/main/webapp
``` 
NOTA: se debe tener en cuenta la ruta del servidor, en caso de no ser la misma modificar el archivo "vue.config.js"

```js
module.exports = {
    publicPath: process.env.NODE_ENV === 'production'
      ? '/bamboo'
      : '/'
  }
```
### configuración de la ruta

El FrontEnd tiene el modo de historial en sus rutas, para las cuales se debe configurar el servidor, en este caso para Tomcat, el .war se debe implementar primero.

Editamos o creamos el archivo "WEB-INF/web.xml" dentro del directorio "tomcat/webapps/bamboo"

```shell
nano tomcat/webapps/bamboo/WEB-INF/web.xml
```
y escribimos lo siguiente
```xml
<web-app>
    <error-page>
        <error-code> 404 </error-code>
        <ubicación> /index.html </ubicación>
    </error-page>
</web-app>
```