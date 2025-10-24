package Database;

import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.DatosPersonales;

public interface DatosPersonalesDAO {
	//retorno true si existe
	boolean existeDNI(int dni) throws SQLException;

	void cargarDatos(DatosPersonales nuevosDatos) throws SQLException;

	ArrayList<DatosPersonales> listar() throws SQLException;

	boolean validarPersona(int id) throws SQLException;

}
