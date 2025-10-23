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

	        String sql = "INSERT INTO USUARIO (NOMBRE_USUARIO, EMAIL, CONTRASENIA, ID_DATOS_PERSONALES, PAIS)" +
	                     "VALUES ('" + u.getNombreUsuario() + "', " +
	                             "'" + u.getContrasenia() + "', " + 
	                             "'" + u.getEmail() + "', " + 
	                             "'" + u.getIdDatosPersonales() + "', " + 
	                                   u.getPais().toString() +
	                             ");";
	        
	        stmt.executeUpdate(sql);
	        stmt.close();	
			
	}
	
	//retorno los datos completos del usuario, si el nombre de usuario no existe retorna null;
	public Usuario buscarPorNombreUsuario(String user) throws SQLException {
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM USUARIO");
		Usuario usuario = new Usuario();
		while (rs.next()) {
			String nombreUsuario=rs.getString("NOMBRE_USUARIO");
			if(nombreUsuario.equals(user)) {
			  int id= rs.getInt("ID");
		      String contrasenia = rs.getString("CONTRASENIA");
		      String email = rs.getString("EMAIL");
		      int id_datos = rs.getInt("ID_DATOS_PERSONALES");
		      String p = rs.getString("PAIS");
		    
		    Pais pais = Pais.valueOf(p);
		    usuario = new Usuario(nombreUsuario,contrasenia, email, id, id_datos, pais);
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
		ResultSet rs = stmt.executeQuery("SELECT * FROM USUARIO");
		    
		while (rs.next()) {
			int id= rs.getInt("ID");
			String nombreUsuario=rs.getString("NOMBRE_USUARIO");
			String contrasenia = rs.getString("CONTRASENIA");
		    String email = rs.getString("EMAIL");
		    int id_datos = rs.getInt("ID_DATOS_PERSONALES");
		    String p = rs.getString("PAIS");
		      
		    Pais pais = Pais.valueOf(p);
		    Usuario datos = new Usuario(nombreUsuario,contrasenia, email, id, id_datos, pais);
		    
		    listaUsuarios.add(datos);
		}
		return listaUsuarios;
	}
	
}

