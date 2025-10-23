package Database;

import java.util.ArrayList;

import Modelo.Resenia;

public interface ReseniaDAO {

	void cargarResenia(Resenia r);
	
	public Resenia descargarResenia(int ID);

	public ArrayList<Resenia> listarNoAprobadas();
		
	public ArrayList<Resenia> listarAprobadas();

	public boolean validarResenia(int ID);
		
	public void eliminarResenia(Resenia r);
	
	public void aprobarResenia(int ID);

}
