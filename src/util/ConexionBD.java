/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.*;
/**
 *
 * @author DAM2
 */
public class ConexionBD {
  public static Connection conectar() throws SQLException {
  
Dotenv env = Dotenv.load();
String url = env.get("DB_URL");
String user = env.get("DB_USER");
String pass = env.get("DB_PASS");

return DriverManager.getConnection(url, user, pass);
  
  
  
  
  }

    public static com.sun.jdi.connect.spi.Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
