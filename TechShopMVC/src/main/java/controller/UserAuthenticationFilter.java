package controller;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import constant.GlobalConstant;
import util.Utility;

/**
 * Servlet Filter implementation class AdminAuthenticationFIlter
 */
@WebFilter(urlPatterns = { GlobalConstant.AUTH_URL })
public class UserAuthenticationFilter implements Filter {
	private static final Logger logger = LogManager.getLogger(UserAuthenticationFilter.class);

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
		try {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpSession session = httpRequest.getSession(false);

			boolean isLoggedIn = (session != null && session.getAttribute(GlobalConstant.USER) != null);
			String loginURI = httpRequest.getContextPath() + GlobalConstant.AUTH_URL;
			boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);
			boolean isLoginPage = httpRequest.getRequestURI().endsWith(GlobalConstant.LOGIN_JSP);
			boolean isLogoutRequest = (httpRequest.getParameter(GlobalConstant.COMMAND) != null)
					? ((String) httpRequest.getParameter(GlobalConstant.COMMAND)).equalsIgnoreCase(
							GlobalConstant.LOGOUT)
					: false;

			if (isLoggedIn && (isLoginRequest || isLoginPage) && !isLogoutRequest) {
				RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConstant.HOME_URL);
				dispatcher.forward(request, response);
			} else if (isLoggedIn || isLoginRequest || isLogoutRequest) {
				// pass the request along the filter chain
				chain.doFilter(request, response);
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConstant.AUTH_URL);
				dispatcher.forward(request, response);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			Utility.handleError((HttpServletResponse) response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);			
		}
	}
}
