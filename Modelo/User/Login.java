package Modelo.User;

/**
 * Clase modelo de usuario para el sistema de login.
 */
public class Login {
    private int id;
    private String nombre;
    private String correo;
    private String pass;
    private String rol; // ✅ Nuevo campo

    public Login() {
    }

    public Login(int id, String nombre, String correo, String pass, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.pass = pass;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getPass() {
        return pass;
    }

    public String getRol() {
        return rol;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
