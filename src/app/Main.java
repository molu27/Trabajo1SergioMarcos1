/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import daos.LibroRepository;
import daos.LibroRepositoryMYSQL;
import java.util.List;
import java.util.Scanner;
import modelo.Libro;
import util.LibroRepositoryArchivo;

/**
 * Clase principal que permite gestionar los libros utilizando un repositorio
 * de archivo de texto o un repositorio MySQL mediante un menú de opciones.
 *
 * @author 2DAM
 */
public class Main {

    /**
     * Inicia la aplicación, permite seleccionar el origen de datos y muestra
     * un menú para consultar, insertar, eliminar y copiar libros.
     *
     * @param args argumentos recibidos al iniciar la aplicación
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Elegimos dónde vamos a guardar/leer los libros
        System.out.println("===== ORIGEN DE DATOS =====");
        System.out.println("1. Fichero TXT");
        System.out.println("2. MySQL");
        System.out.print("Elige una opción: ");

        int tipo = Integer.parseInt(sc.nextLine());

        LibroRepository libros;
        LibroRepository otroRepositorio;

        if (tipo == 1) {

            libros = new LibroRepositoryArchivo("datos/libros.txt");
            otroRepositorio = new LibroRepositoryMYSQL();

        } else if (tipo == 2) {

            libros = new LibroRepositoryMYSQL();
            otroRepositorio = new LibroRepositoryArchivo("datos/libros.txt");

        } else {

            System.out.println("Opción no válida.");
            return;
        }

        int opcion = -1;

        do {

            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("1. Ver todos los libros");
            System.out.println("2. Buscar por título");
            System.out.println("3. Buscar por autor");
            System.out.println("4. Buscar por rango de precio");
            System.out.println("5. Buscar por stock mínimo");
            System.out.println("6. Insertar libro");
            System.out.println("7. Eliminar libro por ID");
            System.out.println("8. Hacer copia");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {

                case 1:

                    System.out.println("Todos los libros:");

                    for (Libro l : libros.obtenerTodos()) {
                        System.out.println(l);
                    }

                    break;

                case 2:
                    System.out.print("Introduce el título: ");
                    String titulo = sc.nextLine();

                    List<Libro> librosTitulo = libros.buscarPorTitulo(titulo);

                    if (librosTitulo.isEmpty()) {
                        System.out.println("No se encontraron libros.");
                    } else {
                        for (Libro l : librosTitulo) {
                            System.out.println(l);
                        }
                    }
                    break;

                case 3:
                    System.out.println("Introduce el autor: ");
                    String autor = sc.nextLine();
                    List<Libro> librosAutor = libros.buscarPorAutor(autor);
                    if (librosAutor.isEmpty()) {
                        System.out.println("No se encontraron autores.");
                    } else {
                        for (Libro l : librosAutor) {
                            System.out.println(l);
                        }
                    }
                    break;

                case 4:
                    System.out.println("Introduce el precio minimo: ");
                    Double precioMin = sc.nextDouble();
                    System.out.println("Introduce el precio maximo: ");
                    Double precioMax = sc.nextDouble();
                    List<Libro> librosRango = libros.buscarPorRangoPrecio(precioMin, precioMax);
                    for (Libro l : librosRango) {
                        System.out.println(l);
                    }
                    break;

                case 5:
                    System.out.println("Introduce el stock minimo: ");
                    int stockMin = sc.nextInt();
                    List<Libro> librosStock = libros.buscarPorStockMinimo(stockMin);
                    for (Libro l : librosStock) {
                        System.out.println(l);
                    }
                    break;

                case 6:
                    System.out.print("Introduce el ID: ");
                    String id = sc.nextLine();

                    System.out.print("Introduce el título: ");
                    String tituloNuevo = sc.nextLine();

                    System.out.print("Introduce el autor: ");
                    String autorNuevo = sc.nextLine();

                    System.out.print("Introduce el precio: ");
                    double precio = Double.parseDouble(sc.nextLine());

                    System.out.print("Introduce el stock: ");
                    int stock = Integer.parseInt(sc.nextLine());

                    Libro nuevoLibro = new Libro(
                            id,
                            tituloNuevo,
                            autorNuevo,
                            precio,
                            stock
                    );

                    if (libros.insertar(nuevoLibro)) {
                        System.out.println("Libro insertado correctamente.");
                    } else {
                        System.out.println("No se ha podido insertar el libro.");
                    }

                    break;

                case 7:
                    System.out.print("Introduce el ID del libro que quieres eliminar: ");
                    String idEliminar = sc.nextLine();

                    if (libros.eliminarPorId(idEliminar)) {
                        System.out.println("Libro eliminado correctamente.");
                    } else {
                        System.out.println("No se encontró ningún libro con ese ID.");
                    }

                    break;

                case 8:
                    libros.hacerCopia(otroRepositorio);
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida. Solo del 0 al 7");
                    break;
            }

        } while (opcion != 0);

        sc.close();
    }
}