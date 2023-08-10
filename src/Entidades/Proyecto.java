package Entidades;

public class Proyecto {
    // Atributos de Proyecto
    private Integer idProyecto;
    private String nombre;
    private String descripcion;
    private String owner;
    private String estado;
    private Float horasReales; //lo calculo entre las horas de todos los proyectos

    // Constructores
    public Proyecto(String nombre, String descripcion, String emailOwner){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.owner = emailOwner;
    }

    public Proyecto(Integer idProyecto, String nombre, String descripcion, String emailOwner, String estado){
        this.idProyecto = idProyecto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.owner = emailOwner;
        this.estado = estado;
    }

    public Proyecto(Integer idProyecto, String nombre, String descripcion, String emailOwner) {
        this.idProyecto = idProyecto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.owner = emailOwner;
    }

    // Getters
    public Integer getIdProyecto(){
        return idProyecto;
    }
    public String getNombre(){
        return nombre;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public String getOwner(){
        return owner;
    }
    public Float getHorasReales(){
        return horasReales;
    }
    public String getEstado(){
        return estado;
    }


    // Setters
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    public void setOwner(String emailOwner){
        this.owner = emailOwner;
    }
    public void setEstado(String estado){
        this.estado = estado;
    }

}
