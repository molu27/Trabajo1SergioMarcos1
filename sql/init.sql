CREATE DATABASE IF NOT EXISTS libreria
    CHARACTER SET utf8mb4;

USE libreria;

DROP TABLE IF EXISTS libros;

CREATE TABLE libros (
    id VARCHAR(50) PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    autor VARCHAR(150) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL
);

INSERT INTO libros (id, titulo, autor, precio, stock) VALUES
('L001', 'Don Quijote de la Mancha', 'Miguel de Cervantes', 19.95, 12),
('L002', 'La sombra del viento', 'Carlos Ruiz Zafón', 14.50, 8),
('L003', 'Cien años de soledad', 'Gabriel García Márquez', 18.00, 10),
('L004', 'El amor en los tiempos del cólera', 'Gabriel García Márquez', 16.75, 6),
('L005', '1984', 'George Orwell', 12.95, 15),
('L006', 'Rebelión en la granja', 'George Orwell', 10.50, 9),
('L007', 'La casa de los espíritus', 'Isabel Allende', 17.25, 5),
('L008', 'El principito', 'Antoine de Saint-Exupéry', 9.95, 20);