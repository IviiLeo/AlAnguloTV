package Modelo;

public class Usuario {

    private String nombreUsuario;
    private String contrasenia;
    private String email;
    private int idUsuario;        
    private int idDatosPersonales;         

    public Usuario() {
        this.nombreUsuario = "sin_nombre";
        this.contrasenia = "sin_clave";
        this.email = "sin_email";
        this.idUsuario = 0;
        this.idDatosPersonales = 0;
    }

    public Usuario(String nombreUsuario, String contrasenia, String email, int idUsuario, int idDatosPersonales) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.email = email;
        this.idUsuario = idUsuario;
        this.idDatosPersonales = idDatosPersonales;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getEmail() {
        return email;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getIdDatosPersonales() {
        return idDatosPersonales;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdDatosPersonales(int idDatosPersonales) {
        this.idDatosPersonales = idDatosPersonales;
    }
    
    // Opcional: Método toString
    @Override
    public String toString() {
        return "Usuario [ID=" + idUsuario + ", User='" + nombreUsuario + "', Email='" + email + ", ID_DatosPers=" + idDatosPersonales + "]";
    }
}

