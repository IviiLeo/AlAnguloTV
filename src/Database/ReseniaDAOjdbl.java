package Database;

import Modelo.Resenia;
import java.sql.*;

public class ReseniaDAOjdbl implements ReseniaDAO {
	private Connection conn;
	
	public ReseniaDAOjdbl(Connection c) {
		conn=c;
	}
	
	@Override
	public void cargarResenia(Resenia r) {
		// TODO Auto-generated method stub
		
	}

}
