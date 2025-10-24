package Comparator;

import Modelo.*;
import java.util.Comparator;

public class ComparatorMail implements Comparator<Usuario> {
		
	public int compare(Usuario u1, Usuario u2) {
		 return u1.getEmail().compareTo(u2.getEmail());

		  }
		 
}
