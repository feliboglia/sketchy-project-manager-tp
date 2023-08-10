package Entidades;

public class Tarea {

    // Atributos de la Tarea
    private Integer idTarea;
    private Integer idProyecto;
    private String nombre;
    private String estado;
    private String descripcion;
    private Float estimacion;

    // Constructores
    public Tarea(){
    }
    public Tarea(String nombre, String estado, String descripcion, Float estimacion){
        this.nombre = nombre;
        this.estado = estado;
        this.descripcion = descripcion;
        this.estimacion = estimacion;
    }

    public Tarea(String nombre) {
        this.nombre = nombre;
    }

    public Tarea(Integer idTarea, Integer idProyecto, String nombre, String estado, String descripcion, Float horasEstimadas) {
        this.idTarea = idTarea;
        this.idProyecto = idProyecto;
        this.nombre = nombre;
        this.estado = estado;
        this.descripcion = descripcion;
        this.estimacion = horasEstimadas;
    }

    public Tarea(Integer idProyecto, String nombre, String descripcion, Float horasEstimadas) {
        this.idProyecto = idProyecto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estimacion = horasEstimadas;
    }

    public Tarea(Integer idTarea, String nombre, String descripcion) {
        this.idTarea = idTarea;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Getters
    public Integer getIdTarea(){
        return idTarea;
    }
    public Integer getIdProyecto(){
        return idProyecto;
    }

    public String getNombre(){
        return nombre;
    }
    public String getEstado(){
        return estado;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public Float getEstimacion(){
        return estimacion;
    }

    // Setters
    public void setIdProyecto(Integer idProyecto){
        this.idProyecto = idProyecto;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setEstado(String estado){
        this.estado = estado;
    }
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    public void setEstimacion(Float estimacion) {
        this.estimacion = estimacion;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "id tarea='" + idTarea + '\'' +
                "id proyecto='" + idProyecto + '\'' +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado='" + estado + '\'' +
                ", horas estimadas='" + estimacion + '\'' +
                '}';
    }

}

