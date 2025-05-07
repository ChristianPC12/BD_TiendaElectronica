package controlador;

import BaseDatos.DataBase;
import Modelo.Producto.Productos;
import Modelo.Producto.ProductosDAO;
import Modelo.Producto.ProductosDTO;
import Modelo.Producto.ProductosMapper;
import Modelo.Proveedores.Proveedor;
import Modelo.Proveedores.ProveedorDAO;
import Modelo.Proveedores.ProveedorDTO;
import Modelo.Vista.Vista;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Controlador para la gestión de Productos
 */
public class ProductosControlador {

    private final ProductosDAO dao;
    private final Vista<Productos> vista;
    private final ProductosMapper mapper;
    private final ProveedorDAO proveedorDAO;
    

    // Constructor completo
    public ProductosControlador(Vista<Productos> vista, ProductosMapper mapper, ProveedorDAO proveedorDAO) {
        this.vista = vista;
        this.mapper = mapper;
        this.proveedorDAO = Objects.requireNonNull(proveedorDAO, "ProveedorDAO no puede ser nulo");
        try {
            this.dao = new ProductosDAO(DataBase.getConnection());
        } catch (SQLException ex) {
            vista.showError("Error al conectar con la Base de Datos: " + ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    // Constructor por defecto
    public ProductosControlador(Vista vista) {
        this.vista = vista;
        try {
            this.proveedorDAO = new ProveedorDAO(DataBase.getConnection());
            this.mapper = new ProductosMapper(proveedorDAO);
            this.dao = new ProductosDAO(DataBase.getConnection());
        } catch (SQLException ex) {
            vista.showError("Error al conectar con la Base de Datos: " + ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    // Crear producto
    public void create(Productos producto) {
        if (producto == null || !validateRequired(producto)) {
            vista.showError("Faltan datos requeridos.");
            return;
        }
        try {
            if (!validatePK(producto.getCodigo())) {
                vista.showError("El código del producto ya está registrado.");
                return;
            }
            dao.create(mapper.toDTO(producto));
            vista.showMessage("Producto agregado exitosamente.");
            readAll(); // Actualizar la vista con los datos nuevos
        } catch (SQLException ex) {
            vista.showError("Error al guardar el producto: " + ex.getMessage());
        }
    }

    // Leer un producto por código
    public void read(int codigo) {
        try {
            ProductosDTO dto = dao.read(codigo);
            Productos producto = mapper.toEnt(dto);
            if (producto != null) {
                vista.show(producto);
            } else {
                vista.showError("Producto no encontrado.");
            }
        } catch (SQLException ex) {
            vista.showError("Error al buscar el producto: " + ex.getMessage());
        }
    }

    // Leer todos los productos y devolver la lista
    public List<Productos> readAll() {
        try {
            // Obtener todos los productos desde el DAO
            List<Productos> productos = dao.readAll()
                    .stream()
                    .map(mapper::toEnt)
                    .toList();

            // Actualizar la vista con los productos obtenidos
            vista.showAll(productos);  // Esto actualizará la tabla en la vista

            return productos;  // También devolvemos la lista de productos para usarla en otras funciones como la búsqueda
        } catch (SQLException ex) {
            vista.showError("Error al cargar los productos: " + ex.getMessage());
            return new ArrayList<>();  // Devuelve una lista vacía en caso de error
        }
    }


    // Actualizar un producto
    public void update(Productos producto) {
        if (producto == null || !validateRequired(producto)) {
            vista.showError("Faltan datos requeridos.");
            return;
        }
        try {
            if (validatePK(producto.getCodigo())) {
                vista.showError("El producto no está registrado.");
                return;
            }
            dao.update(mapper.toDTO(producto));
            vista.showMessage("Producto actualizado exitosamente.");
            readAll(); // Actualizar la vista con los datos nuevos
        } catch (SQLException ex) {
            vista.showError("Error al actualizar el producto: " + ex.getMessage());
        }
    }

    // Eliminar un producto
    public void delete(Productos producto) {
        if (producto == null) {
            vista.showError("No hay ningún producto cargado actualmente.");
            return;
        }
        try {
            if (validatePK(producto.getCodigo())) {
                vista.showError("El producto no está registrado.");
                return;
            }
            dao.delete(producto.getCodigo());
            vista.showMessage("Producto eliminado exitosamente.");
            readAll(); // Actualizar la vista con los datos nuevos
        } catch (SQLException ex) {
            vista.showError("Error al eliminar el producto: " + ex.getMessage());
        }
    }

    // Leer todos los proveedores y convertirlos al modelo de la vista
    public List<Proveedor> readAllProveedores() {
        try {
            List<ProveedorDTO> dtos = proveedorDAO.readAll();
            return mapToProveedores(dtos);
        } catch (SQLException e) {
            System.err.println("Error al obtener proveedores: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Mapeo de DTO a Proveedor
    private List<Proveedor> mapToProveedores(List<ProveedorDTO> dtos) {
        List<Proveedor> proveedores = new ArrayList<>();
        for (ProveedorDTO dto : dtos) {
            // Usa el constructor que omite el ID
            Proveedor proveedor = new Proveedor(
                    dto.getNombre(),
                    dto.getContacto(),
                    dto.getDireccion()
            );
            // Asigna manualmente el ID autoincremental
            proveedor.setId(dto.getId());
            proveedores.add(proveedor);
        }
        return proveedores;
    }

    // Validar campos requeridos
    private boolean validateRequired(Productos producto) {
        return producto.getNombre() != null && !producto.getNombre().trim().isEmpty()
                && producto.getCategoria() != null && !producto.getCategoria().trim().isEmpty()
                && producto.getPrecio() > 0
                && producto.getCantDisponible() >= 0
                && producto.getProveedor() > 0;
    }

    // Validar llave primaria (código)
    private boolean validatePK(int codigo) {
        try {
            return dao.read(codigo) == null;
        } catch (SQLException ex) {
            System.err.println("Error al validar la llave primaria: " + ex.getMessage());
            return false;
        }
    }
}
