package UI.Empleado;

import Entidades.Empleado;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class EmpleadoTabla extends AbstractTableModel {
    private List<Empleado> empleados;
    private String[] columnNames = {"Email", "Nombre", "Apellido", "Costo por Hora"};

    public EmpleadoTabla(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public EmpleadoTabla() {
    }

    @Override
    public int getRowCount() {
        return empleados.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Empleado empleado = empleados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return empleado.getEmail();
            case 1:
                return empleado.getNombre();
            case 2:
                return empleado.getApellido();
            case 3:
                return empleado.getCostoPorHora();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void setEmpleados(List<Empleado> nuevosEmpleados) {
        empleados = nuevosEmpleados;
        fireTableDataChanged();
    }
}
