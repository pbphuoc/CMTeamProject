//package controller;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import dao.OrderDAO;
//import dao.ProductDAO;
//import entity.Order;
//import model.OrderItemDTO;
//import util.Utility;
//
///**
// * Servlet implementation class ConfirmationServlet
// */
//@WebServlet("/Confirmation")
//public class ConfirmationServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public ConfirmationServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		try {
//			reviewOrder(request,response);
//		} catch (Exception e) {
//			throw new ServletException();
//		}
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
//	protected void reviewOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		ProductDAO cartDAO = new ProductDAO();
//		
//		HttpSession session = request.getSession();
//		HashMap<String, Integer> cartItems = (HashMap<String, Integer>) session.getAttribute("cartItems");
//		List<OrderItemDTO> items = cartDAO.getAllProductInCartByID(cartItems);	
//		
//		String id = "";
//		String orderNumber = "";
//		String date = "";
//		String checkOutEmail = request.getParameter("email");
//		
//		String checkOutFullname = "";
//		String checkOutPhone = "";
//		String receiverFullname = request.getParameter("receiverFirstName") + request.getParameter("receiverLastName");
//		
//		String receiverPhone = request.getParameter("receiverPhoneNumber");
//		String receiverAddress = request.getParameter("receiverAddress");
//		String receiveMethod = Utility.RECEIVEMETHOD_MAP.get(request.getParameter("deliveryMethod"));
//		String paymentType = Utility.PAYMENT_MAP.get(request.getParameter("paymentMethod"));
//		String paymentDate = "";
//		String status = "";
//		double shipping = 0;
//		double total = 0;	
//		String billingFullname = request.getParameter("billingFname") + request.getParameter("billingLname");
//		String billingAddress = request.getParameter("billingAddress");
//		String billingPhone = request.getParameter("billingPhone");
//		String paymentName = request.getParameter("cardHolderName");
//		String paymentSource = request.getParameter("cardNumber");
//		
//		Order order = new Order(id, orderNumber, date, checkOutEmail, checkOutFullname,
//					checkOutPhone, receiverFullname, receiverPhone, receiverAddress,
//					receiveMethod, status,shipping, total, paymentType,paymentDate,paymentName,paymentSource, billingFullname,billingAddress,billingPhone);
//		 
//		request.setAttribute("order",order);
//		request.setAttribute("items", items);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("confirmation.jsp");
//		dispatcher.forward(request, response);
//	} 
//
//}
