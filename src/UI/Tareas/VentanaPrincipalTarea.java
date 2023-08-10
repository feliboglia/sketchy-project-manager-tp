package UI.Tareas;

import Entidades.Tarea;
import Entidades.TareaEmpleado;
import Excepciones.ServiceException;
import Servicio.TareaEmpleadoService;
import Servicio.TareaService;
import UI.TareaEmpleado.VentanaCrearTareaEmpleado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaPrincipalTarea extends JFrame {
    // Declaro las estructuras que voy a usar en la ventana
    private JButton btnCrearTarea;
    private JButton btnBorrarTarea;
    private JButton btnEditarTarea;
    private JButton btnCambiarEstadoTarea;
    private JButton btnAsignarTarea;
    private JButton btnDesasignarEmpleados;
    private JTable tablaTareas;
    private TareaTabla modeloTabla;
    private TareaService tareaService;
    private TareaEmpleadoService tareaEmpleadoService;
    private JScrollPane panelScrolleable;

    public VentanaPrincipalTarea(Integer idProyecto) {
        // Setteo las especificaciones de la ventana
        setTitle("Tareas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // ver si cuando cierro los botones puedo llamar un tableModel.fireTableDataChanged()
        setSize(800, 300);
        setLayout(new BorderLayout());

        // Creo las instancias de TareaTabla y TareaService
        modeloTabla = new TareaTabla(new ArrayList<>());
        tareaService = new TareaService();
        tareaEmpleadoService = new TareaEmpleadoService();

        // Especifico los componentes
        btnCrearTarea = new JButton("Crear Tarea");
        btnBorrarTarea = new JButton("Borrar Tarea");
        btnEditarTarea = new JButton("Editar Tarea");
        btnCambiarEstadoTarea = new JButton("Cambiar Estado");
        tablaTareas = new JTable(modeloTabla);
        panelScrolleable = new JScrollPane(tablaTareas);
        btnAsignarTarea = new JButton("Asignar Tarea");
        btnDesasignarEmpleados = new JButton("Desasignar Tareas");


        btnCrearTarea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaCrearTarea ventanaCrearTarea = new VentanaCrearTarea(idProyecto);
                ventanaCrearTarea.setVisible(true);
                System.out.println(idProyecto);
                //actualizarTabla();
            }
        });

        btnBorrarTarea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el índice seleccionado en la tabla
                int selectedRow = tablaTareas.getSelectedRow();
                if (selectedRow != -1) {
                    // Obtener el ID de la tarea seleccionado
                    int idTarea = (int) tablaTareas.getValueAt(selectedRow, 0);

                    try {
                        tareaService.eliminarTarea(idTarea);
                        JOptionPane.showMessageDialog(VentanaPrincipalTarea.this, "Tarea " + idTarea + " eliminada correctamente!");
                        dispose();
                    } catch (ServiceException ex) {
                        JOptionPane.showMessageDialog(VentanaPrincipalTarea.this, "Error al eliminar la tarea: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(VentanaPrincipalTarea.this, "Selecciona una tarea para borrar.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnEditarTarea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el índice seleccionado en la tabla
                int selectedRow = tablaTareas.getSelectedRow();
                if (selectedRow != -1) {
                    // Obtener el ID de la tarea seleccionado
                    int idTarea = (int) tablaTareas.getValueAt(selectedRow, 0);

                    VentanaEditarTarea ventanaEditarTarea = new VentanaEditarTarea(idTarea);
                    ventanaEditarTarea.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(VentanaPrincipalTarea.this, "Seleccione una tarea.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCambiarEstadoTarea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el índice seleccionado en la tabla
                int selectedRow = tablaTareas.getSelectedRow();
                if (selectedRow != -1) {
                    // Obtener el ID del proyecto seleccionado
                    int idTarea = (int) tablaTareas.getValueAt(selectedRow, 0);

                    VentanaCambiarEstadoTarea ventanaCambiarEstadoTarea = new VentanaCambiarEstadoTarea(idTarea);
                    ventanaCambiarEstadoTarea.setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(VentanaPrincipalTarea.this, "Seleccione una tarea.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnAsignarTarea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el índice seleccionado en la tabla
                int selectedRow = tablaTareas.getSelectedRow();
                if (selectedRow != -1) {
                    // Obtener el ID de la tarea seleccionado
                    int idTarea = (int) tablaTareas.getValueAt(selectedRow, 0);

                    VentanaCrearTareaEmpleado ventanaCrearTareaEmpleado = new VentanaCrearTareaEmpleado(idTarea);
                    ventanaCrearTareaEmpleado.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(VentanaPrincipalTarea.this, "Seleccione una tarea.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnDesasignarEmpleados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el índice seleccionado en la tabla
                int selectedRow = tablaTareas.getSelectedRow();
                if (selectedRow != -1) {
                    // Obtener el ID de la tarea seleccionado
                    int idTarea = (int) tablaTareas.getValueAt(selectedRow, 0);

                    try {
                        System.out.println(idTarea);
                        tareaEmpleadoService.desasignarTareaEmpleado(new TareaEmpleado(idTarea));
                        JOptionPane.showMessageDialog(VentanaPrincipalTarea.this, "Se desasignaron los empleados de la tarea " + idTarea + " correctamente!");
                        dispose();
                    } catch (ServiceException ex) {
                        JOptionPane.showMessageDialog(VentanaPrincipalTarea.this, "Error al desasignar: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(VentanaPrincipalTarea.this, "Selecciona una tarea.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Especifico la tabla y la populo con listarTareas, envuelto en el try para levantar el error
        try {
            tablaTareas.setModel(new TareaTabla(tareaService.listarTareas(idProyecto)));
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al obtener la lista de tareas.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        // Creo el label de busqueda y agrego a la ventana
        JPanel panelTarea = new JPanel();
        panelTarea.add(btnCrearTarea);
        panelTarea.add(btnBorrarTarea);
        panelTarea.add(btnEditarTarea);
        add(panelTarea, BorderLayout.NORTH);
        add(panelScrolleable, BorderLayout.CENTER);
        panelTarea.add(btnAsignarTarea);
        panelTarea.add(btnCambiarEstadoTarea);
        panelTarea.add(btnDesasignarEmpleados);

    }
}
