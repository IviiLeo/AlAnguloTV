package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import Database.*;

public class Logica {
	private Factory fabrica;
	public Logica() {
		fabrica=new Factory();
	}
	
	public void conectar() throws SQLException {
		fabrica.conectar();
	}
	public void desconectar() throws SQLException{
		fabrica.desconectar();
	}
	
	public void registrarDatosPersonales() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        DatosPersonales nuevosDatos = new DatosPersonales(); 
        boolean datosCompletos = false;
            
        while (!datosCompletos) {
                System.out.println("\n--- INGRESO DE DATOS PERSONALES ---");
                
                String nombre;
                boolean nombreValido;
                do {
                    nombreValido = true;
                    System.out.print("Ingrese Nombre: ");
                    nombre = scanner.nextLine().trim();
                    
                    // Lógica de validación de letras integrada aquí
                    if (nombre.isEmpty()) {
                        System.out.println("Error: Nombre no puede estar vacío.");
                        nombreValido = false;
                    } else if (!nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
                        System.out.println("Error: Nombre solo debe contener letras.");
                        nombreValido = false;
                    }
                } while (!nombreValido);
                nuevosDatos.setNombre(nombre);

                String apellido;
                boolean apellidoValido;
                do {
                    apellidoValido = true;
                    System.out.print("Ingrese Apellido: ");
                    apellido = scanner.nextLine().trim();
                    
                    // Lógica de validación de letras integrada aquí
                    if (apellido.isEmpty()) {
                        System.out.println("Error: Apellido no puede estar vacío.");
                        apellidoValido = false;
                    } else if (!apellido.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
                        System.out.println("Error: Apellido solo debe contener letras.");
                        apellidoValido = false;
                    }
                } while (!apellidoValido);
                nuevosDatos.setApellido(apellido);

                int dni = 0;
                boolean dniValido = false;
                while (!dniValido) {
                    System.out.print("Ingrese DNI (solo números): ");
                    
                    try {
                        String inputDni = scanner.nextLine().trim();
                        if (inputDni.isEmpty()) {
                             System.out.println("Error: El DNI es obligatorio.");
                             continue;
                        }
                        dni = Integer.parseInt(inputDni);
                        
                        // Uso del método de la fábrica para validación de unicidad
                        if (fabrica.validarDNI(dni)) {
                            dniValido = true;
                        } else {
                            System.out.println("Error: El DNI " + dni + " ya se encuentra registrado. Intente con otro.");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Error: DNI inválido. Debe ingresar solo números enteros.");
                    }
                }
                nuevosDatos.setDni(dni);

                datosCompletos = true; 
            }

            System.out.println("\nDATOS INGRESADOS (Revisión Final):");
            System.out.println(nuevosDatos); 

            // --- CONFIRMACIÓN Y GUARDADO ---
            String confirmacion;
            System.out.print("\n¿Son estos datos correctos? (S/N): ");
            confirmacion = scanner.nextLine().trim().toUpperCase();

            if (confirmacion.equals("S")) {
                fabrica.guardarDatosPersonales(nuevosDatos);
            } else {
                System.out.println("🛑 Guardado cancelado. Los datos no se almacenaron.");
            }

    }
	
	/*public void registrarUsuario() throws SQLException {
		  

	}*/
	
	public void registrarUsuario() throws SQLException {
        
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);       
        Usuario nuevoUsuario = new Usuario(); 
        boolean datosGuardados = false;

        System.out.println("\n--- REGISTRO DE NUEVO USUARIO ---");

