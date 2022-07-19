/*	editAnimalOrder, viewAnimalOrderCommittee, viewbyclient
 *  booking kene wujud dulu
 */

package qurban.servlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import qurban.dao.*;
import qurban.javabean.*;


@WebServlet("/AnimalOrderHandler")
public class AnimalOrderHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AnimalOrderDAO animalOrderDAO;
	private BookingDAO bookingDAO;
	HttpSession session;
	boolean isCommittee = false;
       

    public AnimalOrderHandler() {
        super();
        animalOrderDAO = new AnimalOrderDAO();
        bookingDAO = new BookingDAO();
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
			case "addAnimalOrder":
				addAnimalOrder(request, response); 
				break;
						
			case "updateAnimalOrder":
				updateAnimalOrder(request, response);
				break;
				
			case "updateAnimalOrderCommittee":
				isCommittee = true;
				updateAnimalOrder(request, response);
				break;
				
			case "viewAnimalOrder":
				viewAnimalOrder(request, response);
				break;
				
			case "viewAnimalOrderCommittee":
				isCommittee = true;
				viewAnimalOrder(request, response);
				break;
				
			case "deleteAnimalOrder":
				deleteAnimalOrder(request, response);
				break;
						
			case "cancelUpdate":
				cancelUpdate(request, response);
				break;
				
			case "cancelUpdateCommittee":
				isCommittee = true;
				cancelUpdate(request, response);
				break;
					
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// CRUD METHODS -------------------------------------------------------------------------
	
	// Create/Add New Animal Order - Insert Multiple Values
	private void addAnimalOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {
		
		// Get values from JSP
		String dependentName = request.getParameter("dependentName");		// Nama tanggungan
		int bookingID = Integer.parseInt(request.getParameter("bookingID"));
		int animalDetailsID = Integer.parseInt(request.getParameter("animalDetailsID"));
		
		// Create New Animal Order Object
		AnimalOrder newAnimalOrder = new AnimalOrder();
			
		newAnimalOrder.setDependentName(dependentName);
		newAnimalOrder.setBookingID(bookingID);
		newAnimalOrder.setAnimalDetailsID(animalDetailsID);
		
		// Send to DAO
		animalOrderDAO.addAnimalOrder(newAnimalOrder);
		
		// Redirect to Booking Servlet to add booking
		request.setAttribute("bookingID", bookingID);
		RequestDispatcher toBooking = request.getRequestDispatcher("create-booking.jsp");
		toBooking.forward(request, response);
		
	}
	
	// View Animal Order (Committee & Client)
	public void viewAnimalOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {
		
		// Get values
		int animalOrderID = Integer.parseInt(request.getParameter("animalOrderID"));
		int bookingID = Integer.parseInt(request.getParameter("bookingID"));
		
		// Set attribute
		request.setAttribute("animalOrderID", animalOrderID);
		request.setAttribute("bookingID", bookingID);
		
		// Redirect
		if (isCommittee) {
			// Get extra values
			int committeeID = (int)session.getAttribute("committeeID");
						
			// Create object
			Booking existingBooking = new Booking();
						
			existingBooking.setBookingID(bookingID);
			existingBooking.setCommitteeID(committeeID);
						
			// Update committeeID in booking
			bookingDAO.updateBooking(existingBooking);
			
			RequestDispatcher toPage = request.getRequestDispatcher("edit-committee-animal-order.jsp");
			toPage.forward(request, response);
		} 
		else {
			RequestDispatcher toPage = request.getRequestDispatcher("edit-client-animal-order.jsp");
			toPage.forward(request, response);
		}
		
	}
	
	// Update Animal Order (Committee & Client)
	private void updateAnimalOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {
		
		// Get values
		int bookingID = Integer.parseInt(request.getParameter("bookingID"));
		String dependentName = request.getParameter("dependentName");
		int animalOrderID = Integer.parseInt(request.getParameter("animalOrderID"));
		
		// Create Animal Order
		AnimalOrder existingAnimalOrder = new AnimalOrder();
			
			// Set values
			existingAnimalOrder.setDependentName(dependentName);
			existingAnimalOrder.setAnimalOrderID(animalOrderID);
					
			// Send to DAO
			animalOrderDAO.updateAnimalOrder(existingAnimalOrder);
					
			// Redirect to page
			request.setAttribute("bookingID", bookingID);
			
		if(isCommittee) {
			// Get extra values
			int committeeID = (int)session.getAttribute("committeeID");
									
			// Create object
			Booking existingBooking = new Booking();
									
			existingBooking.setBookingID(bookingID);
			existingBooking.setCommitteeID(committeeID);
									
			// Update committeeID in booking
			bookingDAO.updateBooking(existingBooking);
			
			RequestDispatcher toPage = request.getRequestDispatcher("view-committee-booking.jsp");
			toPage.forward(request, response);
			
		} else {
			RequestDispatcher toPage = request.getRequestDispatcher("view-client-booking.jsp");
			toPage.forward(request, response);
		
		}
		
	}
	
	// Delete Animal Order (Committee Only) 
	private void deleteAnimalOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {
		
		// Get values from JSP
		int animalOrderID = Integer.parseInt(request.getParameter("animalOrderID"));
		int bookingID = Integer.parseInt(request.getParameter("bookingID"));
		
		// Send to DAO
		animalOrderDAO.deleteAnimalOrder(animalOrderID);
		
		// Redirect back to booking
		request.setAttribute("bookingID", bookingID);
		
		RequestDispatcher toBookingPage = request.getRequestDispatcher("create-booking.jsp");
		toBookingPage.forward(request, response);
		
	}
	
	// Cancel Update Animal Order by Client
	private void cancelUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {
		
		// Get values
		int bookingID = Integer.parseInt(request.getParameter("bookingID"));
		
		// Redirect back to booking
		request.setAttribute("bookingID", bookingID);
		
		if (isCommittee) {
			RequestDispatcher toPage = request.getRequestDispatcher("view-committee-booking.jsp");
			toPage.forward(request, response);
			
		} else {
			RequestDispatcher toPage = request.getRequestDispatcher("view-client-booking.jsp");
			toPage.forward(request, response);
		}
	}
	

}
