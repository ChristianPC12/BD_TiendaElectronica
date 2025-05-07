package Modelo.Proveedores;

/**
 *
 * @author Christian Paniagua Castro
 */
public class ProveedorDTO {
    private final int id;
    private final String nombre;
    private final String contacto;
    private final String direccion;

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public String getDireccion() {
        return direccion;
    }
   
    public ProveedorDTO(int id, String nombre, String contacto, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.contacto = contacto;
        this.direccion = direccion;
    }

}
