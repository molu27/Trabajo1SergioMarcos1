/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.*;

/**
 * Gestiona la conexión con la base de datos utilizando los datos de configuración
 * definidos en las variables de entorno.
 *
 * @author DAM2
 */
public class ConexionBD {

    /**
     * Establece una conexión con la base de datos utilizando la URL,
     * usuario y contraseña definidos en las variables de entorno.
     *
     * @return conexión establecida con la base de datos
     * @throws SQLException si se produce un error al establecer la conexión
     */
    public static Connection conectar() throws SQLException {
        Dotenv env = Dotenv.load();
        String url = env.get("DB_URL");
        String user = env.get("DB_USER");
        String pass = env.get("DB_PASS");

        return DriverManager.getConnection(url, user, pass);
    }

    /**
     * Método generado automáticamente que no está implementado.
     *
     * @return no devuelve ninguna conexión
     * @throws UnsupportedOperationException siempre que se ejecuta
     */
    public static com.sun.jdi.connect.spi.Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
