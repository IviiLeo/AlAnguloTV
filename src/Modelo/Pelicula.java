package Modelo;

public class Pelicula {

    private int id;
    private GeneroPelicula genero;
    private String titulo;
    private String resumen;
    private String director;
    private double duracion;

    public Pelicula() {
        this.id = 0;
        this.genero = GeneroPelicula.OTROS; // Valor por defecto
        this.titulo = "Sin título";
        this.resumen = "Sin resumen";
        this.director = "Desconocido";
        this.duracion = 0.0;
    }

    public Pelicula(int id, GeneroPelicula genero, String titulo, String resumen, String director, double duracion) {
        this.id = id;
        this.genero = genero;
        this.titulo = titulo;
        this.resumen = resumen;
        this.director = director;
        this.duracion = duracion;
    }

    // 5. Métodos Getters y Setters para todas las variables
    
    // Getters
    public int getId() {
        return id;
    }

    public GeneroPelicula getGenero() {
        return genero;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumen() {
        return resumen;
    }

    public String getDirector() {
        return director;
    }

    public double getDuracion() {
        return duracion;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setGenero(GeneroPelicula genero) {
        this.genero = genero;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    //Método toString para facilitar la impresión del objeto
    @Override
    public String toString() {
        return "Pelicula =" + titulo + ", Género=" + genero + 
               ", Director= " + director + ", Duración= " + duracion + " min, Resumen=" + 
               (resumen.length() > 50 ? resumen.substring(0, 47) + "..." : resumen) + "\n";
    }
}