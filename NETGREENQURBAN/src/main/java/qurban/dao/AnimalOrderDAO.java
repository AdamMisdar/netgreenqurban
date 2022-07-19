package qurban.dao;

import java.sql.*;
import qurban.connection.ConnectionManager;
import qurban.javabean.*;

public class AnimalOrderDAO {
	
	// Connection object
	Connection connection = null;
	
	// Animal Order attributes
	int animalOrderID;			// PK
	String dependentName;		// Nama yg dikorbankan
	int bookingID;				// FK
	int animalDetailsID;		// FK
	
	// CRUD ----------------------------------------------------------
	
	// Create Animal Order
	public void addAnimalOrder(AnimalOrder newAnimalOrder) throws SQLException {
		
		try {
			
			// Get connection
			connection = ConnectionManager.getConnection();
					
			// Get values
			dependentName = newAnimalOrder.getDependentName();
			animalDetailsID = newAnimalOrder.getAnimalDetailsID();
			bookingID = newAnimalOrder.getBookingID();
			
			// Prepare SQL Statement		
			PreparedStatement addSQL = connection.prepareStatement
			( "INSERT INTO animalorder "
			+ "(dependentname,  animaldetailsid, bookingID) "
			+ "VALUES (?, ?, ?)");
			
			// Set ? values
			addSQL.setString(1, dependentName);
			addSQL.setInt(2, animalDetailsID);
			addSQL.setInt(3, bookingID);
			
			// Execute SQL
			addSQL.executeUpdate();
			
			// Check SQL
			System.out.println(addSQL);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// Update Animal Order (Client)
	public void updateAnimalOrder(AnimalOrder existingAnimalOrder) throws SQLException {
		
		try {
			
			// Get Connection
			connection = ConnectionManager.getConnection();
			
			// Get values
			animalOrderID = existingAnimalOrder.getAnimalOrderID();
			dependentName = existingAnimalOrder.getDependentName();
			
			// Prepare SQL Statement
			PreparedStatement updateSQL = connection.prepareStatement
			( "UPDATE animalorder "
			+ "SET dependentname = ? "
			+ "WHERE animalorderid = ?");
			
			// Set ? values
			updateSQL.setString(1, dependentName);
			updateSQL.setInt(2, animalOrderID);
			
			// Execute SQL
			updateSQL.executeUpdate();
			
			// Check SQL
			System.out.println(updateSQL);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Delete All by bookingID (COMMITTEE)
	public void deleteAnimalOrderByBooking(int bookingID) throws SQLException {
		
		try {
			
			// Get Connection
			connection = ConnectionManager.getConnection();
			
			// Prepare SQL Statement
			PreparedStatement deleteSQL = connection.prepareStatement
			( "DELETE FROM animalorder "
			+ "WHERE bookingid = ?");
			
			// Set ? values
			deleteSQL.setInt(1, bookingID);
			
			// Execute SQL
			deleteSQL.executeUpdate();
			
			// Check SQL
			System.out.println(deleteSQL);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Delete 1 by animalOrderID (COMMITTEE)
	public void deleteAnimalOrder(int animalOrderID) throws SQLException {
		
		try {
			
			// Get Connection
			connection = ConnectionManager.getConnection();
			
			// Prepare SQL Statement
			PreparedStatement deleteSQL = connection.prepareStatement
			( "DELETE FROM animalorder "
			+ "WHERE animalorderid = ?");
			
			// Set ? values
			deleteSQL.setInt(1, animalOrderID);
			
			// Execute SQL
			deleteSQL.executeUpdate();
			
			// Check SQL
			System.out.println(deleteSQL);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
