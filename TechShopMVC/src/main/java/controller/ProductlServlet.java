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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import constant.GlobalConstant;
import dao.ProductDAO;
import entity.Product;
import model.SearchFilterDTO;

/**
 * Servlet implementation class ProductDetailServlet
 */
@WebServlet(urlPatterns = GlobalConstant.PRODUCT_URL)
public class ProductlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(ProductlServlet.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductlServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String command = request.getParameter(GlobalConstant.COMMAND) != null
				? request.getParameter(GlobalConstant.COMMAND)
				: GlobalConstant.BLANK;
		try {
			switch (command) {
			case GlobalConstant.VIEW_PRODUCT_DETAIL:
				getProductDetail(request, response);
				break;
			case GlobalConstant.SEARCH:
				searchProductWithFilter(request, response);
				break;
			case GlobalConstant.BLANK:
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.toString());
		}
	}

	private void getProductDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDAO productDAO = new ProductDAO();
		String id = request.getParameter("productID");
		Product product = productDAO.getProductByID(id);
		List<String> medias = productDAO.getAllMediaByProductID(id);

		request.setAttribute("product", product);
		request.setAttribute("medias", medias);
		RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConstant.PRODUCT_JSP);
		dispatcher.forward(request, response);
	}

	private void searchProductWithFilter(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] searchKeywords = request.getParameter("keywords").split(" ");
		String[] brands = (request.getParameterValues("brand") != null) ? request.getParameterValues("brand")
				: new String[0];
		String[] categories = (request.getParameterValues("category") != null) ? request.getParameterValues("category")
				: new String[0];
		String priceMin = (request.getParameter("priceMin") != null) ? request.getParameter("priceMin")
				: GlobalConstant.BLANK;
		String priceMax = (request.getParameter("priceMax") != null) ? request.getParameter("priceMax")
				: GlobalConstant.BLANK;
		String[] availabilities = (request.getParameterValues("availability") != null)
				? request.getParameterValues("availability")
				: new String[0];
		String sortBy = (request.getParameter("sortBy") != null) ? request.getParameter("sortBy")
				: GlobalConstant.DEFAULT_SORTBY;
		String perPage = (request.getParameter("perPage") != null) ? request.getParameter("perPage")
				: GlobalConstant.DEFAULT_RESULTPERPAGE;
		String page = (request.getParameter("page") != null) ? request.getParameter("page")
				: GlobalConstant.DEFAULT_PAGE;

		ProductDAO productDAO = new ProductDAO();
		Object[] listOfProductAndFilters = productDAO.searchProductByNameWithFilters(searchKeywords, brands, categories,
				priceMin, priceMax, availabilities, sortBy, perPage, page);
		List<Product> products = (List<Product>) listOfProductAndFilters[0];
		Map<String, SearchFilterDTO> brandFilters = (Map<String, SearchFilterDTO>) listOfProductAndFilters[1];
		Map<String, SearchFilterDTO> categoryFilters = (Map<String, SearchFilterDTO>) listOfProductAndFilters[2];
		Map<String, SearchFilterDTO> availabilityFilters = (Map<String, SearchFilterDTO>) listOfProductAndFilters[3];
		Map<String, SearchFilterDTO> sorters = (Map<String, SearchFilterDTO>) listOfProductAndFilters[4];
		Map<String, SearchFilterDTO> resultPerPage = (Map<String, SearchFilterDTO>) listOfProductAndFilters[5];
		Map<String, String> pagingMap = (Map<String, String>) listOfProductAndFilters[6];
		int totalResult = (int) listOfProductAndFilters[7];

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

		RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConstant.SEARCH_JSP);
		dispatcher.forward(request, response);
	}

}
