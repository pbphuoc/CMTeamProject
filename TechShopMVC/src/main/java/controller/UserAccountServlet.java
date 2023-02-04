package controller;

import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import constant.GlobalConstant;
import dao.OrderDAO;
import entity.Order;
import entity.User;

/**
 * Servlet implementation class UserAccountServlet
 */
@WebServlet(urlPatterns = GlobalConstant.ACCOUNT_URL)
public class UserAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(UserAccountServlet.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		String command = request.getParameter(GlobalConstant.COMMAND) != null
				? request.getParameter(GlobalConstant.COMMAND)
				: GlobalConstant.BLANK;
		logger.info(command);
		try {
			switch (command) {
			case GlobalConstant.VIEW_ORDER:
				viewOrders(request, response);
				break;
			case GlobalConstant.BLANK:
				break;
			}
		} catch (Exception e) {
			logger.error(e.toString());
		}
	}

	protected void viewOrders(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute(GlobalConstant.USER);
			OrderDAO orderDAO = new OrderDAO();
			List<Order> orderList = orderDAO.getOrderByUserOrEmailAndOrderNumber(user.getId(), GlobalConstant.BLANK,
					GlobalConstant.BLANK);

			request.setAttribute("orderList", orderList);
			RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConstant.ORDER_HISTORY_JSP);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			logger.error(e.toString());
		}
	}
}
