package Entidades;

public class Empleado {

    // Atributos del Empleado
    private String email;
    private String nombre;
    private String apellido;
    private String password;
    private Float costoPorHora;

    // Constructores
    public Empleado(){
    }

    public Empleado(String email, String nombre, String apellido, String password, Float costoPorHora){
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.costoPorHora = costoPorHora;
    }

    public Empleado(String email) {
        this.email = email;
    }

    public Empleado(String email, String nombre, String apellido) {
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    // Getters
    public String getNombre(){
        return nombre;
    }
    public String getApellido(){
        return apellido;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public Float getCostoPorHora(){
        return costoPorHora;
    }

    // Setters
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setApellido(String apellido){
        this.apellido = apellido;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setCostoPorHora(Float costoPorHora) {
        this.costoPorHora = costoPorHora;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "mail='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", costo por hora='" + costoPorHora + '\'' +
                '}';
    }
}
