/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package daos;

import java.util.List;
import modelo.Libro;

/**
 * Define las operaciones disponibles para gestionar libros
 * en un repositorio.
 * @author DAM2
 */
public interface LibroRepository {
     /**
     * Obtiene todos los libros almacenados en el repositorio.
     *
     * @return lista con todos los libros almacenados
     */
    List<Libro> obtenerTodos();
     /**
     * Busca los libros cuyo título coincide con el texto indicado.
     *
     * @param titulo texto utilizado para buscar los libros por título
     * @return lista de libros que coinciden con el título indicado
     */
    List<Libro> buscarPorTitulo(String titulo);
     /**
     * Busca los libros cuyo autor coincide con el texto indicado.
     *
     * @param autor texto utilizado para buscar los libros por autor
     * @return lista de libros que coinciden con el autor indicado
     */
    List<Libro> buscarPorAutor(String autor);
    /**
     * Busca los libros cuyo precio se encuentra dentro del rango indicado.
     *
     * @param precioMinimo precio mínimo del rango de búsqueda
     * @param precioMaximo precio máximo del rango de búsqueda
     * @return lista de libros cuyo precio se encuentra dentro del rango indicado
     */
    List<Libro> buscarPorRangoPrecio(double precioMinimo, double precioMaximo);
     /**
     * Busca los libros que tienen una cantidad de stock igual o superior
     * al mínimo indicado.
     *
     * @param stockMinimo cantidad mínima de unidades disponibles
     * @return lista de libros cuyo stock es igual o superior al mínimo indicado
     */
    List<Libro> buscarPorStockMinimo(int stockMinimo);
     /**
     * Inserta un libro en el repositorio.
     *
     * @param libro libro que se desea insertar
     * @return {@code true} si el libro se inserta correctamente;
     *         {@code false} si no se puede realizar la inserción
     */
    boolean insertar(Libro libro);
    /**
     * Elimina del repositorio el libro que tenga el identificador indicado.
     *
     * @param id identificador del libro que se desea eliminar
     * @return {@code true} si se elimina el libro; {@code false} si no se puede realizar la eliminación
     */
    boolean eliminarPorId(String id);
     /**
     * Copia los libros de este repositorio en el repositorio de destino.
     *
     * @param destino repositorio en el que se almacenarán los libros copiados
     */
    void hacerCopia(LibroRepository destino);
}
