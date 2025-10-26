package Database;

import java.sql.*;
import java.util.ArrayList;

import Modelo.*;

public class UsuarioDAOjdbl implements UsuarioDAO{
	private Connection connection;

	public UsuarioDAOjdbl(Connection conn) {
		connection=conn;
	}
	
	@Override
	public void cargarUsuario(Usuario u) throws SQLException {
			Statement stmt = connection.createStatement();

	        String sql = "INSERT INTO USUARIO (NOMBRE_USUARIO, EMAIL, CONTRASENIA, ID_DATOS_PERSONALES)" +
	                     "VALUES ('" + u.getNombreUsuario() + "', " +
	                             "'" + u.getContrasenia() + "', " + 
	                             "'" + u.getEmail() + "', " + 
	                             u.getDatosPersonales().getId() +  
	                             ");";
	        
	        stmt.executeUpdate(sql);
	        stmt.close();	
			
	}
	
	//retorno los datos completos del usuario, si el nombre de usuario no existe retorna null;
	public Usuario buscarPorNombreUsuario(String user) throws SQLException {
		Statement stmt = connection.createStatement();		    
		ResultSet rs = stmt.executeQuery("SELECT * FROM USUARIO INNER JOIN DATOS_PERSONALES WHERE ID_DATOSPERSONALES=ID");
		Usuario usuario = null;
		
		while (rs.next()) {
			String nombreUsuario=rs.getString("NOMBRE_USUARIO");
			if(nombreUsuario.equals(user)) {
			  int id= rs.getInt("ID");
		      String contrasenia = rs.getString("CONTRASENIA");
		      String email = rs.getString("EMAIL");
		      int id_datos = rs.getInt("ID_DATOS_PERSONALES");
			  String nombre=rs.getString("DATOS_PERSONALES.NOMBRES");
		      String apellido = rs.getString("DATOS_PERSONALES.APELLIDO");
			  int dni = rs.getInt("DATOS_PERSONALES.DNI");
				
		      DatosPersonales persona=new DatosPersonales(id_datos,nombre, apellido, dni);
	->>		  Usuario datos = new Usuario(nombreUsuario,contrasenia, email, id, persona);
			}
		}
		return usuario;
	}

	
	//retorna true si existe
	public boolean verificarNomUsuario(String u) throws SQLException{
		String sql= "SELECT COUNT(*) FROM USUARIO WHERE NOMBRE_USUARIO=?;";
		PreparedStatement pstmt;
    	pstmt = connection.prepareStatement(sql);
    	boolean existe=false;  
  		pstmt.setString(1,u);
        ResultSet rs=pstmt.executeQuery();
        int cant=rs.getInt(1);
        rs.close();
        if(cant>0) existe=true;
        pstmt.close();
		return existe;
	}
	//retorna true si existe
	public boolean verificarMail(String m) throws SQLException{
		String sql= "SELECT COUNT(*) FROM USUARIO WHERE EMAIL=?;";
		PreparedStatement pstmt;
    	pstmt = connection.prepareStatement(sql);
    	boolean existe=false;  
  		pstmt.setString(1,m);
        ResultSet rs=pstmt.executeQuery();
        int cant=rs.getInt(1);
        rs.close();
        if(cant>0) existe=true;
        pstmt.close();
		return existe;
	}
	
	//retorna una lista de los usuarios
	public ArrayList<Usuario> listarUsuarios() throws SQLException {
		Statement stmt = connection.createStatement();
		ArrayList<Usuario> listaUsuarios= new ArrayList<Usuario>();
		ResultSet rs = stmt.executeQuery("SELECT * FROM USUARIO INNER JOIN DATOS_PERSONALES WHERE ID_DATOSPERSONALES=ID");
		    
		while (rs.next()) {
			int id= rs.getInt("ID");
			String nombreUsuario=rs.getString("NOMBRE_USUARIO");
			String contrasenia = rs.getString("CONTRASENIA");
		    String email = rs.getString("EMAIL");
		    int id_datos = rs.getInt("ID_DATOS_PERSONALES");
		    String nombre=rs.getString("DATOS_PERSONALES.NOMBRES");
			String apellido = rs.getString("DATOS_PERSONALES.APELLIDO");
			int dni = rs.getInt("DATOS_PERSONALES.DNI");
			
			DatosPersonales persona=new DatosPersonales(id_datos,nombre, apellido, dni);
		    Usuario datos = new Usuario(nombreUsuario,contrasenia, email, id, persona);
		    
		    listaUsuarios.add(datos);
		}
		return listaUsuarios;
	}

	
}


