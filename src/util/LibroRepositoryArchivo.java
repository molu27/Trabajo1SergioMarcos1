/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import daos.LibroRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import modelo.Libro;

/**
 *
 * @author 2DAM
 */
public class LibroRepositoryArchivo implements LibroRepository {

    private String rutaArchivo;

    public LibroRepositoryArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    @Override
    public List<Libro> obtenerTodos() {

        List<Libro> lista = new ArrayList<>();

        try {

            List<String> lineas = Files.readAllLines(Paths.get(rutaArchivo));

            for (String linea : lineas) {

                String[] datos = linea.split("\\^");

                Libro libro = new Libro(
                        datos[0],
                        datos[1],
                        datos[2],
                        Double.parseDouble(datos[3]),
                        Integer.parseInt(datos[4])
                );

                lista.add(libro);
            }

        } catch (IOException e) {

            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public List<Libro> buscarPorTitulo(String titulo) {
        List<Libro> resultado = new ArrayList<>();

        for (Libro libro : obtenerTodos()) {

            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                resultado.add(libro);
            }
        }

        return resultado;
    }

    @Override
    public List<Libro> buscarPorAutor(String autor) {
        List<Libro> resultado = new ArrayList<>();

        for (Libro libro : obtenerTodos()) {

            if (libro.getAutor().equalsIgnoreCase(autor)) {
                resultado.add(libro);
            }
        }

        return resultado;
    }

    @Override
    public List<Libro> buscarPorRangoPrecio(double precioMinimo, double precioMaximo) {
        List<Libro> resultado = new ArrayList<>();

        for (Libro libro : obtenerTodos()) {

            if (libro.getPrecio() >= precioMinimo
                    && libro.getPrecio() <= precioMaximo) {

                resultado.add(libro);
            }
        }

        return resultado;
    }

    @Override
    public List<Libro> buscarPorStockMinimo(int stockMinimo) {
        List<Libro> resultado = new ArrayList<>();

        for (Libro libro : obtenerTodos()) {

            if (libro.getStock() >= stockMinimo) {
                resultado.add(libro);
            }
        }

        return resultado;
    }

    @Override
    public boolean insertar(Libro libro) {
        String linea = libro.getId() + "^"
                + libro.getTitulo() + "^"
                + libro.getAutor() + "^"
                + libro.getPrecio() + "^"
                + libro.getStock();

        try {

            Files.write(
                    Paths.get(rutaArchivo),
                    (linea + System.lineSeparator()).getBytes(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );

            return true;

        } catch (IOException e) {

            System.out.println("Error al insertar: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminarPorId(String id) {
        List<Libro> libros = obtenerTodos();

        boolean eliminado = false;

        for (int i = 0; i < libros.size(); i++) {

            if (libros.get(i).getId().equals(id)) {

                libros.remove(i);
                eliminado = true;
                break;
            }
        }

        if (!eliminado) {
            return false;
        }

        try {

            List<String> lineas = new ArrayList<>();

            for (Libro libro : libros) {

                String linea = libro.getId() + "^"
                        + libro.getTitulo() + "^"
                        + libro.getAutor() + "^"
                        + libro.getPrecio() + "^"
                        + libro.getStock();

                lineas.add(linea);
            }

            Files.write(Paths.get(rutaArchivo), lineas);

            return true;

        } catch (IOException e) {

            System.out.println("Error al eliminar: " + e.getMessage());
            return false;
        }

    }

    @Override
    public void hacerCopia(LibroRepository destino) {

        List<Libro> libros = obtenerTodos();

        for (Libro libro : libros) {
            destino.insertar(libro);
        }

        System.out.println("Copia realizada correctamente.");
    }

}
