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
 * Implementa las operaciones del repositorio de libros utilizando un archivo de
 * texto para almacenar los datos.
 *
 * @author 2DAM
 */
public class LibroRepositoryArchivo implements LibroRepository {

    /**
     * * Ruta del archivo donde se almacenan los libros.
     */
    private String rutaArchivo;

    /**
     * Crea un repositorio de libros asociado al archivo indicado.
     *
     * @param rutaArchivo ruta del archivo donde se almacenarán los libros
     */
    public LibroRepositoryArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    /**
     * Lee todas las líneas del archivo indicado, separa los datos de cada libro
     * utilizando {@code ^} y crea un objeto {@link Libro} por cada línea.
     *
     * @return lista con los libros almacenados en el archivo; vacía si no se
     * puede leer el archivo
     */
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

    /**
     * Obtiene todos los libros del archivo y filtra aquellos cuyo título
     * coincide con el indicado sin distinguir entre mayúsculas y minúsculas.
     *
     * @param titulo título que se desea buscar
     * @return lista de libros cuyo título coincide con el indicado
     */
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

    /**
     * Obtiene todos los libros del archivo y filtra aquellos cuyo autor
     * coincide con el indicado sin distinguir entre mayúsculas y minúsculas.
     *
     * @param autor autor que se desea buscar
     * @return lista de libros cuyo autor coincide con el indicado
     */
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

    /**
     * Obtiene todos los libros del archivo y filtra aquellos cuyo precio se
     * encuentra entre el precio mínimo y el precio máximo indicados.
     *
     * @param precioMinimo precio mínimo del rango de búsqueda
     * @param precioMaximo precio máximo del rango de búsqueda
     * @return lista de libros cuyo precio se encuentra dentro del rango
     * indicado
     */
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

    /**
     * Obtiene todos los libros del archivo y filtra aquellos cuyo stock es
     * igual o superior al mínimo indicado.
     *
     * @param stockMinimo cantidad mínima de unidades disponibles
     * @return lista de libros cuyo stock es igual o superior al mínimo indicado
     */
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

    /**
     * Convierte los datos del libro en una línea de texto separada por
     * {@code ^} y la añade al final del archivo.
     *
     * @param libro libro que se desea insertar en el archivo
     * @return {@code true} si el libro se añade correctamente; {@code false} si
     * se produce un error al escribir en el archivo
     */
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

    /**
     * Lee los libros del archivo, elimina de la lista el libro cuyo
     * identificador coincide con el indicado y vuelve a escribir el contenido
     * actualizado en el archivo.
     *
     * @param id identificador del libro que se desea eliminar
     * @return {@code true} si se elimina un libro; {@code false} si no se
     * encuentra el identificador o se produce un error al escribir el archivo
     */
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

    /**
     * Obtiene todos los libros almacenados en el archivo y los inserta en el
     * repositorio de destino para realizar una copia.
     *
     * @param destino repositorio en el que se almacenarán los libros copiados
     */
    @Override
    public void hacerCopia(LibroRepository destino) {

        List<Libro> libros = obtenerTodos();

        for (Libro libro : libros) {
            destino.insertar(libro);
        }

        System.out.println("Copia realizada correctamente.");
    }

}
