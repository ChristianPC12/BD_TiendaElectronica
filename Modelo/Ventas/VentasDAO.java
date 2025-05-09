package Modelo.Ventas;

import Modelo.DAO.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class VentasDAO extends DAO<VentasDTO> {

    public VentasDAO(Connection connection) {
        super(connection);
    }

    public boolean validatePk(Object id) throws SQLException {
        return read(id) == null;
    }

    @Override
    public boolean create(VentasDTO dto) throws SQLException {
        if (dto == null) {
            return false;
        }

        String query = "CALL VentasCreate(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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

    @Override
    public VentasDTO read(Object id) throws SQLException {
        if (id == null || String.valueOf(id).trim().isEmpty()) {
            return null;
        }

        String query = "CALL VentasRead(?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, Integer.parseInt(id.toString()));
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new VentasDTO(
                            rs.getInt(1), rs.getDate(2).toLocalDate(),
                            rs.getInt(3), rs.getString(4),
                            rs.getInt(5), rs.getString(6),
                            rs.getInt(7), rs.getInt(8),
                            rs.getInt(9), rs.getInt(10), rs.getInt(11)
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<VentasDTO> readAll() throws SQLException {
        List<VentasDTO> list = new ArrayList<>();
        String query = "CALL VentasReadAll()";
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new VentasDTO(
                        rs.getInt(1), rs.getDate(2).toLocalDate(),
                        rs.getInt(3), rs.getString(4),
                        rs.getInt(5), rs.getString(6),
                        rs.getInt(7), rs.getInt(8),
                        rs.getInt(9), rs.getInt(10), rs.getInt(11)
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
        if (id == null) {
            return false;
        }

        String query = "DELETE FROM ventas WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, Integer.parseInt(id.toString()));
            return stmt.executeUpdate() > 0;
        }
    }

}
