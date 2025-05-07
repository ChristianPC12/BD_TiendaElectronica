package Modelo.Producto;

/**
 *
 * @author Christian Paniagua Castro
 */
import java.time.LocalDate;

public class Productos {

    private int codigo;
    private String nombre;
    private String categoria;
    private double precio;
    private int cantDisponible;
    private int proveedor;
    private LocalDate fechaPIngresado;

    public Productos() {
    }
    
    public Productos(int codigo, String nombre, String categoria, double precio, int cantDisponible, int proveedor, LocalDate fechaPIngresado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.cantDisponible = cantDisponible;
        this.proveedor = proveedor;
        this.fechaPIngresado = fechaPIngresado;
    }

    public Productos(int codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    // Getters y setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantDisponible() {
        return cantDisponible;
    }

    public void setCantDisponible(int cantDisponible) {
        this.cantDisponible = cantDisponible;
    }

    public int getProveedor() {
        return proveedor;
    }

    public void setProveedor(int proveedor) {
        this.proveedor = proveedor;
    }

    public LocalDate getFechaPIngresado() {
        return fechaPIngresado;
    }

    public void setFechaPIngresado(LocalDate fechaPIngresado) {
        this.fechaPIngresado = fechaPIngresado;
    }
}
