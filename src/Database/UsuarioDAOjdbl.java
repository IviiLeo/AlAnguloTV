package Database;

import java.sql.*;

import Modelo.*;

public class UsuarioDAOjdbl implements UsuarioDAO{
	private Connection connection;

	public UsuarioDAOjdbl(Connection conn) {
		connection=conn;
	}
	
	@Override
	public void cargarUsuario(Usuario u) throws SQLException {
			
	}
	
	//retorno los datos completos del usuario
	public Usuario buscarPorNombreUsuario(String user) {
		Usuario u= new Usuario();
		return u;
	}
	
	//retorna una lista de los usuarios
	//retorna true si existe
	public boolean verificarNomUsuario(String u) {
		return false;
	}
	//retorna true si existe
	public boolean verificarMail(String m) {
		return false;
	}
	
}
