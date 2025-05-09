package controlador;

import BaseDatos.DataBase;
import Modelo.Ventas.Ventas;
import Modelo.Ventas.VentasDAO;
import Modelo.Ventas.VentasDTO;
import Modelo.Vista.Vista;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador para la gestión de ventas
 */
public class VentasControlador {

    private final VentasDAO dao;
    private final Vista<Ventas> vista;

    public VentasControlador(Vista<Ventas> vista) {
        this.vista = vista;
        try {
            this.dao = new VentasDAO(DataBase.getConnection());
        } catch (SQLException ex) {
            vista.showError("Error al conectar con la base de datos: " + ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    // Crear una nueva venta
    public void create(Ventas venta) {
        if (venta == null || !validateRequired(venta)) {
            vista.showError("Todos los campos de venta son obligatorios.");
            return;
        }
        try {
            VentasDTO dto = mapToDTO(venta);
            dao.create(dto);
            vista.showMessage("Venta registrada exitosamente.");
            readAll();
        } catch (SQLException ex) {
            vista.showError("Error al registrar la venta: " + ex.getMessage());
        }
    }

    // Leer todas las ventas
    public List<Ventas> readAll() {
        try {
            List<VentasDTO> dtos = dao.readAll();
            List<Ventas> ventas = new ArrayList<>();
            for (VentasDTO dto : dtos) {
                Ventas venta = mapToVenta(dto);
                ventas.add(venta);
            }
            vista.showAll(ventas);
            return ventas;
        } catch (SQLException ex) {
            vista.showError("Error al cargar las ventas: " + ex.getMessage());
            return new ArrayList<>();
        }
    }

    public void delete(Ventas venta) {
        if (venta == null) {
            vista.showError("No hay venta seleccionada para eliminar.");
            return;
        }
        try {
            dao.delete(venta.getIdPlanilla());
            vista.showMessage("Venta eliminada correctamente.");
            readAll(); // Refrescar la tabla
        } catch (SQLException ex) {
            vista.showError("Error al eliminar la venta: " + ex.getMessage());
        }

    }

    // Validar campos obligatorios
    private boolean validateRequired(Ventas venta) {
        return venta.getCodigoProducto() > 0
                && venta.getPrecioProducto() > 0
                && venta.getCantVendidos() > 0
                && venta.getCedulaEmpleado() > 0
                && venta.getNomEmpleado() != null
                && !venta.getNomEmpleado().trim().isEmpty();
    }

    // Mapear entidad a DTO
    private VentasDTO mapToDTO(Ventas v) {
        return new VentasDTO(
                v.getIdPlanilla(),
                v.getFechaRecibo(),
                v.getCedulaEmpleado(),
                v.getNomEmpleado(),
                v.getCodigoProducto(),
                v.getNombreProducto(),
                v.getPrecioProducto(),
                v.getCantVendidos(),
                v.getSubTotal(),
                v.getImpuestos(),
                v.getTotal()
        );
    }

    // Mapear DTO a entidad
    private Ventas mapToVenta(VentasDTO dto) {
        Ventas venta = new Ventas();
        venta.setIdPlanilla(dto.getIdPlanilla());
        venta.setFechaRecibo(dto.getFechaRecibo());
        venta.setCedulaEmpleado(dto.getCedulaEmpleado());
        venta.setNomEmpleado(dto.getNomEmpleado());
        venta.setCodigoProducto(dto.getCodigoProducto());
        venta.setNombreProducto(dto.getNombreProducto());
        venta.setPrecioProducto(dto.getPrecioProducto());
        venta.setCantVendidos(dto.getCantVendidos());
        venta.setSubTotal(dto.getSubTotal());
        venta.setImpuestos(dto.getImpuestos());
        venta.setTotal(dto.getTotal());
        return venta;
    }
}
