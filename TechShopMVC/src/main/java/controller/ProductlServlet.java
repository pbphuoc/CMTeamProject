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

import dao.DAOService;
import dao.ProductDAO;
import dao.DAO.DAOType;
import entity.Product;
import model.SearchFilterDTO;
import util.Utility;

/**
 * Servlet implementation class ProductDetailServlet
 */
public class ProductlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductlServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String command = request.getParameter("command") != null ? request.getParameter("command") : "";
		try {
			switch (command) {
			case "viewProductDetail":
				getProductDetail(request, response);
				break;
			case "search":
				searchProductWithFilter(request, response);
				break;	
			case "":
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
	
	private void getProductDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDAO productDAO = (ProductDAO) DAOService.getDAO(DAOType.PRODUCT);
		String id = request.getParameter("productID");
		Product product = productDAO.getRecordByID(id);
		List<String> medias = productDAO.getAllMediaByProductID(id);
		request.setAttribute("product", product);
		request.setAttribute("medias", medias);
		RequestDispatcher dispatcher = request.getRequestDispatcher("product.jsp");
		dispatcher.forward(request, response);		
	}	
	
//	private void searchProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		ProductDAO productDAO = (ProductDAO) DAOService.getDAO(DAOType.PRODUCT);
//		String[] searchKeywords = request.getParameter("keywords").split(" ");
//		Object[] listOfProductAndFilters = productDAO.searchProductByName(searchKeywords);
//		List<Product> products = (List<Product>) listOfProductAndFilters[0];
//		Map<String,SearchFilterDTO> brandFilters = (Map<String, SearchFilterDTO>) listOfProductAndFilters[1];
//		Map<String,SearchFilterDTO> categoryFilters = (Map<String, SearchFilterDTO>) listOfProductAndFilters[2];
//		Map<String,SearchFilterDTO> availabilityFilters = (Map<String, SearchFilterDTO>) listOfProductAndFilters[3];
//		Map<String,SearchFilterDTO> sorters = (Map<String, SearchFilterDTO>) listOfProductAndFilters[4];
//		request.setAttribute("keyword", request.getParameter("keywords"));
//		request.setAttribute("products", products);
//		request.setAttribute("brandFilters", brandFilters);
//		request.setAttribute("categoryFilters", categoryFilters);
//		request.setAttribute("availabilityFilters", availabilityFilters);
//		request.setAttribute("sorters", sorters);				
//		RequestDispatcher dispatcher = request.getRequestDispatcher("search.jsp");
//		dispatcher.forward(request, response);		
//	}
	
	private void searchProductWithFilter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDAO productDAO = (ProductDAO) DAOService.getDAO(DAOType.PRODUCT);
		String[] searchKeywords = request.getParameter("keywords").split(" ");
		String[] brands = (request.getParameterValues("brand") != null) ? request.getParameterValues("brand") : new String[0];
		String[] categories = (request.getParameterValues("category") != null) ? request.getParameterValues("category") : new String[0];
		String priceMin = (request.getParameter("priceMin") != null) ? request.getParameter("priceMin") : "";
		String priceMax = (request.getParameter("priceMax") != null) ? request.getParameter("priceMax") : "";
		String[] availabilities = (request.getParameterValues("availability") != null) ? request.getParameterValues("availability") : new String[0];
		String sortBy = (request.getParameter("sortBy") != null) ? request.getParameter("sortBy") : "0";
		String perPage = (request.getParameter("perPage") != null) ? request.getParameter("perPage") : "16";
		String page = (request.getParameter("page") != null) ? request.getParameter("page") : "1" ;
		Object[] listOfProductAndFilters = productDAO.searchProductByNameWithFilters(searchKeywords,brands,categories,priceMin,priceMax,availabilities,sortBy,perPage,page);
		List<Product> products = (List<Product>) listOfProductAndFilters[0];
		Map<String,SearchFilterDTO> brandFilters = (Map<String, SearchFilterDTO>) listOfProductAndFilters[1];
		Map<String,SearchFilterDTO> categoryFilters = (Map<String, SearchFilterDTO>) listOfProductAndFilters[2];
		Map<String,SearchFilterDTO> availabilityFilters = (Map<String, SearchFilterDTO>) listOfProductAndFilters[3];		
		Map<String,SearchFilterDTO> sorters = (Map<String, SearchFilterDTO>) listOfProductAndFilters[4];		
		Map<String,SearchFilterDTO> resultPerPage = (Map<String, SearchFilterDTO>) listOfProductAndFilters[5];
		Map<String,String> pagingMap = (Map<String, String>) listOfProductAndFilters[6];
		int totalResult = (int)listOfProductAndFilters[7];
		request.setAttribute("keyword", request.getParameter("keywords"));
		request.setAttribute("products", products);
		request.setAttribute("brandFilters", brandFilters);
		request.setAttribute("categoryFilters", categoryFilters);
		request.setAttribute("availabilityFilters", availabilityFilters);
		request.setAttribute("sorters", sorters);
		request.setAttribute("priceMin", priceMin);
		request.setAttribute("priceMax", priceMax);
		request.setAttribute("resultPerPage", resultPerPage);
		request.setAttribute("pagingMap", pagingMap);
		request.setAttribute("totalResult", totalResult);
		RequestDispatcher dispatcher = request.getRequestDispatcher("search.jsp");
		dispatcher.forward(request, response);		
	}	

}
