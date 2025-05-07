package Modelo.Producto;

import Modelo.Mapper.Mapper;
import Modelo.Proveedores.ProveedorDAO;
import Modelo.Proveedores.ProveedorDTO;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Mapper que convierte entre entidades del modelo Productos y objetos DTO.
 * También permite acceder a datos del proveedor usando ProveedorDAO.
 * 
 * Autor: Christian Paniagua Castro
 */
public class ProductosMapper implements Mapper<Productos, ProductosDTO> {

    private final ProveedorDAO proveedorDAO;

    /**
     * Constructor que recibe un ProveedorDAO necesario para acceder a datos relacionados.
     *
     * @param proveedorDAO DAO para acceder a proveedores.
     */
    public ProductosMapper(ProveedorDAO proveedorDAO) {
        this.proveedorDAO = Objects.requireNonNull(proveedorDAO, "ProveedorDAO no puede ser nulo");
    }

    /**
     * Convierte una entidad Productos a su equivalente ProductosDTO.
     * Realiza una consulta para obtener información del proveedor si es necesario.
     *
     * @param ent Entidad Productos.
     * @return DTO equivalente para transporte o persistencia.
     */
    @Override
    public ProductosDTO toDTO(Productos ent) {
        try {
            // Se intenta obtener el proveedor (aunque no se usa directamente aquí)
            ProveedorDTO proveedor = proveedorDAO.read(ent.getProveedor());
            // Se podría usar proveedor.getNombre() si fuera necesario mostrarlo
            return new ProductosDTO(
                    ent.getCodigo(),
                    ent.getNombre(),
                    ent.getCategoria(),
                    ent.getPrecio(),
                    ent.getCantDisponible(),
                    ent.getProveedor()
            );
        } catch (SQLException e) {
            System.err.println("Error al obtener el proveedor: " + e.getMessage());
            // Retorna el DTO sin alterar los campos por error
            return new ProductosDTO(
                    ent.getCodigo(),
                    ent.getNombre(),
                    ent.getCategoria(),
                    ent.getPrecio(),
                    ent.getCantDisponible(),
                    ent.getProveedor()
            );
        }
    }

    /**
     * Convierte un objeto DTO de producto a su entidad de dominio.
     *
     * @param dto DTO del producto.
     * @return Entidad Productos lista para usar en la lógica del negocio.
     */
    @Override
    public Productos toEnt(ProductosDTO dto) {
        if (dto == null) {
            return null;
        }

        Productos producto = new Productos();
        producto.setCodigo(dto.getCodigo());
        producto.setNombre(dto.getNombre());
        producto.setCategoria(dto.getCategoria());
        producto.setPrecio(dto.getPrecio());
        producto.setCantDisponible(dto.getCantDisponible());
        producto.setProveedor(dto.getProveedor());
        return producto;
    }
}
