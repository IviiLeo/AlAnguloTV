package Database;

import java.sql.*;
import java.util.*;

import Modelo.DatosPersonales;

public class DatosPersonalesDAOjdbl implements DatosPersonalesDAO{
	private Connection connection;

	public DatosPersonalesDAOjdbl(Connection conn) {
		connection=conn;
	}

	@Override
	public boolean existeDNI(int dni) throws SQLException {
		String sql= "SELECT COUNT(*) FROM DATOS_PERSONALES WHERE DNI=?;";
		PreparedStatement pstmt;
    	pstmt = connection.prepareStatement(sql);
    	boolean existe=false;    
  		pstmt.setInt(1,dni);
        ResultSet rs=pstmt.executeQuery();
        int cant=rs.getInt(1);
        rs.close();
        if(cant>0) existe=true;
        pstmt.close();
		return existe;
	}

	@Override
	public void cargarDatos(DatosPersonales nuevosDatos) throws SQLException {
		Statement stmt = connection.createStatement();
        /* String sql = "INSERT INTO DATOS_PERSONALES (NOMBRES, APELLIDO, DNI)" +
                     "VALUES ('" + nuevosDatos.getNombre()+ "', " +
                             "'" + nuevosDatos.getApellido() + "', " + 
                             "'" + nuevosDatos.getDni() + 
                             ");";
                        */
        String sql = "INSERT INTO DATOS_PERSONALES (NOMBRES, APELLIDO, DNI) VALUES (" +
                "'" + nuevosDatos.getNombre() + "', " +
                "'" + nuevosDatos.getApellido() + "', " +
                "'" + nuevosDatos.getDni() + "'" +
                ");";

        
        stmt.executeUpdate(sql);
        stmt.close();		
	}

	@Override
	public ArrayList<DatosPersonales> listar() throws SQLException {
		Statement stmt = connection.createStatement();
		ArrayList<DatosPersonales> listaPersonas= new ArrayList<DatosPersonales>();
		ResultSet rs = stmt.executeQuery("SELECT * FROM DATOS_PERSONALES");

		while (rs.next()) {
		    int id = rs.getInt("ID");
		    String nombre = rs.getString("NOMBRES");
		    String apellido = rs.getString("APELLIDO");
		    int dni = rs.getInt("DNI");
		    DatosPersonales datos = new DatosPersonales(id, nombre, apellido, dni);
		    
		    listaPersonas.add(datos);
		}
		return listaPersonas;
	}
	
	//retorna true si existe la persona ya
	@Override
	public boolean validarPersona(int id) throws SQLException{
		String sql= "SELECT COUNT(*) FROM DATOS_PERSONALES WHERE ID=?;";
		PreparedStatement pstmt;
    	pstmt = connection.prepareStatement(sql);
    	boolean existe=false;   
  		pstmt.setInt(1,id);
        ResultSet rs=pstmt.executeQuery();
        if (rs.next()) {
        	int cant=rs.getInt(1);
        	if(cant>0) existe=true;
        }
        rs.close();
        pstmt.close();
		return existe;
	}
	
	public DatosPersonales buscarPorID(int id) throws SQLException {
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM DATOS_PERSONALES");
		DatosPersonales datos = null;
		while (rs.next()) {
			int aux=rs.getInt("ID");
			if(aux==id) {
			  String nombre = rs.getString("NOMBRES");
		      String apellido = rs.getString("APELLIDO");
		      int dni = rs.getInt("DNI");
		   
		    datos = new DatosPersonales(id, nombre, apellido, dni);
			}
		}
		return datos;
	}
}