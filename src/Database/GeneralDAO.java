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
	public boolean existeNombreUsuario(String u) {
		if(user.verificarNomUsuario(u)) return true;
		return false;
	}
	
	
	//true si existe
	public boolean existeMail(String m) {
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
                listaPeliculas = pelicula.listarPorNombre(); 
                break;
            case 2:
                listaPeliculas = pelicula.listarPorGenero();
                break;
            case 3:
                listaPeliculas = pelicula.listarPorDuracion();
                break;
            case 4:
                listaPeliculas = pelicula.listarSinOrden();
                break;
            default:
                System.out.println("⚠Opción de ordenación no válida. Se devolverá una lista vacía.");
        }
        
        return listaPeliculas;
    }

	public boolean validarDNI(int dni) {
		if (dp.existeDNI()) return true;
		return false;
	}

	public void guardarDatosPersonales(DatosPersonales nuevosDatos) {
		dp.cargarDatos(nuevosDatos);		
	}
	
	
	public ArrayList<DatosPersonales> listarDatosPersonales() {
		ArrayList<DatosPersonales> lista=dp.listar();
		return lista;
	}
	
	//retorna true si existe
	public boolean existePersona() {
		if(dp.validarPersona()) return true;
		return false;
	}

	public Usuario buscarUsarioPorNombre(String nombreUsuario) {
		Usuario u=user.buscarPorNombreUsuario(nombreUsuario);
		return u;
	}
	
	//devuleva si una pelicula existe, pasandole un ID como parametro
	public boolean existePelicula(int ID) {
		return (pelicula.validarPelicula(ID));
	}

	public void guardarResenia(Resenia r) {
		r.setFechaHora(LocalDateTime.now());
		resenia.cargarResenia(r);
		
	}
}
	
