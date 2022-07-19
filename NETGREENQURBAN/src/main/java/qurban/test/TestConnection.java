package qurban.test;

import java.sql.*;

import qurban.connection.*;

public class TestConnection {

	public static void main(String[] args) {
		
		Connection connection = null;
		connection = ConnectionManager.getConnection();
		
		if (connection != null) {
			System.out.println("Connection success.");
		} 
		else {
			System.out.println("Connection failed!");
		}
		
		
	}

}
