package Entidades;

public class TareaEmpleado {
    // Atributos de TareaEmpleado
    private Integer idTareaEmpleado;
    private Integer idTarea;
    private String empleadoAsignado;
    private Float horasReales;

    // Constructores
    public TareaEmpleado(){
    }

    public TareaEmpleado(Integer idTarea, String emailEmpleado, Float horasReales){
        this.idTarea = idTarea;
        this.empleadoAsignado = emailEmpleado;
        this.horasReales = horasReales;
    }

    public TareaEmpleado(Integer idTarea, String emailEmpleado) {
        this.idTarea = idTarea;
        this.empleadoAsignado = emailEmpleado;
    }

    public TareaEmpleado(Integer idTarea) {
        this.idTarea = idTarea;
    }

    // Getters
    public Integer getIdTareaEmpleado(){
        return idTareaEmpleado;
    }
    public Integer getIdTarea(){
        return idTarea;
    }
    public String getEmpleadoAsignado(){
        return empleadoAsignado;
    }
    public Float getHorasReales(){
        return horasReales;
    }


    // Setters
    public void setIdTarea(Integer idTarea){
        this.idTarea = idTarea;
    }
    public void setEmpleadoAsignado(String empleadoAsignado){
        this.empleadoAsignado = empleadoAsignado;
    }
    public void setHorasReales(Float horasReales) {
        this.horasReales = horasReales;
    }

}
