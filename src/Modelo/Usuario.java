package Modelo;

public class Usuario {

    private String nombreUsuario;
    private String contrasenia;
    private String email;
    private int idUsuario;        
    private DatosPersonales datosP;         

    public Usuario() {
        this.nombreUsuario = "sin_nombre";
        this.contrasenia = "sin_clave";
        this.email = "sin_email";
        this.idUsuario = -1;
        this.datosP = new DatosPersonales();
    }

    public Usuario(String nombreUsuario, String contrasenia, String email, int idUsuario, DatosPersonales
    		datosPersonales) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.email = email;
        this.idUsuario = idUsuario;
        this.datosP = datosPersonales;
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

    public DatosPersonales getDatosPersonales() {
        return datosP;
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

    public void setDatosPersonales(DatosPersonales datosPersonales) {
        this.datosP = datosPersonales;
    }
   
    // Opcional: Método toString
    @Override
    public String toString() {
        return "Usuario [ID=" + idUsuario + ", User='" + nombreUsuario + "', Email='" + email+ ", "+ datosP.toString() + "]";
    }
}


