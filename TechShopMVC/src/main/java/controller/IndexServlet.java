package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;
import dao.DAOService;
import dao.ProductDAO;
import dao.DAO.DAOType;
import model.ProductDTO;
import model.SearchFilterDTO;

/**
 * Servlet implementation class IndexServlet
 */
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("-----------------------------");
		System.out.println("doGet Index Servlet called");		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		String action = request.getServletPath();
//		try {
//			switch(action) {
//				case "/Home":
//					getIndexPage(request, response);
//			}
//		}catch (Exception e) {
//			// TODO: handle exception
//			throw new ServletException(e);
//		}
		getIndexPage(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("-----------------------------");
		System.out.println("doPost Index Servlet called");
		doGet(request, response);
	}
	
	private void getIndexPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDAO productDAO = (ProductDAO)DAOService.getDAO(DAOType.PRODUCT);
		List<ProductDTO> products = productDAO.getAllRecords();
		Map<String,SearchFilterDTO> brands = productDAO.getAllFilterFromDB(ProductDAO.SELECT_ALL_BRAND_SQL);
		Map<String,SearchFilterDTO> categories = productDAO.getAllFilterFromDB(ProductDAO.SELECT_ALL_CATEGORY_SQL);
		request.setAttribute("productList", products);
		request.setAttribute("brandMap", brands);
		request.setAttribute("categoryMap", categories);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

}
