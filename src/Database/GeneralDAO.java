package Database;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import Modelo.*;

public class GeneralDAO {
	private DatabaseDAO database;
	private DatosPersonalesDAO dp;
	private UsuarioDAO user;
	private PeliculaDAO pelicula;
	private ReseniaDAO resenia;
	//private ReseniaDAO resenia;
	
	public GeneralDAO() {
		database=new DatabaseDAOjdbl();
	}
	
	public void conectar() throws SQLException {
		database.iniciar();
		Connection conn=database.getConnection();
		dp=new DatosPersonalesDAOjdbl(conn);
		user=new UsuarioDAOjdbl(conn);
		pelicula=new PeliculaDAOjdbl(conn);
		resenia=new ReseniaDAOjdbl(conn);
	}
	
	public void desconectar() throws SQLException {
		database.apagar();
	}
	
	
	//true si existe
	public boolean existeNombreUsuario(String u) throws SQLException {
		if(user.verificarNomUsuario(u)) return true;
		return false;
	}
	
	//true si existe
	public boolean existeMail(String m) throws SQLException {
		if(user.verificarMail(m)) return true;
		return false;
	}
	
	public void guardarUsuario(Usuario u) throws SQLException {
		user.cargarUsuario(u);
	}
	
	public void guardarPelicula(Pelicula p) throws SQLException {
		pelicula.cargarPelicula(p);
	}
	
	public ArrayList<Pelicula> listarPeliculas(int opcion) {
		ArrayList<Pelicula> listaPeliculas = new ArrayList<>(); 

        switch (opcion) {
            case 1: 
                //nombre
                break;
            case 2:
                //genero
                break;
            case 3:
                //duracion
                break;
            case 4:
            	//sin orden:ordenado por id
                break;
            default:
                System.out.println("Opción de ordenación no válida.");
        }
        
        return listaPeliculas;
    }

	public boolean validarDNI(int dni) throws SQLException {
		if (dp.existeDNI(dni)) return true;
		return false;
	}

	public void guardarDatosPersonales(DatosPersonales nuevosDatos) throws SQLException {
		dp.cargarDatos(nuevosDatos);		
	}
	
	
	public ArrayList<DatosPersonales> listarDatosPersonales() throws SQLException {
		ArrayList<DatosPersonales> lista=dp.listar();
		return lista;
	}
	
	//retorna true si existe
	public boolean existePersona(int idPersona) throws SQLException {
		if(dp.validarPersona(idPersona)) return true;
		return false;
	}

	public Usuario buscarUsuarioPorNombre(String nombreUsuario) throws SQLException {
		Usuario u=user.buscarPorNombreUsuario(nombreUsuario);
		return u;
	}
	
	//devuleva si una pelicula existe, pasandole un ID como parametro
	public boolean existePelicula(int ID) throws SQLException {
		return (pelicula.validarPelicula(ID));
	}

	public void guardarResenia(Resenia r) throws SQLException {
		r.setFechaHora(LocalDateTime.now());
		resenia.cargarResenia(r);
		
	}
	
	public ArrayList<Resenia> listarReseniasNoAprobadas() throws SQLException {
		ArrayList<Resenia> lista=resenia.listarNoAprobadas();
		return lista;
	}
	
	public boolean existeResenia(int ID) throws SQLException {
		return (resenia.validarResenia(ID));
	}
	
	public Resenia descargarResenia(int ID) throws SQLException {
		return (resenia.descargarResenia(ID));
	}
	
	public void actualizarReseniaAprobada(int ID) throws SQLException {
		resenia.aprobarResenia(ID);
	}

	
	public void borrarResenia(Resenia r) throws SQLException {
		resenia.eliminarResenia(r);
	}
	
	public ArrayList<Usuario> listarUsuarios(int opcion){
		ArrayList<Usuario> listaUsuarios = new ArrayList<>();
		switch (opcion) {
			case 1:
				// nombre de usuario
				break;
			case 2:
				// email
				break;
			default:
				System.out.println("Opción de ordenación no válida.");
		}
		return listaUsuarios;
	}


}
	
