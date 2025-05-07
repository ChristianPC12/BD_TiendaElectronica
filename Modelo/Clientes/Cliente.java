package Modelo.Clientes;

/**
 *
 * @author Christian Paniagua Castro
 */
public class Cliente {
    private String cedula;
    private String nombreCompleto;
    private String direccion;
    private String telefono;
    private String correoElectronico;

    public String getCedula() {
        return cedula;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCedula(String cedula) {
        if (cedula == null || cedula.trim().isEmpty()) {
            throw new IllegalArgumentException("La cédula debe ser un número positivo.");
        }
        this.cedula = cedula;
    }

    public void setNombreCompleto(String nombreCompleto) {
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre completo no puede estar vacío.");
        }
        this.nombreCompleto = nombreCompleto;
    }

    public void setDireccion(String direccion) {
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección no puede estar vacía.");
        }
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono no puede estar vacío.");
        }
        this.telefono = telefono;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Cliente(String cedula, String nombreCompleto, String direccion, String telefono, String correoElectronico) {
        setCedula(cedula);
        setNombreCompleto(nombreCompleto);
        setDireccion(direccion);
        setTelefono(telefono);
        setCorreoElectronico(correoElectronico);
    }
    
    public Cliente() {
        
    }

    @Override
    public String toString() {
        return "Cliente{" + "cedula=" + cedula + ", nombreCompleto=" + nombreCompleto + ", direccion=" + direccion + ", telefono=" + telefono + ", correoElectronico=" + correoElectronico + '}';
    }

   
}
