package UI.TareaEmpleado;

import Entidades.Proyecto;
import Excepciones.ServiceException;
import Servicio.ProyectoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VentanaEditarTareaEmpleado extends JFrame {
    // Declaro las estructuras que voy a usar en la ventana
    private JTextField txtNuevoNombre;
    private JTextField txtNuevaDescripcion;
    private JTextField txtNuevoOwner;
    private JButton btnEditar;
    private ProyectoService proyectoService;

    public VentanaEditarTareaEmpleado(Integer idProyecto) {

        // Setteo las especificaciones de la ventana
        setTitle("Editando proyecto " + idProyecto);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 150);
        setLayout(new GridLayout(4, 2));

        // Creo mi instancia de EmpleadoService y especifico los componentes
        proyectoService = new ProyectoService();
        List<Proyecto> unProyecto = new ArrayList<>();

        try {
            unProyecto = proyectoService.verProyecto(idProyecto);
            System.out.println(unProyecto.get(0).getNombre());

        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener el proyecto.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }

        if (unProyecto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Proyecto no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Proyecto proyecto = unProyecto.get(0); // Obtener el primer (y único) proyecto de la lista
        System.out.println(proyecto.getNombre());
        System.out.println(proyecto.getIdProyecto());
        System.out.println(proyecto.getDescripcion());


        // Configuro la ventana, sus componentes y su disposición

        JLabel lblNuevoNombre = new JLabel("Nuevo Nombre:");
        JLabel lblNuevaDescripcion = new JLabel("Nueva Descripcion:");
        JLabel lblNuevoOwner = new JLabel("Nuevo Owner:");
        JSeparator separadorHorizontal = new JSeparator(SwingConstants.HORIZONTAL);

        txtNuevoNombre = new JTextField(20);
        txtNuevaDescripcion = new JTextField(20);
        txtNuevoOwner = new JTextField(20);
        txtNuevoNombre.setText(proyecto.getNombre());
        txtNuevaDescripcion.setText(proyecto.getDescripcion());
        txtNuevoOwner.setText(proyecto.getOwner());
        btnEditar = new JButton("Editar");

        // Configuro el listener del boton
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nuevoNombre = txtNuevoNombre.getText();
                String nuevaDescripcion = txtNuevaDescripcion.getText();
                String nuevoOwner = txtNuevoOwner.getText();

                // Envuelvo en un try catch la llamada al service
                try {
                    proyectoService.modificarProyecto(new Proyecto(idProyecto, nuevoNombre, nuevaDescripcion, nuevoOwner));
                    JOptionPane.showMessageDialog(VentanaEditarTareaEmpleado.this, "Proyecto editado correctamente");
                    dispose();
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(VentanaEditarTareaEmpleado.this, "Error al editar el proyecto, ese owner existe? ");
                }
            }
        });

        /*
        // Agrego los componentes a la ventana
        add(lblNuevoNombre);
        add(txtNuevoNombre);
        //add(separadorHorizontal);
        add(lblNuevaDescripcion);
        add(txtNuevaDescripcion);
        add(lblNuevoOwner);
        add(txtNuevoOwner);
        add(btnEditar);

         */
    }
}
