package Modelo.Clientes;

/**
 * Clase ClienteDTO que actúa como un Data Transfer Object (DTO).
 * Se utiliza para transportar los datos del cliente entre capas del sistema
 * sin exponer directamente la lógica de negocio.
 * 
 * Todos los atributos son inmutables (final).
 * 
 * Autor: Christian Paniagua Castro
 */
public class ClienteDTO {

    private final String cedula;
    private final String nombreCompleto;
    private final String direccion;
    private final String telefono;
    private final String correoElectronico;

    /**
     * Constructor que inicializa todos los campos del cliente.
     * @param cedula Cédula del cliente
     * @param nombreCompleto Nombre completo del cliente
     * @param direccion Dirección del cliente
     * @param telefono Teléfono del cliente
     * @param correoElectronico Correo electrónico del cliente
     */
    public ClienteDTO(String cedula, String nombreCompleto, String direccion, String telefono, String correoElectronico) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
    }

    // Métodos getter para acceder a los atributos

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
}
