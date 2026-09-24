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
import util.ConexionBD;

/**
 *
 * @author DAM2
 */
public class LibroRepositoryArchivo implements LibroRepository {

    @Override
    public List<Libro> obtenerTodos() {

        List<Libro> lista = new ArrayList<>();

        String sql = "SELECT * FROM libros";

        try (Connection con = (Connection) ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

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
    public List<Libro> buscarPorTitulo(String titulo) {

        List<Libro> lista = new ArrayList<>();

        String sql = "SELECT * FROM libros WHERE titulo LIKE ?";

        try (Connection con = (Connection) ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

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

        try (Connection con = (Connection) ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

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

    @Override
    public List<Libro> buscarPorStockMinimo(int stockMinimo) {

        List<Libro> lista = new ArrayList<>();

        String sql = "SELECT * FROM libros WHERE stock >= ?";

        try (Connection con = (Connection) ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

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

    @Override
    public boolean insertar(Libro libro) {

        String sql = "INSERT INTO libros (id, titulo, autor, precio, stock) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = (Connection) ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

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

    @Override
    public boolean eliminarPorId(String id) {

        String sql = "DELETE FROM libro WHERE id=?";

        try (Connection con = (Connection) ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Libro> buscarPorRangoPrecio(double precioMinimo, double precioMaximo) {

        List<Libro> lista = new ArrayList<>();

        String sql = "SELECT * FROM libros WHERE precio BETWEEN ? AND ?";

        try (Connection con = (Connection) ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

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
