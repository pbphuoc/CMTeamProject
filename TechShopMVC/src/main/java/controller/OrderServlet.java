package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import constant.GlobalConstant;
import constant.OrderPaymentTypeEnum;
import constant.OrderReceiveMethodEnum;
import dao.OrderDAO;
import dao.ProductDAO;
import entity.Order;
import entity.PaypalResponse;
import entity.User;
import model.OrderItemDTO;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet(urlPatterns = GlobalConstant.ORDER_URL)
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(OrderServlet.class);

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
		System.out.println("Current command: " + request.getParameter(GlobalConstant.COMMAND));
		String command = request.getParameter(GlobalConstant.COMMAND) != null
				? request.getParameter(GlobalConstant.COMMAND)
				: GlobalConstant.BLANK;

		try {
			switch (command) {

			case GlobalConstant.GET_TRACK_ORDER_FORM:
				getTrackOrderForm(request, response);
				break;
			case GlobalConstant.SUBMIT_ORDER:
				submitOrder(request, response);
				break;
			case GlobalConstant.TRACK_ORDER:
				trackOrder(request, response);
				break;
			case GlobalConstant.VIEW_ORDER_DETAIL:
				viewOrderDetail(request, response);
				break;
			case GlobalConstant.BLANK:
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void trackOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String emailAddress = request.getParameter("emailAddress") != null ? request.getParameter("emailAddress")
				: GlobalConstant.BLANK;
		String orderNumber = request.getParameter("orderNumber") != null ? request.getParameter("orderNumber")
				: GlobalConstant.BLANK;
		OrderDAO orderDAO = new OrderDAO();
		List<Order> orders = orderDAO.getOrderByUserOrEmailAndOrderNumber(GlobalConstant.BLANK, emailAddress,
				orderNumber);

		if (orders.size() != 0) {
			request.setAttribute("orderList", orders);
			RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConstant.ORDER_HISTORY_JSP);
			dispatcher.forward(request, response);
		} else {
			request.setAttribute(GlobalConstant.ERROR, GlobalConstant.NO_ORDER_FOUND);
			RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConstant.TRACK_ORDER_JSP);
			dispatcher.forward(request, response);
		}

	}

	private void getTrackOrderForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(GlobalConstant.TRACK_ORDER_JSP);
	}

	protected void submitOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestBodyData = request.getReader().lines().collect(Collectors.joining());
		System.out.println("order data: " + requestBodyData);
		PaypalResponse paypalResponse = new Gson().fromJson(requestBodyData, PaypalResponse.class);

		OrderDAO orderDAO = new OrderDAO();
		ProductDAO productDAO = new ProductDAO();
		HttpSession session = request.getSession();
		HashMap<String, Integer> cartItems = (HashMap<String, Integer>) session.getAttribute("cartItems");
		List<OrderItemDTO> items = productDAO.getAllProductInCartByID(cartItems);

		String userID = (User) session.getAttribute(GlobalConstant.USER) != null
				? ((User) session.getAttribute(GlobalConstant.USER)).getId()
				: GlobalConstant.GUEST_ID;
		String orderNumber = paypalResponse.getOrderOrderNumber();
		String checkOutEmail = paypalResponse.getPayerEmail();
		String checkOutFullname = paypalResponse.getPayerFullname();
		String checkOutPhone = GlobalConstant.BLANK;
		String receiverFullname = paypalResponse.getReceiverFullname();
		String receiverPhone = GlobalConstant.BLANK;
		String receiverAddress = paypalResponse.getReceiverAddress();
		OrderReceiveMethodEnum receiveMethod = OrderReceiveMethodEnum.DELIVERY;
		double shipping = paypalResponse.getShipping();
		double total = paypalResponse.getTotal();
		OrderPaymentTypeEnum paymentType = paypalResponse.getPaymentType();
		String paymentDate = paypalResponse.getPaymentDate();
		String paymentID = paypalResponse.getPaymentID();

		Order order = orderDAO.insertOrder(orderNumber, userID, checkOutEmail, checkOutFullname, checkOutPhone,
				receiverFullname, receiverPhone, receiverAddress, receiveMethod, shipping, total, items, paymentType,
				paymentDate, paymentID);

		if (order != null) {
			session.setAttribute(GlobalConstant.CART_ITEM, null);
			response.getWriter().append(GlobalConstant.ORDER_URL + "?command=" + GlobalConstant.VIEW_ORDER_DETAIL
					+ "&emailAddress=" + checkOutEmail + "&orderNumber=" + orderNumber);
		}

//		if (order != null) {			
//			request.setAttribute(GlobalConstant.ORDER, order);
//			request.setAttribute(GlobalConstant.ORDER_ITEM_DTO, items);
//			session.setAttribute(GlobalConstant.CART_ITEM, null);
//			RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConstant.CONFIRMATION_JSP);
//			dispatcher.forward(request, response);
//		}
	}

	private void viewOrderDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OrderDAO orderDAO = new OrderDAO();
		String emailAddress = request.getParameter("emailAddress") != null ? request.getParameter("emailAddress")
				: GlobalConstant.BLANK;
		String orderNumber = request.getParameter("orderNumber") != null ? request.getParameter("orderNumber")
				: GlobalConstant.BLANK;
		Order order = orderDAO.getOrderByUserOrEmailAndOrderNumber(GlobalConstant.BLANK, emailAddress, orderNumber)
				.get(0);
		List<OrderItemDTO> items = orderDAO.getOrderItemByOrderID(order.getId());

		request.setAttribute(GlobalConstant.ORDER, order);
		request.setAttribute(GlobalConstant.ORDER_ITEM_DTO, items);
		RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConstant.CONFIRMATION_JSP);
		dispatcher.forward(request, response);
	}
}
