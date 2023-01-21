package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.ProductDAO;
import model.OrderItemDTO;

/**
 * Servlet implementation class CartServlet
 */
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
		// TODO Auto-generated method stub
		String command = request.getParameter("command") != null ? request.getParameter("command") : "";

		String productID = request.getParameter("productID") != null ? request.getParameter("productID") : "";

		try {
			switch (command) {
			case "increase":
				increase(request, response, productID);
				break;
			case "decrease":
				decrease(request, response, productID);
				break;
			case "remove":
				remove(request, response, productID);
				break;
			case "viewCart":
				getCartPage(request, response);
				break;
			default:
				getCartPage(request, response);
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void getCartPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProductDAO cartDAO = new ProductDAO();
		HttpSession session = request.getSession();
		HashMap<String, Integer> cartItems = (HashMap<String, Integer>) session.getAttribute("cartItems");
		if (cartItems == null || cartItems.size() == 0) {
			response.sendRedirect("cart.jsp");
		} else {
			List<OrderItemDTO> cartItemDetails = cartDAO.getAllProductInCartByID(cartItems);
			request.setAttribute("cartItemDetails", cartItemDetails);
			RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void remove(HttpServletRequest request, HttpServletResponse response, String productID)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		HashMap<String, Integer> cartItems = (HashMap<String, Integer>) session.getAttribute("cartItems");
		cartItems.remove(productID);
		session.setAttribute("cartItems", cartItems);
		if (!cartItems.containsKey(productID))
			response.getWriter().append("remove success: product" + productID + "- Cart Size: "
					+ ((HashMap<String, Integer>) session.getAttribute("cartItems")).size());
	}

	protected void increase(HttpServletRequest request, HttpServletResponse response, String productID)
			throws ServletException, IOException {
		ProductDAO cartDAO = new ProductDAO();
		HttpSession session = request.getSession();
		System.out.println(productID);
		HashMap<String, Integer> cartItems = (HashMap<String, Integer>) session.getAttribute("cartItems");
		HashMap<String, Integer> cartList = new HashMap<String, Integer>();

		if (cartItems == null) {
			cartItems = cartList;
			cartItems.put(productID, 1);
		} else if (cartItems.size() >= 0 && cartItems.containsKey(productID) == false) {
			cartItems.put(productID, 1);
		} else {
			cartItems.put(productID, cartItems.get(productID) + 1);
		}

//				if(cartItems.containsKey(productID) ) {
//					cartItems.put(productID, cartItems.get(productID) + 1);				
//				}
//				if(cartItems.size() == 0) {
//					cartItems.put(productID,1);
//				}
//	
//				if(cartItems.size() > 0 && cartItems.containsKey(productID) == false) {
//					cartItems.put(productID,1);
//				}
		session.setAttribute("cartItems", cartItems);
		if (cartItems.get(productID) >= 1)
			response.getWriter().append("increase success: product" + productID + " is now " + cartItems.get(productID)
					+ "- Cart Size: " + ((HashMap<String, Integer>) session.getAttribute("cartItems")).size());
	}

	protected void decrease(HttpServletRequest request, HttpServletResponse response, String productID)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		HashMap<String, Integer> cartItems = (HashMap<String, Integer>) session.getAttribute("cartItems");
		int beforeDecrease = 0;
		if (cartItems.containsKey(productID)) {
			beforeDecrease = cartItems.get(productID);
			cartItems.put(productID, cartItems.get(productID) - 1);
		}
		if (cartItems.get(productID) == 0) {
			cartItems.remove(productID);
		}
		session.setAttribute("cartItems", cartItems);
		if (cartItems.get(productID) == null || (cartItems.get(productID) == beforeDecrease - 1))
			response.getWriter().append("decrease success: product" + productID + " is now " + cartItems.get(productID)
					+ "- Cart Size: " + ((HashMap<String, Integer>) session.getAttribute("cartItems")).size());
	}
}
