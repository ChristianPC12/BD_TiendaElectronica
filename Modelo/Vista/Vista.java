package Modelo.Vista;

import java.util.List;

/**
 * Interfaz genérica para representar vistas en la capa de presentación.
 * Define los métodos comunes para mostrar datos, mensajes, errores y validaciones.
 * 
 * @param <Ent> Tipo de entidad que será manejado por la vista.
 * 
 * @author Christian Paniagua Castro
 */
public interface Vista<Ent> {

    /**
     * Muestra un solo registro en la vista.
     * 
     * @param ent Entidad a mostrar.
     */
    public void show(Ent ent);

    /**
     * Muestra una lista de registros en la vista.
     * 
     * @param ents Lista de entidades a mostrar.
     */
    public void showAll(List<Ent> ents);

    /**
     * Muestra un mensaje informativo al usuario.
     * 
     * @param msg Mensaje a mostrar.
     */
    public void showMessage(String msg);

    /**
     * Muestra un mensaje de error al usuario.
     * 
     * @param err Mensaje de error.
     */
    public void showError(String err);

    /**
     * Muestra una advertencia al usuario.
     * 
     * @param warning Mensaje de advertencia.
     */
    public void showWarnig(String warning);

    /**
     * Valida si los campos requeridos están completos en la vista.
     * 
     * @return true si los campos requeridos son válidos, false en caso contrario.
     */
    public boolean validateRequired();
}
