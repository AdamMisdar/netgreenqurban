package qurban.javabean;

import java.sql.Date;
import javax.servlet.http.Part;

public class Payment {
	
	private int paymentID;
	private double paymentTotal;
	private Date paymentDate;
	private Part paymentReceipt;
	private int bookingID;
	
	// Constructors ------------------------------------------------------
	
	// with ID
	public Payment(int paymentID, double paymentTotal, Date paymentDate, Part paymentReceipt, int bookingID) {
		
		this.paymentID = paymentID;
		this.paymentTotal = paymentTotal;
		this.paymentDate = paymentDate;
		this.paymentReceipt = paymentReceipt;
		this.bookingID = bookingID;
	}
	
	// without ID
	public Payment(double paymentTotal, Date paymentDate, Part paymentReceipt, int bookingID) {

		this.paymentTotal = paymentTotal;
		this.paymentDate = paymentDate;
		this.paymentReceipt = paymentReceipt;
		this.bookingID = bookingID;
	}
	
	// default
	public Payment() {
		
	}
	
	// Setters --------------------------------------------------------------
	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}
	
	public void setPaymentTotal(double paymentTotal) {
		this.paymentTotal = paymentTotal;
	}
	
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	public void setPaymentReceipt(Part paymentReceipt) {
		this.paymentReceipt = paymentReceipt;
	}
	
	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}
	
	// Getters -------------------------------------------------------------
	public int getPaymentID() {
		return paymentID;
	}

	public double getPaymentTotal() {
		return paymentTotal;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public Part getPaymentReceipt() {
		return paymentReceipt;
	}
	
	public int getBookingID() {
		return bookingID;
	}

	
	
	
	
	
	
	
	
	
	
}
