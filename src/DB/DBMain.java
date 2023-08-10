package DB;

import DB.TableManager;

public class DBMain {

    public static void main(String[] args) {
        System.out.println("Url de conxión para usar en la consola H2 del browser u otro cliente SQL:");
        System.out.println(DBManager.obtenerUbicacionBase());

        TableManager tm = new TableManager();
        //tm.droppearTablaProyectos();
        //tm.crearTablaProyectos();
        //tm.droppearTablaTareas();
        //tm.crearTablaTareas();
        //tm.droppearTablaTareaEmpleado();
        //tm.crearTablaTareaEmpleado();

    }



}