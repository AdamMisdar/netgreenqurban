package qurban.dao;


import java.sql.*;
import qurban.connection.ConnectionManager;
import qurban.javabean.*;

public class AnimalDetailsDAO {
	
	// Connection object
	Connection connection = null;
	
	// Animal Details attributes
	private int animalDetailsID;	// PK
	private String animalType;		// Jenis haiwan
	private double animalPrice;		// Harga seekor
	
	// CRUD -----------------------------------------------------------------
	
	// Create Animal Details (Create New Animal)
	public void createAnimalDetails(AnimalDetails newAnimal) throws SQLException {
		
		try {
			
			// Get connection
			connection = ConnectionManager.getConnection();
				
			// Get values
			animalType = newAnimal.getAnimalType();
			animalPrice = newAnimal.getAnimalPrice();
				
			// Prepare SQL Statement
			PreparedStatement createSQL = connection.prepareStatement
			( "INSERT INTO animaldetails "
			+ "(animaltype, animalprice) "
			+ "VALUES (?, ?)");
			
			// Set ? values
			createSQL.setString(1, animalType);
			createSQL.setDouble(2, animalPrice);
			
			// Execute SQL
			createSQL.executeUpdate();
				
			// Check SQL
			System.out.println(createSQL);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Update Animal Details (Update Existing Animal's Details)
	public void updateAnimalDetails(AnimalDetails existingAnimal) throws SQLException {
		
		try {
			
			// Get connection
			connection = ConnectionManager.getConnection();
			
			// Get values
			animalPrice = existingAnimal.getAnimalPrice();
			animalDetailsID = existingAnimal.getAnimalDetailsID();
			
			// Prepare SQL Statement
			PreparedStatement updateSQL = connection.prepareStatement
			( "UPDATE animaldetails "
			+ "SET animalprice = ? "
			+ "WHERE animaldetailsid = ?");
			
			// Set ? Values
			updateSQL.setDouble(1, animalPrice);
			updateSQL.setInt(2, animalDetailsID);
			
			// Execute SQL
			updateSQL.executeUpdate();
			
			// Check SQL
			System.out.println(updateSQL);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Delete Animal Details (Delete Existing Animal)
	public void deleteAnimalDetails(int animalDetailsID) throws SQLException {
		
		try {
			
			// Get Connection
			connection = ConnectionManager.getConnection();
			
			// Prepare SQL Statement
			PreparedStatement deleteSQL = connection.prepareStatement
			( "DELETE FROM animaldetails "
			+ "WHERE animaldetailsid = ?");
			
			// Set ? values
			deleteSQL.setInt(1, animalDetailsID);
			
			// Execute SQL
			deleteSQL.executeUpdate();
			
			// Check SQL
			System.out.println(deleteSQL);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	
}