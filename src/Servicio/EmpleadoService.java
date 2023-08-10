package Servicio;

import DAO.EmpleadoDAO;
import DAO.EmpleadoDAOManager;
import Entidades.Empleado;
import Excepciones.DAOException;
import Excepciones.ServiceException;

import java.util.List;

public class EmpleadoService {

    // Declaro el DAO para empleado
    EmpleadoDAO dao = new EmpleadoDAOManager();

    // Armo los métodos que despues voy a usar en mis ventanas, usando el DAO de empleado
    public List<Empleado> listarEmpleados() throws ServiceException {
        try {
            return dao.listarEmpleados();
        } catch (DAOException e) {
            throw new ServiceException("No se pudieron listar los empleados");
       }
    }

    public List<Empleado> buscarEmpleado(String email) throws ServiceException {
        try {
            return dao.mostrarEmpleado(email);
        } catch (DAOException e) {
            throw new ServiceException("No se pudo encontrar el empleado");
        }
    }

    public void altaEmpleado(Empleado empleado) throws ServiceException {
        try {
            dao.insertarEmpleado(empleado);
        } catch (DAOException e) {
            throw new ServiceException("No se pudo dar de alta el empleado");
        }
    }

    public void bajaEmpleado(Empleado empleado) throws ServiceException {
        try {
            dao.borrarEmpleado(empleado.getEmail());
        } catch (DAOException e) {
            throw new ServiceException("No se pudo borrar el empleado");
        }
    }

    public void modificacionEmpleado(Empleado empleado) throws ServiceException {
        try {
            dao.actualizarEmpleado(empleado);
        } catch (DAOException e) {
            throw new ServiceException("No se pudo modificar el empleado");
        }
    }
}
