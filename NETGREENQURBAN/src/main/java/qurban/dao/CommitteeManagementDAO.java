package qurban.dao;

import java.sql.*;
import qurban.connection.*;
import qurban.javabean.*;

public class CommitteeManagementDAO {
	
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
	// -----------------------
	String managementPosition;
	
	// CRUD -----------------------------------------------
	
	// Create/Register New Committee-Management Account
	// Only Manager can do this
	public void createManagement(Management newManagement) throws SQLException{
		
		try {
			
			// Get connection
			connection = ConnectionManager.getConnection();
			
			// Get values
			committeeFullName = newManagement.getCommitteeFullName();
			committeePhoneNum = newManagement.getCommitteePhoneNum();
			committeeBirthDate = newManagement.getCommitteeBirthDate();
			committeeAddress = newManagement.getCommitteeAddress();
			managerID = newManagement.getManagerID();
			committeeEmail = newManagement.getCommitteeEmail();
			committeePassword = newManagement.getCommitteePassword();
			managementPosition = newManagement.getManagementPosition();
			
			// Prepare SQL Statements
			PreparedStatement createManagementSQL = connection.prepareStatement
			( "INSERT INTO management "
			+ "(committeefullname, committeephonenum, committeebirthdate, "
			+ "committeeaddress, managerID, committeeemail, committeepassword, managementposition) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			
			// Set ? values
			createManagementSQL.setString(1, committeeFullName);
			createManagementSQL.setString(2, committeePhoneNum);
			createManagementSQL.setDate(3, committeeBirthDate);
			createManagementSQL.setString(4, committeeAddress);
			createManagementSQL.setInt(5, managerID);
			createManagementSQL.setString(6, committeeEmail);
			createManagementSQL.setString(7, committeePassword);
			createManagementSQL.setString(8, managementPosition);
			
			// Execute SQLs
			createManagementSQL.executeUpdate();
			System.out.println(createManagementSQL);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Update Committee Management Details (Update Existing Committee)
	// Management can do this themselves
	public void updateManagement(Management existingManagement) throws SQLException {
		
		try {
			
			// Get connection
			connection = ConnectionManager.getConnection();
			
			// Get values
			committeeID = existingManagement.getCommitteeID();
			committeeFullName = existingManagement.getCommitteeFullName();
			committeePhoneNum = existingManagement.getCommitteePhoneNum();
			committeeBirthDate = existingManagement.getCommitteeBirthDate();
			committeeAddress = existingManagement.getCommitteeAddress();
			committeeEmail = existingManagement.getCommitteeEmail();
			committeePassword = existingManagement.getCommitteePassword();
			
			// Prepare SQL Statement
			PreparedStatement updateManagementSQL = connection.prepareStatement
			( "UPDATE management "
			+ "SET committeefullname = ?, committeephonenum = ?, committeebirthdate = ?, "
			+ "committeeaddress = ?, committeeemail = ?, committeePassword = ? "
			+ "WHERE committeeid = ?");

			// Set ? Values
			updateManagementSQL.setString(1, committeeFullName);
			updateManagementSQL.setString(2, committeePhoneNum);
			updateManagementSQL.setDate(3, committeeBirthDate);
			updateManagementSQL.setString(4, committeeAddress);
			updateManagementSQL.setString(5, committeeEmail);
			updateManagementSQL.setString(6, committeePassword);
			updateManagementSQL.setInt(7, committeeID);
			
			
			// Execute SQL
			updateManagementSQL.executeUpdate();
			System.out.println(updateManagementSQL);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Update Committee Management Details (Update Existing Committee)
	// Only Manager can do this
	public void updateManagement_ManagerOnly(Management existingManagement) throws SQLException {
		
		try {
			
			// Get connection
			connection = ConnectionManager.getConnection();
			
			// Get values
			committeeID = existingManagement.getCommitteeID();
			committeeFullName = existingManagement.getCommitteeFullName();
			committeePhoneNum = existingManagement.getCommitteePhoneNum();
			committeeBirthDate = existingManagement.getCommitteeBirthDate();
			committeeAddress = existingManagement.getCommitteeAddress();
			managerID = existingManagement.getManagerID();
			committeeEmail = existingManagement.getCommitteeEmail();
			committeePassword = existingManagement.getCommitteePassword();
			managementPosition = existingManagement.getManagementPosition();
			
			// Prepare SQL Statement
			PreparedStatement updateManagementSQL = connection.prepareStatement
			( "UPDATE management "
			+ "SET committeefullname = ?, committeephonenum = ?, committeebirthdate = ?, "
			+ "committeeaddress = ?, managerid = ?, committeeemail = ?, committeePassword = ?, managementposition = ? "
			+ "WHERE committeeid = ?");

			// Set ? Values
			updateManagementSQL.setString(1, committeeFullName);
			updateManagementSQL.setString(2, committeePhoneNum);
			updateManagementSQL.setDate(3, committeeBirthDate);
			updateManagementSQL.setString(4, committeeAddress);
			updateManagementSQL.setInt(5, managerID);
			updateManagementSQL.setString(6, committeeEmail);
			updateManagementSQL.setString(7, committeePassword);
			updateManagementSQL.setString(8, managementPosition);
			updateManagementSQL.setInt(9, committeeID);
			
			
			// Execute SQL
			updateManagementSQL.executeUpdate();
			System.out.println(updateManagementSQL);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updatePengerusi(Management existingManagement) throws SQLException {
		
		try {
			
			// Get connection
			connection = ConnectionManager.getConnection();
			
			// Get values
			committeeID = existingManagement.getCommitteeID();
			committeeFullName = existingManagement.getCommitteeFullName();
			committeePhoneNum = existingManagement.getCommitteePhoneNum();
			committeeBirthDate = existingManagement.getCommitteeBirthDate();
			committeeAddress = existingManagement.getCommitteeAddress();
			committeeEmail = existingManagement.getCommitteeEmail();
			committeePassword = existingManagement.getCommitteePassword();
			managementPosition = existingManagement.getManagementPosition();
			
			// Prepare SQL Statement
			PreparedStatement updateManagementSQL = connection.prepareStatement
			( "UPDATE management "
			+ "SET committeefullname = ?, committeephonenum = ?, committeebirthdate = ?, "
			+ "committeeaddress = ?, committeeemail = ?, committeePassword = ?, managementposition = ? "
			+ "WHERE committeeid = ?");

			// Set ? Values
			updateManagementSQL.setString(1, committeeFullName);
			updateManagementSQL.setString(2, committeePhoneNum);
			updateManagementSQL.setDate(3, committeeBirthDate);
			updateManagementSQL.setString(4, committeeAddress);
			updateManagementSQL.setString(5, committeeEmail);
			updateManagementSQL.setString(6, committeePassword);
			updateManagementSQL.setString(7, managementPosition);
			updateManagementSQL.setInt(8, committeeID);
			
			
			// Execute SQL
			updateManagementSQL.executeUpdate();
			System.out.println(updateManagementSQL);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Delete Management Account (Manager Only)
	public void deleteManagement(int committeeID) throws SQLException {
		
		try {
			
			// Get Connection
			connection = ConnectionManager.getConnection();
			
			// Prepare SQL Statement
			PreparedStatement deleteManagementSQL = connection.prepareStatement
			( "DELETE FROM management "
			+ "WHERE committeeid = ?");
			
			// Set ? values
			deleteManagementSQL.setInt(1, committeeID);
			
			// Execute SQL
			deleteManagementSQL.executeUpdate();
			System.out.println(deleteManagementSQL);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
