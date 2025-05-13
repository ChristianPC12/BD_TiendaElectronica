package Modelo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import BaseDatos.DataBase;

/**
 * DAO para autenticación de usuarios.
 */
public class LoginDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    /**
     * Valida usuario y contraseña.
     */
    public Login log(String nombre, String pass) {
        Login l = new Login();
        String sql = "SELECT * FROM usuarios WHERE nombre = ? AND pass = ?";
        try {
            con = DataBase.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            if (rs.next()) {
                l.setId(rs.getInt("id"));
                l.setNombre(rs.getString("nombre"));
                l.setCorreo(rs.getString("correo"));
                l.setPass(rs.getString("pass"));
                l.setRol(rs.getString("rol")); // ✅ Rol cargado
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return l;
    }
}
