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

import constant.GlobalConstant;
import constant.OrderPaymentTypeEnum;
import constant.OrderReceiveMethodEnum;
import dao.OrderDAO;
import dao.ProductDAO;
import entity.Order;
import entity.User;
import model.OrderItemDTO;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet(urlPatterns = GlobalConstant.ORDER_URL)
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
			throw new ServletException(e);
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
		OrderDAO orderDAO = new OrderDAO();
		ProductDAO productDAO = new ProductDAO();
		HttpSession session = request.getSession();
		HashMap<String, Integer> cartItems = (HashMap<String, Integer>) session.getAttribute("cartItems");
		List<OrderItemDTO> items = productDAO.getAllProductInCartByID(cartItems);

		String userID = (User) session.getAttribute(GlobalConstant.USER) != null
				? ((User) session.getAttribute(GlobalConstant.USER)).getId()
				: GlobalConstant.GUEST_ID;
		String checkOutEmail = request.getParameter("checkOutEmail");
		String checkOutFullname = GlobalConstant.BLANK;
		String checkOutPhone = GlobalConstant.BLANK;
		String receiverFullname = request.getParameter("receiverFullname");
		String receiverPhone = request.getParameter("receiverPhone");
		String receiverAddress = request.getParameter("receiverAddress");
		OrderReceiveMethodEnum receiveMethod = OrderReceiveMethodEnum.valueOf(request.getParameter("receiveMethod"));
		String shipping = GlobalConstant.DEFAULT_SHIPPING;
		OrderPaymentTypeEnum paymentType = OrderPaymentTypeEnum.valueOf(request.getParameter("paymentType"));
		String paymentDate = GlobalConstant.BLANK;
		String paymentFullname = request.getParameter("cardHolderName");
		String paymentSource = request.getParameter("cardNumber");
		String billingFullname = request.getParameter("billingFullname");
		String billingAddress = request.getParameter("billingPhone");
		String billingPhone = request.getParameter("billingAddress");

		Order order = orderDAO.insertOrder(userID, checkOutEmail, checkOutFullname, checkOutPhone, receiverFullname,
				receiverPhone, receiverAddress, receiveMethod, shipping, items, paymentType, paymentDate,
				paymentFullname, paymentSource, billingFullname, billingAddress, billingPhone);

		if (order != null) {
			request.setAttribute(GlobalConstant.ORDER, order);
			request.setAttribute(GlobalConstant.ORDER_ITEM_DTO, items);
			session.setAttribute(GlobalConstant.CART_ITEM, null);
			RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConstant.CONFIRMATION_JSP);
			dispatcher.forward(request, response);
		}
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
