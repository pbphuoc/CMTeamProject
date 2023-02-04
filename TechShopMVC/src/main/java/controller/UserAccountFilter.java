package controller;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import constant.GlobalConstant;

/**
 * Servlet Filter implementation class UserAccountFilter
 */
@WebFilter(urlPatterns = { GlobalConstant.ACCOUNT_URL })
public class UserAccountFilter implements Filter {
	private static final Logger logger = LogManager.getLogger(UserAccountFilter.class);

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
		try {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpSession session = httpRequest.getSession(false);

			boolean isLoggedIn = (session != null && session.getAttribute(GlobalConstant.USER) != null);
			String userAccountURI = httpRequest.getContextPath() + GlobalConstant.ACCOUNT_URL;
			boolean isUserAccountRequest = httpRequest.getRequestURI().equals(userAccountURI);

			if (!isLoggedIn && isUserAccountRequest) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Auth?command=getLoginForm");
				dispatcher.forward(request, response);
			} else {
				chain.doFilter(request, response);
			}
		} catch (Exception e) {
			logger.error(e.toString());
		}
	}
}
