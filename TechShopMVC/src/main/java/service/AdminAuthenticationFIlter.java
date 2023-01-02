package service;

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

/**
 * Servlet Filter implementation class AdminAuthenticationFIlter
 */
@WebFilter(urlPatterns = {"/Home","/Product","/Cart","/Checkout"})
public class AdminAuthenticationFIlter implements Filter {

    /**
     * Default constructor. 
     */
    public AdminAuthenticationFIlter() {
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
		
		boolean isLoggedIn = (session != null && session.getAttribute("userfullname") != null && !((String)session.getAttribute("userfullname")).equalsIgnoreCase(""));
		String loginURI = httpRequest.getContextPath() + "/Auth";
		boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);
		boolean isLoginPage = httpRequest.getRequestURI().endsWith("login.jsp");
		
		if(isLoggedIn && (isLoginRequest || isLoginPage)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("Home");
			dispatcher.forward(request, response);
		}else if(isLoginRequest || isLoginPage) {
			
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
