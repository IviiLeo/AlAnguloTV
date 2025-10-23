package Database;
import java.io.File;
import java.sql.*;

public class DatabaseDAOjdbl implements DatabaseDAO{
	private static Connection connection;
	private static Statement stmt;
	
	@Override
	public void iniciar() throws SQLException {
		try {
            // Carpeta dentro del proyecto
            File folder = new File("BaseDatos");
            if (!folder.exists()) {
                folder.mkdirs(); // crea la carpeta si no existe
            }

            // Ruta relativa al archivo
            connection= DriverManager.getConnection("jdbc:sqlite:BaseDatos\\plataforma.db");
            stmt = connection.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al conectar a la base de datos.");
        }

		crearTablas();
	}
	
	private void crearTablas() throws SQLException
	{
        String sqlDatosPersonales =
            " CREATE TABLE IF NOT EXISTS DATOS_PERSONALES (" +
            "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "NOMBRES TEXT(100) NOT NULL," +
            "APELLIDO TEXT(100) NOT NULL," +
            "DNI INTEGER NOT NULL" +
            " );";
        stmt.executeUpdate(sqlDatosPersonales);

        String sqlPelicula =
            " CREATE TABLE IF NOT EXISTS PELICULA (" +
            "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "GENERO TEXT(1) NOT NULL," +
            "TITULO TEXT(100) NOT NULL," +
            "RESUMEN TEXT(500)," +
            "DIRECTOR TEXT(100) NOT NULL," +
            "DURACION REAL NOT NULL" +
            " );";
        stmt.executeUpdate(sqlPelicula);

        String sqlUsuario =
                " CREATE TABLE IF NOT EXISTS USUARIO (" +
                "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "NOMBRE_USUARIO TEXT NOT NULL," +
                "CONTRASENIA TEXT NOT NULL," +
                "EMAIL TEXT NOT NULL, " +
                "ID_DATOS_PERSONALES INTEGER NOT NULL," +
                "CONSTRAINT USUARIO_DATOS_PERSONALES_FK FOREIGN KEY (ID_DATOS_PERSONALES) REFERENCES DATOS_PERSONALES(ID)" +
                "PAIS TEXT(1) NOT NULL," +
                " );";
            stmt.executeUpdate(sqlUsuario);

        String sqlResenia =
            " CREATE TABLE IF NOT EXISTS RESENIA (" +
            "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "CALIFICACION INTEGER NOT NULL," +
            "COMENTARIO TEXT(500)," +
            "APROBADO INTEGER DEFAULT (1) NOT NULL," +
            "FECHA_HORA DATETIME NOT NULL," +
            "ID_USUARIO INTEGER NOT NULL," +
            "ID_PELICULA INTEGER NOT NULL," +
            "CONSTRAINT RESENIA_USUARIO_FK FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(ID)," +
            "CONSTRAINT RESENIA_PELICULA_FK FOREIGN KEY (ID_PELICULA) REFERENCES PELICULA(ID)" +
            " );";
        stmt.executeUpdate(sqlResenia);

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
