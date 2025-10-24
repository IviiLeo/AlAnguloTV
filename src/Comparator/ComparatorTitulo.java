package Comparator;

import Modelo.*;
import java.util.Comparator;

public class ComparatorTitulo implements Comparator<Pelicula> {
		
	public int compare(Pelicula p1, Pelicula p2) {
		 return p1.getTitulo().compareTo(p2.getTitulo());

		  }
		 
}
