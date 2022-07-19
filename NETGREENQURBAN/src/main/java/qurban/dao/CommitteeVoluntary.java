package qurban.dao;

import java.sql.*;
import qurban.connection.ConnectionManager;
import qurban.javabean.*;

public class CommitteeVoluntaryDAO {
	// Connection object
	Connection connection = null;
	
	// Committee-Management Attributes
	int committeeID;				// PK
	String committeeFullName;
	String committeePhoneNum;
	Date committeeBirthDate;
	String committeeAddress;
	int managerID;					// FK
	String committeeEmail;
	String committeePassword;
	// ------------------------
	double hourlyRate;
	String voluntaryRole;
	
	// CRUD -----------------------------------------------
	
	// Create/Register New Committee-Voluntary Account
	// Only Manager can do this
	public void createVoluntary(Voluntary newVoluntary) throws SQLException{
		
		try {
			
			// Get connection
			connection = ConnectionManager.getConnection();
			
			// Get values
			committeeFullName = newVoluntary.getCommitteeFullName();
			committeePhoneNum = newVoluntary.getCommitteePhoneNum();
			committeeBirthDate = newVoluntary.getCommitteeBirthDate();
			committeeAddress = newVoluntary.getCommitteeAddress();
			managerID = newVoluntary.getManagerID();
			committeeEmail = newVoluntary.getCommitteeEmail();
			committeePassword = newVoluntary.getCommitteePassword();
			voluntaryRole = newVoluntary.getVoluntaryRole();
			hourlyRate = newVoluntary.getHourlyRate();
			
			// Prepare SQL Statements
			PreparedStatement createVoluntarySQL = connection.prepareStatement
			( "INSERT INTO voluntary "
			+ "(committeefullname, committeephonenum, committeebirthdate, committeeaddress, managerID, committeeemail, committeepassword, voluntaryrole, hourlyrate) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			// Set ? values
			createVoluntarySQL.setString(1, committeeFullName);
			createVoluntarySQL.setString(2, committeePhoneNum);
			createVoluntarySQL.setDate(3, committeeBirthDate);
			createVoluntarySQL.setString(4, committeeAddress);
			createVoluntarySQL.setInt(5, managerID);
			createVoluntarySQL.setString(6, committeeEmail);
			createVoluntarySQL.setString(7, committeePassword);
			createVoluntarySQL.setString(8, voluntaryRole);
			createVoluntarySQL.setDouble(9, hourlyRate);

			// Execute Update
			createVoluntarySQL.executeUpdate();
			System.out.println(createVoluntarySQL);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Update Committee Management Details (Update Existing Committee)
	// VOLUNTARY can do this themselves
	public void updateVoluntary(Voluntary existingVoluntary) throws SQLException {
		
		try {
			
			// Get connection
			connection = ConnectionManager.getConnection();
			
			// Get values
			committeeID = existingVoluntary.getCommitteeID();
			committeeFullName = existingVoluntary.getCommitteeFullName();
			committeePhoneNum = existingVoluntary.getCommitteePhoneNum();
			committeeBirthDate = existingVoluntary.getCommitteeBirthDate();
			committeeAddress = existingVoluntary.getCommitteeAddress();
			committeeEmail = existingVoluntary.getCommitteeEmail();
			committeePassword = existingVoluntary.getCommitteePassword();
			
			// Prepare SQL Statement
			PreparedStatement updateVoluntarySQL = connection.prepareStatement
			( "UPDATE voluntary "
			+ "SET committeefullname = ?, committeephonenum = ?, committeebirthdate = ?, "
			+ "committeeaddress = ?, committeeemail = ?, committeepassword = ? "
			+ "WHERE committeeid = ?");
			
			
			// Set ? Values
			updateVoluntarySQL.setString(1, committeeFullName);
			updateVoluntarySQL.setString(2, committeePhoneNum);
			updateVoluntarySQL.setDate(3, committeeBirthDate);
			updateVoluntarySQL.setString(4, committeeAddress);
			updateVoluntarySQL.setString(5, committeeEmail);
			updateVoluntarySQL.setString(6, committeePassword);
			updateVoluntarySQL.setInt(7, committeeID);
			
			// Execute SQL
			updateVoluntarySQL.executeUpdate();
			System.out.println(updateVoluntarySQL);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Update Committee Management Details (Update Existing Committee)
	// Only Manager can update this
	public void updateVoluntary_ManagerOnly(Voluntary existingVoluntary) throws SQLException {
			
		try {
				
			// Get connection
			connection = ConnectionManager.getConnection();
				
			// Get values
			committeeID = existingVoluntary.getCommitteeID();
			committeeFullName = existingVoluntary.getCommitteeFullName();
			committeePhoneNum = existingVoluntary.getCommitteePhoneNum();
			committeeBirthDate = existingVoluntary.getCommitteeBirthDate();
			committeeAddress = existingVoluntary.getCommitteeAddress();
			committeeEmail = existingVoluntary.getCommitteeEmail();
			committeePassword = existingVoluntary.getCommitteePassword();
			managerID = existingVoluntary.getManagerID();
			voluntaryRole = existingVoluntary.getVoluntaryRole();
			hourlyRate = existingVoluntary.getHourlyRate();
			
			// Prepare SQL Statement
			PreparedStatement updateVoluntarySQL = connection.prepareStatement
			( "UPDATE voluntary "
			+ "SET committeefullname = ?, committeephonenum = ?, committeebirthdate = ?, "
			+ "committeeaddress = ?, committeeemail = ?, committeepassword = ?, managerid = ?, voluntaryrole = ?, hourlyrate = ? "
			+ "WHERE committeeid = ?");
				
			// Set ? Values
			updateVoluntarySQL.setString(1, committeeFullName);
			updateVoluntarySQL.setString(2, committeePhoneNum);
			updateVoluntarySQL.setDate(3, committeeBirthDate);
			updateVoluntarySQL.setString(4, committeeAddress);
			updateVoluntarySQL.setString(5, committeeEmail);
			updateVoluntarySQL.setString(6, committeePassword);
			updateVoluntarySQL.setInt(7, managerID);
			updateVoluntarySQL.setString(8, voluntaryRole);
			updateVoluntarySQL.setDouble(9, hourlyRate);
			updateVoluntarySQL.setInt(10, committeeID);
				
			// Execute SQL
			updateVoluntarySQL.executeUpdate();
			System.out.println(updateVoluntarySQL);
				
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// Delete Committee-Management Account
	// Only Manager can do this
	public void deleteVoluntary(int committeeID) throws SQLException {
		
		try {
			
			// Get Connection
			connection = ConnectionManager.getConnection();
			
			// Prepare SQL Statement
			PreparedStatement deleteVoluntarySQL = connection.prepareStatement
			( "DELETE FROM voluntary "
			+ "WHERE committeeid = ?");

			// Set ? values
			deleteVoluntarySQL.setInt(1, committeeID);
			
			// Execute SQL
			deleteVoluntarySQL.executeUpdate();
			System.out.println(deleteVoluntarySQL);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
