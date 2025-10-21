package Modelo;

/**
 * Representa las fechas de la plataforma de streaming.
 */
public class Fecha {
  private int año;
  private int mes;
  private int dia;
  
  /**
	* Crea una nueva fecha sin los valores especificados.
	*/
  public Fecha() { }
  
  /**
   * Crea una nueva fecha con los valores especificados.
   *
   * @param año el año de la fecha
   * @param mes el mes de la fecha (1-12)
   * @param dia el día del mes
   */
  public Fecha(int año, int mes, int dia) {
	 this.año=año;
	 this.mes=mes;
	 this.dia=dia;
  }
  
  /**
   * Establece los años.
   *
   * @param año el nuevo año
   */
  
  public void setAño(int año) { 
	  this.año= año;
  }
  
  /**
   * Devuelve los años.
   *
   * @return el año
   */
  
  public int getAño() { 
	  return año;
  }
  
  /**
   * Establece los mes.
   *
   * @param mes la nueva mes
   */
  public void setMes(int mes) { 
	  this.mes= mes;
  }
  
  /**
   * Devuelve los mes.
   *
   * @return el mes
   */
  public int getMes() { 
	  return mes;
  }
  
  /**
   * Establece los dia.
   *
   * @param dia el nuevo dia
   */
  public void setDia(int dia) { 
	  this.dia= dia;
  }
  
  /**
   * Devuelve los dia.
   *
   * @return el dia
   */
  public int getdia() { 
	  return dia;
  }
  
  /**
   * Devuelve la fecha.
   *
   * @return el aux
   */
  public String getFecha() {
	  String aux=dia+"/"+mes+"/"+año;
	  return aux;
  }
}

