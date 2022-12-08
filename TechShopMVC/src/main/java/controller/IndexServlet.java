package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/default")
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
		try {
			switch(action) {
				case "/default":
					getIndexPage(request, response);
			}
		}catch (Exception e) {
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
	
	private void getIndexPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Product> products = new ArrayList<Product>();
		products.add(new Product("prd1", "Dell 7040 SFF Bundle Desktop i7-6700", "Dell 7040 SFF Bundle Desktop i7-6700 3.4GHz 16GB RAM 512GB NVMe SSD + 22 Monitor - Refurbished Grade A", "2850.00", "2599.00", null, null, "/images/product/product_computerdell1.jpg"));
		products.add(new Product("prd2", "Apple MacBook Pro 13 M2 chip 512GB Space Grey 2022 MNEJ3X/A", "The 13-inch MacBook Pro laptop is a portable powerhouse. Get more done faster with a next-generation 8-core CPU, 10-core GPU and 8GB of unified memory. Go all day and into the night, thanks to the power-efficient performance of the Apple M2 chip*Thanks to its active cooling system, the 13-inch MacBook Pro can sustain pro levels of performance, so you can run CPU- and GPU-intensive tasks for hours on end.", "0", "3200.00", null, null, "/images/product/product_laptopapple1.jpg"));
		products.add(new Product("prd3", "iPhone 14 Pro Max 128GB", "iPhone 14 Pro Max 128GB", "0", "1850.00", null, null, "/images/product/product_iPhone14promax.jpg"));
		products.add(new Product("prd4", "APPLE IPAD AIR (5 GEN) 10.9-INCH WI-FI+CELL 256GB STARLIGHT", "Apple iPad Air (5th Generation) 10.9-inch 256GB Wi-Fi + Cellular Starlight iPad Air. With an immersive 10.9-inch Liquid Retina display1. The breakthrough Apple M1 chip delivers faster performance, making iPad Air a creative and mobile gaming powerhouse. Featuring Touch ID, advanced cameras, blazing-fast 5G2 and Wi-Fi 6, USB-C, and support for Magic Keyboard and Apple Pencil (2nd generation)", "1000.00", "985.50", null, null, "/images/product/product_iPadairgen51.jpg"));
		products.add(new Product("prd5", "Apple Watch Series 7 (GPS + Cellular) 45mm Blue Aluminium Case with Abyss Blue Sport Band", "Originally released October 2021. S7 with 64-bit dual-core processor. Water resistant to 50 metres¹Always-On Retina LTPO OLED display (1,000 nits brightness). 802.11b/g/n 2.4GHz and 5GHz. Bluetooth 5.0. Built-in rechargeable lithium-ion battery (Up to 18 hours²). Third-generation optical heart sensor. Accelerometer: up to 32 g-forces with fall detection. Gyroscope. Ambient light sensor. Capacity 32GB. Ceramic and sapphire crystal back", "750.00", "639.20", null, null, "/images/product/product_appleWatch71.jpg"));
		products.add(new Product("prd6", "iPhone 11 Pro Max Silicone", "Designed by Apple to complement iPhone 11 Pro Max, the form of the silicone case fits snugly over the volume buttons, side button and curves of your device without adding bulk. A soft microfibre lining on the inside helps protect your iPhone. On the outside, the silky, soft-touch finish of the silicone exterior feels great in your hand. And you can keep it on all the time, even when you’re charging wirelessly. Like every Apple-designed case, it undergoes thousands of hours of testing throughout the design and manufacturing process. So not only does it look great, it’s built to protect your iPhone from scratches and drops.", "0", "65.00", null, null, "/images/product/product_iPhonecase1.jpg"));
		request.setAttribute("productList", products);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/index.jsp");
		dispatcher.forward(request, response);
	}

}
