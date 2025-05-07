package Modelo.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase responsable de establecer la conexión con la base de datos MySQL.
 * Usa JDBC para conectarse a 'bd_tienda_electronica' en localhost.
 * 
 * Autor: Christian Paniagua Castro
 */
public class Conexion {

    Connection con;

    /**
     * Retorna la conexión activa con la base de datos.
     * @return objeto Connection si es exitosa, null si falla
     */
    public Connection getConnection() {
        try {
            String myBD = "jdbc:mysql://127.0.0.1:3306/bd_tienda_electronica";
            con = DriverManager.getConnection(myBD, "root", "");
            return con;
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }

}
