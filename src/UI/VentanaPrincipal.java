package UI;

import UI.Empleado.VentanaPrincipalEmpleado;
import UI.Proyecto.VentanaPrincipalProyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
    // Declaro las estructuras que voy a usar en la ventana
    private JButton btnEmpleados;
    private JButton btnProyectos;

    public VentanaPrincipal() {
        // Setteo especificaciones de la ventana
        setTitle("Administrador de Proyectos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLayout(new FlowLayout());

        // Especifico los botones
        btnEmpleados = new JButton("Empleados");
        btnProyectos = new JButton("Proyectos");

        // Configuro los listeners para los botones
        btnEmpleados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaPrincipalEmpleado VentanaPrincipalEmpleado = new VentanaPrincipalEmpleado();
                VentanaPrincipalEmpleado.setVisible(true);
            }
        });

        btnProyectos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaPrincipalProyecto VentanaPrincipalProyecto = new VentanaPrincipalProyecto();
                VentanaPrincipalProyecto.setVisible(true);
            }
        });

        // Agrego los botones en la ventana principal
        add(btnEmpleados);
        add(btnProyectos);
    }
}