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
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class UserAccountFilter
 */
@WebFilter(urlPatterns = {"/Account"})
public class UserAccountFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public UserAccountFilter() {
        super();
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		
		boolean isLoggedIn = (session != null && session.getAttribute("user") != null);
		String userAccountURI = httpRequest.getContextPath() + "/Account";
		boolean isUserAccountRequest = httpRequest.getRequestURI().equals(userAccountURI);
		boolean isUserAccountPage = httpRequest.getRequestURI().endsWith("account.jsp");
		
		if(!isLoggedIn && (isUserAccountRequest || isUserAccountPage)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Auth?command=getLoginForm");
			dispatcher.forward(request, response);
		}else {
			chain.doFilter(request, response);
		}		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
