package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrderDAO;
import entity.Order;
import entity.User;

/**
 * Servlet implementation class UserAccountServlet
 */
@WebServlet(urlPatterns = "/Account")
public class UserAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("-----------------------------");
		System.out.println("doGet User Account Servlet called");
		System.out.println("Current command: " + request.getParameter("command"));
		String command = request.getParameter("command") != null ? request.getParameter("command") : "";
		try {
			switch (command) {
			case "viewOrders":			
				viewOrders(request, response);
				break;					
			case "":
//				getLoginPage(request, response);
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
	
	protected void viewOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		OrderDAO orderDAO = new OrderDAO();
		List<Order> orderList = orderDAO.getOrderByUserOrEmailAndOrderNumber(user.getId(),"" ,"");
		request.setAttribute("orderList", orderList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("orderhistory.jsp");
		dispatcher.forward(request, response);
	}		

}
