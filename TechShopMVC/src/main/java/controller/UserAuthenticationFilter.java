package controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import constant.GlobalConstant;

/**
 * Servlet Filter implementation class AdminAuthenticationFIlter
 */
@WebFilter(urlPatterns = { GlobalConstant.AUTH_URL })
public class UserAuthenticationFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public UserAuthenticationFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);

		boolean isLoggedIn = (session != null && session.getAttribute(GlobalConstant.USER) != null);
		String loginURI = httpRequest.getContextPath() + GlobalConstant.AUTH_URL;
		boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);
		boolean isLoginPage = httpRequest.getRequestURI().endsWith(GlobalConstant.LOGIN_JSP);
		boolean isLogoutRequest = (httpRequest.getParameter(GlobalConstant.COMMAND) != null)
				? ((String) httpRequest.getParameter(GlobalConstant.COMMAND)).equalsIgnoreCase(GlobalConstant.LOGOUT)
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
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
