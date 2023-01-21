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

import dao.OrderDAO;
import dao.ProductDAO;
import entity.Order;
import model.OrderItemDTO;
import util.Utility;

/**
 * Servlet implementation class ConfirmationServlet
 */
@WebServlet("/Confirmation")
public class ConfirmationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			viewCheckOut(request,response);
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
	protected void viewCheckOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDAO cartDAO = new ProductDAO();
		
		HttpSession session = request.getSession();
		HashMap<String, Integer> cartItems = (HashMap<String, Integer>) session.getAttribute("cartItems");
		List<OrderItemDTO> items = cartDAO.getAllProductInCartByID(cartItems);
		
		/*//String orderNumber E, String dateE, String checkOutEmailP, String checkOutFullnameE,
				String checkOutPhoneE, String receiverFullnameP, String receiverPhoneP, String receiverAddressP,
				String receiveMethod Utility, String paymentTypeUtility, String paymentDate E, String statusE, double shippingE,
				double total*/		
		
		String orderNumber = "";
		String date = "";
		String checkOutEmail = request.getParameter("email");
		
		String checkOutFullname = "";
		String checkOutPhone = "";
		String receiverFullname = request.getParameter("receiverFirstName") + request.getParameter("receiverLastName");
		
		String receiverPhone = request.getParameter("receiverPhoneNumber");
		String receiverAddress = request.getParameter("receiverAddress");
		String receiveMethod = Utility.RECEIVEMETHOD_MAP.get(request.getParameter("deliveryMethod"));
		System.out.println(receiveMethod);
		String paymentType = Utility.PAYMENT_MAP.get(request.getParameter("paymentMethod"));
		String paymentDate = "";
		String status = "";
		double shipping = 0;
		double total = 0;	
		
		
		/*
		 * Order order = new Order(String orderNumber, String date, String
		 * checkOutEmail, String checkOutFullname, String checkOutPhone, String
		 * receiverFullname, String receiverPhone, String receiverAddress, String
		 * receiveMethod, String paymentType, String paymentDate, String status, double
		 * shipping, double total);
		 */
		
		 Order order = new Order("",orderNumber, date, checkOutEmail, checkOutFullname,
				 checkOutPhone, receiverFullname, receiverPhone, receiverAddress,
				 receiveMethod, paymentType, paymentDate, status, shipping, total);
		 
		 request.setAttribute("order",order);
		 
		request.setAttribute("items", items);
		RequestDispatcher dispatcher = request.getRequestDispatcher("confirmation.jsp");
		dispatcher.forward(request, response);
	} 

}
