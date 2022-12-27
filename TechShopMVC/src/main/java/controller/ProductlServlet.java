package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BrandDTO;
import model.CategoryDTO;
import model.Product;
import service.DAOService;
import service.ProductDAO;
import service.DAO.DAOType;

/**
 * Servlet implementation class ProductDetailServlet
 */
@WebServlet("/Product")
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
			case "searchProduct":
				searchProduct(request, response);
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
	

	private void searchProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDAO productDAO = (ProductDAO) DAOService.getDAO(DAOType.PRODUCT);
		String[] searchKeywords = request.getParameter("searchKeywords").split(" ");
		Object[] listOfProductAndFilters = productDAO.searchProductByID(searchKeywords);
		List<Product> products = (List<Product>) listOfProductAndFilters[0];
		Map<String,BrandDTO> brandFilters = (Map<String, BrandDTO>) listOfProductAndFilters[1];
		Map<String,CategoryDTO> categoryFilters = (Map<String, CategoryDTO>) listOfProductAndFilters[2];
		Map<String,Integer> availabilityFilters = (Map<String, Integer>) listOfProductAndFilters[3];
		request.setAttribute("products", products);
		request.setAttribute("brandFilters", brandFilters);
		request.setAttribute("categoryFilters", categoryFilters);
		request.setAttribute("availabilityFilters", availabilityFilters);
		RequestDispatcher dispatcher = request.getRequestDispatcher("search.jsp");
		dispatcher.forward(request, response);		
	}	

}
