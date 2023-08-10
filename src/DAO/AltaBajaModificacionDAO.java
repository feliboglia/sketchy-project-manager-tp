package DAO;

import Excepciones.DAOException;
import DB.DBManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AltaBajaModificacionDAO<T> {
    // Métodos abstractos para implementar en los daos
    protected abstract String crearObjeto(T obj) throws DAOException;
    protected abstract String modificarObjeto(T obj) throws DAOException;
    protected abstract String eliminarObjeto(T obj) throws DAOException;

    public void crear(T obj) throws DAOException {
        Connection c = DBManager.connect();

        try {
            Statement s = c.createStatement();

            String sql = crearObjeto(obj);

            s.executeUpdate(sql);
            c.commit();

        } catch (SQLException e) {
            if (e.getErrorCode() == 23505) {
                throw new DAOException("El objeto ya existe en la base");
            } else {
                throw new DAOException("Error al crear", e);
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void eliminar(T obj) throws DAOException {
        Connection c = DBManager.connect();

        try {
            Statement s = c.createStatement();

            String sql = eliminarObjeto(obj);

            s.executeUpdate(sql);
            c.commit();

        } catch (SQLException e) {
            throw new DAOException("Error al eliminar");
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void modificar(T obj) throws DAOException {
        Connection c = DBManager.connect();

        try {
            Statement s = c.createStatement();

            String sql = modificarObjeto(obj);

            s.executeUpdate(sql);
            c.commit();

        } catch (SQLException e) {
            throw new DAOException("Error al modificar");
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

}
