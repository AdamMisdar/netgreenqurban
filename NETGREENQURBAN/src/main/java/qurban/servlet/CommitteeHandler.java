package qurban.servlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import qurban.dao.*;
import qurban.javabean.*;


@WebServlet("/CommitteeHandler")
public class CommitteeHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommitteeManagementDAO comManagementDAO;
	private CommitteeVoluntaryDAO comVoluntaryDAO;
	boolean managerEdit = false;
	boolean accountEdit = false;
	
	HttpSession session = null;
       

    public CommitteeHandler() {
        super();
        comManagementDAO = new CommitteeManagementDAO();
        comVoluntaryDAO = new CommitteeVoluntaryDAO();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Set content type
		response.setContentType("text/html");
				
		// Get action
		String action = request.getParameter("action");
		System.out.println(action);
		
		// Filter action
		try {
									
			switch(action) {
							
			// Create - Manager creates committee account
			case "createCommittee":
				createCommittee(request, response);
				break;
				
			// ------------------------------------------------	
				
			case "viewManagement":
				viewManagement(request, response);
				break;
				
				// Update Management (Manager Only) can fully update this account
			case "updateManagementManagerOnly":
				updateManagementManagerOnly(request, response);
				break;
				
			// Update Management Account (Normal)
			case "updateManagement":
				updateManagement(request, response);		
				break;
				
			// Delete Management (Manager Only)
			case "deleteManagement":
				deleteManagement(request, response);
				break;
				
			case "editManagement":
				managerEdit = true;
				viewManagement(request, response);
				break;
				
			case "editManagementAcc":
				accountEdit = true;
				viewManagement(request, response);
				break;
				
				
			// 	--------------------------------------------------
				
			case "viewVoluntary":
				viewVoluntary(request, response);
				break;
				
			case "editVoluntary":
				managerEdit = true;
				viewVoluntary(request, response);
				break;
				
				// Update Voluntary (Manager Only) can fully update this account
			case "updateVoluntaryManagerOnly":
				updateVoluntaryManagerOnly(request, response);
				break;
				
			// Update Voluntary (Normal)
			case "updateVoluntary":
				updateVoluntary(request, response);
				break;
			
			// Delete Voluntary (Manager Only)
			case "deleteVoluntary":
				deleteVoluntary(request, response);
				break;
				
			
				
			}
			
									
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	// Methods -----------------------------------------------
	
	// ############################## ALL COMMITTEES ##############################
	// CREATE ----------------------------------------------------------------
	
	// Create committee - only manager can do this
	private void createCommittee(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, SQLException, IOException {
		
		// Get Values from JSP
		String committeeType = request.getParameter("committeeType");
		System.out.println(committeeType);
		
		if (committeeType.equalsIgnoreCase("management")) {
			
			// Get values from JSP
			String committeeFullName = request.getParameter("committeeFullName");
			String committeePhoneNum = request.getParameter("committeePhoneNum");
			Date committeeBirthDate = Date.valueOf(request.getParameter("committeeBirthDate"));
			String committeeAddress = request.getParameter("committeeAddress");
			int managerID = Integer.parseInt(request.getParameter("managerID"));
			String committeeEmail = request.getParameter("committeeEmail");
			String committeePassword = request.getParameter("committeePassword");
			String managementPosition = request.getParameter("committeePosition");
			
			// Create New Committee Object
			Management newManagement = new Management
			(committeeFullName, committeePhoneNum, committeeBirthDate, committeeAddress, 
			managerID, committeeEmail, committeePassword, managementPosition);
			
			// Send to DAO
			comManagementDAO.createManagement(newManagement);
			
			// Redirect to login page
			response.sendRedirect("committee-list.jsp");
			
		}
		else if (committeeType.equalsIgnoreCase("voluntary")) {
			
			// Get values from JSP
			String committeeFullName = request.getParameter("committeeFullName");
			String committeePhoneNum = request.getParameter("committeePhoneNum");
			Date committeeBirthDate = Date.valueOf(request.getParameter("committeeBirthDate"));
			String committeeAddress = request.getParameter("committeeAddress");
			int managerID = Integer.parseInt(request.getParameter("managerID"));
			String committeeEmail = request.getParameter("committeeEmail");
			String committeePassword = request.getParameter("committeePassword");
			String voluntaryRole = request.getParameter("voluntaryRole");
			double hourlyRate = Double.parseDouble(request.getParameter("hourlyRate"));
			
			// Create New Committee Object
			Voluntary newVoluntary = new Voluntary
			(committeeFullName, committeePhoneNum, committeeBirthDate, committeeAddress, 
			managerID, committeeEmail, committeePassword, hourlyRate, voluntaryRole);
			
			// Send to DAO
			comVoluntaryDAO.createVoluntary(newVoluntary);
			
			// Redirect to login page
			response.sendRedirect("committee-list.jsp");
		}
		
	}
	
	// ############################## MANAGEMENT ##############################
	
	// VIEW
	private void viewManagement(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, SQLException, IOException {
		
		// Get values
		int committee_id = Integer.parseInt(request.getParameter("ID"));
		
		// Redirect
		request.setAttribute("ID_committee", committee_id);
		
		if(managerEdit) {
			
			RequestDispatcher toPage = request.getRequestDispatcher("edit-committee-management.jsp");
			toPage.forward(request, response);
			
		} else if(accountEdit) {
			
			RequestDispatcher toPage = request.getRequestDispatcher("edit-committee-management-account.jsp");
			toPage.forward(request, response);
			
		}
		
		else {
		
			RequestDispatcher toPage = request.getRequestDispatcher("view-committee-management.jsp");
			toPage.forward(request, response);
		
		}
		
		managerEdit = false;
		
	}
	
	
	// UPDATE ----------------------------------------------------------------
	
	// Update Management (Manager Only)
	private void updateManagementManagerOnly(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, SQLException, IOException {
		
		// Get values of JSP
		int committeeID = Integer.parseInt(request.getParameter("committeeID"));
		String committeeFullName = request.getParameter("committeeFullName");
		String committeePhoneNum = request.getParameter("committeePhoneNum");
		Date committeeBirthDate = Date.valueOf(request.getParameter("committeeBirthDate"));
		String committeeAddress = request.getParameter("committeeAddress");
		String committeeEmail = request.getParameter("committeeEmail");
		String committeePassword = request.getParameter("committeePassword");
		String managementPosition = request.getParameter("managementPosition");
		String isPengerusi = request.getParameter("isPengerusi");
		
		if (isPengerusi.equalsIgnoreCase("no")) {
			int managerID = Integer.parseInt(request.getParameter("managerID"));
		
			// Create Management Object
			Management existingManagement = new Management
		    (committeeID, committeeFullName, committeePhoneNum, committeeBirthDate, committeeAddress, 
		    managerID, committeeEmail, committeePassword, managementPosition);
			
			// Send to DAO
			comManagementDAO.updateManagement_ManagerOnly(existingManagement);
		}
		
		else if (isPengerusi.equalsIgnoreCase("yes")) {
			
			// Create Management Object
			Management pengerusi = new Management();
			
			pengerusi.setCommitteeID(committeeID);
			pengerusi.setCommitteeFullName(committeeFullName);
			pengerusi.setCommitteePhoneNum(committeePhoneNum);
			pengerusi.setCommitteeBirthDate(committeeBirthDate);
			pengerusi.setCommitteeAddress(committeeAddress);
			pengerusi.setCommitteeEmail(committeeEmail);
			pengerusi.setCommitteePassword(committeePassword);
			pengerusi.setManagementPosition(managementPosition);
			
			// Send to DAO
			comManagementDAO.updatePengerusi(pengerusi);
		
		}

		
		// Redirect back to view account
		response.sendRedirect("committee-list.jsp");
		
	}
	
	// Update Management Account (Normal Management Account Update)
	private void updateManagement(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, SQLException, IOException {
			
		// Get values of JSP
		int committeeID = Integer.parseInt(request.getParameter("committeeID"));
		String committeeFullName = request.getParameter("committeeFullName");
		String committeePhoneNum = request.getParameter("committeePhoneNum");
		Date committeeBirthDate = Date.valueOf(request.getParameter("committeeBirthDate"));
		String committeeAddress = request.getParameter("committeeAddress");
		String committeeEmail = request.getParameter("committeeEmail");
		String committeePassword = request.getParameter("committeePassword");
			
		// Create Management Object
		Management existingManagement = new Management();
		
		existingManagement.setCommitteeID(committeeID);
		existingManagement.setCommitteeFullName(committeeFullName);
		existingManagement.setCommitteePhoneNum(committeePhoneNum);
		existingManagement.setCommitteeBirthDate(committeeBirthDate);
		existingManagement.setCommitteeAddress(committeeAddress);
		existingManagement.setCommitteeEmail(committeeEmail);
		existingManagement.setCommitteePassword(committeePassword);
			
		// Send to DAO
		comManagementDAO.updateManagement(existingManagement);
			
		// Redirect back to view account
		response.sendRedirect("view-committee-management-account.jsp");
			
		}
	
	// DELETE ---------------------------------------------------------------- 
	
	// Delete Management Account
	private void deleteManagement(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, SQLException, IOException {
			
		// Get values from JSP
		int committeeID = Integer.parseInt(request.getParameter("ID"));
					
		// Send to DAO
		comManagementDAO.deleteManagement(committeeID);;
					
		// Redirect to home page
		response.sendRedirect("committee-list.jsp");
	}
	
	// ############################## VOLUNTARY ##############################
	
	// VIEW
	private void viewVoluntary(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, SQLException, IOException {
		
		// Get values
		int committee_id = Integer.parseInt(request.getParameter("ID"));
		
		// Redirect
		request.setAttribute("ID_committee", committee_id);
		
		
		if(managerEdit) {
			
			RequestDispatcher toPage = request.getRequestDispatcher("edit-committee-voluntary.jsp");
			toPage.forward(request, response);
			
		} else {
		
			RequestDispatcher toPage = request.getRequestDispatcher("view-committee-voluntary.jsp");
			toPage.forward(request, response);
		
		}
		
	}
	
	
	// UPDATE --------------------------------------------------------------
	
	// Update Voluntary (Manager Only)
	private void updateVoluntaryManagerOnly(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, SQLException, IOException {
	
		// Get values
		int committeeID = Integer.parseInt(request.getParameter("ID"));
		String committeeFullName = request.getParameter("committeeFullName");
		String committeePhoneNum = request.getParameter("committeePhoneNum");
		Date committeeBirthDate = Date.valueOf(request.getParameter("committeeBirthDate"));
		String committeeAddress = request.getParameter("committeeAddress");
		int managerID = Integer.parseInt(request.getParameter("managerID"));
		String committeeEmail = request.getParameter("committeeEmail");
		String committeePassword = request.getParameter("committeePassword");
		double hourlyRate = Double.parseDouble(request.getParameter("hourlyRate"));
		String voluntaryRole = request.getParameter("voluntaryRole");
		
		// Create Voluntary Object
		Voluntary existingVoluntary = new Voluntary
		(committeeID, committeeFullName, committeePhoneNum, committeeBirthDate, committeeAddress,
		managerID, committeeEmail, committeePassword, hourlyRate, voluntaryRole);
		
		// Send to DAO
		comVoluntaryDAO.updateVoluntary_ManagerOnly(existingVoluntary);
		
		// Redirect back to view account
		response.sendRedirect("view-committee-voluntary-list.jsp");
		
	}	
	
	// Update Voluntary Account (Normal)
	private void updateVoluntary(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, SQLException, IOException {
	
		// Get values
		int committeeID = Integer.parseInt(request.getParameter("committeID"));
		String committeeFullName = request.getParameter("committeeFullName");
		String committeePhoneNum = request.getParameter("committeePhoneNum");
		Date committeeBirthDate = Date.valueOf(request.getParameter("committeeBirthDate"));
		String committeeAddress = request.getParameter("committeeAddress");
		String committeeEmail = request.getParameter("committeeEmail");
		String committeePassword = request.getParameter("committeePassword");

		
		// Create Voluntary Object
		Voluntary existingVoluntary = new Voluntary();
		
		existingVoluntary.setCommitteeID(committeeID);
		existingVoluntary.setCommitteeFullName(committeeFullName);
		existingVoluntary.setCommitteePhoneNum(committeePhoneNum);
		existingVoluntary.setCommitteeBirthDate(committeeBirthDate);
		existingVoluntary.setCommitteeAddress(committeeAddress);
		existingVoluntary.setCommitteeEmail(committeeEmail);
		existingVoluntary.setCommitteePassword(committeePassword);
		
		// Send to DAO
		comVoluntaryDAO.updateVoluntary_ManagerOnly(existingVoluntary);
		
		// Redirect back to view account
		response.sendRedirect("committee-account.jsp");
		
	}	
	
	// DELETE --------------------------------------------------------------
	
	// Delete Voluntary (Manager Only)
	private void deleteVoluntary(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, SQLException, IOException {
		
		// Get values from JSP
		int committeeID = Integer.parseInt(request.getParameter("ID"));
						
		// Send to DAO
		comVoluntaryDAO.deleteVoluntary(committeeID);;
						
		// Redirect to home page
		response.sendRedirect("committee-list.jsp");
	}
	
	// ####### ADDITIONAL METHODS #################
	
	
	
	

}
