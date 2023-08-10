package DAO;

import DB.DBManager;
import Entidades.Tarea;
import Entidades.TareaEmpleado;
import Excepciones.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TareaEmpleadoDAOManager implements TareaEmpleadoDAO{
    @Override
    public void asignarTareaEmpleado(TareaEmpleado tareaEmpleado) throws DAOException {
        // Especifico la información y la meto en la query
        Integer idTarea = tareaEmpleado.getIdTarea();
        String empleadoAsignado = tareaEmpleado.getEmpleadoAsignado();
        Float horasReales = tareaEmpleado.getHorasReales();

        Connection c = DBManager.connect();

        try {
            Statement s = c.createStatement();
            String query = "INSERT INTO tareaempleado (id_tarea, email_empleado, horas_reales) VALUES ('" + idTarea + "','" + empleadoAsignado + "','" + horasReales + "')";

            s.executeUpdate(query);
            c.commit();
        } catch (SQLException e) {
            if (e.getErrorCode() == 23506) {
                throw new DAOException("Ese empleado no existe en la base!");
            } else {
                throw new DAOException("Error al asignar la tarea", e);
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void modificarTareaEmpleado(TareaEmpleado tareaEmpleado) throws DAOException {
        // Especifico la información
        Integer idTareaEmpleado = tareaEmpleado.getIdTarea();
        Integer idTarea = tareaEmpleado.getIdTarea();
        String nuevoEmpleadoAsignado = tareaEmpleado.getEmpleadoAsignado();

        Connection c = DBManager.connect();

        try {
            Statement s = c.createStatement();
            String query = "UPDATE tareaempleado set empleado_asignado = '" + nuevoEmpleadoAsignado + "' WHERE id_tarea_empleado = '" + idTareaEmpleado + "' ";

            s.executeUpdate(query);
            c.commit();

        } catch (SQLException e) {
            throw new DAOException("Error al modificar", e);
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void desasignarTareaEmpleado(TareaEmpleado tareaEmpleado) throws DAOException {
        // Especifico la información

        Integer idTarea = tareaEmpleado.getIdTarea();
        Connection c = DBManager.connect();

        try {
            Statement s = c.createStatement();
            String query = "DELETE FROM tareaempleado WHERE id_tarea = '" + idTarea + "' ";

            s.executeUpdate(query);
            c.commit();

        } catch (SQLException e) {
            throw new DAOException("Error al eliminar", e);

        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
    public void actualizarHoras(Integer idTarea, Float horasReales, String emailEmpleado) throws DAOException{
        Connection c = DBManager.connect();

        try {
            Statement s = c.createStatement();
            String query = "UPDATE tareaempleado SET horas_reales = '"+horasReales+"' WHERE id_tarea = '" + idTarea + "' and email_empleado = '"+ emailEmpleado +"' ";

            s.executeUpdate(query);
            c.commit();

        } catch (SQLException e) {
            throw new DAOException("Error al eliminar", e);
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public List<Tarea> listarTareasEmpleado(String emailEmpleado) throws DAOException { //    Lista las tareas asignadas de un empleado en particular
        // Creo una lista de tareas y especifico la query y conexión
        List<Tarea> todasLasTareasEmpleado = new ArrayList<>();

        Connection c = DBManager.connect();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT tareas.* " +
                    "FROM tareas " +
                    "JOIN tareaempleado ON tareas.id_tarea = tareaempleado.id_tarea " +
                    "WHERE tareaempleado.email_empleado = ?";

            pstmt = c.prepareStatement(query);
            pstmt.setString(1, emailEmpleado);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Integer r_idTarea = rs.getInt("id_tarea");
                Integer r_idProyecto = rs.getInt("id_proyecto");
                String r_nombre = rs.getString("nombre");
                String r_estado = rs.getString("estado");
                String r_descripcion = rs.getString("descripcion");
                Float r_horasEstimadas = rs.getFloat("horas_estimadas");

                Tarea t = new Tarea(r_idTarea, r_idProyecto, r_nombre, r_estado, r_descripcion, r_horasEstimadas);
                todasLasTareasEmpleado.add(t);
            }
        } catch (SQLException e) {
            throw new DAOException("Error al fetchear las tareas", e);
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return todasLasTareasEmpleado;
    }

}
