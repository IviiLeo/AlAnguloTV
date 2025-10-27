package Database;

import java.sql.*;
import java.util.ArrayList;

import Modelo.*;

public interface UsuarioDAO {
	public void cargarUsuario(Usuario u) throws SQLException;
	public Usuario buscarPorNombreUsuario(String u) throws SQLException;
	//public ArrayList<Usuario> listarUsuarios();
	

	public boolean verificarNomUsuario(String u) throws SQLException;
	public boolean verificarMail(String m)throws SQLException;
	
	public ArrayList<Usuario> listarUsuarios() throws SQLException;
	public Usuario buscarPorID(int ID) throws SQLException;
			
}
