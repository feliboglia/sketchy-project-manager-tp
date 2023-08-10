package DAO;

import Entidades.Tarea;
import Excepciones.DAOException;

import java.util.List;

public interface TareaDAO {

    void crearTarea(Tarea tarea) throws DAOException;

    void eliminarTarea(Integer idTarea) throws DAOException;

    void modificarTarea(Tarea tarea) throws DAOException;

    void cambiarEstado(Integer idTarea, String estado) throws DAOException;

    void finalizarTarea(Integer idTarea) throws DAOException;

    List<Tarea> listarTareas(Integer idProyecto) throws DAOException;
}
