package Modelo.Ventas;

import java.time.LocalDate;

/**
 *
 * @author Christian Paniagua Castro
 */
public class VentasDTO {
    
     private int idIncremento = 1; 

    private int idPlanilla;
    private LocalDate fechaRecibo = LocalDate.now();
    private int cedulaEmpleado;
    private String nomEmpleado;
    private int codigoProducto;
    private String nombreProducto;
    private int precioProducto;
    private int cantVendidos;
    private int subTotal;
    private int impuestos;
    private int total;

    public int getIdIncremento() {
        return idIncremento;
    }

    public int getIdPlanilla() {
        return idPlanilla;
    }

    public LocalDate getFechaRecibo() {
        return fechaRecibo;
    }

    public int getCedulaEmpleado() {
        return cedulaEmpleado;
    }

    public String getNomEmpleado() {
        return nomEmpleado;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public int getPrecioProducto() {
        return precioProducto;
    }

    public int getCantVendidos() {
        return cantVendidos;
    }

    public int getSubTotal() {
        return subTotal;
    }

    public int getImpuestos() {
        return impuestos;
    }

    public int getTotal() {
        return total;
    }

    public VentasDTO(int idPlanilla, LocalDate toLocalDate, int cedulaEmpleado, String nomEmpleado, int codigoProducto, String nombreProducto, int precioProducto, int cantVendidos, int subTotal, int impuestos, int total) {
        this.idPlanilla = idPlanilla;
        this.cedulaEmpleado = cedulaEmpleado;
        this.nomEmpleado = nomEmpleado;
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.cantVendidos = cantVendidos;
        this.subTotal = subTotal;
        this.impuestos = impuestos;
        this.total = total;
    }
    
    
    
    
}
