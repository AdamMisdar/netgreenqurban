package qurban.servlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import qurban.dao.*;
import qurban.javabean.*;


@WebServlet("/PaymentHandler")
@MultipartConfig(maxFileSize = 16177216) // 16MB of size
public class PaymentHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
      private PaymentDAO paymentDAO;

    public PaymentHandler() {
        super();
        paymentDAO = new PaymentDAO();
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
			case "addPayment":
				addPayment(request, response); 
				break;
													
			}
							
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// CRUD -----------------------------------------------------------------
	private void addPayment(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, SQLException, IOException {
		
		// Get values from JSP
		double paymentTotal = Double.parseDouble(request.getParameter("paymentTotal"));
		Date paymentDate = Date.valueOf(request.getParameter("paymentDate"));
		Part paymentReceipt = request.getPart("paymentReceipt");
		int bookingID = Integer.parseInt(request.getParameter("bookingID"));
		
		// Create new Payment object
		Payment newPayment = new Payment(paymentTotal, paymentDate, paymentReceipt, bookingID);
		
		// Send to DAO
		paymentDAO.addPayment(newPayment);
		
		// Redirect to booking list
		RequestDispatcher toBookingList = request.getRequestDispatcher("client-booking-list.jsp");
		toBookingList.forward(request, response);
	}

	
}
