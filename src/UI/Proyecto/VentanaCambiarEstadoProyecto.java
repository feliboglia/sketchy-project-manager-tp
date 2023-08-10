package UI.Proyecto;

import Entidades.Proyecto;
import Excepciones.ServiceException;
import Servicio.ProyectoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class VentanaCambiarEstadoProyecto extends JFrame{

    // Declaro las estructuras que voy a usar en la ventana
    private Integer idProyecto;
    private JRadioButton  chkIniciado;
    private JRadioButton  chkFinalizado;
    private JRadioButton  chkRetrasado;
    private JRadioButton  chkCancelado;
    private ButtonGroup buttonGroup;
    private JButton btnAceptar;
    private JButton btnCancelar;

    private ProyectoService proyectoService;

    private String obtenerNuevoEstado() {
        if (chkIniciado.isSelected()) {
            return "Iniciado";
        } else if (chkFinalizado.isSelected()) {
            return "Finalizado";
        } else if (chkRetrasado.isSelected()) {
            return "Retrasado";
        } else if (chkCancelado.isSelected()) {
            return "Cancelado";
        } else {
            return null;
        }
    }

    public VentanaCambiarEstadoProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
        proyectoService = new ProyectoService();

        setTitle("Cambiando Estado del Proyecto " + idProyecto);
        setSize(300, 200);
        setLayout(new BorderLayout());

        // Crear checkboxes
        chkIniciado = new JRadioButton ("Iniciado");
        chkFinalizado = new JRadioButton ("Finalizado");
        chkRetrasado = new JRadioButton ("Retrasado");
        chkCancelado = new JRadioButton ("Cancelado");

        // Crear grupo de botones
        buttonGroup = new ButtonGroup();
        buttonGroup.add(chkIniciado);
        buttonGroup.add(chkFinalizado);
        buttonGroup.add(chkRetrasado);
        buttonGroup.add(chkCancelado);

        // Crear panel para los checkboxes
        JPanel panelCheckboxes = new JPanel();
        panelCheckboxes.setLayout(new GridLayout(4, 1));
        panelCheckboxes.add(chkIniciado);
        panelCheckboxes.add(chkFinalizado);
        panelCheckboxes.add(chkRetrasado);
        panelCheckboxes.add(chkCancelado);

        // Crear botones
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");

        // Configurar el botón Aceptar
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nuevoEstado = obtenerNuevoEstado();

                if (nuevoEstado != null) {
                    try{
                        proyectoService.cambiarEstado(idProyecto, nuevoEstado);
                        JOptionPane.showMessageDialog(VentanaCambiarEstadoProyecto.this, "Estado actualizado correctamente!");
                        dispose();
                    } catch (ServiceException ex) {
                        JOptionPane.showMessageDialog(VentanaCambiarEstadoProyecto.this, "Error al cambiar el estado");
                    }
                    dispose(); // Cerrar la ventana después de realizar cambios
                } else {
                    JOptionPane.showMessageDialog(VentanaCambiarEstadoProyecto.this,
                            "Debes seleccionar un estado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Configurar el botón Cancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cerrar la ventana sin hacer cambios
            }
        });

        // Crear panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);

        // Agregar los paneles al contenido de la ventana
        add(panelCheckboxes, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }


}

