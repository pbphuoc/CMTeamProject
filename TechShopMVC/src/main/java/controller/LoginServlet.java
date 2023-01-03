package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOService;
import dao.UserDAO;
import dao.DAO.DAOType;
import dao.DAO.QueryResult;
import model.User;


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
		System.out.println("Current User: " + getCurrentUser(request));
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String command = request.getParameter("command") != null ? request.getParameter("command") : "";
		try {
//			switch (command) {
//				case "login":
//					if (!getCurrentUser(request).equalsIgnoreCase(""))
//						redirectToHome(request, response);
//					else {
//						login(request, response);
//					}
//					break;
//				case "logout":
//					logout(request, response);
//					break;
//				case "getLoginForm":
//					if (!getCurrentUser(request).equalsIgnoreCase(""))
//						redirectToHome(request, response);
//					else {
//						getLoginPage(request, response);
//					}
//					break;
//				case "getRegisterForm":
//					if (!getCurrentUser(request).equalsIgnoreCase(""))
//						redirectToHome(request, response);
//					else {
//						getRegisterPage(request, response);
//					}
//					break;
//				case "register":
//					if (!getCurrentUser(request).equalsIgnoreCase(""))
//						redirectToHome(request, response);
//					else {
//						register(request, response);
//					}
//					break;
//				case "":
//					if (!getCurrentUser(request).equalsIgnoreCase(""))
//						redirectToHome(request, response);
//					else {
//						getLoginPage(request, response);
//					}
//					break;
//			}
				switch (command) {
				case "login":			
					login(request, response);
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
				case "":
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
		UserDAO userDAO = (UserDAO)DAOService.getDAO(DAOType.USER);
		User user = userDAO.getRecordByID(email);
		if(user != null) {
			
		}else {
			user = new User(email, password, fullname, mobile);
			QueryResult queryResult = userDAO.insertUser(user);
			if (queryResult == QueryResult.SUCCESSFUL) {
				HttpSession session = request.getSession();
				session.setAttribute("userfullname", fullname);
				session.setAttribute("useremail", email);
				RequestDispatcher dispatcher = request.getRequestDispatcher("Home");
				dispatcher.forward(request, response);					
			}
		}
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
	
	private void redirectToHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("Home");
		dispatcher.forward(request, response);	
	}
	
	private void getRegisterPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
		dispatcher.forward(request, response);			 
	}		
	
	private void getLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);			
	}	
	
	private String getCurrentUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("userfullname") != null && !((String)session.getAttribute("userfullname")).equalsIgnoreCase(""))
			return (String)session.getAttribute("userfullname");
		else {
			session.setAttribute("userfullname", "");
			return "";
		}
	}

}
