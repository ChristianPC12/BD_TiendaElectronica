package Modelo.Clientes;

import Modelo.Mapper.Mapper;

/**
 * Clase ClientesMapper que implementa la interfaz genérica Mapper.
 * Esta clase se encarga de convertir objetos entre las capas de la aplicación:
 * de Cliente (entidad del modelo) a ClienteDTO (objeto de transferencia de datos) y viceversa.
 * 
 * Autor: Christian Paniagua Castro
 */
public class ClientesMapper implements Mapper<Cliente, ClienteDTO> {

    /**
     * Convierte un objeto Cliente (entidad del modelo) a ClienteDTO.
     * @param ent Objeto Cliente
     * @return ClienteDTO con los mismos datos
     */
    @Override
    public ClienteDTO toDTO(Cliente ent) {
        return new ClienteDTO(
                ent.getCedula(),
                ent.getNombreCompleto(),
                ent.getDireccion(),
                ent.getTelefono(),
                ent.getCorreoElectronico()
        );
    }

    /**
     * Convierte un objeto ClienteDTO a Cliente (entidad del modelo).
     * @param dto Objeto ClienteDTO
     * @return Cliente con los mismos datos
     */
    @Override
    public Cliente toEnt(ClienteDTO dto) {
        return new Cliente(
                dto.getCedula(),
                dto.getNombreCompleto(),
                dto.getDireccion(),
                dto.getTelefono(),
                dto.getCorreoElectronico()
        );
    }
}
