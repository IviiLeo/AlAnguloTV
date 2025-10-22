package Modelo;
import java.time.LocalDateTime;

public class Resenia {

    private int id;                 
    private int calificacion;       
    private String comentario;      
    private boolean aprobado;       
    private LocalDateTime fechaHora; 
    private int idUsuario;          
    private int idPelicula;         
   
    public Resenia() {
        aprobado=false;
    }

    public Resenia(int id, int calificacion, String comentario, boolean aprobado, LocalDateTime fechaHora, int idUsuario, int idPelicula) {
        this.id = id;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.aprobado = aprobado;
        this.fechaHora = fechaHora;
        this.idUsuario = idUsuario;
        this.idPelicula = idPelicula;
    }

    public int getId() {
        return id;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    @Override
    public String toString() {
        return "Resenia{" +
                "id=" + id +
                ", calificacion=" + calificacion +
                ", comentario='" + comentario + '\'' +
                ", aprobado=" + aprobado +
                ", fechaHora=" + fechaHora +
                ", idUsuario=" + idUsuario +
                ", idPelicula=" + idPelicula +
                '}';
    }

	public String toStringSinID() {
		return ("Resenia [Usuario Nro ="+idUsuario+". calificacion ="+calificacion+". comentario = "+comentario+". Pelicula Nro = "+idPelicula);
	}
}
