/*	REQUIRES ATTENTION!!
 * 
 *	- ClientHandler
 *	- ClientDAO
 */	


package qurban.servlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import qurban.dao.*;
import qurban.javabean.*;


@WebServlet("/ClientHandler")
public class ClientHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClientDAO clientDAO;
	boolean managerEdit = false;
	HttpSession session = null;
	
    public ClientHandler() {
        super();
        clientDAO = new ClientDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Set content type
		response.setContentType("text/html");
		
		// Get action
		String action = request.getParameter("action");
		
		// Filter action
		try {
			
			switch(action) {
			
			case "createClient":
				createClient(request, response);
				break;
				
			case "updateClient":
				updateClient(request, response);
				break;
				
			case "updateClientCom":
				managerEdit = true;
				updateClient(request, response);
				break;
			
			case "deleteClient":
				deleteClient(request, response);
				break;
				
			case "cancelUpdate":
				cancelUpdate(request, response);
				break;
				
			case "viewClient":
				viewClient(request, response);
				break;
				
			case "editClient":
				managerEdit = true;
				viewClient(request, response);
				break;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// CRUD METHODS --------------------------------------------------------
	
	// Register/Create Client Account
	private void createClient(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, SQLException, IOException {
		
		// Get values from JSP
		String clientFullName = request.getParameter("clientFullName");
		String clientAddress = request.getParameter("clientAddress");
		String clientPhoneNum = request.getParameter("clientPhoneNum");
		Date clientBirthDate = Date.valueOf(request.getParameter("clientBirthDate"));
		String clientEmail = request.getParameter("clientEmail");
		String clientPassword = request.getParameter("clientPassword");
		
		// Create New Client Object
		Client newClient = new Client(clientFullName, clientAddress, clientPhoneNum, clientBirthDate, clientEmail, clientPassword);
		
		// Send to DAO
		clientDAO.createClient(newClient);
		
		// Redirect to login page
		response.sendRedirect("login.jsp");

	}
	
	// Update Client Account
	private void updateClient(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, SQLException, IOException {
		
		// Get values from JSP
		System.out.println(request.getParameter("clientID")); 
		System.out.println(request.getParameter("clientBirthDate")); 
		System.out.println(request.getParameter("clientFullName"));
		System.out.println(request.getParameter("clientAddress"));
		
		int clientID = Integer.parseInt(request.getParameter("clientID"));
		String clientFullName = request.getParameter("clientFullName");
		String clientAddress = request.getParameter("clientAddress");
		String clientPhoneNum = request.getParameter("clientPhoneNum");
		
		Date clientBirthDate = Date.valueOf(request.getParameter("clientBirthDate"));
		String clientEmail = request.getParameter("clientEmail");
		String clientPassword = request.getParameter("clientPassword");
		
		// Create Client Object
		Client exisitingClient = new Client(clientID, clientFullName, clientAddress, clientPhoneNum, clientBirthDate, clientEmail, clientPassword);
		
		// Send to DAO
		clientDAO.updateClientDetails(exisitingClient);
		
		// Redirect back to view account page
		
		if(managerEdit) {
			response.sendRedirect("client-list.jsp");
		} 
		
		else {
			response.sendRedirect("view-client-account.jsp");
		}

	}
	
	// Delete Client Account (Manager Only)
	private void deleteClient(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, SQLException, IOException {
		
		// Get values from JSP
		int clientID = Integer.parseInt(request.getParameter("clientID"));
		
		// Send to DAO
		clientDAO.deleteClient(clientID);
		
		// Redirect to home page
		response.sendRedirect("client-list.jsp");
		
	}
	
	// Cancel Update Button 
	private void cancelUpdate(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, SQLException, IOException {
		response.sendRedirect("view-account-client.jsp");
	}
	
	// ADDITIONAL METHODS? ----------------------------------------------
	private void viewClient(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, SQLException, IOException {
		
		// Get values
		int client_id = Integer.parseInt(request.getParameter("ID"));
				
		// Redirect
		request.setAttribute("ID_client", client_id);
		
		if(managerEdit) {
			RequestDispatcher toPage = request.getRequestDispatcher("edit-client-manager.jsp");
			toPage.forward(request, response);
		}
			else {
			RequestDispatcher toPage = request.getRequestDispatcher("view-client-manager.jsp");
			toPage.forward(request, response);
		}
		
	}
	
}
