package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.ProductDAO;
import model.CartItemDetail;

/**
 * Servlet implementation class CheckOutServlet
 */
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			viewPayment(request,response);
		} catch (Exception e) {
			throw new ServletException();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	 protected void viewPayment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDAO cartDAO = new ProductDAO();
		HttpSession session = request.getSession();
		HashMap<String, Integer> cartItems = (HashMap<String, Integer>) session.getAttribute("cartItems");
		List<CartItemDetail> cartItemDetails = cartDAO.getAllProductInCartByID(cartItems);
		request.setAttribute("CartItemDetails", cartItemDetails);
		System.out.println(cartItemDetails.size());
		RequestDispatcher dispatcher = request.getRequestDispatcher("payment.jsp");
		dispatcher.forward(request, response);
	} 
		// TODO Auto-generated method stub

	
}
