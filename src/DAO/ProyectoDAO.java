package DAO;

import Entidades.Proyecto;
import Excepciones.DAOException;

import java.util.List;

public interface ProyectoDAO {

    void crearProyecto(Proyecto proyecto) throws DAOException;

    void modificarProyecto(Proyecto proyecto) throws DAOException;

    void eliminarProyecto(Integer idProyecto) throws DAOException;

    List<Proyecto> verProyecto(Integer idProyecto) throws DAOException;

    List<Proyecto> listarProyectos() throws DAOException;

    Float calcularHorasProyecto(String nombreProyecto) throws DAOException;

    Float calcularCostoProyecto(Integer idProyecto) throws DAOException;

    void cambiarEstado(Integer idProyecto, String nuevoEstado) throws DAOException;

}
