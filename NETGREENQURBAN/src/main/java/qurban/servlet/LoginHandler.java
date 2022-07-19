package qurban.servlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import qurban.connection.ConnectionManager;
import qurban.dao.*;
import qurban.javabean.*;


@WebServlet("/LoginHandler")
public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public LoginHandler() {
        super();
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
			
				case "login":
					login(request, response); 
					break;	
					
				case "logout":
					logout(request, response); 
					break;
				}
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	// ADDITIONAL METHODS ---------------------------------------------
	
	// Login
	private void login(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, SQLException, IOException {
		
		// Get values
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		// Create Session
		HttpSession session = request.getSession();
		
		// Create dispatcher
		RequestDispatcher toPage = null;
		
		try {
			// Get Connection
			Connection connection = ConnectionManager.getConnection();
			
			// Prepare Statement - Check Client
			PreparedStatement checkClientSQL = connection.prepareStatement
			( "SELECT * FROM client WHERE clientemail = ? AND clientpassword = ?");
			
			// Check Committee
			PreparedStatement checkCommitteeSQL = connection.prepareStatement
			( "SELECT * FROM committee WHERE committeeemail = ? AND committeepassword = ?");
			
			// Set ? values
			checkClientSQL.setString(1, email);
			checkClientSQL.setString(2, password);
			
			checkCommitteeSQL.setString(1, email);
			checkCommitteeSQL.setString(2, password);
			
			System.out.println(checkClientSQL);
			System.out.println(checkCommitteeSQL);
		
			// Execute Query
			ResultSet resultClient = checkClientSQL.executeQuery();
			ResultSet resultCommittee = checkCommitteeSQL.executeQuery();
			
			boolean loginCommittee = false;
			boolean loginClient = false;
			
			if(resultClient.next()) {
				loginClient = true;
			}
			
			if(resultCommittee.next()) {
				loginCommittee = true;
			}
			
	
			// If client exists
			if(loginClient) {
				
				// Set Client for this session
				session.setAttribute("clientID", resultClient.getInt(1));
						
				// Redirect to client's home page
				toPage = request.getRequestDispatcher("index-client.jsp");
				toPage.forward(request, response);
				
				System.out.println("Ada client");
				
			}

			// If committee exists
			else if (loginCommittee) {
				
				// Set Client for this session
				session.setAttribute("committeeID", resultCommittee.getInt(1));		
				
				// Redirect to client's home page
				toPage = request.getRequestDispatcher("index-committee.jsp");
				toPage.forward(request, response);
				
				System.out.println("Ada committee");
				
			}
			
			else {			
					
			// Set status: failed login
			request.setAttribute("status", "loginfailed");
					
			//Redirect back to login page (non-existent client)
			toPage = request.getRequestDispatcher("login.jsp");
			toPage.forward(request, response);
					
			System.out.println("client/committee does not exist");
			
			}
				
			
				
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	// Logout
	private void logout(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, SQLException, IOException {
		
		// Get session
		HttpSession session = request.getSession();
		
		// Invalidate session
		session.invalidate();
		
		// Redirect to login page
		response.sendRedirect("login.jsp");
	}
	

}