        do {
            
            ArrayList<DatosPersonales> listaDatosPersonales = fabrica.listarDatosPersonales();
            int idSeleccionado = 0;
            boolean idValido = false;

            while (!idValido) {
                System.out.println("\nDATOS PERSONALES EXISTENTES");
                System.out.println("ID\t| Nombre\t| Apellido\t| DNI\n");
                 
                // Imprimir lista
                for (DatosPersonales dp : listaDatosPersonales) {
                    System.out.printf("%d\t| %s\t| %s\t| %d\n", 
                                      dp.getId(), dp.getNombre(), dp.getApellido(), dp.getDni());
                }

                System.out.print("Seleccione el ID Personal a asociar: ");
                String inputId = scanner.nextLine().trim();

                try {
                    idSeleccionado = Integer.parseInt(inputId);
                                       
                    if (fabrica.existePersona()) {
                        nuevoUsuario.setIdDatosPersonales(idSeleccionado);
                        idValido = true;
                        System.out.println("ID " + idSeleccionado + " asociado correctamente.");
                    } else {
                        System.out.println("Error: ID no encontrado en la lista. Intente de nuevo.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Por favor, ingrese un número entero válido.");
                } catch (Exception e) {
                     System.out.println("Error: No se pudo cargar la lista de datos personales.");
                     return;
                }
            }
            
            // -------------------------------------------------------------------
            // --- CONTINUACIÓN DEL INGRESO DE DATOS DE USUARIO ---
            
            // A. Nombre de Usuario (Obligatorio)
            String nombreUsuario;
            do {
                System.out.print("Ingrese Nombre de Usuario: ");
                nombreUsuario = scanner.nextLine().trim();
                if (nombreUsuario.isEmpty()) {
                    System.out.println("❌ Error: El nombre de usuario es obligatorio.");
                }
            } while (nombreUsuario.isEmpty());
            nuevoUsuario.setNombreUsuario(nombreUsuario);

            // B. Contraseña (Obligatoria)
            String contrasenia;
            do {
                System.out.print("Ingrese Contraseña: ");
                contrasenia = scanner.nextLine().trim();
                if (contrasenia.isEmpty()) {
                    System.out.println("❌ Error: La contraseña es obligatoria.");
                }
            } while (contrasenia.isEmpty());
            nuevoUsuario.setContrasenia(contrasenia);
            
            // C. Email (Validación de Formato y Unicidad)
            String email;
            boolean emailValido = false;
            do {
                System.out.print("Ingrese Email: ");
                email = scanner.nextLine().trim();
                
                if (email.isEmpty()) {
                    System.out.println("Error: El email es obligatorio.");
                } 
                // ➡️ VERIFICACIÓN DE FORMATO BÁSICO INTEGRADA: xxx@yyy
                else if (!email.contains("@") || email.startsWith("@") || email.endsWith("@")) {
                    System.out.println("Error: El email debe tener el formato básico xxx@yyy.");
                }
                // ➡️ VERIFICACIÓN DE UNICIDAD con la fábrica
                else if (fabrica.existeMail(email)) { 
                    System.out.println("Error: El email ya está registrado.");
                } else {
                    emailValido = true;
                }
            } while (!emailValido);
            nuevoUsuario.setEmail(email);

            // D. País (Ingreso por índice numérico y Obligatorio)
            Pais pais = null;
            boolean paisValido = false;
            Pais[] paises = Pais.values();
            
            while (!paisValido) {
                System.out.println("\nPaíses disponibles (Ingrese el número):");
                for (int i = 0; i < paises.length; i++) {
                    System.out.println("  " + (i + 1) + ". " + paises[i]);
                }
                System.out.print("Ingrese el número del País: ");
                String inputNumero = scanner.nextLine().trim();
                
                try {
                    int indiceSeleccionado = Integer.parseInt(inputNumero);
                    
                    if (indiceSeleccionado >= 1 && indiceSeleccionado <= paises.length) {
                        pais = paises[indiceSeleccionado - 1]; 
                        paisValido = true;
                    } else {
                        System.out.println("❌ Error: Número de país fuera del rango válido.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("❌ Error: Por favor, ingrese un número válido para el país.");
                }
            }
            nuevoUsuario.setPais(pais);
            
            // 2. MUESTRA Y CONFIRMACIÓN
            System.out.println("\n-------------------------------------");
            System.out.println("DATOS INGRESADOS (Revisión Final):");
            System.out.println(nuevoUsuario);
            System.out.println("-------------------------------------");

            System.out.print("¿Son estos datos correctos? (S/N): ");
            String confirmacion = scanner.nextLine().trim().toUpperCase();

            if (confirmacion.equals("S")) {
                fabrica.guardarUsuario(nuevoUsuario);
                datosGuardados = true; 
            } else {
                System.out.println("Datos no confirmados. Debe reingresar todos los datos.");
            }
            
        } while (!datosGuardados); 

}
	
	public void registrarPelicula() throws SQLException {
		    Scanner scanner = new Scanner(System.in);
		    
		    System.out.println("REGISTRAR NUEVA PELÍCULA");

			Pelicula nuevaPelicula = new Pelicula(); 

			// 1. Título (Obligatorio)
			String titulo;
	        do {
	            System.out.print("Título de la película: ");
	            titulo = scanner.nextLine().trim();
	            if (titulo.isEmpty()) {
	                System.out.println("El título no puede estar vacío. Intente de nuevo.");
	            }
	        } while (titulo.isEmpty());
	        nuevaPelicula.setTitulo(titulo);

			// 2. Género (Corroborar que esté dentro del Enum)
			GeneroPelicula genero = null;
	        boolean generoValido = false;
	        GeneroPelicula[] generos = GeneroPelicula.values();
	        
	        while (!generoValido) {
	            System.out.println("\nGéneros disponibles (Ingrese el número):");
	            // Imprimir la lista de géneros con su número correspondiente
	            for (int i = 0; i < generos.length; i++) {	               
	                System.out.println("  " + (i + 1) + ") " + generos[i]);
	            }
	            System.out.print("Ingrese el número del Género: ");
	            String inputNumero = scanner.nextLine().trim();
	            
	            try {
	                int indiceSeleccionado = Integer.parseInt(inputNumero);
	                
	                // El índice debe estar entre 1 y la cantidad total de géneros (generos.length)
	                if (indiceSeleccionado >= 1 && indiceSeleccionado <= generos.length) {
	                    // Restamos 1 porque los arrays/enums en Java comienzan en el índice 0
	                    genero = generos[indiceSeleccionado - 1]; 
	                    generoValido = true;
	                } else {
	                    System.out.println("Error: Número fuera del rango válido. Intente de nuevo.");
	                }
	            } catch (NumberFormatException e) {
	                System.out.println("Error: Por favor, ingrese un número válido.");
	            }
	        }
	        nuevaPelicula.setGenero(genero);

			// 3. Director (Validar que solo contenga letras)
	        String director;
	        boolean directorValido;
	        
	        do {
	            directorValido = true; // Asumimos que la entrada es válida al comienzo del ciclo
	            System.out.print("\nNombre del Director: ");
	            director = scanner.nextLine().trim();
	            
	            // LÓGICA DE VERIFICACIÓN DE LETRAS
	            if (director.isEmpty()) {
	                System.out.println("Error: El nombre del director no puede estar vacío.");
	                directorValido = false;
	            } 
	            // Expresión regular: solo letras (mayúsculas/minúsculas), espacios, acentos y Ñ/ñ
	            else if (!director.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) { 
	                System.out.println("Error: El nombre del director debe contener solo letras y espacios. Intente de nuevo.");
	                directorValido = false;
	            }
	            
	        } while (!directorValido); // Continuar si la entrada NO fue válida
	        
	        nuevaPelicula.setDirector(director);

			// 4. Duración (Real: Minutos.Segundos)
			double duracion = 0.0;
			boolean duracionValida = false;
			while (!duracionValida) {
			    System.out.print("\nDuración (en formato Minutos.Segundos, ej: 125.30): ");
			    String inputDuracion = scanner.nextLine().trim().replace(',', '.'); // Permite coma o punto
			    
			    try {
			        duracion = Double.parseDouble(inputDuracion);
			        if (duracion > 0) {
			            // Validación de que la parte decimal (segundos) no exceda 59
			            double parteDecimal = duracion - Math.floor(duracion);
			            int segundos = (int) Math.round(parteDecimal * 100); 

			            if (segundos < 60) {
			                duracionValida = true;
			            } else {
			                System.out.println("Error de formato: Los segundos (parte decimal) no deben ser 60 o más. Use 0.59 como máximo.");
			            }
			        } else {
			            System.out.println("Error: La duración debe ser un valor positivo.");
			        }
			    } catch (NumberFormatException e) {
			        System.out.println("Error: Formato de duración incorrecto. Use Minutos.Segundos (ej: 95.45).");
			    }
			}
			nuevaPelicula.setDuracion(duracion);

			// 5. Resumen (Opcional)
			System.out.print("\nResumen/Sinopsis (Opcional, presione Enter para omitir): ");
			String resumen = scanner.nextLine().trim();
			if (resumen.isEmpty()) {
			     resumen = "Sin resumen disponible.";
			}
			scanner.close();
			nuevaPelicula.setResumen(resumen);
			
			fabrica.guardarPelicula(nuevaPelicula);
	}
	
	public void listarPeliculas() {
		Scanner scanner = new Scanner(System.in);
        
		ArrayList<Pelicula> listaPeliculas = null;
        boolean opcionValida = false;
        int opcion=0;
        
        while (!opcionValida) {
            System.out.println("\n--- LISTAR PELÍCULAS ---");
            System.out.println("Seleccione el criterio de ordenación:");
            System.out.println("1. Por Nombre (Título)");
            System.out.println("2. Por Género");
            System.out.println("3. Por Duración");
            System.out.println("4. Sin Orden");
            System.out.print("Ingrese su opción (1-4): ");

            String criterio = scanner.nextLine().trim();
            opcion = Integer.parseInt(criterio);
            if(opcion<1 || opcion>4) {
            	System.out.print("Error: criterio de ordenacion invalido, por favor ingrese otro: ");
            	criterio = scanner.nextLine().trim();
                opcion = Integer.parseInt(criterio);
            }
        }
        listaPeliculas=fabrica.listarPeliculas(opcion);        
        
        scanner.close();

        // Una vez que se obtiene la lista, se imprime
        if (listaPeliculas != null && !listaPeliculas.isEmpty()) {
            System.out.println("\n LISTADO DE PELÍCULAS:");
            for (Pelicula p : listaPeliculas) {
                // Asumimos que la clase Pelicula tiene un método toString() informativo
                System.out.println(p); 
            }
        } else {
            System.out.println("No se encontraron películas para listar.");
        }
    }
	
	public void registrarResenia(Scanner in) {
		String user, pass, comentario, titulo ="";
		int num, puntaje;
		System.out.println("Creemos una reseña.");
		System.out.println("Primero ingresa tu usuario:");
		user = in.next();
		System.out.println("Ahora tu contraseña:");
		pass = in.next();
		// Validar usuario
		System.out.println("Validación exitosa. "+user+", elige una de las siguientes peliculas por su numero.");
		// Mostrar películas
		num = in.nextInt();
		// Recibir película num
		System.out.println("Ingresa una calificación para la pelicula '"+titulo+"' (1-10):");
		puntaje = in.nextInt();
		while (puntaje < 1 || puntaje > 10){
			System.out.println("Puntaje no valido. Ingresar nuevamente un valor entre 1 y 10");
			puntaje = in.nextInt();
		}
		System.out.println("Ahora ingresar un comentario:");
		in.nextLine();
		comentario = in.nextLine();
		System.out.println("Desea publicar la siguiente reseña?:");
		System.out.println(titulo);
		System.out.println(puntaje);
		System.out.println(comentario);
		System.out.println("(Ingresar true/false)");
		if (in.nextBoolean()) {
			// Guardar en Base de Datos
		}
	}
	
	public void aprobarResenia(Scanner in) {
		int num;
		System.out.println("Reseñas sin aprobar:");
		// Mostrar reseñas
		System.out.println("Ingrese el numero de la reseña a aprobar:");
		num = in.nextInt();
		/* while not existe
			pedir otro num */
		System.out.println("Desea aprobar la siguiente reseña?:");
		// Mostrar reseña
		System.out.println("(Ingresar true/false)");
		if (in.nextBoolean()) {
			// Guardar en Base de Datos (aprobadas)
			// Borrar de Base de Datos (no aprobadas)
		}
	}
}

