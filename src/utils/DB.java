package utils;

import java.sql.*;

public class DB {
	
	private Connection connection = null;
	
	public DB() throws Exception {
		
		// WITHOUT POOL
		String user = "prac";
		String password="prac";
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection=DriverManager.getConnection("jdbc:mysql://localhost/lab4?user="+user+"&password="+password+"&serverTimezone=Europe/Paris");
	}
	
	//execute queries
	
	public PreparedStatement prepareStatement(String query) throws SQLException{
		// Note that this is done using https://www.arquitecturajava.com/jdbc-prepared-statement-y-su-manejo/
		return connection.prepareStatement(query);
	}
	
	public void disconnectBD() throws SQLException{
		connection.close();
	}
}