package DAO;
import DB.DBManager;
import Entidades.Empleado;
import Excepciones.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// TO-DO: - Implementar prepared statements para las querys

public class EmpleadoDAOManager implements EmpleadoDAO {

    public void insertarEmpleado(Empleado empleado) throws DAOException {
        // Especifico la información del empleado y la meto en la query
        String email = empleado.getEmail();
        String nombre = empleado.getNombre();
        String apellido = empleado.getApellido();
        String password = empleado.getPassword();
        Float costo_xhora = empleado.getCostoPorHora();

        Connection c = DBManager.connect();

        try {
            Statement s = c.createStatement();
            String query = "INSERT INTO empleados (email, nombre, apellido, password, costo_xhora) VALUES ('" + email + "','" + nombre + "','" + apellido + "','" + password + "','" + costo_xhora + "')";

            s.executeUpdate(query);
            c.commit();

        } catch (SQLException e) {
            try {
                if(e.getErrorCode() == 23505) {
                    throw new DAOException();
                }
                e.printStackTrace();
                c.rollback();
            } catch (SQLException e1) {
                e.printStackTrace();
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void borrarEmpleado(String email) throws DAOException {
        String sql = "DELETE FROM empleados WHERE email = '"+ email +"' ";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            s.executeUpdate(sql);
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException ex) {
            }
            throw new DAOException();
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void actualizarEmpleado(Empleado empleado) throws DAOException{
        String email = empleado.getEmail();
        String nuevoNombre = empleado.getNombre();
        String nuevoApellido = empleado.getApellido();

        Connection c = DBManager.connect();

        try {
            Statement s = c.createStatement();
            String query = "UPDATE empleados set nombre = '" + nuevoNombre + "', apellido = '" + nuevoApellido + "' WHERE email = '" + email + "'";

            s.executeUpdate(query);
            c.commit();

        } catch (SQLException e) {
            try {
                c.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public List<Empleado> listarEmpleados() throws DAOException{
        // Creo una lista de empleados y especifico la query y conexión
        List<Empleado> todosLosEmpleados = new ArrayList<>();
        String sql = "SELECT * FROM empleados";
        Connection c = DBManager.connect();

        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            // Armo un objeto empleado con los valores que encontré en la base y voy populando mi lista
            while (rs.next()) {
                String email = rs.getString("email");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String password = rs.getString("password");
                Float costo_xhora = rs.getFloat("costo_xhora");

                Empleado e = new Empleado(email, nombre, apellido, password, costo_xhora);
                todosLosEmpleados.add(e);
            }
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return todosLosEmpleados;
    }

    public List<Empleado> mostrarEmpleado(String email) throws DAOException{
        // Creo una lista de empleados y especifico la query y conexión
        List<Empleado> empleadosEncontrados = new ArrayList<>();
        String sql = "SELECT * FROM empleados WHERE email = '" + email + "'";
        Connection c = DBManager.connect();

        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            // Armo un objeto empleado con los valores que encontré en la base y populo mi lista
            if (rs.next()) {
                String r_mail = rs.getString("email");
                String r_nombre = rs.getString("nombre");
                String r_apellido = rs.getString("apellido");
                String r_password = rs.getString("password");
                Float r_costo_xhora = rs.getFloat("costo_xhora");
                Empleado e = new Empleado(r_mail, r_nombre, r_apellido, r_password, r_costo_xhora);
                empleadosEncontrados.add(e);
            }
        } catch (SQLException e) {
            try {
                c.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        // Devuelvo la lista
        return empleadosEncontrados;
    }

}
