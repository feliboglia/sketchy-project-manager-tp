package DAO;

import Entidades.Empleado;
import Excepciones.DAOException;

import java.util.List;

public interface EmpleadoDAO {
    void insertarEmpleado(Empleado empleado) throws DAOException;

    void borrarEmpleado(String email) throws DAOException;

    void actualizarEmpleado(Empleado empleado) throws DAOException;

    List<Empleado> mostrarEmpleado(String email) throws DAOException;

    List<Empleado> listarEmpleados() throws DAOException;

}
