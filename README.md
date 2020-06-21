# Bamboo

Bamboo es un sistema para la automatización de las tarifas de servicio de agua potable.


## Dependencias

Este proyecto se divide en backend y frontend, por lo que debe tener los entornos para ambos módulos.

Dependencias para el **backend**.
* Java
* Maven
* Postgresql
* python 

Dependencias para el **frontend** 
* Node.js
* npm
* vue/cli

## Base de datos
Bamboo usa postgresql como DBMS. Para montar la base de datos existen los respectivos scripts en el directorio **/sql**.

Acceder al directorio de los scripts
```
cd directorio/sql
```

Dentro de este directorio hay un script desarrollado con python para automatizar la creación de la base de datos, para lo cual debe instalarse la biblioteca psycopg2.
``` shell
pip instalar psycopg2 
```

Una vez installado la biblioteca creamos la base de datos.
``` shell
createdb -U postgres bamboo
```

Ya creada la base de datos procedemos a ejecutar el script.
``` shell
python startDB.py
```
Nota: en caso de configurar la connexion a la base de datos.
```
python startDB.py -h

Connection options:
	-H, --host=HOSTNAME      database server host (default: localhost)
	-p, --port=PORT          database server port (default: 5432)
	-u, --user=USERNAME      database user name (default: postgres)
	-d, --dbname=DBNAME      database name to connect to (default: bamboo)
	-t, --test=TESTFILE      database test file

```

Para el acceso a la base de datos del backend se lo debe configurar en el archivo *src/main/java/com/bamboo/connection/DBConnection.java*.

```java
    final String DRIVER = "org.postgresql.Driver";
    final String URL = "jdbc:postgresql://localhost:5432/bamboo";
    final String USER = "postgres";
    final String PASSWORD = "postgres";
```
## Backend

Acceder al directorio del proyecto.
```
cd directorio
```
Instalar las dependencias.
```
mvn dependency:resolve
```

## Frontend


Acceder al directorio del frontend.
``` shell
cd directorio/front
```
Instalar las dependencias.
```shell 
yarn  install
```

## Compilación

Bamboo al estar separado por backend y frontend se los debe compilar por separado.


1. **Frontend**

Acceder al frontend
```shell 
cd directorio/front
```
Compilar el frontend.
```shell 
yarn  build
```
<span style="color:grey">NOTA: se debe compilar primero el frontend para que se agrege al servidor.</span>
1. **Backend**

Acceder al directorio de bamboo.
Acceder al frontend
```shell 
cd directorio
```

Compilar el backend.
```shell 
mvn clean install
```
El backend genera un archivo con la extención *.war* en el directorio *target*.

<span style="color:grey">NOTA: se debe tener la conexión a la base de datos abierta para la compilación.</span>


## Despliege
Para la implementación en tomcat 9, el usuario debe estar configurado en *tomcat9/config/tomcat-users.xml*.
```xml 
<user password="secret_password" username="user" roles="manager-script,admin, manager-gui" /
```
<span style="color:grey">NOTA: si la ruta es la raiz, el archivo *.war* no debe tener nombre, solo se debe llamar *.war*.</span>