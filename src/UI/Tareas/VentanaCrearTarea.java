package UI.Tareas;

import Entidades.Tarea;
import Excepciones.ServiceException;
import Servicio.TareaService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCrearTarea extends JFrame {
    // Declaro las estructuras que voy a usar en la ventana
    private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JTextField txtHorasEstimadas;
    private JButton btnGuardar;
    private TareaService tareaService;

    public VentanaCrearTarea(Integer idProyecto) {
        // Setteo las especificaciones de la ventana

        setTitle("Nueva Tarea");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 200);
        setLayout(new GridLayout(6, 2));

        // Creo mi instancia de tareaservice y especifico los componentes
        tareaService = new TareaService();

        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblDescripcion = new JLabel("Descripcion:");
        JLabel lblHorasEstimadas = new JLabel("Horas Estimadas:");

        txtNombre = new JTextField(20);
        txtDescripcion = new JTextField(20);
        txtHorasEstimadas = new JTextField(10);
        btnGuardar = new JButton("Guardar");

        // Configuro el listener del boton
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                String descripcion = txtDescripcion.getText();
                float horasEstimadas = 0F;
                try {
                    Float.parseFloat(txtHorasEstimadas.getText());
                    horasEstimadas = Float.parseFloat(txtHorasEstimadas.getText());
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(VentanaCrearTarea.this, "Ingrese un número!: " + nfe.getMessage());
                }

                // Envuelvo en un try catch la llamada al service
                try {

                    tareaService.crearTarea(new Tarea(idProyecto, nombre, descripcion, horasEstimadas));

                    JOptionPane.showMessageDialog(VentanaCrearTarea.this, "Tarea creada correctamente! Vuelva a abrir la sección.");
                    dispose();
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(VentanaCrearTarea.this, "Error al crear la tarea: " + ex.getMessage());
                }

            }
        });

        // Agrego los componentes a la ventana
        add(lblNombre);
        add(txtNombre);
        add(lblDescripcion);
        add(txtDescripcion);
        add(lblHorasEstimadas);
        add(txtHorasEstimadas);
        add(btnGuardar);
    }
}
