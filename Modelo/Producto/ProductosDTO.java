package Modelo.Producto;

import java.time.LocalDate;

public class ProductosDTO {

    private final int codigo;
    private final String nombre;
    private final String categoria;
    private final double precio; 
    private final int cantDisponible;
    private final int proveedor; 


    public ProductosDTO(int codigo, String nombre, String categoria, double precio, int cantDisponible, int proveedor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.cantDisponible = cantDisponible;
        this.proveedor = proveedor;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantDisponible() {
        return cantDisponible;
    }

    public int getProveedor() {
        return proveedor;
    }

}
