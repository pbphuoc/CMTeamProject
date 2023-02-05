package controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
import entity.User;
import model.OrderItemDTO;
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
			logger.error(e.toString());
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
			logger.error(e.toString());
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
			logger.error(e.toString());
		}
	}

	protected void authorizePayment(HttpServletRequest request, HttpServletResponse response) {
		try {
			ProductDAO productDAO = ProductDAO.getProductDAO();
			HttpSession session = request.getSession();
			HashMap<String, Integer> cartItems = (HashMap<String, Integer>) session.getAttribute("cartItems");
			List<OrderItemDTO> items = productDAO.getAllProductInCartByID(cartItems);

			PaymentServices paymentServices = PaymentServices.getPaymentServices();
			String approvalLink = paymentServices.authorizePayment(items);
			System.out.println("approval link: " + approvalLink);
			response.sendRedirect(approvalLink);
		} catch (Exception e) {
			logger.error(e.toString());
		}
	}

	protected void getConfirmationPage(HttpServletRequest request, HttpServletResponse response) {
		try {
			ProductDAO productDAO = ProductDAO.getProductDAO();
			HttpSession session = request.getSession();
			HashMap<String, Integer> cartItems = (HashMap<String, Integer>) session.getAttribute("cartItems");
			List<OrderItemDTO> items = productDAO.getAllProductInCartByID(cartItems);

			String paymentID = request.getParameter("paymentId");
			PaymentServices paymentServices = PaymentServices.getPaymentServices();
			Payment payment = paymentServices.getPaymentDetails(paymentID);
			PayerInfo payerInfo = payment.getPayer().getPayerInfo();
			Transaction transaction = payment.getTransactions().get(0);
			Details details = transaction.getAmount().getDetails();
			ShippingAddress shippingAddress = transaction.getItemList().getShippingAddress();

			String currentShippingCost = details.getShipping();
			String currentSubtotal = details.getSubtotal();
			String checkOutEmail = payerInfo.getEmail();
			String checkOutFullname = payerInfo.getFirstName() + " " + payerInfo.getLastName();
			String checkOutPhone = payerInfo.getPhone();
			String receiverFullname = shippingAddress.getRecipientName();
			String receiverPhone = shippingAddress.getPhone();
			String addressLine2 = shippingAddress.getLine2() != null ? shippingAddress.getLine2() : "";
			String receiverAddress = String.format("%s %s %s %s %s %s", shippingAddress.getLine1(), addressLine2,
					shippingAddress.getCity(), shippingAddress.getState(), shippingAddress.getPostalCode(),
					shippingAddress.getCountryCode());
			String paymentDate = "";
			OrderReceiveMethodEnum receiveMethod = OrderReceiveMethodEnum.DELIVERY;
			OrderPaymentTypeEnum paymentType = OrderPaymentTypeEnum.CARD;
			OrderStatusEnum status = OrderStatusEnum.REVIEWING;
			double shippingCost = Utility.calculateShippingCost(receiverAddress);
			double total = Utility.calculateTotalCost(items) + shippingCost;

			if (Double.parseDouble(currentShippingCost) != shippingCost)
				paymentServices.updateShippingCost(shippingCost + "", currentSubtotal, paymentID);

			if ((User) session.getAttribute(GlobalConstant.USER) != null) {
				checkOutEmail = ((User) session.getAttribute(GlobalConstant.USER)).getEmail();
				checkOutFullname = ((User) session.getAttribute(GlobalConstant.USER)).getName();
				checkOutPhone = ((User) session.getAttribute(GlobalConstant.USER)).getPhoneNumber();
			}

			Order order = new Order(checkOutEmail, checkOutFullname, checkOutPhone, receiverFullname, receiverPhone,
					receiverAddress, receiveMethod, status, shippingCost, total, paymentType, paymentDate, paymentID);

			request.setAttribute(GlobalConstant.ORDER, order);
			request.setAttribute(GlobalConstant.ORDER_ITEM_DTO, items);
			RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConstant.CONFIRMATION_JSP);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			logger.error(e.toString());
		}
	}
	
	protected void submitOrder(HttpServletRequest request, HttpServletResponse response) {
		try {
			OrderDAO orderDAO = OrderDAO.getOrderDAO();
			ProductDAO productDAO = ProductDAO.getProductDAO();
			HttpSession session = request.getSession();
			HashMap<String, Integer> cartItems = (HashMap<String, Integer>) session.getAttribute("cartItems");
			List<OrderItemDTO> items = productDAO.getAllProductInCartByID(cartItems);

			String paymentID = request.getParameter("paymentId");
			String PayerID = request.getParameter("PayerID");
			PaymentServices paymentServices = PaymentServices.getPaymentServices();
			Payment payment = paymentServices.executePayment(paymentID, PayerID);
			PayerInfo payerInfo = payment.getPayer().getPayerInfo();
			Transaction transaction = payment.getTransactions().get(0);
			ShippingAddress shippingAddress = transaction.getItemList().getShippingAddress();

			String checkOutEmail = payerInfo.getEmail();
			String checkOutFullname = payerInfo.getFirstName() + " " + payerInfo.getLastName();
			String checkOutPhone = payerInfo.getPhone();
			String receiverFullname = shippingAddress.getRecipientName();
			String receiverPhone = shippingAddress.getPhone();
			String addressLine2 = shippingAddress.getLine2() != null ? shippingAddress.getLine2() : "";
			String receiverAddress = String.format("%s %s %s %s %s %s", shippingAddress.getLine1(), addressLine2,
					shippingAddress.getCity(), shippingAddress.getState(), shippingAddress.getPostalCode(),
					shippingAddress.getCountryCode());
			String paymentDate = payment.getUpdateTime();
			String userID = GlobalConstant.GUEST_ID;
			
			if ((User) session.getAttribute(GlobalConstant.USER) != null) {
				userID = ((User) session.getAttribute(GlobalConstant.USER)).getId();				
				checkOutEmail = ((User) session.getAttribute(GlobalConstant.USER)).getEmail();
				checkOutFullname = ((User) session.getAttribute(GlobalConstant.USER)).getName();
				checkOutPhone = ((User) session.getAttribute(GlobalConstant.USER)).getPhoneNumber();
			}			
			
			OrderReceiveMethodEnum receiveMethod = OrderReceiveMethodEnum.DELIVERY;
			OrderPaymentTypeEnum paymentType = OrderPaymentTypeEnum.CARD;
			OrderStatusEnum status = OrderStatusEnum.RECEIVED;
			double shippingCost = Double
					.parseDouble(payment.getTransactions().get(0).getAmount().getDetails().getShipping());
			double totalCost = Double.parseDouble(payment.getTransactions().get(0).getAmount().getTotal());
			double expectedTotalCost = Utility.calculateTotalCost(items)
					+ Utility.calculateShippingCost(receiverAddress);

			if (!paymentDate.equalsIgnoreCase("")) {
				Instant instant = Instant.parse(paymentDate);
				ZoneId zone = ZoneId.of("Australia/Sydney");
				paymentDate = LocalDate.ofInstant(instant, zone).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			}

			if (expectedTotalCost == totalCost) {
				Order order = orderDAO.insertOrder(userID, checkOutEmail, checkOutFullname, checkOutPhone,
						receiverFullname, receiverPhone, receiverAddress, receiveMethod, shippingCost, totalCost, items,
						paymentType, paymentDate, paymentID);
				if (order != null) {
					request.setAttribute(GlobalConstant.ORDER, order);
					request.setAttribute(GlobalConstant.ORDER_ITEM_DTO, items);
					session.setAttribute(GlobalConstant.CART_ITEM, null);
					RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConstant.CONFIRMATION_JSP);
					dispatcher.forward(request, response);
				}
			}
		} catch (Exception e) {
			logger.error(e.toString());
		}
	}	
}
