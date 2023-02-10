package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.UserDAO;
import entity.User;
import global.GlobalConstant;
import model.UserSession;
import util.Utility;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(urlPatterns = GlobalConstant.AUTH_URL)
public class AuthenticationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(AuthenticationServlet.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		String command = request.getParameter(GlobalConstant.COMMAND) != null
				? request.getParameter(GlobalConstant.COMMAND)
				: GlobalConstant.GET_LOGIN_FORM;

		logger.info(command);

		try {
			switch (command) {
			case GlobalConstant.LOGIN:
				login(request, response);
				break;

			case GlobalConstant.LOGOUT:
				logout(request, response);
				break;

			case GlobalConstant.GET_LOGIN_FORM:
				getLoginPage(request, response);
				break;

			case GlobalConstant.GET_REGISTER_FORM:
				getRegisterPage(request, response);
				break;

			case GlobalConstant.REGISTER:
				register(request, response);
				break;
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			Utility.handleError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			doGet(request, response);

		} catch (Exception e) {
			logger.error(e.getMessage());
			Utility.handleError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	private void register(HttpServletRequest request, HttpServletResponse response) {
		try {
			String fullname = request.getParameter(GlobalConstant.FULLNAME_REGISTER);
			String email = request.getParameter(GlobalConstant.EMAIL_REGISTER);
			String password = request.getParameter(GlobalConstant.PASSWORD_REGISTER);
			String mobile = request.getParameter(GlobalConstant.MOBILE_REGISTER);

			UserSession user = UserDAO.getUserDAO().insertUser(new User(email, password, fullname, mobile));

			if (user != null) {
				String prevUrl = Utility.getCorrectPrevUrl(request.getParameter(GlobalConstant.PREV_URL));
				HttpSession session = request.getSession();
				session.setAttribute(GlobalConstant.USER, user);
				response.sendRedirect(prevUrl);
			} else {
				request.setAttribute(GlobalConstant.ERROR, GlobalConstant.EMAIL_REGISTERED_ALREADY);
				RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConstant.REGISTER_JSP);
				dispatcher.forward(request, response);
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			Utility.handleError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) {
		try {
			String email = request.getParameter(GlobalConstant.EMAIL_LOGIN);
			String password = request.getParameter(GlobalConstant.PASSWORD_LOGIN);

			UserSession user = UserDAO.getUserDAO().authenticateUser(email, password);

			if (user != null) {
				String prevUrl = Utility.getCorrectPrevUrl(request.getParameter(GlobalConstant.PREV_URL));
				HttpSession session = request.getSession();
				session.setAttribute(GlobalConstant.USER, user);
				response.sendRedirect(prevUrl);
			} else {
				request.setAttribute(GlobalConstant.ERROR, GlobalConstant.INCORRECT_LOGIN_DETAIL);
				RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConstant.LOGIN_JSP);
				dispatcher.forward(request, response);
			}

		} catch (Exception e) {
			logger.error(e);
			Utility.handleError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) {
		try {
			String prevUrl = Utility.getCorrectPrevUrl(request.getParameter(GlobalConstant.PREV_URL));
			HttpSession session = request.getSession();
			session.setAttribute(GlobalConstant.USER, null);
			response.sendRedirect(prevUrl);

		} catch (Exception e) {
			logger.error(e);
			Utility.handleError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	private void getRegisterPage(HttpServletRequest request, HttpServletResponse response) {
		try {
			String prevUrl = Utility.getCorrectPrevUrl(request.getParameter(GlobalConstant.PREV_URL));
			response.sendRedirect("register.jsp?prevUrl=" + prevUrl);

		} catch (Exception e) {
			logger.error(e);
			Utility.handleError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	private void getLoginPage(HttpServletRequest request, HttpServletResponse response) {
		try {
			String prevUrl = Utility.getCorrectPrevUrl(request.getParameter(GlobalConstant.PREV_URL));
			response.sendRedirect("login.jsp?prevUrl=" + prevUrl);

		} catch (Exception e) {
			logger.error(e);
			Utility.handleError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
