package Modelo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase DAO encargada de manejar el proceso de autenticación de usuarios.
 * Consulta la base de datos para validar nombre de usuario y contraseña.
 * 
 * Autor: Christian Paniagua Castro
 */
public class LoginDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();

    /**
     * Valida las credenciales del usuario en la base de datos.
     * @param nombre Nombre de usuario ingresado.
     * @param pass Contraseña ingresada.
     * @return Objeto Login con los datos del usuario si son válidos, o vacío si no coincide.
     */
    public Login log(String nombre, String pass) {
        Login l = new Login();
        String sql = "SELECT * FROM usuarios WHERE nombre= ? AND pass = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            if (rs.next()) {
                l.setId(rs.getInt("id"));
                l.setNombre(rs.getString("nombre"));
                l.setCorreo(rs.getString("correo"));
                l.setPass(rs.getString("pass"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return l;
    }
}
