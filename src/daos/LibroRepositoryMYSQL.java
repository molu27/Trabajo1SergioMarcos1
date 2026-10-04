/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Libro;
import util.ConexionBD;

/**
 * Implementa las operaciones del repositorio de libros utilizando una base de
 * datos MySQL.
 *
 * @author DAM2
 */
public class LibroRepositoryMYSQL implements LibroRepository {

    /**
     * Obtiene todos los libros almacenados en la tabla {@code libros} de la
     * base de datos y los convierte en objetos {@link Libro}.
     *
     * @return lista con todos los libros almacenados; vacía si no se encuentra
     * ningún libro o se produce un error de SQL
     */
    @Override
    public List<Libro> obtenerTodos() {

        List<Libro> lista = new ArrayList<>();

        String sql = "SELECT * FROM libros";

        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapearFila(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return lista;
    }

    /**
     * * Busca en la tabla {@code libros} aquellos libros cuyo título * contiene
     * el texto indicado, utilizando una consulta SQL con {@code LIKE}.
     *
     * * @param titulo texto que se utilizará para buscar coincidencias en el
     * título * @return lista de libros cuyo título contiene el texto indicado;
     * vacía si no se encuentra ningún libro o se produce un error de SQL
     */

    @Override
    public List<Libro> buscarPorTitulo(String titulo) {

        List<Libro> lista = new ArrayList<>();

        String sql = "SELECT * FROM libros WHERE titulo LIKE ?";

        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + titulo + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapearFila(rs));

            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public List<Libro> buscarPorAutor(String autor) {

        List<Libro> lista = new ArrayList<>();

        String sql = "SELECT * FROM libros WHERE autor LIKE ?";

        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + autor + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapearFila(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return lista;
    }

    /**
     * Busca en la tabla {@code libros} aquellos libros cuyo stock es mayor o
     * igual que el valor mínimo indicado.
     *
     * @param stockMinimo cantidad mínima de unidades que debe tener el libro
     * @return lista de libros cuyo stock es mayor o igual al mínimo indicado;
     * vacía si no se encuentra ningún libro o se produce un error de SQL
     */
    @Override
    public List<Libro> buscarPorStockMinimo(int stockMinimo) {

        List<Libro> lista = new ArrayList<>();

        String sql = "SELECT * FROM libros WHERE stock >= ?";

        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, stockMinimo);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapearFila(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return lista;
    }

    /**
     * Inserta un nuevo libro en la tabla {@code libros} de la base de datos.
     *
     * @param libro libro que se desea insertar
     * @return {@code true} si el libro se inserta correctamente; {@code false}
     * si no se realiza ninguna inserción o se produce un error de SQL
     */
    @Override
    public boolean insertar(Libro libro) {

        String sql = "INSERT INTO libros (id, titulo, autor, precio, stock) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, libro.getId());
            ps.setString(2, libro.getTitulo());
            ps.setString(3, libro.getAutor());
            ps.setDouble(4, libro.getPrecio());
            ps.setInt(5, libro.getStock());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina de la tabla {@code libros} el libro que tenga el identificador
     * indicado.
     *
     * @param id identificador del libro que se desea eliminar
     * @return {@code true} si se elimina algún libro; {@code false} si no se
     * encuentra el identificador o se produce un error de SQL
     */
    @Override
    public boolean eliminarPorId(String id) {

        String sql = "DELETE FROM libros WHERE id=?";

        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }

    /**
     * Busca en la tabla {@code libros} los libros cuyo precio se encuentra
     * entre el precio mínimo y el precio máximo indicados.
     *
     * @param precioMinimo precio mínimo del rango de búsqueda
     * @param precioMaximo precio máximo del rango de búsqueda
     * @return lista de libros cuyo precio está dentro del rango indicado; vacía
     * si no se encuentra ningún libro o se produce un error de SQL
     */
    @Override
    public List<Libro> buscarPorRangoPrecio(double precioMinimo, double precioMaximo) {

        List<Libro> lista = new ArrayList<>();

        String sql = "SELECT * FROM libros WHERE precio BETWEEN ? AND ?";

        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, precioMinimo);
            ps.setDouble(2, precioMaximo);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapearFila(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return lista;
    }

    /**
     * Obtiene todos los libros de este repositorio y los inserta en el
     * repositorio de destino para realizar una copia.
     *
     * @param destino repositorio en el que se almacenará la copia de los libros
     */
    @Override
    public void hacerCopia(LibroRepository destino) {
        List<Libro> libros = obtenerTodos();

        for (Libro libro : libros) {
            destino.insertar(libro);
        }

        System.out.println("Copia realizada correctamente.");
    }

    /**
     * Convierte una fila del resultado de una consulta SQL en un objeto
     * {@link Libro}, obteniendo sus datos de las columnas de la tabla.
     *
     * @param rs resultado de la consulta que contiene la fila que se va a
     * mapear
     * @return objeto {@link Libro} creado con los datos de la fila
     * @throws SQLException si se produce un error al obtener los datos de la
     * fila del resultado
     */
    private Libro mapearFila(ResultSet rs) throws SQLException {

        Libro libro = new Libro();

        libro.setId(rs.getString("id"));
        libro.setTitulo(rs.getString("titulo"));
        libro.setAutor(rs.getString("autor"));
        libro.setPrecio(rs.getDouble("precio"));
        libro.setStock(rs.getInt("stock"));

        return libro;
    }

}
