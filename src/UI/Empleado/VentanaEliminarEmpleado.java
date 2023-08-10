package UI.Empleado;

import Entidades.Empleado;
import Excepciones.ServiceException;
import Servicio.EmpleadoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaEliminarEmpleado extends JFrame {
    // Declaro las estructuras que voy a usar en la ventana
    private JTextField txtEmail;
    private JButton btnEliminar;
    private JLabel lblEmail;
    private EmpleadoService empleadoService;

    public VentanaEliminarEmpleado() {
        // Setteo las especificaciones de la ventana
        setTitle("Eliminar Empleado");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 150);
        setLayout(new FlowLayout());

        // Creo mi instancia de EmpleadoService y especifico los componentes
        empleadoService = new EmpleadoService();

        lblEmail = new JLabel("Email:");
        txtEmail = new JTextField(20);
        btnEliminar = new JButton("Eliminar");

        // Configuro el listener del boton
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText();

                // Envuelvo en un try catch la llamada al service
                try {
                    empleadoService.bajaEmpleado(new Empleado(email));
                    JOptionPane.showMessageDialog(VentanaEliminarEmpleado.this, "Empleado eliminado correctamente");
                    dispose();
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(VentanaEliminarEmpleado.this, "Error al eliminar el empleado: " + ex.getMessage());
                }
            }
        });

        // Agrego los componentes a la ventana
        add(lblEmail);
        add(txtEmail);
        add(btnEliminar);
    }
}