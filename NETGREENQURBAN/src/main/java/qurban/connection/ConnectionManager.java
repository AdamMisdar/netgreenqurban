package qurban.connection;

import java.sql.*;

public class ConnectionManager {
	
	// Postgresql database
	private static String dbURL = "jdbc:postgresql://ec2-3-223-169-166.compute-1.amazonaws.com:5432/dfcvvjuvqh9c4k?sslmode=require";
	private static String username = "mledzxdxfykycr";
	private static String password = "9d9f02cdbcf786cb80ebf7cdbcabfa637a4c84994673ed9256a9e83e39131589";
	
/*	// Oracle database
	private static String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String username = "netgreen";
	private static String password = "system";
*/	
	public static Connection getConnection() {
		Connection connection = null;
		
		try {
			// Postgresql database
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(dbURL, username, password);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		}
		
		return connection;
	}
}