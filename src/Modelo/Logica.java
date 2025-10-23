package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import Database.*;

public class Logica {
	private GeneralDAO fabrica;
	public Logica() {
		fabrica=new GeneralDAO();
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
                System.out.println("\nINGRESO DE DATOS PERSONALES");
                
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
                System.out.println("Guardado cancelado. Los datos no se almacenaron.");
            }

    }
	
	public void registrarUsuario() throws SQLException {
        
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);       
        Usuario nuevoUsuario = new Usuario(); 
        boolean datosGuardados = false;

        System.out.println("\nREGISTRO DE NUEVO USUARIO");

        do {
            
            ArrayList<DatosPersonales> listaDatosPersonales = fabrica.listarDatosPersonales();
            int idSeleccionado = 0;
            boolean idValido = false;

            while (!idValido) {
                System.out.println("\nDATOS PERSONALES EXISTENTES");
                System.out.println("ID\t| Nombre\t| Apellido\t| DNI\n");
                 
                // Imprimir lista
                for (DatosPersonales dp : listaDatosPersonales) {
                    System.out.printf("%d\t| %s\t| %s\t| %d\n"+
                                      dp.getId()+" | "+ dp.getNombre()+" | "+ dp.getApellido()+" | "+ dp.getDni());
                }

                System.out.print("Seleccione el ID Personal a asociar: ");
                int idPersona=scanner.nextInt();

                try {
                                       
                    if (fabrica.existePersona(idPersona)) {
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
                    System.out.println("Error: El nombre de usuario es obligatorio.");
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
                // VERIFICACIÓN DE FORMATO BÁSICO INTEGRADA: xxx@yyy
                else if (!email.contains("@") || email.startsWith("@") || email.endsWith("@")) {
                    System.out.println("Error: El email debe tener el formato básico xxx@yyy.");
                }
                // VERIFICACIÓN DE UNICIDAD con la fábrica
                else if (fabrica.existeMail(email)) { 
                    System.out.println("Error: El email ya está registrado.");
                } else {
                    emailValido = true;
                }
            } while (!emailValido);
            nuevoUsuario.setEmail(email); 
            
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
	
	public void registrarResenia() throws SQLException {
		Scanner in = new Scanner(System.in);
		String user, comentario;
		int num, puntaje;
		Resenia nuevaResenia=new Resenia();
		Usuario u;		
		boolean validacion=false;
		// Se valida el usuario
		do {
			System.out.print("Ingresa el nombre de usuario: ");
			user = in.next();
			u=fabrica.buscarUsuarioPorNombre(user);
			if(u==null) {
				System.out.println("El nombre de usuario es incorrecto.\n");
			} else {
				System.out.print("Ingresa la contrasena: ");
				String contrasenia=in.nextLine().trim();
				if(u.getContrasenia().equals(contrasenia)) {
					validacion=true;
				} else System.out.println("Error: contrasena incorrecta");					
			}			
		}while(!validacion);
		System.out.println("Validación exitosa, bienvenido "+user);
		nuevaResenia.setIdUsuario(u.getIdUsuario());
		// Se muestran las peliculas disponibles
		System.out.println("A continuacion se mostrara un listado de peliculas disponibles:\n");
		ArrayList<Pelicula> listaPeliculas=fabrica.listarPeliculas(4);
		for (Pelicula p:listaPeliculas) {
			System.out.print(p.getId() + " - "+ p.getTitulo()+"\n");
		}
		// Se selecciona una pelicula disponible
		validacion=false;
		do {
			System.out.print("Ingrese el ID de la pelicula a la que quiere agregarle una resenia: ");
			num = in.nextInt();
			if(fabrica.existePelicula(num)) {
				validacion=true;
			} else System.out.println("Error. Película no disponible.");
		}while (!validacion);
		nuevaResenia.setIdPelicula(num);
		// Se ingresa calificacion de la reseña
		System.out.println("\nIngresa una calificación para la pelicula (1..10):");
		puntaje = in.nextInt();
		while (puntaje < 1 || puntaje > 10){
			System.out.println("Puntaje no valido. Ingresar nuevamente un valor entre 1 y 10");
			puntaje = in.nextInt();
		}
		nuevaResenia.setCalificacion(puntaje);
		// Se ingresa comentario de la reseña
		System.out.println("Ahora ingresar un comentario:");
		comentario = in.nextLine();
		nuevaResenia.setComentario(comentario);
		// Se muestra la reseña final y se solicita confirmacion
		System.out.println("Desea publicar la siguiente reseña?:");
		System.out.println(nuevaResenia.toStringSinID());
		System.out.println("(Ingresar true/false)");
		if (in.nextBoolean()) {
			fabrica.guardarResenia(nuevaResenia);
		}
		in.close();
	}
	
	public void aprobarResenia() {
		Scanner in = new Scanner(System.in);
		int num;
		boolean validacion;
		// Se muestran las reseñas pendientes de aprobación
		System.out.println("Resenias sin aprobar:");
		ArrayList<Resenia> listaResenias=fabrica.listarReseniasNoAprobadas();
		System.out.println("Las siguientes resenias aguardan aprobacion");
		System.out.println("Formato ID resenia - ID pelicula - ID usuario");
		for (Resenia r:listaResenias) {
			System.out.println(r.getId() + " - "+ r.getIdPelicula() + " - "+ r.getIdUsuario() + "\n");
		}
		// Se solicita la reseña a aprobar y se verifica su existencia
		validacion=false;
		do {
			System.out.println("Ingrese el numero de la resenia a aprobar:");
			num = in.nextInt();
			if (fabrica.existeResenia(num))
				validacion=true;	
			else System.out.println("Error. ID de resenia incorrecto.");
		} while(!validacion);
		// Se muestra la reseña y se solicita aprobacion o desaprobacion
		Resenia r = fabrica.descargarResenia(num);
		System.out.println("Desea aprobar la siguiente reseña?:");
		System.out.println(r.toString());
		System.out.println("(Ingresar true/false)");
		if (in.nextBoolean()) {
			// Guardar en Base de Datos (aprobada)
			fabrica.actualizarReseniaAprobada(num);
			System.out.println("La resenia fue aprobada con exito.");
		} else { // Borrar de Base de Datos (desaprobada)
			fabrica.borrarResenia(r);
			System.out.println("La resenia fue desaprobada con exito.");
		}
		in.close();
	}

	
	public void listarUsuarios() {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Usuario> listaUsuarios = null;
		// Se solicita un criterio de ordenación de usuarios
		int opcion ;
		System.out.println("\n--- LISTAR USUARIOS ---");
        System.out.println("Seleccione el criterio de ordenación:");
        System.out.println("1. Por nombre de usuario");
        System.out.println("2. Por email asociado");
        System.out.println("Ingrese su opcion (1-2): ");
        opcion = scanner.nextInt();
        while (opcion != 1 && opcion !=2) {
        	System.out.println("Error: criterio de ordenacion invalido");
        	opcion = scanner.nextInt();
        }
        // Se consigue la lista de usuarios segun el orden elegido y se muestra en pantalla
        listaUsuarios=fabrica.listarUsuarios(opcion);
        scanner.close();
        if (listaUsuarios != null && !listaUsuarios.isEmpty()) {
            System.out.println("\n LISTADO DE USUARIOS:");
            for (Usuario u : listaUsuarios) {
                System.out.println(u.toString()); 
                // Faltaria imprimir los datos personales
            }
        } else
            System.out.println("No se encontraron usuarios para listar.");
	}
}

