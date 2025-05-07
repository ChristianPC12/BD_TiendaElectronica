
package Modelo.DAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Christian Paniagua Castro
 */
public abstract class DAO<Dto> {
    protected Connection connection;

    public DAO(Connection connection) {
        this.connection = connection;
    }
    
    public abstract boolean create (Dto dto)throws SQLException;
    public abstract Dto read (Object id)throws SQLException;
    public abstract List<Dto> readAll ()throws SQLException;
    public abstract boolean update (Dto dto)throws SQLException;
    public abstract boolean delete (Object id)throws SQLException;
    
}
