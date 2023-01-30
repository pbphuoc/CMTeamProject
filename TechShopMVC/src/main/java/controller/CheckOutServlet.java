package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constant.GlobalConstant;
import constant.OrderPaymentTypeEnum;
import constant.OrderReceiveMethodEnum;
import constant.OrderStatusEnum;
import dao.ProductDAO;
import dao.UserDAO;
import entity.Order;
import model.OrderItemDTO;

/**
 * Servlet implementation class CheckOutServlet
 */
@WebServlet(urlPatterns = "/Checkout")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		System.out.println("-----------------------------");
		System.out.println("doGet Checkout Servlet called");
		System.out.println("Current command: " + request.getParameter("command"));
		String command = request.getParameter(GlobalConstant.COMMAND) != null ? request.getParameter(GlobalConstant.COMMAND) : "";
		
		try {
			switch (command) {
			case GlobalConstant.MORE_INFO:			
				getAdditionalInformation(request,response);
				break;							
			case "confirm":
				getConfirmation(request, response);
				break;			
			default:
				getAdditionalInformation(request,response);
				break;
			}
		} catch (Exception e) {
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
	
	protected void getAdditionalInformation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// LOG4J
		System.out.println("getting additional info");
		ProductDAO cartDAO = new ProductDAO();
		HttpSession session = request.getSession();
		HashMap<String, Integer> cartItems = (HashMap<String, Integer>) session.getAttribute("cartItems");
		List<OrderItemDTO> cartItemDetails = cartDAO.getAllProductInCartByID(cartItems);
		
		
		request.setAttribute("CartItemDetails", cartItemDetails);
		System.out.println(cartItemDetails.size());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("checkout.jsp");
		dispatcher.forward(request, response);
	}
	 
	protected void getConfirmation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDAO cartDAO = new ProductDAO();		
		UserDAO userDao = new UserDAO();
		HttpSession session = request.getSession();
		String checkOutEmail = request.getParameter("checkOutEmail");
		if(session.getAttribute("user") == null && userDao.userExist(checkOutEmail)) {
			request.setAttribute("error", "This Email Address has been registered in the system. Please login before proceed to checkout");
			RequestDispatcher dispatcher = request.getRequestDispatcher("Checkout?command=moreInfo");
			dispatcher.forward(request, response);			
		}else {
			HashMap<String, Integer> cartItems = (HashMap<String, Integer>) session.getAttribute("cartItems");
			List<OrderItemDTO> items = cartDAO.getAllProductInCartByID(cartItems);			
			String id = "";
			String orderNumber = "";
			String date = "";		
			String checkOutFullname = "";
			String checkOutPhone = "";
			String paymentDate = "";
			double shipping = 0;
			double total = 0;	
			
			String receiverFullname = request.getParameter("receiverFirstName") + " " + request.getParameter("receiverLastName");		
			String receiverPhone = request.getParameter("receiverPhoneNumber");
			String receiverAddress = request.getParameter("receiverAddress");
			
			OrderReceiveMethodEnum receiveMethod = OrderReceiveMethodEnum.valueOf(request.getParameter("deliveryMethod"));
			OrderPaymentTypeEnum paymentType = OrderPaymentTypeEnum.valueOf(request.getParameter("paymentMethod"));
			
			OrderStatusEnum status = OrderStatusEnum.REVIEWING;
			
			String billingFullname = request.getParameter("billingFname") + " " + request.getParameter("billingLname");
			String billingAddress = request.getParameter("billingAddress");
			String billingPhone = request.getParameter("billingPhone");
			String paymentName = request.getParameter("cardHolderName");
			String paymentSource = request.getParameter("cardNumber");				
			
			Order order = new Order(id, orderNumber, date, checkOutEmail, checkOutFullname,
						checkOutPhone, receiverFullname, receiverPhone, receiverAddress,
						receiveMethod, status,shipping, total, paymentType,paymentDate,paymentName,paymentSource, billingFullname,billingAddress,billingPhone);
			 
			request.setAttribute("order",order);
			request.setAttribute("items", items);
			RequestDispatcher dispatcher = request.getRequestDispatcher("confirmation.jsp");
			dispatcher.forward(request, response);			
		}
	}
}
