package Comparator;

import Modelo.*;
import java.util.Comparator;

public class ComparatorDuracion implements Comparator<Pelicula> {
		
	public int compare(Pelicula p1, Pelicula p2) {
		 return Double.compare(p1.getDuracion(),(int)p2.getDuracion());
		  }
		 
}
