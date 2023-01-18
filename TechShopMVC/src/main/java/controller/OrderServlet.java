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

import dao.DAOService;
import dao.OrderDAO;
import dao.ProductDAO;
import dao.DAO.DAOType;
import dao.DAO.QueryResult;
import model.CartItemDTO;
import model.OrderDTO;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("-----------------------------");
		System.out.println("doGet Order Servlet called");
		System.out.println("Current command: " + request.getParameter("command"));
		String command = request.getParameter("command") != null ? request.getParameter("command") : "";
		try {
			switch (command) {
			case "submitOrder":			
				submitOrder(request, response);
				break;	
			case "trackOrder":			
				trackOrder(request, response);
				break;				
			case "":
//				getLoginPage(request, response);
				break;
			}			
		} catch (Exception e) {
			// TODO: handle exception
			throw new ServletException(e);
		}				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void submitOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderDAO orderDAO = (OrderDAO) DAOService.getDAO(DAOType.ORDER);
		ProductDAO productDAO = (ProductDAO) DAOService.getDAO(DAOType.PRODUCT);
		String checkOutEmail = "ff.pbphuoc@gmail.com";
		String checkOutFullname = "Phuoc Pham";
		String checkOutPhone = "04987654321";
		String receiverFullname = "Chau Vuong";
		String receiverPhone = "04123456789";
		String receiverAddress = "123 abc street, inala, qld, 4077";
		String receiverMethodId = "1";
		String paymentTypeId = "1";
		String paymentDate = "";
		String shipping = "0";
		HttpSession session = request.getSession();
		HashMap<String, Integer> cartItems = (HashMap<String, Integer>) session.getAttribute("cartItems");
		List<CartItemDTO> cartItemsDetail = productDAO.getAllProductInCartByID(cartItems);		
		QueryResult result = orderDAO.insertOrder(checkOutEmail, checkOutFullname, checkOutPhone, receiverFullname, receiverPhone, receiverAddress, receiverMethodId, paymentTypeId, paymentDate, shipping, cartItemsDetail);
		System.out.println(result);
	}
	
	protected void trackOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String emailAddress = request.getParameter("email") != null ? request.getParameter("email") : "";
		String orderNumber = request.getParameter("orderNumber") != null ? request.getParameter("orderNumber") : "";
		OrderDAO orderDAO = (OrderDAO) DAOService.getDAO(DAOType.ORDER);
		OrderDTO order = orderDAO.getOrderByUserEmailAndOrderNumber(emailAddress,orderNumber);
		List<OrderDTO> orders = new ArrayList<OrderDTO>();
		orders.add(order);
		request.setAttribute("orderList", orders);
		RequestDispatcher dispatcher = request.getRequestDispatcher("orderhistory.jsp");
		dispatcher.forward(request, response);
	}		

}
