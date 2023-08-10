package DB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import DB.DBManager;

public class TableManager {
    /*---- Empleados ----*/
    public void crearTablaEmpleado() {
    // Crea la tabla de empleados
        Connection c = DBManager.connect();

        String sql = "CREATE TABLE empleados (email VARCHAR(256) NOT NULL, nombre VARCHAR(256), apellido VARCHAR(256), password VARCHAR(256), costo_xhora FLOAT(16) )";

        try {
            Statement s = c.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            try {
                c.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        finally {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void droppearTablaEmpleado() {
    // Elimina la tabla de empleados

        Connection c = DBManager.connect();

        String sql = "DROP TABLE empleados";

        try {
            Statement s = c.createStatement();
            s.execute(sql);
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        finally {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /*---- Proyecto ----*/
    public void crearTablaProyectos() {
        // Crea la tabla de proyectos
        Connection c = DBManager.connect();

        String sql = "CREATE TABLE proyectos (id_proyecto INTEGER IDENTITY, nombre VARCHAR(256) UNIQUE, descripcion VARCHAR(256), email_owner VARCHAR(256), estado VARCHAR(256), FOREIGN KEY (email_owner) REFERENCES empleados(email) )";

        try {
            Statement s = c.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            try {
                c.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        finally {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void droppearTablaProyectos() {
        // Elimina la tabla de proyectos

        Connection c = DBManager.connect();

        String sql = "DROP TABLE proyectos";

        try {
            Statement s = c.createStatement();
            s.execute(sql);
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        finally {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /*---- Tareas ----*/
    public void crearTablaTareas() {
        // Crea la tabla de tareas
        Connection c = DBManager.connect();

        String sql = "CREATE TABLE tareas (id_tarea INTEGER IDENTITY, id_proyecto INTEGER NOT NULL, nombre VARCHAR(256) NOT NULL, estado VARCHAR(256), descripcion VARCHAR(256), horas_estimadas float(16), FOREIGN KEY (id_proyecto) REFERENCES proyectos(id_proyecto) )";

        try {
            Statement s = c.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            try {
                c.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        finally {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void droppearTablaTareas() {
        // Elimina la tabla de tareas

        Connection c = DBManager.connect();

        String sql = "DROP TABLE tareas";

        try {
            Statement s = c.createStatement();
            s.execute(sql);
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        finally {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /*---- TareaEmpleado ----*/
    public void crearTablaTareaEmpleado() {
        // Crea la tabla de tareas
        Connection c = DBManager.connect();

        String sql = "CREATE TABLE tareaEmpleado (id_tarea_empleado INTEGER IDENTITY, id_tarea INTEGER NOT NULL, email_empleado VARCHAR(256) NOT NULL, horas_reales FLOAT(16), FOREIGN KEY (id_tarea) REFERENCES tareas(id_tarea), FOREIGN KEY (email_empleado) REFERENCES empleados(email) )";

        try {
            Statement s = c.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            try {
                c.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        finally {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void droppearTablaTareaEmpleado() {
        // Elimina la tabla de tareas

        Connection c = DBManager.connect();

        String sql = "DROP TABLE tareaEmpleado";

        try {
            Statement s = c.createStatement();
            s.execute(sql);
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        finally {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



}