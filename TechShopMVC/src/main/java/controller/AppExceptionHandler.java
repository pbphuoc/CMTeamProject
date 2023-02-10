package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import global.GlobalConstant;

/**
 * Servlet implementation class AppExceptionHandler
 */
@WebServlet("/AppExceptionHandler")
public class AppExceptionHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(AppExceptionHandler.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processError(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processError(request, response);
	}

	private void processError(HttpServletRequest request, HttpServletResponse response) {
		try {
			Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
			String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");

			if (servletName == null) {
				servletName = "Unknown";
			}
			String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
			if (requestUri == null) {
				requestUri = "Unknown";
			}

			switch (statusCode) {
			case GlobalConstant.ERROR_403:
				request.setAttribute(GlobalConstant.ERROR_CODE, GlobalConstant.ERROR_403);
				request.setAttribute(GlobalConstant.ERROR_NAME, GlobalConstant.ERROR_403_NAME);
				request.setAttribute(GlobalConstant.ERROR_MESSAGE, GlobalConstant.ERROR_403_MESSAGE);
				logger.debug(String.format("Error %d", statusCode));
				logger.debug(String.format("Requested URI %s", requestUri));
				logger.debug(String.format("Servlet Name %s", servletName));
				break;

			case GlobalConstant.ERROR_404:
				request.setAttribute(GlobalConstant.ERROR_CODE, GlobalConstant.ERROR_404);
				request.setAttribute(GlobalConstant.ERROR_NAME, GlobalConstant.ERROR_404_NAME);
				request.setAttribute(GlobalConstant.ERROR_MESSAGE, GlobalConstant.ERROR_404_MESSAGE);
				logger.debug(String.format("Error %d", statusCode));
				logger.debug(String.format("Requested URI %s", requestUri));
				logger.debug(String.format("Servlet Name %s", servletName));
				break;

			case GlobalConstant.ERROR_500:
				request.setAttribute(GlobalConstant.ERROR_CODE, GlobalConstant.ERROR_500);
				request.setAttribute(GlobalConstant.ERROR_NAME, GlobalConstant.ERROR_500_NAME);
				request.setAttribute(GlobalConstant.ERROR_MESSAGE, GlobalConstant.ERROR_500_MESSAGE);
				logger.error(String.format("Error %d", statusCode));
				logger.error(String.format("Requested URI %s", requestUri));
				logger.error(String.format("Servlet Name %s", servletName));
				break;

			default:
				break;
			}
			logger.debug(String.format("Error %d", statusCode));
			logger.debug(String.format("Requested URI %s", requestUri));
			logger.debug(String.format("Servlet Name %s", servletName));

			RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConstant.ERROR_JSP);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
