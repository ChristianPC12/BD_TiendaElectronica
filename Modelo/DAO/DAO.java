
package Modelo.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Clase abstracta DAO (Data Access Object) genérica.
 * Define las operaciones básicas que deben implementar todas las clases DAO concretas
 * para interactuar con una base de datos (CRUD).
 *
 * @param <Dto> Tipo genérico que representa el DTO de la entidad que se va a manejar.
 * 
 * Autor: Christian Paniagua Castro
 */
public abstract class DAO<Dto> {
    
    // Conexión a la base de datos, compartida por todas las subclases DAO
    protected Connection connection;

    /**
     * Constructor que recibe la conexión a la base de datos.
     * @param connection Objeto Connection para realizar consultas SQL.
     */
    public DAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Método abstracto para insertar un nuevo registro en la base de datos.
     * @param dto Objeto DTO a insertar.
     * @return true si se insertó correctamente, false en caso contrario.
     * @throws SQLException en caso de error con la base de datos.
     */
    public abstract boolean create(Dto dto) throws SQLException;

    /**
     * Método abstracto para obtener un registro por su ID o clave primaria.
     * @param id Clave o identificador del registro a buscar.
     * @return Objeto DTO si se encuentra, null si no.
     * @throws SQLException en caso de error con la base de datos.
     */
    public abstract Dto read(Object id) throws SQLException;

    /**
     * Método abstracto para obtener todos los registros de la tabla.
     * @return Lista de objetos DTO con todos los registros.
     * @throws SQLException en caso de error con la base de datos.
     */
    public abstract List<Dto> readAll() throws SQLException;

    /**
     * Método abstracto para actualizar un registro existente.
     * @param dto Objeto DTO con los nuevos datos.
     * @return true si la actualización fue exitosa, false si no.
     * @throws SQLException en caso de error con la base de datos.
     */
    public abstract boolean update(Dto dto) throws SQLException;

    /**
     * Método abstracto para eliminar un registro por su ID.
     * @param id Clave o identificador del registro a eliminar.
     * @return true si se eliminó correctamente, false si no.
     * @throws SQLException en caso de error con la base de datos.
     */
    public abstract boolean delete(Object id) throws SQLException;
}
