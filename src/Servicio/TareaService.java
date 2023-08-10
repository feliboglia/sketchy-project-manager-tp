package Servicio;

import DAO.TareaDAO;
import DAO.TareaDAOManager;
import Entidades.Tarea;
import Excepciones.DAOException;
import Excepciones.ServiceException;

import java.util.List;

public class TareaService {

    // Declaro el DAO para tarea
    TareaDAO dao = new TareaDAOManager();

    // Armo los métodos que despues voy a usar en mis ventanas, usando el DAO de tarea
    public List<Tarea> listarTareas(Integer idProyecto) throws ServiceException {
        try {
            return dao.listarTareas(idProyecto);
        } catch (DAOException e) {
            throw new ServiceException("No se pudieron listar las tareas");
       }
    }

    public void crearTarea(Tarea tarea) throws ServiceException {
        try {
            dao.crearTarea(tarea);
        } catch (DAOException e) {
            throw new ServiceException(e.toString());
            //throw new ServiceException("No se pudo crear la tarea");
        }
    }

    public void eliminarTarea(Integer idTarea) throws ServiceException {
        try {
            dao.eliminarTarea(idTarea);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void modificarTarea(Tarea tarea) throws ServiceException {
        try {
            dao.modificarTarea(tarea);
        } catch (DAOException e) {
            throw new ServiceException("No se pudo modificar la tarea");
        }
    }

    public void cambiarEstado(Integer idTarea, String estado) throws ServiceException {
        try {
            dao.cambiarEstado(idTarea, estado);
        } catch (DAOException e) {
            throw new ServiceException("No se pudo cambiar el estado");
        }
    }

    public void finalizarTarea(Integer idTarea) throws ServiceException {
        try {
            dao.finalizarTarea(idTarea);
        } catch (DAOException e) {
            throw new ServiceException("No se pudo cambiar el estado");
        }
    }



}
