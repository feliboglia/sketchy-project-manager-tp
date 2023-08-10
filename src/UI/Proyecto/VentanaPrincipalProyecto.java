package UI.Proyecto;

import Entidades.Proyecto;
import Excepciones.ServiceException;
import Servicio.ProyectoService;
import UI.Tareas.VentanaPrincipalTarea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VentanaPrincipalProyecto extends JFrame {
    // Declaro las estructuras que voy a usar en la ventana
    private JButton btnCrearProyecto;
    private JButton btnBorrarProyecto;
    private JButton btnEditarProyecto;
    private JButton btnCambiarEstado;
    private JButton btnRefrescar;
    private JButton btnVerTareas;
    private JButton btnCalcularCosto;
    private JTable tablaProyectos;
    private ProyectoTabla modeloTabla;
    private ProyectoService proyectoService;
    private JScrollPane panelScrolleable;

    private void actualizarTabla() {
        try {
            List<Proyecto> proyectos = proyectoService.listarProyectos();
            modeloTabla.setProyectos(proyectos);
            modeloTabla.fireTableDataChanged();
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al obtener la lista de proyectos.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public VentanaPrincipalProyecto() {
        // Setteo las especificaciones de la ventana
        setTitle("Proyectos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // ver si cuando cierro los botones puedo llamar un tableModel.fireTableDataChanged()
        setSize(800, 400);
        setLayout(new BorderLayout());

        // Creo las instancias de EmpleadoTabla y EmpleadoService
        modeloTabla = new ProyectoTabla(new ArrayList<>());
        proyectoService = new ProyectoService();
        // Especifico los componentes
        btnCrearProyecto = new JButton("Crear");
        btnBorrarProyecto = new JButton("Borrar");
        btnEditarProyecto = new JButton("Editar");
        btnCambiarEstado = new JButton("Cambiar Estado");
        tablaProyectos = new JTable(modeloTabla);
        panelScrolleable = new JScrollPane(tablaProyectos);
        btnVerTareas = new JButton("Ver Tareas");
        btnCalcularCosto = new JButton("Calcular Costo");


        btnCrearProyecto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaCrearProyecto ventanaCrearProyecto = new VentanaCrearProyecto();
                ventanaCrearProyecto.setVisible(true);
                //actualizarTabla();
            }
        });

        btnBorrarProyecto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el índice seleccionado en la tabla
                int selectedRow = tablaProyectos.getSelectedRow();
                if (selectedRow != -1) {
                    // Obtener el ID del proyecto seleccionado
                    int idProyecto = (int) tablaProyectos.getValueAt(selectedRow, 0);

                    try {
                        proyectoService.eliminarProyecto(idProyecto);
                        JOptionPane.showMessageDialog(VentanaPrincipalProyecto.this, "Proyecto " + idProyecto + " eliminado correctamente!");
                        dispose();
                    } catch (ServiceException ex) {
                        JOptionPane.showMessageDialog(VentanaPrincipalProyecto.this, "Error al eliminar el proyecto: " + ex.getMessage());
                    }

                } else {
                    JOptionPane.showMessageDialog(VentanaPrincipalProyecto.this, "Selecciona un proyecto para borrar.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnEditarProyecto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el índice seleccionado en la tabla
                int selectedRow = tablaProyectos.getSelectedRow();
                if (selectedRow != -1) {
                    // Obtener el ID del proyecto seleccionado
                    int idProyecto = (int) tablaProyectos.getValueAt(selectedRow, 0);

                    VentanaEditarProyecto ventanaEditarProyecto = new VentanaEditarProyecto(idProyecto);
                    ventanaEditarProyecto.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(VentanaPrincipalProyecto.this, "Selecciona un proyecto para borrar.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCambiarEstado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el índice seleccionado en la tabla
                int selectedRow = tablaProyectos.getSelectedRow();
                if (selectedRow != -1) {
                    // Obtener el ID del proyecto seleccionado
                    int idProyecto = (int) tablaProyectos.getValueAt(selectedRow, 0);

                    VentanaCambiarEstadoProyecto ventanaCambiarEstadoProyecto = new VentanaCambiarEstadoProyecto(idProyecto);
                    ventanaCambiarEstadoProyecto.setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(VentanaPrincipalProyecto.this, "Selecciona un proyecto.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnVerTareas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el índice seleccionado en la tabla
                int selectedRow = tablaProyectos.getSelectedRow();
                if (selectedRow != -1) {
                    // Obtener el ID del proyecto seleccionado
                    int idProyecto = (int) tablaProyectos.getValueAt(selectedRow, 0);

                    VentanaPrincipalTarea ventanaPrincipalTarea = new VentanaPrincipalTarea(idProyecto);
                    ventanaPrincipalTarea.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(VentanaPrincipalProyecto.this, "Selecciona un proyecto para ver sus tareas.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCalcularCosto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el índice seleccionado en la tabla
                int selectedRow = tablaProyectos.getSelectedRow();
                if (selectedRow != -1) {
                    // Obtener el ID del proyecto seleccionado
                    int idProyecto = (int) tablaProyectos.getValueAt(selectedRow, 0);

                    try {
                        Float costoProyecto = proyectoService.calcularCostoProyecto(idProyecto);
                        if(costoProyecto == 0F){
                            JOptionPane.showMessageDialog(VentanaPrincipalProyecto.this, "No hay costo, las tareas tienen empleados asignados?");
                        }
                        else{
                            JOptionPane.showMessageDialog(VentanaPrincipalProyecto.this, "El costo del proyecto "+ idProyecto + " es "+ costoProyecto +" $.");
                        }
                    } catch (ServiceException ex) {
                        JOptionPane.showMessageDialog(VentanaPrincipalProyecto.this, "Error al calcular el costo: " + ex.getMessage());
                    }

                } else {
                    JOptionPane.showMessageDialog(VentanaPrincipalProyecto.this, "Selecciona un proyecto para calcular su costo.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Especifico la tabla y la populo con listarEmpleados, envuelto en el try para levantar el error
        try {
            tablaProyectos.setModel(new ProyectoTabla(proyectoService.listarProyectos()));
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al obtener la lista de proyectos.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        // Creo el label de busqueda y agrego a la ventana
        JPanel panelProyecto = new JPanel();
        panelProyecto.add(btnCrearProyecto);
        panelProyecto.add(btnBorrarProyecto);
        panelProyecto.add(btnEditarProyecto);
        add(panelProyecto, BorderLayout.NORTH);
        add(panelScrolleable, BorderLayout.CENTER);
        panelProyecto.add(btnVerTareas);
        panelProyecto.add(btnCalcularCosto);
        panelProyecto.add(btnCambiarEstado);

    }
}
