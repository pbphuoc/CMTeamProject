package controller;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
 * Servlet implementation class OrderServlet
 */
@WebServlet(urlPatterns = GlobalConstant.ORDER_URL)
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(OrderServlet.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		String command = request.getParameter(GlobalConstant.COMMAND) != null
				? request.getParameter(GlobalConstant.COMMAND)
				: GlobalConstant.BLANK;
		logger.info(command);
		try {
			switch (command) {
			case GlobalConstant.GET_TRACK_ORDER_FORM:
				response.sendRedirect(GlobalConstant.TRACK_ORDER_JSP);
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			doGet(request, response);
		} catch (Exception e) {
			logger.error(e.toString());
		}
	}

	protected void trackOrder(HttpServletRequest request, HttpServletResponse response) {
		try {
			String emailAddress = request.getParameter("emailAddress") != null ? request.getParameter("emailAddress")
					: GlobalConstant.BLANK;
			String orderNumber = request.getParameter("orderNumber") != null ? request.getParameter("orderNumber")
					: GlobalConstant.BLANK;
			OrderDAO orderDAO = OrderDAO.getOrderDAO();
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
		} catch (Exception e) {
			logger.error(e.toString());
		}
	}

	private void viewOrderDetail(HttpServletRequest request, HttpServletResponse response) {
		try {
			OrderDAO orderDAO = OrderDAO.getOrderDAO();
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
		} catch (Exception e) {
			logger.error(e.toString());
		}
	}
}
