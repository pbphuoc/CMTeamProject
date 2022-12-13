package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
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
		String command = request.getParameter("command");
		try {
			switch(command) {
				case "login":
					String username = request.getParameter("emailLogin");
					String password = request.getParameter("passwordLogin");
					login(request, response, username, password);
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
	
	private void login(HttpServletRequest request, HttpServletResponse response, String username, String password) throws ServletException, IOException {

	}	
	
	private void getLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(getUsername(request).equalsIgnoreCase("")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);			
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
	}	
	
	private String getUsername(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("isLoggedIn") != null && (boolean)session.getAttribute("isLoggedIn"))
			return (String)session.getAttribute("username");
		else
			return "";
	}

}
