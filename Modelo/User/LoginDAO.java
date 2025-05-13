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
        Login l = null; // ⬅️ Evita devolver un objeto vacío
        String sql = "SELECT * FROM usuarios WHERE Nombre = ? AND Pass = ?";

        try (Connection con = DataBase.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                l = new Login();
                l.setId(rs.getInt("Id"));
                l.setNombre(rs.getString("Nombre"));
                l.setCorreo(rs.getString("Correo"));
                l.setPass(rs.getString("Pass"));
                l.setRol(rs.getString("rol"));
            }

        } catch (SQLException e) {
            System.out.println("Error en login: " + e.getMessage());
        }

        return l;
    }

}
