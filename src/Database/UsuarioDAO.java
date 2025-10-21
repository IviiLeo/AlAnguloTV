package Database;

import java.sql.*;

import Modelo.*;

public interface UsuarioDAO {
	public void cargarUsuario(Usuario u) throws SQLException;
	public Usuario buscarPorNombreUsuario(String u);
	//public ArrayList<Usuario> listarUsuarios();
	

	public boolean verificarNomUsuario(String u);
	public boolean verificarMail(String m);
		
			
}
