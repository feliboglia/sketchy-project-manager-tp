package UI.Empleado;

import Entidades.Empleado;
import Excepciones.ServiceException;
import Servicio.EmpleadoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAgregarEmpleado extends JFrame {
    // Declaro las estructuras que voy a usar en la ventana
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JTextField txtCostoHora;
    private JButton btnGuardar;
    private EmpleadoService empleadoService;

    public VentanaAgregarEmpleado() {
        // Setteo las especificaciones de la ventana
        setTitle("Agregar Empleado");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 200);
        setLayout(new GridLayout(6, 2));

        // Creo mi instancia de EmpleadoService y especifico los componentes
        empleadoService = new EmpleadoService();

        JLabel lblEmail = new JLabel("Email:");
        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblApellido = new JLabel("Apellido:");
        JLabel lblPassword = new JLabel("Contraseña:");
        JLabel lblCostoHora = new JLabel("Costo por Hora:");

        txtEmail = new JTextField(20);
        txtNombre = new JTextField(20);
        txtApellido = new JTextField(20);
        txtPassword = new JPasswordField(20);
        txtCostoHora = new JTextField(20);
        btnGuardar = new JButton("Guardar");

        // Configuro el listener del boton
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText();
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                String password = new String(txtPassword.getPassword());
                float costoHora = Float.parseFloat(txtCostoHora.getText());

                // Envuelvo en un try catch la llamada al service
                try {
                    empleadoService.altaEmpleado(new Empleado(email, nombre, apellido, password, costoHora));
                    JOptionPane.showMessageDialog(VentanaAgregarEmpleado.this, "Empleado agregado correctamente");
                    dispose();
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(VentanaAgregarEmpleado.this, "Error al agregar el empleado: " + ex.getMessage());
                }
            }
        });

        // Agrego los componentes a la ventana
        add(lblEmail);
        add(txtEmail);
        add(lblNombre);
        add(txtNombre);
        add(lblApellido);
        add(txtApellido);
        add(lblPassword);
        add(txtPassword);
        add(lblCostoHora);
        add(txtCostoHora);
        add(btnGuardar);
    }
}