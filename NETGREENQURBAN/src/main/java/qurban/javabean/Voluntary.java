package qurban.javabean;

import java.sql.Date;

public class Voluntary extends Committee {
	
	double hourlyRate;
	String voluntaryRole;
	
	// Constructor ------------------------
	
	// default
	public Voluntary() {
		super();
	}
	
	// with ID
	public Voluntary(int ID, String name, String phoneNum, Date date, String address, 
			int manID, String email, String password, double rate, String role) {
		
		super(ID, name, phoneNum, date, address, manID, email, password);
		hourlyRate = rate;
		voluntaryRole = role;
	}
	
	// without ID
	public Voluntary(String name, String phoneNum, Date date, String address, 
			int manID, String email, String password, double rate, String role) {
		
		super(name, phoneNum, date, address, manID, email, password);
		hourlyRate = rate;
		voluntaryRole = role;
	}
	
	// Setter -----------------------------
	public void setHourlyRate(double rate) {
		hourlyRate = rate;
	}
	public void setVoluntaryRole(String role) {
		voluntaryRole = role;
	}
	
	// Getter -----------------------------
	public double getHourlyRate() {
		return hourlyRate;
	}
	public String getVoluntaryRole() {
		return voluntaryRole;
	}

}
