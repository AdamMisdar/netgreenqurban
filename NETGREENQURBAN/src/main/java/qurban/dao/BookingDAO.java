package qurban.dao;

import java.sql.*;
import qurban.connection.ConnectionManager;
import qurban.javabean.*;

public class BookingDAO {
	
	// Connection object
	Connection connection = null;
	
	// Booking attributes
	private int bookingID;			// PK
	private Date bookingDate;
	private int clientID;			// FK
	private int committeeID;		// FK
	
	// CRUD ---------------------------------------------------------
	
	// Create Booking (Client only) // When Client press button create booking
	public int createBooking(Booking newBooking) {
		int booking_id = 0;;
		
		try {
			
			// Get connection
			connection = ConnectionManager.getConnection();
			
			
			// Get values
			bookingDate = newBooking.getBookingDate();
			clientID = newBooking.getClientID();
			
			// Prepare SQL Statement
			PreparedStatement createSQL = connection.prepareStatement
			( "INSERT INTO booking "
			+ "(bookingdate, clientid) "
			+ "VALUES (?, ?)");
			
			// Set ? values
			createSQL.setDate(1, bookingDate);
			createSQL.setInt(2, clientID);
			
			// Execute SQL
			createSQL.executeUpdate();
			
			// Check SQL
			System.out.println(createSQL);
			
			//PrepareStatement select ------------	
			PreparedStatement selectSQL = connection.prepareStatement
			( "SELECT bookingid, clientid "
			+ "FROM booking "
			+ "WHERE clientid = ? "
			+ "ORDER BY bookingid");
			
			// Set ? values
			selectSQL.setInt(1, clientID);
			
			// Execute SQL
			ResultSet result = selectSQL.executeQuery();
		
			// Assign booking id
			while(result.next()) {
				booking_id = result.getInt("bookingid");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Return id
		return booking_id;
	}
	
	// Update (Committee id)
	public void updateBooking(Booking existingBooking) {
		
		try {
			// Get connection
			connection = ConnectionManager.getConnection();
			
			// Get values
			committeeID = existingBooking.getCommitteeID();
			bookingID = existingBooking.getBookingID();
			
			// Prepare SQL
			PreparedStatement updateSQL = connection.prepareStatement
			("UPDATE booking "
			+ "SET committeeid = ? "
			+ "WHERE bookingid = ?");
			
			// Set ? values
			updateSQL.setInt(1, committeeID);
			updateSQL.setInt(2, bookingID);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Delete Booking (Committee) (ON DELETE CASCADE)
	public void deleteBooking(int bookingID) {
		
		try {
			
			// Get Connection
			connection = ConnectionManager.getConnection();
			
			// Prepare SQL Statement
			PreparedStatement deleteSQL = connection.prepareStatement
			( "DELETE FROM booking "
			+ "WHERE bookingid = ?");
			
			// Set ? values
			deleteSQL.setInt(1, bookingID);
			
			// ExecuteSQL
			deleteSQL.executeUpdate();
			
			// Check SQL
			System.out.println(deleteSQL);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
		
}
