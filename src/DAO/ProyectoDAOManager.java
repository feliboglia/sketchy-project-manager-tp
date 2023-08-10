package DAO;

import DB.DBManager;
import Entidades.Empleado;
import Entidades.Proyecto;
import Excepciones.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProyectoDAOManager implements ProyectoDAO {

    public void crearProyecto(Proyecto proyecto) throws DAOException {
        // Especifico la información del proyecto y la meto en la query
        String nombre = proyecto.getNombre();
        String descripcion = proyecto.getDescripcion();
        String owner = proyecto.getOwner();
        //String estado = proyecto.getEstado();

        Connection c = DBManager.connect();

        try {
            Statement s = c.createStatement();
            String query = "INSERT INTO proyectos (nombre, descripcion, email_owner, estado) VALUES ('" + nombre + "','" + descripcion + "','" + owner + "','" + "Creado" + "')";

            s.executeUpdate(query);
            c.commit();
        } catch (SQLException e) {
            if (e.getErrorCode() == 23505) {
                throw new DAOException("El proyecto ya existe");
            }
            else if (e.getErrorCode() == 23506) {
                throw new DAOException("El owner que específico no existe, por favor inserte un empleado ya creado");
            }
            else {
                throw new DAOException("Error inesperado al crear el proyecto", e);
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void modificarProyecto(Proyecto proyecto) throws DAOException {
        // Especifico la información del proyecto y la meto en la query
        Integer idProyecto = proyecto.getIdProyecto();
        String nuevoNombre = proyecto.getNombre();
        String nuevoOwner = proyecto.getOwner();
        String nuevaDescripcion = proyecto.getDescripcion();

        Connection c = DBManager.connect();

        try {
            Statement s = c.createStatement();
            String query = "UPDATE proyectos set nombre = '" + nuevoNombre + "', descripcion = '" + nuevaDescripcion + "', email_owner = '" + nuevoOwner + "' WHERE id_proyecto = '" + idProyecto + "'";

            s.executeUpdate(query);
            c.commit();

        } catch (SQLException e) {
            throw new DAOException("Error al modificar el proyecto", e);
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void eliminarProyecto(Integer idProyecto) throws DAOException {
        // Especifico la información del proyecto y la meto en la query

        Connection c = DBManager.connect();

        try {
            Statement s = c.createStatement();
            String query = "DELETE FROM proyectos where id_proyecto = '" + idProyecto + "'";

            s.executeUpdate(query);
            c.commit();

        } catch (SQLException e) {
            throw new DAOException("Error al eliminar el proyecto", e);
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
    public void cambiarEstado(Integer idProyecto, String nuevoEstado) throws DAOException {
        // Especifico la información de la tarea y la meto en la query
        Connection c = DBManager.connect();

        try {
            Statement s = c.createStatement();
            String query = "UPDATE proyectos set estado = '" + nuevoEstado + "' WHERE id_proyecto = '" + idProyecto + "'";

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

    public List<Proyecto> listarProyectos() throws DAOException{
        // Creo una lista de proyectos y especifico la query y conexión
        List<Proyecto> todosLosProyectos = new ArrayList<>();

        String sql = "SELECT * FROM proyectos";

        Connection c = DBManager.connect();

        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            // Armo un objeto proyecto con los valores que encontré en la base y voy populando mi lista
            while (rs.next()) {
                Integer idProyecto = rs.getInt("id_proyecto");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String owner = rs.getString("email_owner");
                String estado = rs.getString("estado");

                Proyecto p = new Proyecto(idProyecto, nombre, descripcion, owner, estado);
                todosLosProyectos.add(p);
            }
        } catch (SQLException e) {
            throw new DAOException("Error al fetchear los proyectos", e);
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return todosLosProyectos;
    }

    @Override
    public Float calcularHorasProyecto(String nombreProyecto) throws DAOException {

        Float horasEstimadas = null;
        Float horasReales = null;
        Connection c = DBManager.connect();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT SUM(horas_estimadas), SUM(horas_reales) " +
                    "FROM tareas " +
                    "JOIN proyectos ON tareas.id_proyecto = proyectos.id_proyecto " +
                    "WHERE proyectos.nombre = ?";

            pstmt = c.prepareStatement(query);
            pstmt.setString(1, nombreProyecto);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                horasEstimadas = rs.getFloat(1);
                horasReales = rs.getFloat(2);
            }
        } catch (SQLException e) {
            throw new DAOException("Error al calcular horas", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        return horasEstimadas;
    }

    @Override
    public Float calcularCostoProyecto(Integer idProyecto) throws DAOException {

        Float costoProyecto = null;
        Connection c = DBManager.connect();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT SUM(costo_xhora * horas_estimadas) " +
                    "FROM empleados " +
                    "JOIN tareaempleado ON empleados.email = tareaempleado.email_empleado " +
                    "JOIN tareas ON tareas.id_tarea = tareaempleado.id_tarea " +
                    "JOIN proyectos ON tareas.id_proyecto = proyectos.id_proyecto " +
                    "WHERE proyectos.id_proyecto = ?";

            pstmt = c.prepareStatement(query);
            pstmt.setInt(1, idProyecto);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                costoProyecto = rs.getFloat(1);
            }
        } catch (SQLException e) {
            throw new DAOException("Error al calcular horas", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        return costoProyecto;
    }

    public List<Proyecto> verProyecto(Integer idProyecto) throws DAOException{
        // Creo una lista de proyectos y especifico la query y conexión
        List<Proyecto> unProyecto = new ArrayList<>();

        String sql = "SELECT * FROM proyectos WHERE id_proyecto = '" + idProyecto + "'";

        Connection c = DBManager.connect();

        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            // Armo un objeto proyecto con los valores que encontré en la base y voy populando mi lista
            while (rs.next()) {
                Integer r_idProyecto = rs.getInt("id_proyecto");
                String r_nombre = rs.getString("nombre");
                String r_descripcion = rs.getString("descripcion");
                String r_owner = rs.getString("email_owner");
                String r_estado = rs.getString("estado");

                Proyecto p = new Proyecto(r_idProyecto, r_nombre, r_descripcion, r_owner, r_estado);
                unProyecto.add(p);
            }
        } catch (SQLException e) {
            throw new DAOException("Error al fetchear los proyectos: " + e.getErrorCode(), e);
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return unProyecto;
    }


}
