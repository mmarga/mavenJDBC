package maven.mavenJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private Connection connection = null;
	
	public Connection conectarse() throws SQLException {
		if (connection == null || connection.isClosed()) { 
			connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/mavenjdbc", "root", "");
		}
		
		return connection;
	}
	
}
 