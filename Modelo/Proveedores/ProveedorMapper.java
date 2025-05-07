package Modelo.Proveedores;

import Modelo.Mapper.Mapper;

/**
 * Mapea entre objetos Proveedor y ProveedorDTO.
 * Implementa conversión de entidad a DTO y viceversa.
 * 
 * @author Christian
 */
public class ProveedorMapper implements Mapper<Proveedor, ProveedorDTO> {

    @Override
    public ProveedorDTO toDTO(Proveedor ent) {
        return new ProveedorDTO(
                ent.getId(),
                ent.getNombre(),
                ent.getContacto(),
                ent.getDireccion()
        );
    }

    @Override
    public Proveedor toEnt(ProveedorDTO dto) {
        return new Proveedor(
                dto.getId(),
                dto.getNombre(),
                dto.getContacto(),
                dto.getDireccion()
        );
    }
}
