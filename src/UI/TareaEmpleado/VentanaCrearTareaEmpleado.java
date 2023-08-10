package UI.TareaEmpleado;

import Entidades.Tarea;
import Entidades.TareaEmpleado;
import Excepciones.ServiceException;
import Servicio.TareaEmpleadoService;
import UI.Empleado.VentanaTablaEmpleado;
import UI.Tareas.VentanaCambiarEstadoTarea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCrearTareaEmpleado extends JFrame {
    // Declaro las estructuras que voy a usar en la ventana
    private JTextField txtEmailEmpleado;
    private JTextField txtHorasReales;
    private JButton btnGuardar;
    private JButton btnVerEmpleadoDisponibles;
    private TareaEmpleadoService tareaEmpleadoService;

    public VentanaCrearTareaEmpleado(Integer idTarea) {
        // Setteo las especificaciones de la ventana

        setTitle("Nueva Tarea");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 200);
        setLayout(new GridLayout(6, 2));

        // Creo mi instancia de tareaservice y especifico los componentes
        tareaEmpleadoService = new TareaEmpleadoService();

        JLabel lblEmailEmpleado = new JLabel("Empleado a asignar:");
        JLabel lblHorasReales = new JLabel("Horas Reales:");

        txtEmailEmpleado = new JTextField(20);
        txtHorasReales = new JTextField(20);
        btnGuardar = new JButton("Guardar");
        btnVerEmpleadoDisponibles = new JButton("Empleados Disponibles");

        // Configuro el listener del boton
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emailEmpleado = txtEmailEmpleado.getText();
                float horasReales = 0F;
                try {
                    Float.parseFloat(txtHorasReales.getText());
                    horasReales = Float.parseFloat(txtHorasReales.getText());
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(VentanaCrearTareaEmpleado.this, "Ingrese un número! ");
                }

                // Envuelvo en un try catch la llamada al service
                try {

                    tareaEmpleadoService.asignarTareaEmpleado(new TareaEmpleado(idTarea, emailEmpleado, horasReales));

                    JOptionPane.showMessageDialog(VentanaCrearTareaEmpleado.this, "Empleado asignado correctamente!");
                    dispose();
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(VentanaCrearTareaEmpleado.this, "Error al asignar: " + ex.getMessage());
                }

            }
        });

        btnVerEmpleadoDisponibles.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 VentanaTablaEmpleado ventanaTablaEmpleado = new VentanaTablaEmpleado();
                 ventanaTablaEmpleado.setVisible(true);
             }
        });

        // Agrego los componentes a la ventana
        add(lblEmailEmpleado);
        add(txtEmailEmpleado);
        add(lblHorasReales);
        add(txtHorasReales);
        add(btnGuardar);
        add(btnVerEmpleadoDisponibles);
    }
}
