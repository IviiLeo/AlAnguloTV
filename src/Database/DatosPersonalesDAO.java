package Database;

import java.util.ArrayList;

import Modelo.DatosPersonales;

public interface DatosPersonalesDAO {
	//retorno true si existe
	boolean existeDNI();

	void cargarDatos(DatosPersonales nuevosDatos);

	ArrayList<DatosPersonales> listar();

	boolean validarPersona();

}
