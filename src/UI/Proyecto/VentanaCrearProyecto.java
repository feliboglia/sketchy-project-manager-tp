package UI.Proyecto;

import Entidades.Proyecto;
import Excepciones.ServiceException;
import Servicio.ProyectoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCrearProyecto extends JFrame {
    // Declaro las estructuras que voy a usar en la ventana
    private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JTextField txtEmailOwner;
    private JButton btnGuardar;
    private ProyectoService proyectoService;

    public VentanaCrearProyecto() {
        // Setteo las especificaciones de la ventana
        setTitle("Nuevo Proyecto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 200);
        setLayout(new GridLayout(6, 2));

        // Creo mi instancia de ProyectoService y especifico los componentes
        proyectoService = new ProyectoService();

        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblDescripcion = new JLabel("Descripcion:");
        JLabel lblEmailOwner = new JLabel("Mail owner:");

        txtNombre = new JTextField(20);
        txtDescripcion = new JTextField(20);
        txtEmailOwner = new JTextField(20);
        btnGuardar = new JButton("Guardar");

        // Configuro el listener del boton
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                String descripcion = txtDescripcion.getText();
                String emailOwner = txtEmailOwner.getText();

                // Envuelvo en un try catch la llamada al service
                try {
                    proyectoService.crearProyecto(new Proyecto(nombre, descripcion, emailOwner));
                    JOptionPane.showMessageDialog(VentanaCrearProyecto.this, "Proyecto creado correctamente! Vuelva a abrir la sección.");
                    dispose();
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(VentanaCrearProyecto.this, "Error al crear el proyecto: " + ex.getMessage());
                }

            }
        });

        // Agrego los componentes a la ventana
        add(lblNombre);
        add(txtNombre);
        add(lblDescripcion);
        add(txtDescripcion);
        add(lblEmailOwner);
        add(txtEmailOwner);
        add(btnGuardar);
    }
}
