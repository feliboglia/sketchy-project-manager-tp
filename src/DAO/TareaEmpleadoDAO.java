package DAO;

import Entidades.Tarea;
import Entidades.TareaEmpleado;
import Excepciones.DAOException;

import java.util.List;

public interface TareaEmpleadoDAO {
    void asignarTareaEmpleado(TareaEmpleado tareaEmpleado) throws DAOException; // Asignar empleado, recibe idTarea y input email empleado
    void modificarTareaEmpleado(TareaEmpleado tareaEmpleado) throws DAOException; // Cambia que empleado esta asignado a la tarea
    void desasignarTareaEmpleado(TareaEmpleado tareaEmpleado) throws DAOException; // Desasigna el empleado introducido de la tarea

    List<Tarea> listarTareasEmpleado(String emailEmpleado) throws DAOException;

    void actualizarHoras(Integer idTarea, Float horasReales, String emailEmpleado) throws DAOException;
}
