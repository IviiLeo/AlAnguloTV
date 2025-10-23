package Database;

import Modelo.Resenia;
import java.sql.*;
import java.util.ArrayList;

public class ReseniaDAOjdbl implements ReseniaDAO {
	private Connection conn;
	
	public ReseniaDAOjdbl(Connection c) {
		conn=c;
	}
	
	@Override
	public void cargarResenia(Resenia r) {
		// TODO Auto-generated method stub
		
	}
	
	public Resenia descargarResenia(int ID) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<Resenia> listarNoAprobadas(){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<Resenia> listarAprobadas(){
		// TODO Auto-generated method stub
		return null;
	}
	
	//retorna true si existe
	@Override
	public boolean validarResenia(int ID) {
		// TODO Auto-generated method stub
		return false;
	}

	public void eliminarResenia(Resenia r) {
		// TODO Auto-generated method stub
	}

}
