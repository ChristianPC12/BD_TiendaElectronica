package Modelo.Ventas;

import java.time.LocalDate;

public class Ventas {

    // Variable estática para gestionar el incremento automático del ID de planilla
    private int idIncremento = 1; // Cambiado para empezar en 1

    // Atributos de la clase Ventas
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

    public Ventas(String fechaPago, int cedulaEmpleado, String nomEmpleado, int codigoProducto, String nombreProducto,
            int precioProducto, int cantVendidos, int subTotal, int impuestos, int total) {
        this.idPlanilla = idIncremento++;
        this.cedulaEmpleado = 0;
        this.nomEmpleado = "pronto";
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.cantVendidos = cantVendidos;
        this.subTotal = subTotal;
        this.impuestos = impuestos;
        this.total = total;
    }

    /**
     * Constructor por defecto que inicializa los atributos con valores
     * predeterminados.
     */
    public Ventas() {
        this.idPlanilla = idIncremento++;
        this.fechaRecibo = LocalDate.now();
        this.cedulaEmpleado = 0;
        this.nomEmpleado = "pronto";
        this.codigoProducto = 0;
        this.nombreProducto = "";
        this.precioProducto = 0;
        this.cantVendidos = 0;
        this.subTotal = 0;
        this.impuestos = 0;
        this.total = 0;
    }

    // Métodos getter y setter para cada atributo
    /**
     * Obtiene el ID de la planilla.
     *
     * @return El ID de la planilla.
     */
    public int getIdPlanilla() {
        return idPlanilla;
    }

    public int getIdIncremento() {
        return idIncremento;
    }

    /**
     * Establece el ID de la planilla.
     *
     * @param idPlanilla El ID de la planilla.
     */
    public void setIdPlanilla(int idPlanilla) {
        this.idPlanilla = idPlanilla;
    }

    /**
     * Obtiene la fecha de creación de la planilla.
     *
     * @return La fecha de creación de la planilla.
     */
    public LocalDate getFechaRecibo() {
        return fechaRecibo;
    }

    public void setFechaRecibo(LocalDate fechaRecibo) {
        this.fechaRecibo = fechaRecibo;
    }

    public int getCedulaEmpleado() {
        return cedulaEmpleado;
    }

    public int setCedulaEmpleado(int cedulaEmpleado) {
        return this.cedulaEmpleado = cedulaEmpleado;
    }

    /**
     * Obtiene el nombre del empleado.
     *
     * @return El nombre del empleado.
     */
    public String getNomEmpleado() {
        return nomEmpleado;
    }

    /**
     * Establece el nombre del empleado.
     *
     * @param nomEmpleado El nombre del empleado.
     */
    public void setNomEmpleado(String nomEmpleado) {
        this.nomEmpleado = nomEmpleado;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(int precioProducto) {
        this.precioProducto = precioProducto;
    }

    public int getCantVendidos() {
        return cantVendidos;
    }

    public void setCantVendidos(int cantVendidos) {
        this.cantVendidos = cantVendidos;
    }

    public int getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(int subTotal) {
        this.subTotal = subTotal;
    }

    public int getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(int impuestos) {
        this.impuestos = impuestos;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
}
