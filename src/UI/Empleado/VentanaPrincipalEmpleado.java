package UI.Empleado;

import UI.TareaEmpleado.VentanaVerTareaEmpleado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipalEmpleado extends JFrame {
    // Declaro las estructuras que voy a usar en la ventana
    private JButton btnAgregarEmpleado;
    private JButton btnEliminarEmpleado;
    private JButton btnEditarEmpleado;
    private JButton btnListarEmpleados;
    private JButton btnInfoEmpleado;

    public VentanaPrincipalEmpleado() {
        // Setteo especificaciones de la ventana
        setTitle("Empleados");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLayout(new FlowLayout());

        // Especifico los botones
        btnAgregarEmpleado = new JButton("Agregar Empleado");
        btnEliminarEmpleado = new JButton("Eliminar Empleado");
        btnEditarEmpleado = new JButton("Editar Empleado");
        btnListarEmpleados = new JButton("Listar Empleados");
        btnInfoEmpleado = new JButton("Informacion Empleado");


        // Configuro los listeners para los botones
        btnAgregarEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaAgregarEmpleado ventanaAgregarEmpleado = new VentanaAgregarEmpleado();
                ventanaAgregarEmpleado.setVisible(true);
            }
        });

        btnEliminarEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaEliminarEmpleado ventanaEliminarEmpleado = new VentanaEliminarEmpleado();
                ventanaEliminarEmpleado.setVisible(true);
            }
        });

        btnEditarEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaEditarEmpleado ventanaEditarEmpleado = new VentanaEditarEmpleado();
                ventanaEditarEmpleado.setVisible(true);
            }
        });

        btnListarEmpleados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaTablaEmpleado VentanaTablaEmpleado = new VentanaTablaEmpleado();
                VentanaTablaEmpleado.setVisible(true);
            }
        });

        btnInfoEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaInfoEmpleado VentanaInfoEmpleado = new VentanaInfoEmpleado();
                VentanaInfoEmpleado.setVisible(true);
            }
        });


        // Agrego los botones en la ventana principal
        add(btnAgregarEmpleado);
        add(btnEliminarEmpleado);
        add(btnEditarEmpleado);
        add(btnListarEmpleados);
        add(btnInfoEmpleado);
    }
}