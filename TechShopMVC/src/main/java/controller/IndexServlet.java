package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexServlet() {
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
		System.out.println("doGet Index Servlet called");
		getIndexPage(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("-----------------------------");
		System.out.println("doPost Index Servlet called");
		doGet(request, response);
	}

	private void getIndexPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDAO productDAO = new ProductDAO();
		List<Product> products = productDAO.getPopularProducts();
		List<Brand> brands = productDAO.getAllBrands();
		List<Category> categories = productDAO.getAllCategory();

		request.setAttribute("productList", products);
		request.setAttribute("brandList", brands);
		request.setAttribute("categoryList", categories);

		RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConstant.INDEX_JSP);
		dispatcher.forward(request, response);
	}

}
