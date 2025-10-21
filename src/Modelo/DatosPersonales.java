package Modelo;

public class DatosPersonales {

    private int id;
    private String nombre;
    private String apellido;
    private int dni;

    public DatosPersonales() {
        this.id = 0;
        this.nombre = "N/A";
        this.apellido = "N/A";
        this.dni = 0; 
    }
    public DatosPersonales(int id, String nombre, String apellido, int dni) { 
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }



    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }
    
    @Override
    public String toString() {
        return ("DatosPersonales: Nombre='" + nombre + "', Apellido='" + apellido + "', DNI=" + dni);
    }
}