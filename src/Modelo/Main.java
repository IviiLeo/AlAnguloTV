package Modelo;

import java.sql.*;

public class Main {

	 public static void main(String[] args) throws SQLException {
		 Logica logica=new Logica();
		 logica.conectar();
		 logica.registrarDatosPersonales();
		 logica.registrarUsuario();
	  }
}