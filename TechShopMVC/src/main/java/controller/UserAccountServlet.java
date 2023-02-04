package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
	 * @see HttpServlet#HttpServlet()
	 */
	public UserAccountServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("-----------------------------");
		System.out.println("doGet User Account Servlet called");
		System.out.println("Current command: " + request.getParameter(GlobalConstant.COMMAND));
		String command = request.getParameter(GlobalConstant.COMMAND) != null
				? request.getParameter(GlobalConstant.COMMAND)
				: GlobalConstant.BLANK;
		try {
			switch (command) {
			case GlobalConstant.VIEW_ORDER:
				viewOrders(request, response);
				break;
			case GlobalConstant.BLANK:
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.toString());
		}
	}

	protected void viewOrders(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(GlobalConstant.USER);
		OrderDAO orderDAO = new OrderDAO();
		List<Order> orderList = orderDAO.getOrderByUserOrEmailAndOrderNumber(user.getId(), GlobalConstant.BLANK,
				GlobalConstant.BLANK);

		request.setAttribute("orderList", orderList);
		RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConstant.ORDER_HISTORY_JSP);
		dispatcher.forward(request, response);
	}

}
