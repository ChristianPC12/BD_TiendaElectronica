package Modelo.Mapper;

/**
 * Interfaz genérica para mapear entre entidades del modelo y sus correspondientes DTOs.
 * Permite transformar datos entre la capa de lógica del negocio (Entidad)
 * y la capa de transferencia de datos (DTO).
 *
 * @param <Ent> Tipo de la entidad (modelo de dominio).
 * @param <DTO> Tipo del Data Transfer Object (DTO).
 * 
 * Autor: Christian Paniagua Castro
 */
public interface Mapper<Ent, DTO> {

    /**
     * Convierte una entidad a su representación en DTO.
     *
     * @param ent Objeto de tipo entidad.
     * @return Objeto DTO correspondiente.
     */
    public DTO toDTO(Ent ent);

    /**
     * Convierte un DTO a su representación como entidad.
     *
     * @param dto Objeto de tipo DTO.
     * @return Objeto entidad correspondiente.
     */
    public Ent toEnt(DTO dto);
}
