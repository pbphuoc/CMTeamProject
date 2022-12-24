package controller;


import java.io.IOException;
import java.lang.reflect.Type;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mysql.cj.Session;

import model.CartItemDetail;
import model.CartItem;
import model.Product;
import service.CartDAO;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/Cart")




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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		String command = request.getParameter("command") != null ? request.getParameter("command") : "";
		
		String productID = request.getParameter("productID") != null ? request.getParameter("productID") : "";
		
		try {
			switch (command) {
				case "increase":
					increase(request,response,productID);
					break;
				case "decrease":
					decrease(request,response,productID);
					break;
				case "remove":
					remove(request,response,productID);
					break;				
				case "viewCart":				
					getCartPage(request,response);
					break;
				
				default:
					getCartPage(request,response);
					break;
			}			
		} catch (Exception e) {
			// TODO: handle exception
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	protected void getCartPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CartDAO cartDAO = new CartDAO();
		HttpSession session = request.getSession();
		HashMap<String,Integer> cartItems = (HashMap<String,Integer>) session.getAttribute("cartItems");
			if(cartItems == null || cartItems.size() == 0) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
				dispatcher.forward(request, response);
			} else {
					List<CartItemDetail> cartItemDetails = cartDAO.getAllProductInCartByID(cartItems);
					request.setAttribute("cartItemDetails", cartItemDetails);
					RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
					dispatcher.forward(request, response);
				
			}
	}
	protected void remove(HttpServletRequest request, HttpServletResponse response, String productID)throws ServletException, IOException {
		
		HttpSession session = request.getSession();	
		HashMap<String,Integer> cartItems = (HashMap<String, Integer>) session.getAttribute("cartItems");
		cartItems.remove(productID);						
		session.setAttribute("cartItems", cartItems);						
		response.getWriter().append("success");				
	}

		protected void increase(HttpServletRequest request, HttpServletResponse response, String productID)throws ServletException, IOException {
			CartDAO cartDAO = new CartDAO();
			HttpSession session = request.getSession();
			System.out.println(productID);	
			HashMap<String,Integer> cartItems = (HashMap<String, Integer>) session.getAttribute("cartItems");
			HashMap<String,Integer> cartList = new HashMap<String,Integer>();
				if(cartItems == null) {
					cartItems = cartList;
				}
				if(cartItems.containsKey(productID) ) {
					cartItems.put(productID, cartItems.get(productID) + 1);				
				}
				if(cartItems.size() == 0) {
					cartItems.put(productID,1);
				}
	
				if(cartItems.size() > 0 && cartItems.containsKey(productID) == false) {
					cartItems.put(productID,1);
				}
					session.setAttribute("cartItems", cartItems);
					response.getWriter().append("success");					
		}
	
		protected void decrease(HttpServletRequest request, HttpServletResponse response,String productID)throws ServletException, IOException {
			HttpSession session = request.getSession();	
			HashMap<String, Integer> cartItems = (HashMap<String, Integer>) session.getAttribute("cartItems");
			
			if(cartItems.containsKey(productID) ) {
				cartItems.put(productID, cartItems.get(productID) - 1);				
			}
			if(cartItems.get(productID) == 0) {
				cartItems.remove(productID);
			}	
			session.setAttribute("cartItems", cartItems);
			response.getWriter().append("success");		
		}
}
