package UI.Empleado;

import Excepciones.ServiceException;
import Servicio.EmpleadoService;

import javax.swing.*;
import java.awt.*;

public class VentanaTablaEmpleado extends JFrame {
    // Declaro las estructuras que voy a usar en la ventana
    private JTable tablaEmpleados;
    private JScrollPane panelScrolleable;
    private EmpleadoService empleadoService;

    public VentanaTablaEmpleado() {
        // Setteo las especificaciones de la ventana
        setTitle("Tabla de Empleados");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLayout(new BorderLayout());

        // Creo las instancias de EmpleadoTabla y EmpleadoService y especifico mi tabla scrolleable
        empleadoService = new EmpleadoService();
        tablaEmpleados = new JTable();
        panelScrolleable = new JScrollPane(tablaEmpleados);

        // Especifico la tabla y la populo con listarEmpleados, envuelto en el try para levantar el error
        try {
            tablaEmpleados.setModel(new EmpleadoTabla(empleadoService.listarEmpleados()));
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al obtener la lista de empleados.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        // Agrego la tabla a la ventana
        add(panelScrolleable, BorderLayout.CENTER);
    }
}