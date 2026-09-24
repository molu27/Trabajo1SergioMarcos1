/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package daos;

import java.util.List;

/**
 *
 * @author DAM2
 */
public interface LibroRepository {
    List<Libro> obtenerTodos();
    List<Libro> buscarPorTitulo(String titulo);
    List<Libro> buscarPorAutor(String autor);
    List<Libro> buscarPorRangoPrecio(double precioMinimo, double precioMaximo);
    List<Libro> buscarPorStockMinimo(int stockMinimo);
    boolean insertar(Libro libro);
    boolean eliminarPorId(String id);
}
