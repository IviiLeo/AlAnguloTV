package Database;

import java.sql.Connection;
import java.util.ArrayList;

import Modelo.DatosPersonales;

public class DatosPersonalesDAOjdbl implements DatosPersonalesDAO{
	private Connection conn;

	public DatosPersonalesDAOjdbl(Connection connection) {
		conn=connection;
	}

	@Override
	public boolean existeDNI() {
		//escribir codigo
		return true;
	}

	@Override
	public void cargarDatos(DatosPersonales nuevosDatos) {
		//escribir codigo
		
	}

	@Override
	public ArrayList<DatosPersonales> listar() {
		//escribir codigo
		return null;
	}
	
	//retorna true si existe la persona ya
	@Override
	public boolean validarPersona() {
		//escribir codigo
		return false;
	}

}
