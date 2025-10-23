package Database;

import java.sql.*;
import java.util.ArrayList;

import Modelo.Pelicula;
import Modelo.GeneroPelicula;

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
	public ArrayList<Pelicula> listarPeliculas() throws SQLException {
		Statement stmt = connection.createStatement();
		ArrayList<Pelicula> listaPeliculas= new ArrayList<Pelicula>();
		ResultSet rs = stmt.executeQuery("SELECT * FROM PELICULA");

		while (rs.next()) {
		    int id = rs.getInt("ID");
		    String g = rs.getString("GENERO");
		    String titulo = rs.getString("TITULO");
		    String resumen = rs.getString("RESUMEN");
		    String director = rs.getString("DIRECTOR");
		    double duracion = rs.getInt("DURACION");
		    
		    GeneroPelicula genero = GeneroPelicula.valueOf(g);
		    Pelicula datos = new Pelicula(id, genero, titulo, resumen, director, duracion);
		    
		    listaPeliculas.add(datos);
		}
		return listaPeliculas;
	}

	//devuleva si una pelicula existe, pasandole un ID como parametro. si existe retorna true, si no false
	@Override
	public boolean validarPelicula(int id) throws SQLException{
			String sql= "SELECT COUNT(*) FROM PELICULAS WHERE ID=?;";
			PreparedStatement pstmt;
	    	pstmt = connection.prepareStatement(sql);
	    	boolean existe=false;  
	  		pstmt.setInt(1,id);
	        ResultSet rs=pstmt.executeQuery();
	        int cant=rs.getInt(1);
	        rs.close();
	        if(cant>0) existe=true;
	        pstmt.close();
			return existe;
	}

}

