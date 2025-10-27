package Database;

import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.Pelicula;

public interface PeliculaDAO {
	public void cargarPelicula(Pelicula p) throws SQLException;

	public ArrayList<Pelicula> listarPeliculas() throws SQLException;

	public boolean validarPelicula(int id) throws SQLException;
	public Pelicula buscarPorID(int ID) throws SQLException;
}
