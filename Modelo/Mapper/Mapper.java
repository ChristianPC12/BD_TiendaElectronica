package Modelo.Mapper;

/**
 *
 * @author Christian Paniagua Castro
 */
public interface Mapper <Ent,DTO>{
    public DTO toDTO(Ent ent);
    public Ent toEnt(DTO dto);
}
