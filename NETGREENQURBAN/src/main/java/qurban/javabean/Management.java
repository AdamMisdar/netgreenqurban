package qurban.javabean;

import java.sql.Date;

public class Management extends Committee {
	
	String managementPosition;
	
	// Constructor ------------------------
	
	// default
	public Management() {
		super();
	}
	
	// with ID
	public Management(int ID, String name, String phoneNum, Date date, String address, 
			int manID, String email, String password, String position){
		
		super(ID, name, phoneNum, date, address, manID, email, password);
		managementPosition = position;
	}
	
	// without ID
	public Management(String name, String phoneNum, Date date, String address, 
			int manID, String email, String password, String position){
		
		super(name, phoneNum, date, address, manID, email, password);
		managementPosition = position;
	}
	
	// Setter ------------------------------
	public void setManagementPosition(String position) {
		managementPosition = position;
	}
	
	// Getter ------------------------------
	public String getManagementPosition() {
		return managementPosition;
	}
}
