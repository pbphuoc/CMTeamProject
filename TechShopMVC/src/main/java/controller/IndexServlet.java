package controller;

import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import constant.GlobalConstant;
import dao.ProductDAO;
import entity.Brand;
import entity.Category;
import entity.Product;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet(urlPatterns = GlobalConstant.HOME_URL)
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(IndexServlet.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			logger.info("Get Home");
			getIndexPage(request, response);
		} catch (Exception e) {
			logger.error(e.toString());
		}

	}

	private void getIndexPage(HttpServletRequest request, HttpServletResponse response) {
		try {
			ProductDAO productDAO = ProductDAO.getProductDAO();
			List<Product> products = productDAO.getPopularProducts();
			List<Brand> brands = productDAO.getAllBrands();
			List<Category> categories = productDAO.getAllCategory();

			request.setAttribute("productList", products);
			request.setAttribute("brandList", brands);
			request.setAttribute("categoryList", categories);

			RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConstant.INDEX_JSP);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			logger.error(e.toString());
		}
	}

}
