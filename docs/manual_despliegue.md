Manual de despliegue - Sistema de Gestión de Biblioteca
1. Introducción y alcance
Este manual explica cómo instalar, configurar, ejecutar y administrar la aplicación Java de gestión de libros del proyecto Trabajo1SergioMarcos1. La aplicación permite trabajar con dos formas de almacenamiento: un fichero de texto y una base de datos MySQL.

Está dirigido a una persona que tenga conocimientos básicos de Windows y que necesite preparar el proyecto en su ordenador, aunque no haya trabajado antes con él.

El manual incluye los requisitos necesarios, la obtención del código, la configuración de las librerías, la inicialización de la base de datos, la configuración del archivo .env, el arranque de la aplicación y las comprobaciones necesarias para verificar su funcionamiento. También recoge tareas de administración y posibles errores durante la instalación.

Al terminar el despliegue, se deberá poder ejecutar la aplicación y utilizar sus dos opciones de almacenamiento.

2. Requisitos y entorno de pruebas
2.1. Requisitos de software
Las versiones comprobadas en el equipo de desarrollo son las siguientes:

Software	Versión	Para qué se utiliza
Java	26.0.1 (64 bits)	Compilar y ejecutar la aplicación Java.
Apache NetBeans	IDE 31	Abrir, compilar y ejecutar el proyecto Ant.
MySQL Community Server	8.0.46 (64 bits)	Gestionar la base de datos de libros.
Git	2.55.0.windows.5	Obtener el código del repositorio.
2.2. Entorno de pruebas
Las versiones anteriores se han consultado en el equipo de desarrollo. La prueba completa del despliegue desde una instalación limpia queda pendiente.

El proyecto utiliza Apache NetBeans y una base de datos MySQL. Para comprobar el despliegue desde cero, se preparará un entorno limpio y se documentarán los pasos realizados y sus resultados.

3. Instalación del software necesario
Para desplegar la aplicación se necesitan los siguientes programas:

Java JDK: permite compilar y ejecutar la aplicación Java.

Apache NetBeans: permite abrir, compilar y ejecutar el proyecto, que utiliza Ant.

MySQL Community Server: permite utilizar el repositorio de libros basado en una base de datos.

Git: permite obtener el código del repositorio.

Las versiones comprobadas en el equipo de desarrollo están indicadas en el apartado 2.1.

La instalación y comprobación de estos programas en un equipo limpio se documentará durante la prueba de despliegue, incluyendo las capturas de pantalla correspondientes.

4. Estructura del proyecto
El proyecto está organizado en diferentes carpetas y archivos para separar el código fuente, las librerías, los datos y la documentación.

src/: contiene el código fuente Java de la aplicación.

datos/: contiene los datos utilizados por la aplicación que trabaja con ficheros de texto.

lib/: contiene las librerías necesarias para compilar y ejecutar el proyecto.

sql/: contiene el script init.sql, utilizado para preparar la base de datos MySQL.

docs/: contiene la documentación del proyecto y las imágenes utilizadas en ella.

javadoc/: contiene la documentación generada a partir del código Java.

nbproject/: contiene los archivos de configuración del proyecto de NetBeans.

.env.example: sirve como plantilla para configurar la conexión con MySQL.

.gitignore: indica los archivos y carpetas que Git debe ignorar.

README.md: contiene la descripción del proyecto y las instrucciones básicas de instalación.

El archivo build.xml contiene la configuración de construcción del proyecto mediante Ant.

La estructura descrita corresponde a la organización del proyecto en el momento de redactar este manual.

5. Obtención del código fuente
El código fuente del proyecto se obtiene descargando o clonando el repositorio Git.

Una vez obtenido, hay que comprobar que la carpeta del proyecto contiene los archivos necesarios, como src/, lib/, sql/, nbproject/, build.xml y README.md.

También se debe comprobar que el archivo .env.example está disponible para configurar la conexión con la base de datos.

El archivo .env contiene los datos de conexión personales y no debe compartirse ni incluirse en el repositorio.

6. Librerías necesarias
El proyecto utiliza dos librerías externas para acceder a las funciones necesarias:

dotenv-java-3.2.0.jar: permite leer las variables de configuración del archivo .env.

mysql-connector-j-26.7.0.jar: permite conectar la aplicación Java con MySQL.

Ambos archivos JAR se encuentran en la carpeta lib/ del proyecto.

Las rutas de las librerías están configuradas en nbproject/project.properties mediante rutas relativas al proyecto, para evitar depender de las carpetas personales del equipo donde se desarrolló la aplicación.

Para comprobar la configuración, se debe abrir el proyecto en Apache NetBeans y ejecutar una limpieza y compilación. El resultado debe indicar que la construcción ha finalizado correctamente.

7. Inicialización de la base de datos
El proyecto incluye el script sql/init.sql, que prepara la base de datos libreria y crea la tabla libros con los campos necesarios para la aplicación.

El script también inserta ocho libros de ejemplo para poder realizar las comprobaciones iniciales.

Advertencia: el script elimina la tabla libros si ya existe y vuelve a crearla. Por tanto, al ejecutarlo se perderán los datos anteriores de esa tabla.

La ejecución y comprobación del script quedan pendientes hasta disponer del entorno MySQL preparado. En ese momento se verificará que la tabla se crea correctamente y que contiene los registros de ejemplo.



