package qurban.javabean;

import java.sql.*;

public class Booking {

	private int bookingID;
	private Date bookingDate;
	private int clientID;
	private int committeeID;
	
	// Constructors --------------------------------------------------------------------
	
	// with ID
	public Booking(int bookingID, Date bookingDate, int clientID, int committeeID) {
		
		this.bookingID = bookingID;
		this.bookingDate = bookingDate;
		this.clientID = clientID;
		this.committeeID = committeeID;
	}
	
	// without ID
	public Booking(Date bookingDate, int paymentID, int clientID, int committeeID) {
		
		this.bookingDate = bookingDate;
		this.clientID = clientID;
		this.committeeID = committeeID;
	}
	
	// default
	public Booking() {
		
	}
	
	// Setters ---------------------------------------------------------
	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}
	
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	
	public void setCommitteeID(int committeeID) {
		this.committeeID = committeeID;
	}
	
	// Getters ---------------------------------------------------------
	public int getBookingID() {
		return bookingID;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public int getClientID() {
		return clientID;
	}

	public int getCommitteeID() {
		return committeeID;
	}	
	
}
