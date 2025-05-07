package BaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Christian Paniagua Castro
 */
public class DataBase {
    
    private static final String URL ="jdbc:mysql://127.0.0.1:3306/bd_tienda_electronica";
    private static final String USER ="root";
    private static final String PASSWORD ="1234@@";
    
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
