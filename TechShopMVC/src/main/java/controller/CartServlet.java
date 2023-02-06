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
 * Servlet implementation class CartServlet
 */
@WebServlet(urlPatterns = GlobalConstant.CART_URL)
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(CartServlet.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		String command = request.getParameter(GlobalConstant.COMMAND) != null
				? request.getParameter(GlobalConstant.COMMAND)
				: GlobalConstant.BLANK;
		String productID = request.getParameter(GlobalConstant.PRODUCT_ID) != null
				? request.getParameter(GlobalConstant.PRODUCT_ID)
				: GlobalConstant.VIEW_CART;
		logger.info(command + " - " + productID);
		try {
			switch (command) {
			case GlobalConstant.INCREASE:
				increase(request, response, productID);
				break;
			case GlobalConstant.DECREASE:
				decrease(request, response, productID);
				break;
			case GlobalConstant.REMOVE:
				remove(request, response, productID);
				break;
			case GlobalConstant.VIEW_CART:
				getCartPage(request, response);
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

	protected void getCartPage(HttpServletRequest request, HttpServletResponse response) {
		try {
			ProductDAO cartDAO = ProductDAO.getProductDAO();
			HttpSession session = request.getSession();
			HashMap<String, Integer> cartItems = (HashMap<String, Integer>) session
					.getAttribute(GlobalConstant.CART_ITEM);

			if (cartItems == null || cartItems.size() == 0) {
				response.sendRedirect(GlobalConstant.CART_JSP);
			} else {
				List<OrderItemDTO> cartItemDetails = cartDAO.getAllProductInCartByID(cartItems);
				request.setAttribute(GlobalConstant.ORDER_ITEM_DTO, cartItemDetails);
				RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConstant.CART_JSP);
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	protected void remove(HttpServletRequest request, HttpServletResponse response, String productID) {
		try {
			HttpSession session = request.getSession();
			HashMap<String, Integer> cartItems = (HashMap<String, Integer>) session
					.getAttribute(GlobalConstant.CART_ITEM);
			cartItems.remove(productID);
			session.setAttribute(GlobalConstant.CART_ITEM, cartItems);
			response.getWriter().append(cartItems.size() + "");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	protected void increase(HttpServletRequest request, HttpServletResponse response, String productID) {
		try {
			HttpSession session = request.getSession();
			HashMap<String, Integer> cartItems = (HashMap<String, Integer>) session
					.getAttribute(GlobalConstant.CART_ITEM);
			HashMap<String, Integer> cartList = new HashMap<String, Integer>();

			if (cartItems == null) {
				cartItems = cartList;
				cartItems.put(productID, 1);
			} else if (cartItems.size() >= 0 && cartItems.containsKey(productID) == false) {
				cartItems.put(productID, 1);
			} else {
				cartItems.put(productID, cartItems.get(productID) + 1);
			}

			session.setAttribute(GlobalConstant.CART_ITEM, cartItems);
			response.getWriter().append(cartItems.size() + "");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	protected void decrease(HttpServletRequest request, HttpServletResponse response, String productID) {
		try {
			HttpSession session = request.getSession();
			HashMap<String, Integer> cartItems = (HashMap<String, Integer>) session
					.getAttribute(GlobalConstant.CART_ITEM);

			if (cartItems.containsKey(productID)) {
				cartItems.put(productID, cartItems.get(productID) - 1);
			}
			if (cartItems.get(productID) == 0) {
				cartItems.remove(productID);
			}

			session.setAttribute(GlobalConstant.CART_ITEM, cartItems);
			response.getWriter().append(cartItems.size() + "");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
