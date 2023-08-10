package UI;

import UI.Empleado.VentanaPrincipalEmpleado;
import javax.swing.*;

public class PanelManager {
    public static void main() {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal VentanaPrincipal = new VentanaPrincipal();
            VentanaPrincipal.setVisible(true);
        });
    }
}
