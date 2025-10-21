package Database;

import java.sql.*;

public interface DatabaseDAO {
	
	public void iniciar() throws SQLException;
		
	
	public void apagar() throws SQLException;
	
	public Connection getConnection();
//adad
}
