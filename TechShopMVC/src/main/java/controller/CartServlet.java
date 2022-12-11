package controller;

import java.io.IOException;
<<<<<<< Updated upstream
=======
import java.util.List;

import javax.servlet.RequestDispatcher;
>>>>>>> Stashed changes
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

<<<<<<< Updated upstream
/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/Cart")
=======
import model.Product;
import service.CartDAO;
import service.DAO;
import service.DAOService;
import service.ProductDAO;
import service.DAO.DAOType;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/cart.html")
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
=======
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/cart.html": {
				getCartPage(request,response);
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + action);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
>>>>>>> Stashed changes
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
<<<<<<< Updated upstream
=======
	protected void getCartPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CartDAO cartDAO = new CartDAO();
		List<Product> cartList = cartDAO.getAllRecords();
		request.setAttribute("cartList", cartList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
		dispatcher.forward(request, response);
	}
>>>>>>> Stashed changes

}
