package qurban.dao;

import java.sql.*;
import qurban.connection.*;
import qurban.javabean.*;

public class ClientDAO {
	
	// Connection object
	Connection connection = null;
	
	// Client Attributes
	private int clientID;				// PK
	private String clientFullName;
	private String clientAddress;
	private String clientPhoneNum;
	private Date clientBirthDate;
	private String clientEmail;
	private String clientPassword;
	
	// CRUD --------------------------------------------------------
	
	// Create/Register New Client Account
	public void createClient(Client newClient) throws SQLException {
		
		try {
			
			// Get connection
			connection = ConnectionManager.getConnection();
			
			// Get values
			clientFullName = newClient.getClientFullName();
			clientAddress = newClient.getClientAddress();
			clientPhoneNum = newClient.getClientPhoneNum();
			clientBirthDate = newClient.getClientBirthDate();
			clientEmail = newClient.getClientEmail();
			clientPassword = newClient.getClientPassword();
			
			// Prepare SQL Statement
			PreparedStatement createSQL = connection.prepareStatement
			( "INSERT INTO client "
			+ "(clientfullname, clientaddress, clientphonenum, clientbirthdate, clientemail, clientpassword) "
			+ "VALUES (?, ?, ?, ?, ?, ?)");
			
			// Set ? values
			createSQL.setString(1, clientFullName);
			createSQL.setString(2, clientAddress);
			createSQL.setString(3, clientPhoneNum);
			createSQL.setDate(4, clientBirthDate);
			createSQL.setString(5, clientEmail);
			createSQL.setString(6, clientPassword);
			
			// Execute SQL
			createSQL.executeUpdate();
			
			// Check SQL
			System.out.println(createSQL);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Update Client Details (Update Existing Client)
	public void updateClientDetails(Client existingClient) throws SQLException {
		
		try {
			
			// Get Connection
			connection = ConnectionManager.getConnection();
			
			// Get values
			clientID = existingClient.getClientID();
			clientFullName = existingClient.getClientFullName();
			clientAddress = existingClient.getClientAddress();
			clientPhoneNum = existingClient.getClientPhoneNum();
			clientBirthDate = existingClient.getClientBirthDate();
			clientEmail = existingClient.getClientEmail();
			clientPassword = existingClient.getClientPassword();
			
			// Prepare SQL Statement
			PreparedStatement updateSQL = connection.prepareStatement
			( "UPDATE client "
			+ "SET clientfullname = ?, clientaddress = ?, clientphonenum = ?, "
			+ "clientbirthdate = ?, clientemail = ?, clientpassword = ? "
			+ "WHERE clientid = ?");
			
			// Set ? values
			updateSQL.setString(1, clientFullName);
			updateSQL.setString(2, clientAddress);
			updateSQL.setString(3, clientPhoneNum);
			updateSQL.setDate(4, clientBirthDate);
			updateSQL.setString(5, clientEmail);
			updateSQL.setString(6, clientPassword);
			updateSQL.setInt(7, clientID);
			
			// Execute SQL
			updateSQL.executeUpdate();
			
			// Check SQL
			System.out.println(updateSQL);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Delete Client (Client Deletes their account)
	// TENGOK BALIK, KLAU CLIENT DELETE, BOOKING DELETE TAK
	public void deleteClient(int clientID) throws SQLException {
		
		try {
			
			// Get Connection
			connection = ConnectionManager.getConnection();
			
			// Prepare SQL Statement
			PreparedStatement deleteSQL = connection.prepareStatement
			( "DELETE FROM client "
			+ "WHERE clientid = ?");
			
			// Set ? values
			deleteSQL.setInt(1, clientID);
			
			// Execute SQL
			deleteSQL.executeUpdate();
			
			// Check SQL
			System.out.println(deleteSQL);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
