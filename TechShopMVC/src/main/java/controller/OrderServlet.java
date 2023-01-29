package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
 * Servlet implementation class OrderServlet
 */
//@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("-----------------------------");
		System.out.println("doGet Order Servlet called");
		System.out.println("Current command: " + request.getParameter("command"));
		String command = request.getParameter("command") != null ? request.getParameter("command") : "";
		
		try {
			switch (command) {
	
			case "getTrackOrderForm":			
				getTrackOrderForm(request, response);
				break;							
			case "submitOrder":
				submitOrder(request, response);
				break;
			case "trackOrder":
				trackOrder(request, response);
				break;
			case "viewOrderDetail":
				viewOrderDetail(request, response);
				break;				
			case "":
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

//	protected void submitOrder(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		OrderDAO orderDAO = new OrderDAO();
//		ProductDAO productDAO = new ProductDAO();
//		String checkOutEmail = "ff.pbphuoc@gmail.com";
//		String checkOutFullname = "Phuoc Pham";
//		String checkOutPhone = "04987654321";
//		String receiverFullname = "Chau Vuong";
//		String receiverPhone = "04123456789";
//		String receiverAddress = "123 abc street, inala, qld, 4077";
//		String receiverMethodId = Utility.RECEIVEMETHOD_PICKUP;
//		String paymentTypeId = Utility.PAYMENT_UNPAID;
//		String paymentDate = "";
//		String shipping = "0";
//		HttpSession session = request.getSession();
//		HashMap<String, Integer> cartItems = (HashMap<String, Integer>) session.getAttribute("cartItems");
//		List<OrderItemDTO> cartItemsDetail = productDAO.getAllProductInCartByID(cartItems);
//		Utility.QueryResult result = orderDAO.insertOrder(checkOutEmail, checkOutFullname, checkOutPhone,
//				receiverFullname, receiverPhone, receiverAddress, receiverMethodId, paymentTypeId, paymentDate,
//				shipping, cartItemsDetail);
//		System.out.println(result);
//	}

	protected void trackOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String emailAddress = request.getParameter("emailAddress") != null ? request.getParameter("emailAddress") : "";
		String orderNumber = request.getParameter("orderNumber") != null ? request.getParameter("orderNumber") : "";
		OrderDAO orderDAO = new OrderDAO();
		List<Order> orders = orderDAO.getOrderByUserEmailAndOrderNumber(emailAddress, orderNumber);
		if(orders.size() != 0) {
			request.setAttribute("orderList", orders);
			RequestDispatcher dispatcher = request.getRequestDispatcher("orderhistory.jsp");
			dispatcher.forward(request, response);
		}else {
			request.setAttribute("error", "No Order Found");
			RequestDispatcher dispatcher = request.getRequestDispatcher("trackOrder.jsp");
			dispatcher.forward(request, response);				
		}
		
	}
	
	private void getTrackOrderForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("trackOrder.jsp");	
	}	

	protected void submitOrder(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		OrderDAO orderDAO = new OrderDAO();
		ProductDAO productDAO = new ProductDAO();
		
		String checkOutEmail = request.getParameter("checkOutEmail");
		String checkOutFullname = "";
		String checkOutPhone = "";
		String receiverFullname = request.getParameter("receiverFullname");
		String receiverPhone = request.getParameter("receiverPhone");
		String receiverAddress = request.getParameter("receiverAddress");
		String receiveMethodId = request.getParameter("receiveMethod").equals("DELIVERY") ? "2" : "1";
		String shipping = "0";
		String paymentTypeId = request.getParameter("paymentType").equals("CARD")? "1":"0";
		String paymentDate = "";
		String paymentFullname = request.getParameter("cardHolderName");	
		String paymentSource = request.getParameter("cardNumber");	
		String billingFullname = request.getParameter("billingFullname");	
		String billingAddress = request.getParameter("billingPhone");	
		String billingPhone = request.getParameter("billingAddress");	
		HttpSession session = request.getSession();
		HashMap<String, Integer> cartItems = (HashMap<String, Integer>) session.getAttribute("cartItems");
		List<OrderItemDTO> items = productDAO.getAllProductInCartByID(cartItems);	
		
		Order order = orderDAO.insertOrder(checkOutEmail, checkOutFullname, checkOutPhone,
				receiverFullname, receiverPhone, receiverAddress, receiveMethodId,
				shipping, items, paymentTypeId, paymentDate,
				paymentFullname, paymentSource, billingFullname, billingAddress,
				billingPhone);
		if (order != null) {
			request.setAttribute("order",order);
			request.setAttribute("items", items);
			RequestDispatcher dispatcher = request.getRequestDispatcher("confirmation.jsp");
			dispatcher.forward(request, response);		
			session.setAttribute("cartItems", null);
		}
	}
	
	private void viewOrderDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderDAO orderDAO = new OrderDAO();
		String emailAddress = request.getParameter("emailAddress") != null ? request.getParameter("emailAddress") : "";
		String orderNumber = request.getParameter("orderNumber") != null ? request.getParameter("orderNumber") : "";
		Order order = orderDAO.getOrderByUserEmailAndOrderNumber(emailAddress, orderNumber).get(0);
		List<OrderItemDTO> items = orderDAO.getOrderItemByOrderID(order.getId());
		request.setAttribute("order",order);
		request.setAttribute("items", items);
		RequestDispatcher dispatcher = request.getRequestDispatcher("confirmation.jsp");
		dispatcher.forward(request, response);		
	}	
}
