package Database;

import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.Pelicula;

public interface PeliculaDAO {
	public void cargarPelicula(Pelicula p) throws SQLException;

	public ArrayList<Pelicula> listarPorNombre();

	public ArrayList<Pelicula> listarPorGenero();

	public ArrayList<Pelicula> listarPorDuracion();

	public ArrayList<Pelicula> listarSinOrden();

}
