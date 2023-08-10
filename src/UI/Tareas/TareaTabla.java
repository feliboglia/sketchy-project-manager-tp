package UI.Tareas;

import Entidades.Tarea;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TareaTabla extends AbstractTableModel {
    private List<Tarea> tareas;
    private String[] columnNames = {"Id Tarea", "Id Proyecto", "Nombre", "Estado", "Descripcion", "Horas Estimadas"};

    public TareaTabla(List<Tarea> tareas) {
        this.tareas = tareas;
    }

    public TareaTabla() {
    }

    @Override
    public int getRowCount() {
        return tareas.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Tarea tarea = tareas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return tarea.getIdTarea();
            case 1:
                return tarea.getIdProyecto();
            case 2:
                return tarea.getNombre();
            case 3:
                return tarea.getEstado();
            case 4:
                return tarea.getDescripcion();
            case 5:
                return tarea.getEstimacion();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void setTareas(List<Tarea> nuevasTareas) {
        tareas = nuevasTareas;
        fireTableDataChanged();
    }
}
