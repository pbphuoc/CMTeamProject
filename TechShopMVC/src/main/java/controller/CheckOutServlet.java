package controller;

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

import constant.GlobalConstant;
import dao.ProductDAO;
import model.OrderItemDTO;

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
				: GlobalConstant.PAYMENT;
		logger.info(command);
		try {
			switch (command) {
			case GlobalConstant.PAYMENT:
				getCheckoutPage(request, response);
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
			ProductDAO cartDAO = new ProductDAO();
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
}
