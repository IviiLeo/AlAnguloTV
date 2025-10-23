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
	
	//todos los listar deben devolver la tabla completa(ordenada) con id, nombre, etc. 
	//para ordenar usa el comparator, anda a chequear que carajo es eso (lo pidieron en calse)
	
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
	//sin orden me refiero a ordenado por id
	@Override
	public ArrayList<Pelicula> listarSinOrden() {
		// TODO Auto-generated method stub
		return null;
	}
	//devuleva si una pelicula existe, pasandole un ID como parametro. si existe retorna true, si no false
	@Override
	public boolean validarPelicula(int iD) {
		// TODO Auto-generated method stub
		return false;
	}

}
