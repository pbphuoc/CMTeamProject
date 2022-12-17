package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import service.DAO.DAOType;
import service.DAOService;
import service.UserDAO;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Auth")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
//		String action = request.getServletPath();
		String command = request.getParameter("command") != null ? request.getParameter("command") : "";
		System.out.println(command);
		String username,password;
		try {
			switch(command) {
				case "login":
					username = request.getParameter("emailLogin");
					password = request.getParameter("passwordLogin");
					login(request, response, username, password);
					break;
				case "logout":
					username = request.getParameter("emailLogin");
					System.out.println(username);
					logout(request, response, username);			
					break;
				case "getLoginForm":
					getLoginPage(request, response);
					break;
				default:
					getLoginPage(request, response);
					break;					
			}
		}catch (Exception e) {
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
	
	private void login(HttpServletRequest request, HttpServletResponse response, String email, String password) throws ServletException, IOException {
		UserDAO userDAO = (UserDAO)DAOService.getDAO(DAOType.USER);
		User user = userDAO.getUserByEmailAndPassword(email, password);
		HttpSession session = request.getSession();
		if(user != null) {			
			session.setAttribute("user", user.getFullname());
			session.setAttribute("username", email);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Home");
			dispatcher.forward(request, response);			
		}else {
			session.setAttribute("username", "invalid");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);				
		}
	}	
	
	private void logout(HttpServletRequest request, HttpServletResponse response, String email) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("user", "");
		session.setAttribute("username", "");			
		RequestDispatcher dispatcher = request.getRequestDispatcher("Home");
		dispatcher.forward(request, response);	
	}
	
	private void getLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(getUsername(request).equalsIgnoreCase("")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);			
		}else {
//			ProductDAO productDAO = (ProductDAO)DAOService.getDAO(DAOType.PRODUCT);
//			List<Product> products = productDAO.getAllRecords();
//			request.setAttribute("productList", products);
//			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
//			dispatcher.forward(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Home");
			dispatcher.forward(request, response);			
		}
	}	
	
	private String getUsername(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("user") != null && session.getAttribute("user") != "")
			return (String)session.getAttribute("user");
		else {
			session.setAttribute("user", "");
			return "";
		}
	}

}
