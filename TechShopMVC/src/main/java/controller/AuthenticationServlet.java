package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.UserDAO;
import entity.User;
import util.Utility;

/**
 * Servlet implementation class LoginServlet
 */
public class AuthenticationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthenticationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("-----------------------------");
		System.out.println("doGet Authentication Servlet called");
		System.out.println("Current command: " + request.getParameter("command"));
//		System.out.println("Current User: " + getCurrentUser(request));
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String command = request.getParameter("command") != null ? request.getParameter("command") : "";
		try {
			switch (command) {
			case "login":			
				login(request, response);
				break;
			case "logout":			
				logout(request, response);
				break;				
			case "getLoginForm":
				getLoginPage(request, response);	
				break;
			case "getRegisterForm":
				getRegisterPage(request, response);
				break;
			case "register":
				register(request, response);
				break;
			default:
				getLoginPage(request, response);
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
		System.out.println("-----------------------------");
		System.out.println("doPost Login Servlet called");	
		doGet(request, response);
	}
	
	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fullname = request.getParameter("fullnameRegister");
		String email = request.getParameter("emailRegister");
		String password = request.getParameter("passwordRegister");
		String mobile = request.getParameter("mobileRegister");
		UserDAO userDAO = new UserDAO();
		User user = userDAO.getUserByEmail(email);
		if(user != null) {
			request.setAttribute("error", "This email address has been registered already");
			RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
			dispatcher.forward(request, response);				
		}else {			
			Utility.QueryResult queryResult = userDAO.insertUser(email, password, fullname, mobile);
			if (queryResult == Utility.QueryResult.SUCCESSFUL) {
				String prevUrl = Utility.getCorrectPrevUrl(request.getParameter("prevUrl"));		
				System.out.println("prev Url" + prevUrl);				
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				response.sendRedirect(prevUrl);				
			}
		}
	}	
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("emailLogin");
		String password = request.getParameter("passwordLogin");	
		UserDAO userDAO = new UserDAO();
		User user = userDAO.getUserByEmailAndPassword(email, password);
		HttpSession session = request.getSession();
		if(user != null) {			
			String prevUrl = Utility.getCorrectPrevUrl(request.getParameter("prevUrl"));		
			System.out.println("prev Url" + prevUrl);			
			session.setAttribute("user", user);
			response.sendRedirect(prevUrl);				
		}else {
			session.setAttribute("user", null);
			request.setAttribute("error", "Incorrect Email or Password. Please try again");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);				
		}
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prevUrl = Utility.getCorrectPrevUrl(request.getParameter("prevUrl"));		
		System.out.println("prev Url" + prevUrl);			
		HttpSession session = request.getSession();
		session.setAttribute("user", null);
		response.sendRedirect(prevUrl);
	}	
	
	private void getRegisterPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prevUrl = Utility.getCorrectPrevUrl(request.getParameter("prevUrl"));		
		System.out.println("prevUrl: " + prevUrl);		
		response.sendRedirect("register.jsp?prevUrl="+prevUrl);		
	}		
	
	private void getLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prevUrl = Utility.getCorrectPrevUrl(request.getParameter("prevUrl"));		
		System.out.println("prevUrl: " + prevUrl);		
		response.sendRedirect("login.jsp?prevUrl="+prevUrl);	
	}	
}
