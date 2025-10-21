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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void cargarDatos(DatosPersonales nuevosDatos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<DatosPersonales> listar() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//retorna true si existe la persona ya
	@Override
	public boolean validarPersona() {
		// TODO Auto-generated method stub
		return false;
	}

}
