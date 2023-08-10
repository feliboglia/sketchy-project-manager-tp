package Servicio;

import DAO.TareaEmpleadoDAO;
import DAO.TareaEmpleadoDAOManager;
import Entidades.Tarea;
import Entidades.TareaEmpleado;
import Excepciones.DAOException;
import Excepciones.ServiceException;

import java.util.List;

public class TareaEmpleadoService {

    // Declaro el DAO para proyecto
    TareaEmpleadoDAO dao = new TareaEmpleadoDAOManager();

    // Armo los métodos que despues voy a usar en mis ventanas, usando el DAO de tareaEmpleado
    public List<Tarea> listarTareasEmpleado(String emailEmpleado) throws ServiceException {
        try {
            return dao.listarTareasEmpleado(emailEmpleado);
        } catch (DAOException e) {
            throw new ServiceException("No se pudieron listar los empleados");
       }
    }

    public void asignarTareaEmpleado(TareaEmpleado tareaEmpleado) throws ServiceException {
        try {
            dao.asignarTareaEmpleado(tareaEmpleado);
        } catch (DAOException e) {
            throw new ServiceException("No se pudo asignar la tarea: ", e);
        }
    }

    public void modificarTareaEmpleado(TareaEmpleado tareaEmpleado) throws ServiceException {
        try {
            dao.modificarTareaEmpleado(tareaEmpleado);
        } catch (DAOException e) {
            throw new ServiceException("No se pudo modificar la asignacion");
        }
    }

    public void desasignarTareaEmpleado(TareaEmpleado tareaEmpleado) throws ServiceException {
        try {
            dao.desasignarTareaEmpleado(tareaEmpleado);
        } catch (DAOException e) {
            throw new ServiceException("No se pudieron desasignar");
        }
    }

    public void actualizarHoras(Integer idTarea, Float horasReales, String emailEmpleado) throws ServiceException{
        try {
            dao.actualizarHoras(idTarea, horasReales, emailEmpleado);
        } catch (DAOException e) {
            throw new ServiceException("No se pudieron modificar las horas");
        }
    }

}
