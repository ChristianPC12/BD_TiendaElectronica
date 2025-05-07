package Modelo.Clientes; 

/**
 * Clase ClienteDAO para manejar operaciones de base de datos relacionadas con clientes.
 * Utiliza procedimientos almacenados para las operaciones CRUD.
 * Extiende de la clase DAO genérica.
 * 
 * @author Christian Paniagua
 */

import Modelo.DAO.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends DAO<ClienteDTO> {

    // Constructor que recibe una conexión activa a la base de datos
    public ClienteDAO(Connection connection) {
        super(connection);
    }

    /**
     * Crea un nuevo cliente si no existe previamente.
     * @param dto Objeto ClienteDTO con la información a guardar
     * @return true si la inserción fue exitosa
     */
    @Override
    public boolean create(ClienteDTO dto) throws SQLException {
        if (dto == null || !validatePK(dto.getCedula())) {
            return false;
        }
        String query = "Call ClientesCreate(?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, dto.getCedula());
            stmt.setString(2, dto.getNombreCompleto());
            stmt.setString(3, dto.getDireccion());
            stmt.setString(4, dto.getTelefono());
            stmt.setString(5, dto.getCorreoElectronico());
            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * Busca un cliente en la base de datos según su cédula.
     * @param id Cédula del cliente
     * @return ClienteDTO encontrado o null si no existe
     */
    @Override
    public ClienteDTO read(Object id) throws SQLException {
        if (id == null || String.valueOf(id).trim().isEmpty()) {
            return null;
        }
        String query = "Call ClientesRead(?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, String.valueOf(id));
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ClienteDTO(
                            rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5));
                }
            }
        }
        return null;
    }

    /**
     * Obtiene todos los clientes registrados.
     * @return Lista de objetos ClienteDTO
     */
    @Override
    public List<ClienteDTO> readAll() throws SQLException {
        String query = "Call ClientesReadAll()";
        List<ClienteDTO> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new ClienteDTO(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)));
            }
        }
        return list;
    }

    /**
     * Actualiza la dirección, teléfono y correo electrónico de un cliente.
     * @param dto Objeto ClienteDTO con la información actualizada
     * @return true si se actualizó correctamente
     */
    @Override
    public boolean update(ClienteDTO dto) throws SQLException {
        if (dto == null) {
            return false;
        }
        String query = "Call ClientesUpdate(?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, dto.getCedula());
            stmt.setString(2, dto.getDireccion());
            stmt.setString(3, dto.getTelefono());
            stmt.setString(4, dto.getCorreoElectronico());
            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * Elimina un cliente de la base de datos por su cédula.
     * @param id Cédula del cliente
     * @return true si la eliminación fue exitosa
     */
    @Override
    public boolean delete(Object id) throws SQLException {
        if (id == null || String.valueOf(id).trim().isEmpty()) {
            return false;
        }
        String query = "Call ClientesDelete(?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, String.valueOf(id));
            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * Verifica si la cédula ya está registrada.
     * @param id Cédula a verificar
     * @return true si la cédula no existe (disponible para uso)
     */
    public boolean validatePK(Object id) throws SQLException {
        return read(id) == null;
    }

    /**
     * Método alternativo para eliminar directamente un cliente por su cédula,
     * sin usar procedimiento almacenado.
     * @param cedula Cédula del cliente
     */
    public void deleteByCedula(String cedula) {
        String sql = "DELETE FROM clientes WHERE cedula = ?"; 
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cedula);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No se encontró un cliente con la cédula: " + cedula);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el cliente: " + e.getMessage(), e);
        }
    }
}
