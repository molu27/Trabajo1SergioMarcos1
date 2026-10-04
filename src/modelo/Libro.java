/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 * Representa un libro con sus datos de identificación, título, autor, precio y
 * cantidad disponible en stock.
 *
 * @author DAM2
 */
public class Libro {

    /**
     * Identificador del libro.
     */
    protected String id;

    /**
     * Título del libro.
     */
    protected String titulo;

    /**
     * Autor del libro.
     */
    protected String autor;

    /**
     * Precio del libro.
     */
    protected double precio;

    /**
     * Cantidad disponible del libro.
     */
    protected int stock;

    /**
     * Crea un libro con todos sus datos, incluido su identificador.
     *
     * @param id identificador del libro
     * @param titulo título del libro
     * @param autor autor del libro
     * @param precio precio del libro
     * @param stock cantidad de unidades disponibles del libro
     */
    public Libro(String id, String titulo, String autor, double precio, int stock) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;
        this.stock = stock;
    }

    /**
     * Crea un libro con su título, autor, precio y cantidad disponible, sin
     * establecer un identificador.
     *
     * @param titulo título del libro
     * @param autor autor del libro
     * @param precio precio del libro
     * @param stock cantidad de unidades disponibles del libro
     */
    public Libro(String titulo, String autor, double precio, int stock) {
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;
        this.stock = stock;
    }

    /**
     * Crea un libro sin establecer ninguno de sus datos.
     */
    public Libro() {
    }

    /**
     * Obtiene el identificador del libro.
     *
     * @return identificador del libro
     */
    public String getId() {
        return id;
    }

    /**
     * Obtiene el título del libro.
     *
     * @return título del libro
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Obtiene el autor del libro.
     *
     * @return autor del libro
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Obtiene el precio del libro.
     *
     * @return precio del libro
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Obtiene la cantidad de unidades disponibles del libro.
     *
     * @return cantidad de unidades disponibles
     */
    public int getStock() {
        return stock;
    }

    /**
     * Establece el identificador del libro.
     *
     * @param id nuevo identificador del libro
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Establece el título del libro.
     *
     * @param titulo nuevo título del libro
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Establece el autor del libro.
     *
     * @param autor nuevo autor del libro
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Establece el precio del libro.
     *
     * @param precio nuevo precio del libro
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Establece la cantidad de unidades disponibles del libro.
     *
     * @param stock nueva cantidad de unidades disponibles
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Devuelve una representación textual del libro con todos sus datos.
     *
     * @return representación textual del libro
     */
    @Override
    public String toString() {
        return "Libro{" + "id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", precio=" + precio + ", stock=" + stock + '}';
    }

}
