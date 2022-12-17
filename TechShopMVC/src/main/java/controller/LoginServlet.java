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
		System.out.println("-----------------------------");
		System.out.println("doGet Login Servlet called");				
		System.out.println("Current command: " + request.getParameter("command"));
		System.out.println("Current User: " + getUsername(request));			
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String command = request.getParameter("command") != null ? request.getParameter("command") : "";
		try {
			switch(command) {
				case "login":
					login(request, response);
					break;
				case "logout":
					logout(request, response);			
					break;
				case "getLoginForm":
					getLoginPage(request, response);
					break;
				case "register":
					getLoginPage(request, response);
					break;					
				case "":
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
		System.out.println("-----------------------------");
		System.out.println("doPost Login Servlet called");	
		doGet(request, response);
	}
	
	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}	
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("emailLogin");
		String password = request.getParameter("passwordLogin");		
		UserDAO userDAO = (UserDAO)DAOService.getDAO(DAOType.USER);
		User user = userDAO.getUserByEmailAndPassword(email, password);
		HttpSession session = request.getSession();
		if(user != null) {			
			session.setAttribute("userfullname", user.getFullname());
			session.setAttribute("useremail", email);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Home");
			dispatcher.forward(request, response);			
		}else {
			session.setAttribute("useremail", "invalid");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);				
		}
	}	
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("emailLogin");
		HttpSession session = request.getSession();
		System.out.println("Email to logout " + email);
		System.out.println("Current useremail: " + (String)session.getAttribute("useremail"));
		if(((String)session.getAttribute("useremail")).equals(email)) {
			session.setAttribute("userfullname", "");
			session.setAttribute("useremail", "");				
		}
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
		if(session.getAttribute("userfullname") != null && session.getAttribute("userfullname") != "")
			return (String)session.getAttribute("userfullname");
		else {
			session.setAttribute("userfullname", "");
			return "";
		}
	}

}
