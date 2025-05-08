package Modelo.Proveedores;

import Modelo.DAO.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO para gestionar operaciones CRUD sobre la entidad Proveedor. Utiliza
 * procedimientos almacenados y acceso JDBC para conectar con la base de datos.
 *
 * Autor: Christian Paniagua Castro
 */
public class ProveedorDAO extends DAO<ProveedorDTO> {

    public ProveedorDAO(Connection connection) {
        super(connection);
    }

    /**
     * Crea un nuevo proveedor si no existe previamente en la base de datos.
     *
     * @param dto Objeto ProveedorDTO con los datos del proveedor.
     * @return true si el proveedor fue creado correctamente.
     * @throws SQLException si ocurre un error en la base de datos.
     */
    @Override
    public boolean create(ProveedorDTO dto) throws SQLException {
        if (dto == null || !validatePk(dto.getId())) {
            return false;
        }
        String query = "CALL ProveedoresCreate(?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, dto.getNombre());
            stmt.setString(2, dto.getContacto());
            stmt.setString(3, dto.getDireccion());
            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * Lee un proveedor desde la base de datos usando su ID.
     *
     * @param id ID del proveedor a buscar.
     * @return ProveedorDTO con los datos encontrados o null si no existe.
     * @throws SQLException si ocurre un error en la consulta.
     */
    @Override
    public ProveedorDTO read(Object id) throws SQLException {
        if (id == null || String.valueOf(id).trim().isEmpty()) {
            return null;
        }
        String query = "CALL ProveedoresRead(?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, String.valueOf(id));
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ProveedorDTO(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4));
                }
            }
        }
        return null;
    }

    /**
     * Lee todos los proveedores de la base de datos.
     *
     * @return Lista de ProveedorDTO.
     * @throws SQLException si ocurre un error en la lectura.
     */
    @Override
    public List<ProveedorDTO> readAll() throws SQLException {
        String query = "CALL ProveedoresReadAll";
        List<ProveedorDTO> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new ProveedorDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)));
            }
        }
        return list;
    }

    /**
     * Actualiza únicamente el contacto de un proveedor existente.
     *
     * @param dto Objeto ProveedorDTO con los nuevos datos.
     * @return true si el proveedor fue actualizado correctamente.
     * @throws SQLException si ocurre un error durante la actualización.
     */
    @Override
    public boolean update(ProveedorDTO dto) throws SQLException {
        if (dto == null) {
            return false;
        }
        String query = "CALL ProveedoresUpdate(?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, dto.getId());
            stmt.setString(2, dto.getNombre());
            stmt.setString(3, dto.getContacto());
            stmt.setString(4, dto.getDireccion());
            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * Elimina un proveedor por ID.
     *
     * @param id Identificador del proveedor.
     * @return true si la eliminación fue exitosa.
     * @throws SQLException si ocurre un error en la operación.
     */
    @Override
    public boolean delete(Object id) throws SQLException {
        if (id == null || !(id instanceof Integer)) {
            return false;
        }
        String query = "CALL ProveedoresDelete(?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, (Integer) id);
            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * Valida si un ID ya existe en la base de datos.
     *
     * @param id Identificador a validar.
     * @return true si no existe, false si ya está registrado.
     * @throws SQLException si ocurre un error al consultar.
     */
    public boolean validatePk(Object id) throws SQLException {
        return read(id) == null;
    }

    /**
     * Busca proveedores por nombre, contacto o dirección.
     *
     * @param filter Texto a buscar en los campos.
     * @return Lista de proveedores que coinciden con el filtro.
     * @throws SQLException si hay un problema con la base de datos.
     */
    public List<Proveedor> search(String filter) throws SQLException {
        List<Proveedor> lista = new ArrayList<>();
        String query = "SELECT * FROM proveedores WHERE nombre LIKE ? OR contacto LIKE ? OR direccion LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, "%" + filter + "%");
            stmt.setString(2, "%" + filter + "%");
            stmt.setString(3, "%" + filter + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Proveedor proveedor = new Proveedor(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("contacto"),
                        rs.getString("direccion")
                );
                lista.add(proveedor);
            }
        }
        return lista;
    }
}
