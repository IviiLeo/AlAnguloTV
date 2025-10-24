package Database;

import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.Resenia;

public interface ReseniaDAO {

	void cargarResenia(Resenia r) throws SQLException;
	
	public Resenia descargarResenia(int ID) throws SQLException;

	public ArrayList<Resenia> listarNoAprobadas() throws SQLException;
		
	public ArrayList<Resenia> listarAprobadas() throws SQLException;

	public boolean validarResenia(int ID) throws SQLException;
		
	public void eliminarResenia(Resenia r) throws SQLException;

	void aprobarResenia(int iD);


}
