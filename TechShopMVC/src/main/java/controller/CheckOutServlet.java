package controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paypal.api.payments.Details;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.ShippingAddress;
import com.paypal.api.payments.Transaction;

import constant.GlobalConstant;
import constant.OrderPaymentTypeEnum;
import constant.OrderReceiveMethodEnum;
import constant.OrderStatusEnum;
import dao.OrderDAO;
import dao.ProductDAO;
import entity.Order;
import model.OrderItemDTO;
import model.UserSession;
import service.PaymentServices;
import util.Utility;

/**
 * Servlet implementation class CheckOutServlet
 */
@WebServlet(urlPatterns = GlobalConstant.CHECKOUT_URL)
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(CheckOutServlet.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		String command = request.getParameter(GlobalConstant.COMMAND) != null
				? request.getParameter(GlobalConstant.COMMAND)
				: GlobalConstant.DETAIL;
		logger.info(command);
		try {
			switch (command) {
			case GlobalConstant.DETAIL:
				getCheckoutPage(request, response);
				break;
			case GlobalConstant.PAYMENT:
				authorizePayment(request, response);
				break;
			case GlobalConstant.CONFIRM:
				getConfirmationPage(request, response);
				break;
			case GlobalConstant.SUBMIT_ORDER:
				submitOrder(request, response);
				break;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			doGet(request, response);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	protected void getCheckoutPage(HttpServletRequest request, HttpServletResponse response) {
		try {
			ProductDAO cartDAO = ProductDAO.getProductDAO();
			HttpSession session = request.getSession();
			HashMap<String, Integer> cartItems = (HashMap<String, Integer>) session
					.getAttribute(GlobalConstant.CART_ITEM);
			List<OrderItemDTO> cartItemDetails = cartDAO.getAllProductInCartByID(cartItems);

			request.setAttribute(GlobalConstant.ORDER_ITEM_DTO, cartItemDetails);
			RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConstant.CHECKOUT_JSP);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	protected void authorizePayment(HttpServletRequest request, HttpServletResponse response) {
		try {
			ProductDAO productDAO = ProductDAO.getProductDAO();
			HttpSession session = request.getSession();
			HashMap<String, Integer> cartItems = (HashMap<String, Integer>) session
					.getAttribute(GlobalConstant.CART_ITEM);
			List<OrderItemDTO> items = productDAO.getAllProductInCartByID(cartItems);

			PaymentServices paymentServices = PaymentServices.getPaymentServices();
			String approvalLink = paymentServices.authorizePayment(items);
			response.sendRedirect(approvalLink);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

//	protected void collectOrderDetail(HttpServletRequest request, HttpServletResponse response) {
//		ProductDAO productDAO = ProductDAO.getProductDAO();
//		HttpSession session = request.getSession();
//		HashMap<String, Integer> cartItems = (HashMap<String, Integer>) session.getAttribute(GlobalConstant.CART_ITEM);
//		List<OrderItemDTO> items = productDAO.getAllProductInCartByID(cartItems);
//		
//		String paymentID = request.getParameter("paymentId");
//		PaymentServices paymentServices = PaymentServices.getPaymentServices();
//		Payment payment = paymentServices.getPaymentDetails(paymentID);
//		PayerInfo payerInfo = payment.getPayer().getPayerInfo();
//		Transaction transaction = payment.getTransactions().get(0);
//		Details details = transaction.getAmount().getDetails();
//		ShippingAddress shippingAddress = transaction.getItemList().getShippingAddress();
//		
//		String paymentID = request.getParameter("paymentId");
//		String PayerID = request.getParameter("PayerID");
//		PaymentServices paymentServices = PaymentServices.getPaymentServices();
//		Payment payment = paymentServices.executePayment(paymentID, PayerID);
//		PayerInfo payerInfo = payment.getPayer().getPayerInfo();
//		Transaction transaction = payment.getTransactions().get(0);
//		ShippingAddress shippingAddress = transaction.getItemList().getShippingAddress();		
//		
//	}
//	
	protected void getConfirmationPage(HttpServletRequest request, HttpServletResponse response) {
		try {
			OrderDAO orderDAO = OrderDAO.getOrderDAO();
			ProductDAO productDAO = ProductDAO.getProductDAO();
			HttpSession session = request.getSession();
			HashMap<String, Integer> cartItems = (HashMap<String, Integer>) session
					.getAttribute(GlobalConstant.CART_ITEM);
			List<OrderItemDTO> items = productDAO.getAllProductInCartByID(cartItems);

			String paymentID = request.getParameter("paymentId");
			PaymentServices paymentServices = PaymentServices.getPaymentServices();
			Payment payment = paymentServices.getPaymentDetails(paymentID);
			PayerInfo payerInfo = payment.getPayer().getPayerInfo();
			Transaction transaction = payment.getTransactions().get(0);
			Details details = transaction.getAmount().getDetails();
			ShippingAddress shippingAddress = transaction.getItemList().getShippingAddress();

			OrderReceiveMethodEnum receiveMethod = OrderReceiveMethodEnum.DELIVERY;
			OrderPaymentTypeEnum paymentType = OrderPaymentTypeEnum.CARD;
			OrderStatusEnum orderStatus = OrderStatusEnum.REVIEWING;
			String checkOutEmail = payerInfo.getEmail();
			String checkOutFullname = payerInfo.getFirstName() + " " + payerInfo.getLastName();
			String checkOutPhone = payerInfo.getPhone();
			String receiverFullname = shippingAddress.getRecipientName();
			String receiverPhone = shippingAddress.getPhone();
			System.out.println("receiver: " + shippingAddress.toString());
			String currentSubtotal = details.getSubtotal();
			String currentShippingCost = details.getShipping();
			String addressLine2 = shippingAddress.getLine2() != null ? shippingAddress.getLine2() : "";
			String receiverAddress = String.format("%s %s %s %s %s %s", shippingAddress.getLine1(), addressLine2,
					shippingAddress.getCity(), shippingAddress.getState(), shippingAddress.getPostalCode(),
					shippingAddress.getCountryCode());
			double expectedShippingCost = Utility.calculateShippingCost(receiverAddress);
			double expectedTotalCost = Utility.calculateTotalCost(items) + expectedShippingCost;

			if (Double.parseDouble(currentShippingCost) != expectedShippingCost) {
				if (paymentServices.updateShippingCost(expectedShippingCost + "", currentSubtotal, paymentID))
					payment = paymentServices.getPaymentDetails(paymentID);
			}
			double authorizedTotalCost = Double.parseDouble(payment.getTransactions().get(0).getAmount().getTotal());
			double authorizedShippingCost = Double
					.parseDouble(payment.getTransactions().get(0).getAmount().getDetails().getShipping());

			if (expectedTotalCost == authorizedTotalCost && expectedShippingCost == authorizedShippingCost) {
				String userID = GlobalConstant.GUEST_ID;
				UserSession user = (UserSession) session.getAttribute(GlobalConstant.USER);

				if (user != null) {
					userID = user.getId();
					checkOutEmail = user.getEmail();
					checkOutFullname = user.getFullname();
					checkOutPhone = user.getPhoneNumber();
				}

				Order order = new Order(checkOutEmail, checkOutFullname, checkOutPhone, receiverFullname, receiverPhone,
						receiverAddress, receiveMethod, orderStatus, authorizedShippingCost, authorizedTotalCost,
						paymentType);

				Object[] results = orderDAO.insertOrder(userID, order, items);

				boolean successful = (boolean) results[0];
				Order insertedOrder = (Order) results[1];
				List<OrderItemDTO> insertedItems = (List<OrderItemDTO>) results[2];

				if (successful) {
					request.setAttribute(GlobalConstant.ORDER, insertedOrder);
					request.setAttribute(GlobalConstant.ORDER_ITEM_DTO, insertedItems);
//					session.setAttribute(GlobalConstant.CART_ITEM, null);
					RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConstant.CONFIRMATION_JSP);
					dispatcher.forward(request, response);
				}
			}

//			request.setAttribute(GlobalConstant.ORDER, order);
//			request.setAttribute(GlobalConstant.ORDER_ITEM_DTO, items);
//			RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConstant.CONFIRMATION_JSP);
//			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	protected void submitOrder(HttpServletRequest request, HttpServletResponse response) {
		try {
			String checkOutEmail = request.getParameter("checkOutEmail");
			String orderNumber = request.getParameter("orderNumber");
			OrderDAO orderDAO = OrderDAO.getOrderDAO();
			Order order = orderDAO.getOrderByUserOrEmailAndOrderNumber("", checkOutEmail, orderNumber).get(0);
			List<OrderItemDTO> items = orderDAO.getOrderItemByOrderID(order.getId());

			String paymentID = request.getParameter("paymentId");
			String PayerID = request.getParameter("PayerID");
			PaymentServices paymentServices = PaymentServices.getPaymentServices();
			Payment payment = paymentServices.executePayment(paymentID, PayerID);

			if (payment.getState().equalsIgnoreCase("approved")) {
				order.setPaymentID(paymentID);
				order.setPaymentDate(payment.getUpdateTime());
				order.setOrderStatus(OrderStatusEnum.RECEIVED);
				boolean successful = orderDAO.receiveOrder(order);

				if (successful) {
					HttpSession session = request.getSession();
					request.setAttribute(GlobalConstant.ORDER, order);
					request.setAttribute(GlobalConstant.ORDER_ITEM_DTO, items);
					session.setAttribute(GlobalConstant.CART_ITEM, null);
					RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConstant.CONFIRMATION_JSP);
					dispatcher.forward(request, response);
				}
			}

//			PayerInfo payerInfo = payment.getPayer().getPayerInfo();
//			Transaction transaction = payment.getTransactions().get(0);
//			ShippingAddress shippingAddress = transaction.getItemList().getShippingAddress();
//
//			String checkOutEmail = payerInfo.getEmail();
//			String checkOutFullname = payerInfo.getFirstName() + " " + payerInfo.getLastName();
//			String checkOutPhone = payerInfo.getPhone();
//			String receiverFullname = shippingAddress.getRecipientName();
//			String receiverPhone = shippingAddress.getPhone();
//			String addressLine2 = shippingAddress.getLine2() != null ? shippingAddress.getLine2() : "";
//			String receiverAddress = String.format("%s %s %s %s %s %s", shippingAddress.getLine1(), addressLine2,
//					shippingAddress.getCity(), shippingAddress.getState(), shippingAddress.getPostalCode(),
//					shippingAddress.getCountryCode());

//			String userID = GlobalConstant.GUEST_ID;

//			UserSession user = (UserSession) session.getAttribute(GlobalConstant.USER);
//			if (user != null) {
//				userID = user.getId();
//				checkOutEmail = user.getEmail();
//				checkOutFullname = user.getFullname();
//				checkOutPhone = user.getPhoneNumber();
//			}

//			OrderReceiveMethodEnum receiveMethod = OrderReceiveMethodEnum.DELIVERY;
//			OrderPaymentTypeEnum paymentType = OrderPaymentTypeEnum.CARD;
//			OrderStatusEnum status = OrderStatusEnum.RECEIVED;
//			double shippingCost = Double
//					.parseDouble(payment.getTransactions().get(0).getAmount().getDetails().getShipping());
//			double totalCost = Double.parseDouble(payment.getTransactions().get(0).getAmount().getTotal());
//			double expectedTotalCost = Utility.calculateTotalCost(items)
//					+ Utility.calculateShippingCost(receiverAddress);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
}
