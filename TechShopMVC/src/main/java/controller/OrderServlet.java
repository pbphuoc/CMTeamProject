package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOService;
import dao.OrderDAO;
import dao.DAO.DAOType;
import dao.DAO.QueryResult;

/**
 * Servlet implementation class OrderServlet
 */
//@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		submitOrder(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void submitOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderDAO orderDAO = (OrderDAO) DAOService.getDAO(DAOType.ORDER);
		String date = "14/01/2023";
		String checkOutEmail = "ff.pbphuoc@gmail.com";
		String checkOutFullname = "Phuoc Pham";
		String checkOutPhone = "04987654321";
		String receiverFullname = "Chau Vuong";
		String receiverPhone = "04123456789";
		String receiverAddress = "123 abc street, inala, qld, 4077";
		String receiverMethodId = "1";
		String paymentTypeId = "1";
		String paymentDate = "";
		String shipping = "0";
		String total = "12345.00";
		HashMap<String,Integer> orderItems = new HashMap<>() {{
			put("1", 1);
			put("2", 2);
			put("3", 3);
			put("4", 4);
		}};
		QueryResult result = orderDAO.insertOrder(date, checkOutEmail, checkOutFullname, checkOutPhone, receiverFullname, receiverPhone, receiverAddress, receiverMethodId, paymentTypeId, paymentDate, shipping, total, orderItems);
		System.out.println(result);
	}	

}