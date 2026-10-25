# Trabajo1SergioMarcos1

## Descripción

Aplicación Java para gestionar libros, con dos opciones de almacenamiento: un fichero de texto y una base de datos MySQL.

## Requisitos

- Java compatible con la versión utilizada en el proyecto.
- MySQL para utilizar la opción de base de datos.
- Apache NetBeans para abrir, compilar y ejecutar el proyecto.

## Instalación rápida

1. Descargar o clonar el repositorio del proyecto.
2. Comprobar que las librerías necesarias están disponibles en la carpeta `lib/`.
3. Configurar la base de datos ejecutando el script `sql/init.sql` en MySQL.
4. Crear el archivo `.env` a partir de `.env.example` y configurar los datos de conexión.
5. Abrir el proyecto en Apache NetBeans.
6. Limpiar y compilar el proyecto.
7. Ejecutar la clase principal `app.Main`.

**Importante:** el script `sql/init.sql` elimina y vuelve a crear la tabla `libros`. Antes de ejecutarlo, hay que guardar los datos que se quieran conservar.

## Variables de entorno

El archivo `.env` contiene los datos necesarios para conectar la aplicación con MySQL.

| Variable | Descripción |
|---|---|
| `DB_URL` | URL de conexión con la base de datos. |
| `DB_USER` | Usuario de MySQL. |
| `DB_PASS` | Contraseña del usuario de MySQL. |

No se debe publicar el archivo `.env`, ya que puede contener información privada de conexión.

## Documentación

- [Manual de despliegue](docs/manual_despliegue.pdf)
- [Javadoc](javadoc/index.html)
