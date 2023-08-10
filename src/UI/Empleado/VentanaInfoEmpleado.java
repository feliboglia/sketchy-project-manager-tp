package UI.Empleado;

import Entidades.Empleado;
import Excepciones.ServiceException;
import Servicio.EmpleadoService;
import UI.TareaEmpleado.VentanaVerTareaEmpleado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VentanaInfoEmpleado extends JFrame {
    // Declaro las estructuras que voy a usar en la ventana
    private JButton btnBuscar;
    private JButton btnVerTareasAsignadas;
    private JTextField txtBusqueda;
    private JLabel lblTxtBusqueda;
    private JTable tablaEmpleados;
    private EmpleadoTabla modeloTabla;
    private EmpleadoService empleadoService;
    private JScrollPane panelScrolleable;

    public VentanaInfoEmpleado() {
        // Setteo las especificaciones de la ventana
        setTitle("Información de Empleado");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 300);
        setLayout(new BorderLayout());

        // Creo las instancias de EmpleadoTabla y EmpleadoService
        modeloTabla = new EmpleadoTabla(new ArrayList<>());
        empleadoService = new EmpleadoService();

        // Especifico los componentes
        btnBuscar = new JButton("Buscar");
        btnVerTareasAsignadas = new JButton("Ver Tareas Asignadas");
        txtBusqueda = new JTextField(20);
        lblTxtBusqueda = new JLabel("Buscar empleado: ");
        tablaEmpleados = new JTable(modeloTabla);
        panelScrolleable = new JScrollPane(tablaEmpleados);

        // Configuro el listener para el boton de buscar
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtBusqueda.getText();
                List<Empleado> empleados = null;

                // Envuelvo en un try catch la llamada al service
                try {
                    empleados = empleadoService.buscarEmpleado(email);
                } catch (ServiceException ex) {
                    throw new RuntimeException(ex);
                }

                if (empleados.isEmpty()) {
                    // Mostrar alerta de "no se encontró el empleado"
                    JOptionPane.showMessageDialog(VentanaInfoEmpleado.this,
                            "No se encontró el empleado", "Alerta", JOptionPane.WARNING_MESSAGE);
                } else {
                    modeloTabla.setEmpleados(empleados);
                }
                //modelo.setEmpleados(empleados);
            }
        });

        btnVerTareasAsignadas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtBusqueda.getText();
                if (email.isEmpty()) {
                    // Mostrar alerta
                    JOptionPane.showMessageDialog(VentanaInfoEmpleado.this,
                            "Especifique el email", "Alerta", JOptionPane.WARNING_MESSAGE);
                } else {
                    VentanaVerTareaEmpleado ventanaVerTareaEmpleado = new VentanaVerTareaEmpleado(email);
                    ventanaVerTareaEmpleado.setVisible(true);
                }
            }
        });


        // Creo el label de busqueda y agrego a la ventana
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.add(lblTxtBusqueda);
        panelBusqueda.add(txtBusqueda);
        panelBusqueda.add(btnBuscar);
        panelBusqueda.add(btnVerTareasAsignadas);
        add(panelBusqueda, BorderLayout.NORTH);
        add(panelScrolleable, BorderLayout.CENTER);
    }
}
