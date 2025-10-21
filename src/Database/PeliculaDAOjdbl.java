package Database;

import java.sql.*;
import java.util.ArrayList;

import Modelo.Pelicula;

public class PeliculaDAOjdbl implements PeliculaDAO {
	private Connection connection;

	public PeliculaDAOjdbl(Connection conn) {
		connection=conn;
	}
	public void cargarPelicula(Pelicula p) throws SQLException {
		Statement stmt = connection.createStatement();
        String sql = "INSERT INTO PELICULA (GENERO, TITULO, RESUMEN, DIRECTOR, DURACION)" +
                     "VALUES ('" + p.getGenero().toString() + "', " +
                             "'" + p.getTitulo() + "', " + 
                             "'" + p.getResumen() + "', " + 
                             "'" + p.getDirector() + "', " + 
                                   p.getDuracion() +
                             ");";
        
        stmt.executeUpdate(sql);
        stmt.close();		
	}
	
	@Override
	public ArrayList<Pelicula> listarPorNombre() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<Pelicula> listarPorGenero() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<Pelicula> listarPorDuracion() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<Pelicula> listarSinOrden() {
		// TODO Auto-generated method stub
		return null;
	}

}
