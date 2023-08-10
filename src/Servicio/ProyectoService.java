package Servicio;

import DAO.ProyectoDAO;
import DAO.ProyectoDAOManager;
import Entidades.Proyecto;
import Excepciones.DAOException;
import Excepciones.ServiceException;

import java.util.List;

public class ProyectoService {

    // Declaro el DAO para proyecto
    ProyectoDAO dao = new ProyectoDAOManager();

    // Armo los métodos que despues voy a usar en mis ventanas, usando el DAO de proyecto
    public List<Proyecto> listarProyectos() throws ServiceException {
        try {
            return dao.listarProyectos();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
       }
    }

    public List<Proyecto> verProyecto(Integer idProyecto) throws ServiceException {
        try {
            return dao.verProyecto(idProyecto);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Float calcularHorasProyecto(String nombreProyecto) throws ServiceException {
        try {
            return dao.calcularHorasProyecto(nombreProyecto);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Float calcularCostoProyecto(Integer idProyecto) throws ServiceException {
        try {
            return dao.calcularCostoProyecto(idProyecto);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void crearProyecto(Proyecto proyecto) throws ServiceException {
        try {
            dao.crearProyecto(proyecto);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void eliminarProyecto(Integer idProyecto) throws ServiceException {
        try {
            dao.eliminarProyecto(idProyecto);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void modificarProyecto(Proyecto proyecto) throws ServiceException {
        try {
            dao.modificarProyecto(proyecto);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void cambiarEstado(Integer idProyecto, String nuevoEstado) throws ServiceException {
        try {
            dao.cambiarEstado(idProyecto, nuevoEstado);
        } catch (DAOException e) {
            throw new ServiceException("No se pudo cambiar el estado");
        }
    }

}
