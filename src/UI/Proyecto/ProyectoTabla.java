package UI.Proyecto;

import Entidades.Proyecto;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ProyectoTabla extends AbstractTableModel {
    private List<Proyecto> proyectos;
    private String[] columnNames = {"Id Proyecto", "Nombre", "Descripcion", "Owner", "Estado"};

    public ProyectoTabla(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    public ProyectoTabla() {
    }

    @Override
    public int getRowCount() {
        return proyectos.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Proyecto proyecto = proyectos.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return proyecto.getIdProyecto();
            case 1:
                return proyecto.getNombre();
            case 2:
                return proyecto.getDescripcion();
            case 3:
                return proyecto.getOwner();
            case 4:
                return proyecto.getEstado();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void setProyectos(List<Proyecto> nuevosProyectos) {
        proyectos = nuevosProyectos;
        fireTableDataChanged();
    }
}
