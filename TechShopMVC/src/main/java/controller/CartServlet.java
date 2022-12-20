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




//request gui len tang la  command *increment* , command *decrement* , command *remove*, *viewCart*
// request gui len co para (command, productID)
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
		response.getWriter().append("Served at: ").append(request.getContextPath());	
		String command = request.getParameter("command") != null ? request.getParameter("command") : "";
		
		String productID = request.getParameter("productID") != null ? request.getParameter("productID") : "";
		
		try {
			//swich case command (incre decre remove) viewCart default thi chay vo getcartpage
			
			switch (command) {
				case "increase":
					increase(request,response,productID);
					getCartPage(request,response);
					break;
				case "decrease":
					decrease(request,response,productID);
					getCartPage(request,response);
					break;
				case "remove":
					remove(request,response,productID);
					getCartPage(request,response);
					break;
				
				case "viewCart":
					//viewCart(request,response,productID);
					getCartPage(request,response);
				
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
		//lay session, lay cartList
			
			HashMap<String,Integer> cartItems = (HashMap<String,Integer>) session.getAttribute("cartItems");
			
			
			//check cartItems if null and size = 0 => empty jsp, else loop cartItems
			if(cartItems == null || cartItems.size() == 0) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
				dispatcher.forward(request, response);
			} else {
					//truyen cartItems xuong DAO,tra ve gan vao day
					List<CartItemDetail> cartItemDetails = cartDAO.getAllProductInCartByID(cartItems);
					request.setAttribute("cartItemDetails", cartItemDetails);
					RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
					dispatcher.forward(request, response);
				
			}
	}
	protected void remove(HttpServletRequest request, HttpServletResponse response, String productID)throws ServletException, IOException {
		HttpSession session = request.getSession();	
		HashMap<String,Integer> cartItems = (HashMap<String, Integer>) session.getAttribute("cartItems");
		System.out.println(cartItems.size());
				cartItems.remove(productID);				
		System.out.println(cartItems.size());
		//set nguoc vo session de truyen ra getcartpage and jsp		
		session.setAttribute("cartItems", cartItems);		
	}

		protected void increase(HttpServletRequest request, HttpServletResponse response, String productID)throws ServletException, IOException {
			HttpSession session = request.getSession();
			System.out.println(productID);
			//viet getAttribute(cartItemDetails) check null or 0 ko, dung if de check, neu ko null thi lay tu do, neu null thi cartItemDetails rong, tao session(tao List cartItemDetails) cho no
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
				
					
			//tim productID truyen len co trong cartList chua, neu co roi loop qua check, dat bien boolean, neu false thi tao cai moi =>true, neu true => add		
				
					session.setAttribute("cartItems", cartItems);
					//truyen cartItems xuong DAO,tra ve gan vao day
					
			//set nguoc vo session de truyen ra getcartpage and jsp		
		}
	
		protected void decrease(HttpServletRequest request, HttpServletResponse response,String productID)throws ServletException, IOException {
			HttpSession session = request.getSession();

			// viet getAttribute(cartItemDetails) check null or 0 ko, dung if de check, neu
			// ko null thi lay tu do, neu null thi cartItemDetails rong, tao session(tao
			// List cartItemDetails) cho no
			HashMap<String, Integer> cartItems = (HashMap<String, Integer>) session.getAttribute("cartItems");
			
			if(cartItems.containsKey(productID) ) {
				cartItems.put(productID, cartItems.get(productID) - 1);				
			}
			if(cartItems.get(productID) == 0) {
				cartItems.remove(productID);
			}

			// tim productID truyen len co trong cartList chua, neu co roi loop qua check,
			// dat bien boolean, neu false thi tao cai moi =>true, neu true => add
			
			session.setAttribute("cartItems", cartItems);
			// truyen cartItems xuong DAO,tra ve gan vao day

			// set nguoc vo session de truyen ra getcartpage and jsp
		}
}
