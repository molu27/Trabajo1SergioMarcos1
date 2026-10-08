# Base de datos de Páginas de Villa Serena

## 1. Resumen del caso
Páginas de Villa Serena es una libreria que cuenta con tres tiendas: Centro, Ribera y Universidad. La librería trabaja con libros de diferentes autores y editoriales y también tiene empleados, clientes y un sistema de pedidos. La libreria necesita tener organizada toda esta información porque los libros pueden estar disponibles en unas tiendas y no en otras, los empleados trabajan en una tienda concreta y los clientes pueden realizar pedidos. Además, un pedido puede incluir varios libros y es necesario guardar la cantidad y el precio que se pagó por cada uno. La base de datos servirá para guardar y relacionar toda esta información. También permitirá consultar datos como el stock de los libros en cada tienda, los pedidos realizados, las ventas y la información relacionada con los autores y las editoriales. De esta forma, la librería podrá gestionar mejor la información de sus tiendas y realizar las consultas necesarias sobre su  actividad. 
## 2. Análisis del caso
## 2.1 Entidades y atributos
| Entidad | Atributos |
|---|---|
| Tienda | nombre, dirección, teléfono, ciudad |
| Libro | ISBN, titulo, año de publicación, número de páginas, precio de catálogo |
| Editorial | nombre, país, teléfono |
| Autor | nombre, nacionalidad, año de nacimiento |
| Inventario | cantidad de stock, fecha del último recuento |
| Empleado | DNI, nombre, apellidos, puesto, fecha de contratación, correo electrónico de trabajo |
| Cliente | nombre completo, correo electrónico, teléfono, fecha de alta |
| Pedido | fecha, método de pago, estado |
## 2.2 Relaciones
| Relación | Cardinalidad | Descripción |
|---|---|---|
| Tienda - Empleado | 1:N | Una tienda puede tener varios empleados y cada empleado trabaja en una única tienda. |
| Tienda - Inventario | 1:N | Una tienda puede tener varios registros de inventario y cada registro de inventario corresponde a una única tienda. |
| Libro - Editorial | N:1 | Una editorial puede publicar varios libros y cada libro pertenece a una única editorial. |
## 2.3 Datos descartados

## 3. Reglas de negocio

## 4. Diagrama entidad-relación

## 5. Modelo lógico

## 6. Script SQL (schema.sql)

## 7. Diccionario de datos

## 8. Decisiones de diseño

## 9. Datos de prueba

## 10. Consultas de prueba

## 11. Limitaciones y mejoras futuras