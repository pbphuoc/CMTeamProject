package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.CartItemDetail;
import model.CartItem;
import model.Product;

public class CartDAO  {

	private final String jdbcURL = "jdbc:mysql://localhost:3306/cm_project";
	private final String jdbcUserName = "projectuser";
	private final String idbcPassword = "codingmentor";
	
	private static final String SELECT_ALL_PRODUCT_SQL = "SELECT * FROM product;";
	private static final String SELECT_PRODUCT_BY_ID_SQL = "SELECT * FROM product where id=?";
	
	
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL,jdbcUserName,idbcPassword);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return connection;
	};
	
	public CartDAO() {
		super();
	}
	
	
	
	public List<CartItemDetail> getAllProductInCartByID(HashMap<String, Integer> cartItems){
		List<CartItemDetail> cartList = new ArrayList<CartItemDetail>();
		Product product = null;
		try {
			for (Map.Entry<String, Integer> cI : cartItems.entrySet()) {
				
				ps = getConnection().prepareStatement(SELECT_PRODUCT_BY_ID_SQL);
				ps.setString(1, (String) cI.getKey());
				rs = ps.executeQuery();
				while(rs.next()) {
					product = new Product(rs.getString("id"), rs.getString("name"), rs.getString("description"), rs.getDouble("old_price"), rs.getDouble("new_price"), rs.getString("brand_id"), rs.getString("category_id"), rs.getString("img_src"));
					cartList.add(new CartItemDetail(product, (int) cI.getValue()));
			}	
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cartList;
	}

	
	
	
}
