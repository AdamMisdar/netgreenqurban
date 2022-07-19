package qurban.servlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import qurban.dao.*;
import qurban.javabean.*;

@WebServlet("/AnimalDetailsServlet")
public class AnimalDetailsHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AnimalDetailsDAO animalDetailsDAO;
    
    public AnimalDetailsHandler() {
        super();
        animalDetailsDAO = new AnimalDetailsDAO();
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
			case "createAnimalDetails":
				createAnimalDetails(request, response); 
				break;
				
			case "updateAnimalDetails":
				updateAnimalDetails(request, response);
				break;
				
			case "deleteAnimalDetails":
				deleteAnimalDetails(request, response);
				break;
				
			case "cancel":
				cancel(request, response);
				break;
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// CRUD METHODS ----------------------------------------------------------------------
	
	// Create Animal Details (Add/Create New Animal)
	private void createAnimalDetails(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, SQLException, IOException {
		
		// Get values from JSP
		String animalType = request.getParameter("animalType");
		double animalPrice = Double.parseDouble(request.getParameter("animalPrice"));
		
		// Create New AnimalDetails Object
		AnimalDetails newAnimal = new AnimalDetails(animalType, animalPrice);
		
		// Send to DAO
		animalDetailsDAO.createAnimalDetails(newAnimal);
		
		// Redirect back to JSP after successful completion
		response.sendRedirect("animal-details-list.jsp");
	}
	
	// Update Animal Details (Update the Price and Supplier Name of Existing Animal)
	private void updateAnimalDetails(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {
		
		// Get values from JSP
		int animalDetailsID = Integer.parseInt(request.getParameter("animalDetailsID"));
		double animalPrice = Double.parseDouble(request.getParameter("animalPrice"));
		
		// Create AnimalDetails Object
		AnimalDetails existingAnimal = new AnimalDetails();
		
		existingAnimal.setAnimalDetailsID(animalDetailsID);
		existingAnimal.setAnimalPrice(animalPrice);
		
		// Send to DAO
		animalDetailsDAO.updateAnimalDetails(existingAnimal);
		
		// Redirect to JSP after successful completion
		response.sendRedirect("animal-details-list.jsp");
		
	}
	
	// Delete Animal Details (Delete Existing Animal)
	private void deleteAnimalDetails(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {
		
		// Get Values from JSP
		int animalDetailsID = Integer.parseInt(request.getParameter("animalDetailsID"));
		
		// Send to DAO
		animalDetailsDAO.deleteAnimalDetails(animalDetailsID);
		
		// Redirect Back to JSP after successful completion
		response.sendRedirect("animal-details-list.jsp");
	
	}
	
	// Cancel Button
	private void cancel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {
		response.sendRedirect("animal-details-list.jsp");
	}
	
}
