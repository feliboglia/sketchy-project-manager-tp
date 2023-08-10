package DAO;

import DB.DBManager;
import Entidades.Tarea;
import Excepciones.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TareaDAOManager implements TareaDAO{

    @Override
    public void crearTarea(Tarea tarea) throws DAOException {
        // Especifico la información de la tarea y la meto en la query
        Integer idProyecto = tarea.getIdProyecto();
        String nombre = tarea.getNombre();
        String descripcion = tarea.getDescripcion();
        Float estimacion = tarea.getEstimacion();

        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            String query = "INSERT INTO tareas (id_proyecto, nombre, estado, descripcion, horas_estimadas) VALUES ('" + idProyecto + "','"+ nombre +"','"+ "Creada" +"','" + descripcion + "','" + estimacion + "')";

            s.executeUpdate(query);
            c.commit();
        } catch (SQLException e) {
            if (e.getErrorCode() == 23505) {
                throw new DAOException("La tarea ya existe");
            } else {
                throw new DAOException("Error al crear la tarea", e);
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
    public void eliminarTarea(Integer idTarea) throws DAOException {
    // Especifico la información de la tarea y la meto en la query

        Connection c = DBManager.connect();

        try {
            Statement s = c.createStatement();
            String query = "DELETE FROM tareas where id_tarea = '" + idTarea + "'";

            s.executeUpdate(query);
            c.commit();

        } catch (SQLException e) {
            if(e.getErrorCode() == 23503){
                throw new DAOException("Tiene empleados asignados!", e);
            }
            else{
                throw new DAOException("Error al eliminar la tarea", e);
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
    public void modificarTarea(Tarea tarea) throws DAOException {
    // Especifico la información de la tarea y la meto en la query
        Integer idTarea = tarea.getIdTarea();
        String nuevoNombre = tarea.getNombre();
        String nuevaDescripcion = tarea.getDescripcion();

        Connection c = DBManager.connect();

        try {
            Statement s = c.createStatement();
            String query = "UPDATE tareas set nombre = '" + nuevoNombre + "', nuevaDescripcion = '" + nuevaDescripcion + "' WHERE id_tarea = '" + idTarea + "'";

            s.executeUpdate(query);
            c.commit();

        } catch (SQLException e) {
            throw new DAOException("Error al modificar la tarea", e);
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void cambiarEstado(Integer idTarea, String nuevoEstado) throws DAOException {
        // Especifico la información de la tarea y la meto en la query
        Connection c = DBManager.connect();

        try {
            Statement s = c.createStatement();
            String query = "UPDATE tareas set estado = '" + nuevoEstado + "' WHERE id_tarea = '" + idTarea + "'";

            s.executeUpdate(query);
            c.commit();

        } catch (SQLException e) {
            throw new DAOException("Error al modificar el estado", e);
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
    @Override
    public void finalizarTarea(Integer idTarea) throws DAOException {
        cambiarEstado(idTarea, "Finalizada");
    }

    public List<Tarea> listarTareas(Integer idProyecto) throws DAOException {
        // Creo una lista de tareas y especifico la query y conexión
        List<Tarea> todasLasTareas = new ArrayList<>();

        String sql = "SELECT * FROM tareas WHERE id_proyecto = '" + idProyecto + "'";

        Connection c = DBManager.connect();

        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            // Armo un objeto tarea con los valores que encontré en la base y voy populando mi lista
            while (rs.next()) {
                Integer r_idTarea = rs.getInt("id_tarea");
                Integer r_idProyecto = rs.getInt("id_proyecto");
                String r_nombre = rs.getString("nombre");
                String r_estado = rs.getString("estado");
                String r_descripcion = rs.getString("descripcion");
                Float r_horasEstimadas = rs.getFloat("horas_estimadas");

                Tarea t = new Tarea(r_idTarea, r_idProyecto, r_nombre, r_estado, r_descripcion, r_horasEstimadas);
                todasLasTareas.add(t);
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
        return todasLasTareas;
    }
}
