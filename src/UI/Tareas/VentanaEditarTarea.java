package UI.Tareas;

import Entidades.Tarea;
import Excepciones.ServiceException;
import Servicio.TareaService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VentanaEditarTarea extends JFrame {
    // Declaro las estructuras que voy a usar en la ventana
    private JTextField txtNuevoNombre;
    private JTextField txtNuevaDescripcion;
    private JTextField txtNuevoOwner;
    private JButton btnEditar;
    private TareaService tareaService;

    public VentanaEditarTarea(Integer idTarea) {

        // Setteo las especificaciones de la ventana
        setTitle("Editando tarea " + idTarea);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 150);
        setLayout(new GridLayout(4, 2));

        // Creo mi instancia de TareaService y especifico los componentes
        tareaService = new TareaService();

        // Configuro la ventana, sus componentes y su disposición

        JLabel lblNuevoNombre = new JLabel("Nuevo Nombre:");
        JLabel lblNuevaDescripcion = new JLabel("Nueva Descripcion:");

        txtNuevoNombre = new JTextField(20);
        txtNuevaDescripcion = new JTextField(20);
        btnEditar = new JButton("Editar");

        // Configuro el listener del boton
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nuevoNombre = txtNuevoNombre.getText();
                String nuevaDescripcion = txtNuevaDescripcion.getText();

                // Envuelvo en un try catch la llamada al service
                try {
                    tareaService.modificarTarea(new Tarea(idTarea, nuevoNombre, nuevaDescripcion));
                    JOptionPane.showMessageDialog(VentanaEditarTarea.this, "Tarea editada correctamente!");
                    dispose();
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(VentanaEditarTarea.this, "Error al editar la tarea");
                }
            }
        });

        // Agrego los componentes a la ventana
        add(lblNuevoNombre);
        add(txtNuevoNombre);
        add(lblNuevaDescripcion);
        add(txtNuevaDescripcion);
        add(btnEditar);
    }
}
