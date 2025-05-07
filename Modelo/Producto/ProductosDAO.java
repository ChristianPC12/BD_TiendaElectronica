package Modelo.Producto;

import Modelo.DAO.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO para gestionar la persistencia de productos en la base de datos.
 * Implementa operaciones CRUD utilizando procedimientos almacenados.
 * 
 * Autor: Christian Paniagua Castro
 */
public class ProductosDAO extends DAO<ProductosDTO> {

    /**
     * Constructor que recibe una conexión activa a la base de datos.
     *
     * @param connection Conexión JDBC activa.
     */
    public ProductosDAO(Connection connection) {
        super(connection);
    }

    /**
     * Crea un nuevo producto en la base de datos.
     *
     * @param dto Objeto DTO del producto.
     * @return true si se creó correctamente, false si ya existe o hubo error.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    @Override
    public boolean create(ProductosDTO dto) throws SQLException {
        if (dto == null || !validatePk(dto.getCodigo())) {
            return false;
        }

        String query = "Call ProductosCreate(?,?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, dto.getCodigo());
            stmt.setString(2, dto.getNombre());
            stmt.setString(3, dto.getCategoria());
            stmt.setDouble(4, dto.getPrecio());
            stmt.setInt(5, dto.getCantDisponible());
            stmt.setInt(6, dto.getProveedor());
            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * Consulta un producto por su código.
     *
     * @param id Código del producto.
     * @return ProductoDTO si se encuentra, null si no.
     * @throws SQLException Si ocurre un error al consultar.
     */
    @Override
    public ProductosDTO read(Object id) throws SQLException {
        if (id == null || String.valueOf(id).trim().isEmpty()) {
            return null;
        }

        String query = "Call ProductosRead(?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, Integer.parseInt(id.toString()));
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ProductosDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getInt(5),
                        rs.getInt(6)
                    );
                }
            }
            return null;
        }
    }

    /**
     * Lista todos los productos registrados.
     *
     * @return Lista de productos.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    @Override
    public List<ProductosDTO> readAll() throws SQLException {
        String query = "Call ProductosReadAll()";
        List<ProductosDTO> list = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new ProductosDTO(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDouble(4),
                    rs.getInt(5),
                    rs.getInt(6)
                ));
            }
        }

        return list;
    }

    /**
     * Actualiza los datos de un producto existente.
     *
     * @param dto Producto actualizado.
     * @return true si se modificó correctamente.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    @Override
    public boolean update(ProductosDTO dto) throws SQLException {
        if (dto == null) {
            return false;
        }

        String query = "Call ProductosUpdate(?,?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, dto.getCodigo());
            stmt.setString(2, dto.getNombre());
            stmt.setString(3, dto.getCategoria());
            stmt.setDouble(4, dto.getPrecio());
            stmt.setInt(5, dto.getCantDisponible());
            stmt.setInt(6, dto.getProveedor());
            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * Elimina un producto por su código.
     *
     * @param id Código del producto.
     * @return true si se eliminó exitosamente.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    @Override
    public boolean delete(Object id) throws SQLException {
        if (id == null || String.valueOf(id).trim().isEmpty()) {
            return false;
        }

        String query = "Call ProductosDelete(?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, Integer.parseInt(id.toString()));
            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * Valida si el producto no existe aún, según su código.
     *
     * @param id Código del producto.
     * @return true si no existe (clave válida), false si ya existe.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public boolean validatePk(Object id) throws SQLException {
        return read(id) == null;
    }
}
