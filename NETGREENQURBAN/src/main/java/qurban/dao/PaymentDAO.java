package qurban.dao;

import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import qurban.connection.ConnectionManager;
import qurban.javabean.*;

public class PaymentDAO {
	
	// Connection object
	Connection connection = null;
	
	// Payment Attributes
	private int paymentID;
	private double paymentTotal;
	private Date paymentDate;
	private Part paymentReceipt;
	private InputStream inputstream;
	private int bookingID;
	
	// CRUD ---------------------------------------------------
	
	// Add Payment
	public void addPayment(Payment newPayment) throws SQLException {
		
		try {
			
			// Get connection
			connection = ConnectionManager.getConnection();
			
			// Get values
			bookingID = newPayment.getBookingID();
			paymentTotal = newPayment.getPaymentTotal();
			paymentDate = newPayment.getPaymentDate();
			paymentReceipt = newPayment.getPaymentReceipt();
			System.out.println(paymentReceipt);
			
			inputstream = new BufferedInputStream(paymentReceipt.getInputStream());
			
			byte[] byteArray = inputstream.readAllBytes();
			
			// Prepare SQL Statement
			PreparedStatement addSQL = connection.prepareStatement
			("INSERT INTO payment "
			+ "(paymenttotal, paymentdate, paymentreceipt, bookingid) "
			+ "VALUES (?, ?, ?, ?)");
			
			// Set ? values
			addSQL.setDouble(1, paymentTotal);
			addSQL.setDate(2, paymentDate);
			addSQL.setBytes(3, byteArray);
			addSQL.setInt(4, bookingID);
			
			// Execute SQL
			addSQL.executeUpdate();
			
			// Check SQL
			System.out.println(addSQL);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Delete Payment when Booking is deleted (For Committee only)
	public void deletePayment(int paymentID) throws SQLException {
		
		try {
			
			// Get Connection
			connection = ConnectionManager.getConnection();
			
			// Prepare SQL Statement
			PreparedStatement deleteSQL = connection.prepareStatement
			( "DELETE FROM payment "
			+ "WHERE paymentid = ?");
			
			// Set ? values
			deleteSQL.setInt(1, paymentID);
			
			// ExecuteSQL
			deleteSQL.executeUpdate();
			
			// Check SQL
			System.out.println(deleteSQL);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
