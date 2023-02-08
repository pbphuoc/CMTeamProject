package controller;

import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import constant.GlobalConstant;
import dao.ProductDAO;
import entity.Product;
import model.SearchFilter;
import util.Utility;

/**
 * Servlet implementation class ProductDetailServlet
 */
@WebServlet(urlPatterns = GlobalConstant.PRODUCT_URL)
public class ProductlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(ProductlServlet.class);

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
			logger.error(e.getMessage());
			Utility.handleError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	private void getProductDetail(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("productID");

			ProductDAO productDAO = ProductDAO.getProductDAO();
			Product product = productDAO.getProductByID(id);
			List<String> medias = productDAO.getAllMediaByProductID(id);

			request.setAttribute("product", product);
			request.setAttribute("medias", medias);
			RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConstant.PRODUCT_JSP);
			dispatcher.forward(request, response);

		} catch (Exception e) {
			logger.error(e.getMessage());
			Utility.handleError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	private void searchProductWithFilter(HttpServletRequest request, HttpServletResponse response) {
		try {
			String[] searchKeywords = request.getParameter("keywords").split(" ");
			String[] brands = (request.getParameterValues("brand") != null) ? request.getParameterValues("brand")
					: new String[0];
			String[] categories = (request.getParameterValues("category") != null)
					? request.getParameterValues("category")
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

			ProductDAO productDAO = ProductDAO.getProductDAO();
			Object[] listOfProductAndFilters = productDAO.searchProductByNameWithFilters(searchKeywords, brands,
					categories, priceMin, priceMax, availabilities, sortBy, perPage, page);

			List<Product> products = (List<Product>) listOfProductAndFilters[0];
			Map<String, SearchFilter> brandFilters = (Map<String, SearchFilter>) listOfProductAndFilters[1];
			Map<String, SearchFilter> categoryFilters = (Map<String, SearchFilter>) listOfProductAndFilters[2];
			Map<String, SearchFilter> stockStatusFilters = (Map<String, SearchFilter>) listOfProductAndFilters[3];
			Map<String, String> sorters = (Map<String, String>) listOfProductAndFilters[4];
			Map<String, String> resultPerPage = (Map<String, String>) listOfProductAndFilters[5];
			Map<String, String> pagingMap = (Map<String, String>) listOfProductAndFilters[6];

			int totalResult = (int) listOfProductAndFilters[7];

			request.setAttribute("keyword", request.getParameter("keywords"));
			request.setAttribute("products", products);
			request.setAttribute("brandFilters", brandFilters);
			request.setAttribute("categoryFilters", categoryFilters);
			request.setAttribute("availabilityFilters", stockStatusFilters);
			request.setAttribute("sorters", sorters);
			request.setAttribute("priceMin", priceMin);
			request.setAttribute("priceMax", priceMax);
			request.setAttribute("resultPerPage", resultPerPage);
			request.setAttribute("pagingMap", pagingMap);
			request.setAttribute("totalResult", totalResult);

			RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConstant.SEARCH_JSP);
			dispatcher.forward(request, response);

		} catch (Exception e) {
			logger.error(e.getMessage());
			Utility.handleError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
