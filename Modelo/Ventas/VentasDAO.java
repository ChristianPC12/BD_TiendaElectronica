package Modelo.Ventas;

import Modelo.DAO.DAO;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Clase DAO encargada de la persistencia de los datos de ventas.
 * Implementa operaciones para crear, leer por ID y leer todas las ventas desde la base de datos.
 * 
 * Autor: Christian Paniagua Castro
 */
public class VentasDAO extends DAO<VentasDTO> {

    public VentasDAO(Connection connection) {
        super(connection);
    }

    /**
     * Verifica si una venta con el ID dado ya existe en la base de datos.
     * @param id ID a validar.
     * @return true si no existe (válido), false si ya existe.
     */
    public boolean validatePk(Object id) throws SQLException {
        return read(id) == null;
    }

    /**
     * Crea un nuevo registro de venta en la base de datos.
     * @param dto Objeto DTO de la venta a insertar.
     * @return true si se insertó correctamente.
     */
    @Override
    public boolean create(VentasDTO dto) throws SQLException {
        if (dto == null || !validatePk(dto.getCodigoProducto())) {
            return false;
        }

        String query = "Call VentasCreate(?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, dto.getIdPlanilla());
            stmt.setDate(2, java.sql.Date.valueOf(dto.getFechaRecibo()));
            stmt.setInt(3, dto.getCedulaEmpleado());
            stmt.setString(4, dto.getNomEmpleado());
            stmt.setInt(5, dto.getCodigoProducto());
            stmt.setString(6, dto.getNombreProducto());
            stmt.setInt(7, dto.getPrecioProducto());
            stmt.setInt(8, dto.getCantVendidos());
            stmt.setInt(9, dto.getSubTotal());
            stmt.setInt(10, dto.getImpuestos());
            stmt.setInt(11, dto.getTotal());

            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * Lee un registro de venta por su ID.
     * @param id ID de la venta.
     * @return Objeto VentasDTO si se encuentra, null si no existe.
     */
    @Override
    public VentasDTO read(Object id) throws SQLException {
        if (id == null || String.valueOf(id).trim().isEmpty()) {
            return null;
        }

        String query = "Call VentasRead(?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, Integer.parseInt(id.toString()));
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new VentasDTO(
                        rs.getInt(1), rs.getDate(2).toLocalDate(),
                        rs.getInt(3), rs.getString(4),
                        rs.getInt(5), rs.getString(6),
                        rs.getInt(7), rs.getInt(8),
                        rs.getInt(9), rs.getInt(10),
                        rs.getInt(11)
                    );
                }
            }
        }
        return null;
    }

    /**
     * Retorna una lista con todos los registros de ventas.
     * @return Lista de objetos VentasDTO.
     */
    @Override
    public List<VentasDTO> readAll() throws SQLException {
        String query = "Call VentasReadAll()";
        List<VentasDTO> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new VentasDTO(
                    rs.getInt(1), rs.getDate(2).toLocalDate(),
                    rs.getInt(3), rs.getString(4),
                    rs.getInt(5), rs.getString(6),
                    rs.getInt(7), rs.getInt(8),
                    rs.getInt(9), rs.getInt(10),
                    rs.getInt(11)
                ));
            }
        }
        return list;
    }

    @Override
    public boolean update(VentasDTO dto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(Object id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
