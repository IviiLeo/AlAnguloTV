package Modelo;

import java.sql.*;
import java.util.Scanner;

public class Main {

	 public static void main(String[] args) throws SQLException {
		 Logica logica=new Logica();
		 logica.conectar();
		 Scanner scanner = new Scanner(System.in);
	     int opcion = 0;
	     do {
	    	 mostrarMenu();
		     System.out.print("Ingrese una opción: ");
	         opcion = Integer.parseInt(scanner.nextLine());

	         switch (opcion) {
	             case 1:
	                 logica.registrarDatosPersonales();
	                 break;
	             case 2:
	                 logica.registrarUsuario();
	                 break;
	             case 3:
	                 logica.registrarPelicula();
	                 break;
	             case 4:
	                 logica.registrarResenia();
	                 break;
	             case 5:
	                 logica.listarPeliculas();
	                 break;
	             case 6:
	                 logica.aprobarResenia();
	                 break;
	             case 7:
	                 logica.listarUsuarios();
	                 break;
	             case 0:
	                 System.out.println("Saliendo del programa. ¡Hasta pronto!");
	                 break;
	             default:
	                 System.out.println("Opción inválida. Intente de nuevo.");
	         }
	     }while (opcion != 0);
	     
         scanner.close();
		 logica.desconectar();
		 
	  }
	  
	 public static void mostrarMenu() {
	        System.out.println("========== MENÚ DE OPCIONES ==========");
	        System.out.println("1. Registrar Datos Personales");
	        System.out.println("2. Registrar Usuario");
	        System.out.println("3. Registrar Película");
	        System.out.println("4. Registrar Reseña");
	        System.out.println("5. Listar Películas");
	        System.out.println("6. Aprobar Reseña");
	        System.out.println("7. Listar Usuarios");
	        System.out.println("----------------------------------------");
	        System.out.println("0. Salir");
	        System.out.println("========================================");
	    }
}