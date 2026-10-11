CREATE DATABASE IF NOT EXISTS libreria;
USE libreria;

CREATE TABLE tienda (
    nombre VARCHAR(100) PRIMARY KEY,
    direccion VARCHAR(150) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    ciudad VARCHAR(100) NOT NULL
);

CREATE TABLE editorial (
    nombre VARCHAR(100) PRIMARY KEY,
    pais VARCHAR(100) NOT NULL,
    telefono VARCHAR(20) NOT NULL
);

CREATE TABLE autor (
    id_autor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    nacionalidad VARCHAR(100) NOT NULL,
    ano_nacimiento INT NOT NULL
);

CREATE TABLE cliente (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL,
    telefono VARCHAR(20),
    fecha_alta_socio DATE
);

CREATE TABLE libro (
    isbn CHAR(13) PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    ano_publicacion INT NOT NULL,
    num_paginas INT NOT NULL,
    precio_catalogo DECIMAL(10,2) NOT NULL,
    editorial_nombre VARCHAR(100) NOT NULL,
    FOREIGN KEY (editorial_nombre) REFERENCES editorial(nombre),
    CHECK (num_paginas > 0),
    CHECK (precio_catalogo >= 0)
);

CREATE TABLE empleado (
    dni VARCHAR(15) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(150) NOT NULL,
    puesto VARCHAR(20) NOT NULL,
    fecha_contratacion DATE NOT NULL,
    correo VARCHAR(150) NOT NULL,
    tienda_nombre VARCHAR(100) NOT NULL,
    FOREIGN KEY (tienda_nombre) REFERENCES tienda(nombre),
    CHECK (puesto IN ('librero', 'cajero', 'encargado'))
);

CREATE TABLE libro_autor (
    isbn CHAR(13),
    id_autor INT,
    tipo_participacion VARCHAR(20) NOT NULL,
    PRIMARY KEY (isbn, id_autor),
    FOREIGN KEY (isbn) REFERENCES libro(isbn),
    FOREIGN KEY (id_autor) REFERENCES autor(id_autor),
    CHECK (tipo_participacion IN ('principal', 'colaborador'))
);

CREATE TABLE inventario (
    tienda_nombre VARCHAR(100),
    isbn CHAR(13),
    cantidad INT NOT NULL,
    fecha_recuento DATE NOT NULL,
    PRIMARY KEY (tienda_nombre, isbn),
    FOREIGN KEY (tienda_nombre) REFERENCES tienda(nombre),
    FOREIGN KEY (isbn) REFERENCES libro(isbn),
    CHECK (cantidad >= 0)
);

CREATE TABLE pedido (
    id_pedido INT PRIMARY KEY,
    fecha DATE NOT NULL,
    metodo_pago VARCHAR(20) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    tienda_nombre VARCHAR(100) NOT NULL,
    empleado_dni VARCHAR(15) NOT NULL,
    id_cliente INT NOT NULL,
    FOREIGN KEY (tienda_nombre) REFERENCES tienda(nombre),
    FOREIGN KEY (empleado_dni) REFERENCES empleado(dni),
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
    CHECK (metodo_pago IN ('efectivo', 'tarjeta', 'bizum')),
    CHECK (estado IN ('preparado', 'entregado', 'cancelado'))
);

CREATE TABLE detalle_pedido (
    id_pedido INT,
    isbn CHAR(13),
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (id_pedido, isbn),
    FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
    FOREIGN KEY (isbn) REFERENCES libro(isbn),
    CHECK (cantidad > 0),
    CHECK (precio_unitario >= 0)
);

INSERT INTO tienda (nombre, direccion, telefono, ciudad) VALUES
('Centro', 'Calle Mayor 10', '976111111', 'Villa Serena'),
('Ribera', 'Avenida del Rio 25', '976222222', 'Aldeaverde'),
('Universidad', 'Calle Universidad 5', '976333333', 'Villa Serena');

INSERT INTO editorial (nombre, pais, telefono) VALUES
('Alfaguara', 'España', '911111111'),
('Alianza', 'España', '922222222'),
('Plaza & Janes', 'España', '933333333');

INSERT INTO autor (nombre, nacionalidad, ano_nacimiento) VALUES
('Julio Cortazar', 'Argentina', 1914),
('Jorge Luis Borges', 'Argentina', 1899),
('Juan Rulfo', 'Mexico', 1917);