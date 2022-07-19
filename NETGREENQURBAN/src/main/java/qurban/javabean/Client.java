package qurban.javabean;

import java.sql.Date;

public class Client {
	private int clientID;
	private String clientFullName;
	private String clientAddress;
	private String clientPhoneNum;
	private Date clientBirthDate;
	private String clientEmail;
	private String clientPassword;
	
	// Constructor -------------------------------------------------------------------------
	
	// with ID
	public Client(int clientID, String clientFullName, String clientAddress, String clientPhoneNum,
			Date clientBirthDate, String clientEmail, String clientPassword) {

		this.clientID = clientID;
		this.clientFullName = clientFullName;
		this.clientAddress = clientAddress;
		this.clientPhoneNum = clientPhoneNum;
		this.clientBirthDate = clientBirthDate;
		this.clientEmail = clientEmail;
		this.clientPassword = clientPassword;
	}
	
	// without ID
	public Client(String clientFullName, String clientAddress, String clientPhoneNum,
			Date clientBirthDate, String clientEmail, String clientPassword) {

		this.clientFullName = clientFullName;
		this.clientAddress = clientAddress;
		this.clientPhoneNum = clientPhoneNum;
		this.clientBirthDate = clientBirthDate;
		this.clientEmail = clientEmail;
		this.clientPassword = clientPassword;
	}
	
	// default
	public Client() {
		
	}
	
	// Setters ----------------------------------------------------
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	
	public void setClientFullName(String clientFullName) {
		this.clientFullName = clientFullName;
	}
	
	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}
	
	public void setClientPhoneNum(String clientPhoneNum) {
		this.clientPhoneNum = clientPhoneNum;
	}
	
	public void setClientBirthDate(Date clientBirthDate) {
		this.clientBirthDate = clientBirthDate;
	}
	
	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}
	
	public void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}

	
	// Getters -----------------------------------------------------
	public int getClientID() {
		return clientID;
	}

	public String getClientFullName() {
		return clientFullName;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public String getClientPhoneNum() {
		return clientPhoneNum;
	}

	public Date getClientBirthDate() {
		return clientBirthDate;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public String getClientPassword() {
		return clientPassword;
	}

	
	
	
	
	
}
