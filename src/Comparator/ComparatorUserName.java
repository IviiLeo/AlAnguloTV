package Comparator;

import Modelo.*;
import java.util.Comparator;

public class ComparatorUserName implements Comparator<Usuario> {
		
	public int compare(Usuario u1, Usuario u2) {
		 return u1.getNombreUsuario().compareTo(u2.getNombreUsuario());

		  }
		 
}
