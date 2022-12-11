package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Product;

public class CartDAO extends DAO<Product> {

	private static final String SELECT_ALL_PRODUCT_SQL = "SELECT * FROM product;";
	private static final String SELECT_PRODUCT_BY_ID_SQL = "SELECT * FROM product where id=?";
	
	int[][] array = {{1,1},{2,1},{4,1}};
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	private Connection connection;
	public CartDAO() {
		super();
	}
	
	@Override 
	public List<Product> getAllRecords(){
		List<Product> cartList = new ArrayList<Product>();
		Product product = null;
		try {
			for (int[] a : array) {
				connection = getConnection();
				ps = connection.prepareStatement(SELECT_PRODUCT_BY_ID_SQL);
				ps.setString(1, String.valueOf(a[0]));
				rs = ps.executeQuery();
				while(rs.next()) {
					product = new Product(rs.getString("id"), rs.getString("name"), rs.getString("description"), rs.getDouble("old_price"), rs.getDouble("new_price"), rs.getString("brand_id"), rs.getString("category_id"), rs.getString("img_src"));
					cartList.add(product);
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cartList;
	}

	@Override
	public Product getRecordByID(String id) {
		// TODO Auto-generated method stub
		return null;
	};
	
	
}
