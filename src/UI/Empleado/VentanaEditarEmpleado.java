package UI.Empleado;

import Entidades.Empleado;
import Excepciones.ServiceException;
import Servicio.EmpleadoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaEditarEmpleado extends JFrame {
    // Declaro las estructuras que voy a usar en la ventana
    private JTextField txtEmail;
    private JTextField txtNuevoNombre;
    private JTextField txtNuevoApellido;
    private JButton btnEditar;
    private EmpleadoService empleadoService;

    public VentanaEditarEmpleado() {
        // Setteo las especificaciones de la ventana
        setTitle("Editar Empleado");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 150);
        setLayout(new GridLayout(4, 2));

        // Creo mi instancia de EmpleadoService y especifico los componentes
        empleadoService = new EmpleadoService();

        JLabel lblEmail = new JLabel("Email del usuario:");
        JLabel lblNuevoNombre = new JLabel("Nuevo Nombre:");
        JLabel lblNuevoApellido = new JLabel("Nuevo Apellido:");
        JSeparator separadorHorizontal = new JSeparator(SwingConstants.HORIZONTAL);

        txtEmail = new JTextField(20);
        txtNuevoNombre = new JTextField(20);
        txtNuevoApellido = new JTextField(20);
        btnEditar = new JButton("Editar");

        // Configuro el listener del boton
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText();
                String nuevoNombre = txtNuevoNombre.getText();
                String nuevoApellido = txtNuevoApellido.getText();

                // Envuelvo en un try catch la llamada al service
                try {
                    empleadoService.modificacionEmpleado(new Empleado(email, nuevoNombre, nuevoApellido));
                    JOptionPane.showMessageDialog(VentanaEditarEmpleado.this, "Empleado editado correctamente");
                    dispose();
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(VentanaEditarEmpleado.this, "Error al editar el empleado: " + ex.getMessage());
                }
            }
        });

        // Agrego los componentes a la ventana
        add(lblEmail);
        add(txtEmail);
        //add(separadorHorizontal);
        add(lblNuevoNombre);
        add(txtNuevoNombre);
        add(lblNuevoApellido);
        add(txtNuevoApellido);
        add(btnEditar);
    }
}