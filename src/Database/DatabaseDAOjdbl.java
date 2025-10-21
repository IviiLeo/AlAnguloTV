package Database;
import java.sql.*;

public class DatabaseDAOjdbl implements DatabaseDAO{
	private static Connection connection;
	private static Statement stmt;
	
	@Override
	public void iniciar() throws SQLException {
		connection= DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ASUS\\eclipse-workspace\\TDLtp2\\plataforma.db");
		stmt = connection.createStatement();
		crearTablas();
	}
	
	private void crearTablas() throws SQLException
	{
	String sql=" DROP TABLE IF EXISTS USUARIO; ";
	stmt.executeUpdate(sql);  
	
	 sql=" CREATE TABLE IF NOT EXISTS USUARIO ("+
	"ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
	"NOMBRES TEXT(100) NOT NULL,"+
	"APELLIDO TEXT(100) NOT NULL,"+
	"PAIS TEXT(100) NOT NULL,"+
	"EMAIL TEXT NOT NULL,"+
	"FECHANACIMIENTO DATETIME NOT NULL,"+
    "IDIOMA TEXT(100) NOT NULL,"+
	"NOMBRE_USUARIO TEXT NOT NULL,"+
	"CONTRASENIA TEXT NOT NULL"+
	");";
	
	stmt.executeUpdate(sql);
	sql=" CREATE TABLE IF NOT EXISTS PELICULA ("+
	"ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
	"GENERO TEXT(1) NOT NULL,"+
	"TITULO TEXT(100) NOT NULL,"+
	"RESUMEN TEXT(500),"+
	"DIRECTOR TEXT(100) NOT NULL,"+
	"DURACION REAL NOT NULL"+
	");";
	
	stmt.executeUpdate(sql);
	sql=" CREATE TABLE IF NOT EXISTS RESENIA ("+
	"ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
	"CALIFICACION INTEGER NOT NULL,"+
	"COMENTARIO TEXT(500),"+
	"APROBADO INTEGER DEFAULT (1) NOT NULL,"+
	"FECHA_HORA DATETIME NOT NULL,"+
	"ID_USUARIO INTEGER NOT NULL,"+
	"ID_PELICULA INTEGER NOT NULL,"+
	"CONSTRAINT RESENIA_USUARIO_FK FOREIGN KEY (ID_USUARIO)	REFERENCES USUARIO(ID),"+
	"CONSTRAINT RESENIA_PELICULA_FK FOREIGN KEY (ID_PELICULA) REFERENCES PELICULA(ID)"+
	");";
	stmt.executeUpdate(sql);
	stmt.close();
	}
	
	public void apagar() throws SQLException {
		connection.close();
	}
	
	public Connection getConnection() {
		return connection;
	}
	
/*	public void agregarUsuario(String nombre, String apellido, String pais, String correo, Fecha fechaNacimiento, String idioma, String user, String contrasena) {
		String sql=" INSERT INTO USUARIO (NOMBRES, APELLIDO, PAIS, EMAIL, FECHANACIMIENTO, IDIOMA, NOMBRE_USUARIO, CONTRASENIA)" +
	    		   "VALUES ('" + nombre + "', '" + apellido + "', '" + pais + "', '" + correo + "', '"+ fechaNacimiento.toString() + "', '" + idioma + "', '" + user + "', '" + contrasena + "');";
	     stmt.executeUpdate(sql);
		 stmt.close();
	}*/

}
