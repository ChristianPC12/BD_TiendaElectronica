/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import BaseDatos.DataBase;
import Modelo.Clientes.Cliente;
import Modelo.Clientes.ClienteDAO;
import Modelo.Clientes.ClienteDTO;
import Modelo.Clientes.ClientesMapper;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import Modelo.Vista.Vista;

/**
 *
 * @author munoz
 */
public class ClienteControlador {
    private ClienteDAO dao;
    private final Vista vista;
    private final ClientesMapper mapper;

    public ClienteControlador(Vista vista, ClientesMapper mapper) {
        this.vista = vista;
        this.mapper = mapper;
        try {
            dao = new ClienteDAO(DataBase.getConnection());
        } catch (SQLException ex) {
            vista.showError("Error al conectar con la Base de Datos");
        }
    }

    public ClienteControlador(Vista vista) {
        this.vista = vista;
        mapper = new ClientesMapper();
        try {
            dao = new ClienteDAO(DataBase.getConnection());
        } catch (SQLException ex) {
            vista.showError("Error al conectar con la Base de Datos");
        }
    }

    public void create(Cliente cliente) {
        if (cliente == null || !validateRequired(cliente)) {
            vista.showError("Faltan datos requeridos");
            return;
        }
        try {
            if (!validatePK(cliente.getCedula())) {
                vista.showError("La cédula ingresada ya se encuentra registrada");
                return;
            }
            dao.create(mapper.toDTO(cliente));
            vista.showMessage("Datos guardados correctamente");
        } catch (SQLException ex) {
            vista.showError("Ocurrió un error al guardar los datos: " + ex.getMessage());
        }
    }

    public void read(String cedula) {
        try {
            ClienteDTO dto = dao.read(cedula); 
            Cliente cliente = mapper.toEnt(dto);
            if (cliente != null) {
                vista.show(cliente); 
            } else {
                vista.showError("Cliente no encontrado");
            }
        } catch (SQLException ex) {
            vista.showError("Error al cargar los datos: " + ex.getMessage());
        }
    }

    public void readAll() {
        try {
            List<ClienteDTO> dtoList = dao.readAll();
            List<Cliente> clienteList = dtoList.stream()
                    .map(mapper::toEnt)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            vista.showAll(clienteList); 
        } catch (SQLException ex) {
            vista.showError("Error al cargar los datos: " + ex.getMessage());
        }
    }

    public void update(Cliente cliente) {
        if (cliente == null || !validateRequired(cliente)) {
            vista.showError("Faltan datos requeridos");
            return;
        }
        try {
           
            if (validatePK(cliente.getCedula())) {
                vista.showError("La cédula ingresada no se encuentra registrada");
                return;
            }
            dao.update(mapper.toDTO(cliente));
            vista.showMessage("Datos actualizados correctamente");
        } catch (SQLException ex) {
            vista.showError("Ocurrió un error al actualizar los datos: " + ex.getMessage());
        }
    }

    public void delete(Cliente cliente) {
        if (cliente == null || !validateRequired(cliente)) {
            vista.showError("No hay ningún cliente cargado actualmente");
            return;
        }
        try {
            
            if (validatePK(cliente.getCedula())) {
                vista.showError("La cédula ingresada no está registrada");
                return;
            }
            dao.delete(cliente.getCedula());
            vista.showMessage("Cliente eliminado correctamente");
        } catch (SQLException ex) {
            vista.showError("Ocurrió un error al eliminar los datos: " + ex.getMessage());
        }
    }


    public boolean validateRequired(Cliente cliente) {
        return cliente.getNombreCompleto()!= null && !cliente.getNombreCompleto().trim().isEmpty()
                && cliente.getCedula() != null && !cliente.getCedula().trim().isEmpty()
                && cliente.getDireccion() != null && !cliente.getDireccion().trim().isEmpty()
                && cliente.getTelefono() != null && !cliente.getTelefono().trim().isEmpty();
    }

   
    public boolean validatePK(String cedula) {
        try {
           
            ClienteDTO clienteDTO = dao.read(cedula);
            return clienteDTO == null;  
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
    }
    
     public void deleteByCedula(String cedula) {
        
        try {
           
            dao.deleteByCedula(cedula);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el cliente: " + e.getMessage());
        }
    }
}
