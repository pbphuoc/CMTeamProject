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
import dao.ProductDAO;
import model.OrderItemDTO;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet(urlPatterns = GlobalConstant.CART_URL)
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartServlet() {
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
		System.out.println("doGet Cart Servlet called");
		System.out.println("Current command: " + request.getParameter(GlobalConstant.COMMAND));
		String command = request.getParameter(GlobalConstant.COMMAND) != null
				? request.getParameter(GlobalConstant.COMMAND)
				: GlobalConstant.BLANK;
		String productID = request.getParameter(GlobalConstant.PRODUCT_ID) != null
				? request.getParameter(GlobalConstant.PRODUCT_ID)
				: GlobalConstant.BLANK;

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
			default:
				getCartPage(request, response);
				break;
			}
		} catch (Exception e) {
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

	protected void getCartPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDAO cartDAO = new ProductDAO();
		HttpSession session = request.getSession();
		HashMap<String, Integer> cartItems = (HashMap<String, Integer>) session.getAttribute(GlobalConstant.CART_ITEM);

		if (cartItems == null || cartItems.size() == 0) {
			response.sendRedirect(GlobalConstant.CART_JSP);
		} else {
			List<OrderItemDTO> cartItemDetails = cartDAO.getAllProductInCartByID(cartItems);
			request.setAttribute(GlobalConstant.ORDER_ITEM_DTO, cartItemDetails);
			RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConstant.CART_JSP);
			dispatcher.forward(request, response);
		}
	}

	protected void remove(HttpServletRequest request, HttpServletResponse response, String productID)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		HashMap<String, Integer> cartItems = (HashMap<String, Integer>) session.getAttribute(GlobalConstant.CART_ITEM);
		cartItems.remove(productID);
		session.setAttribute(GlobalConstant.CART_ITEM, cartItems);
		response.getWriter().append(cartItems.size() + "");
	}

	protected void increase(HttpServletRequest request, HttpServletResponse response, String productID)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		HashMap<String, Integer> cartItems = (HashMap<String, Integer>) session.getAttribute(GlobalConstant.CART_ITEM);
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
	}

	protected void decrease(HttpServletRequest request, HttpServletResponse response, String productID)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		HashMap<String, Integer> cartItems = (HashMap<String, Integer>) session.getAttribute(GlobalConstant.CART_ITEM);

		if (cartItems.containsKey(productID)) {
			cartItems.put(productID, cartItems.get(productID) - 1);
		}
		if (cartItems.get(productID) == 0) {
			cartItems.remove(productID);
		}

		session.setAttribute(GlobalConstant.CART_ITEM, cartItems);
		response.getWriter().append(cartItems.size() + "");
	}
}
