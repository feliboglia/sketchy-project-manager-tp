import DAO.ProyectoDAO;
import DAO.ProyectoDAOManager;
import Entidades.Proyecto;
import Excepciones.DAOException;
import Excepciones.ServiceException;
import Servicio.ProyectoService;

import UI.PanelManager;

public class Main {
    static PanelManager manager;
    static Proyecto pr;


    public static void main(String [] args) throws DAOException {

        // Llamo a mi panel manager
        iniciarManager();
        //testProyecto();

    }
    private static void iniciarManager() {
        manager = new PanelManager();
        PanelManager.main();
    }

}
