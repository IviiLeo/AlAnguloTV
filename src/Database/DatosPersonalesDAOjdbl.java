package Database;

import java.sql.Connection;
import java.util.ArrayList;

import Modelo.DatosPersonales;

public class DatosPersonalesDAOjdbl implements DatosPersonalesDAO{
	private Connection conn;

	public DatosPersonalesDAOjdbl(Connection connection) {
		conn=connection;
	}

	//retorna true si ya existe el dni
	@Override
	public boolean existeDNI() {
		//escribir codigo
		return true;
	}
	
	//carga datos personales
	@Override
	public void cargarDatos(DatosPersonales nuevosDatos) {
		//escribir codigo
		
	}
	
	//ordenado por id
	@Override
	public ArrayList<DatosPersonales> listar() {
		//escribir codigo
		return null;
	}
	
	//retorna true si existe la persona, busca en base al id
	@Override
	public boolean validarPersona(int ID) {
		//escribir codigo
		return false;
	}

}
