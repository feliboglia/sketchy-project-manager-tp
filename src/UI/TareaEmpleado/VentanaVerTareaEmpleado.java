package UI.TareaEmpleado;

import Excepciones.ServiceException;
import Servicio.TareaEmpleadoService;
import UI.Tareas.TareaTabla;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VentanaVerTareaEmpleado extends JFrame {
    // Declaro las estructuras que voy a usar en la ventana
    private JTable tablaTareas;
    private TareaTabla modeloTabla;
    private TareaEmpleadoService tareaEmpleadoService;
    private JScrollPane panelScrolleable;
    private JTextField txtEmailEmpleado;

    public VentanaVerTareaEmpleado(String emailEmpleado) {
        // Setteo las especificaciones de la ventana
        setTitle("Tareas Asignadas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // ver si cuando cierro los botones puedo llamar un tableModel.fireTableDataChanged()
        setSize(500, 300);
        setLayout(new BorderLayout());

        // Creo las instancias de TareaTabla y TareaService
        modeloTabla = new TareaTabla(new ArrayList<>());
        tareaEmpleadoService = new TareaEmpleadoService();

        // Especifico los componentes
        tablaTareas = new JTable(modeloTabla);
        panelScrolleable = new JScrollPane(tablaTareas);

        // Especifico la tabla y la populo con listarTareasEmpleado, envuelto en el try para levantar el error
        try {
            tablaTareas.setModel(new TareaTabla(tareaEmpleadoService.listarTareasEmpleado(emailEmpleado)));
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al obtener la lista de tareas.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        // Creo el label de busqueda y agrego a la ventana
        JPanel panelTarea = new JPanel();
        add(panelTarea, BorderLayout.NORTH);
        add(panelScrolleable, BorderLayout.CENTER);

    }
}
