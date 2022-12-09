package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;

public class ProductDAO extends DAO {
	
	private static final String SELECT_ALL_PRODUCT_SQL = "SELECT * FROM product;";
	private Connection connection;
	public ProductDAO() {
		super();
	}	

	@Override
	public List<Product> getAll() {	
		List<Product> products = new ArrayList<Product>();
		connection = getConnection();
		try(
				PreparedStatement selectStm = connection.prepareStatement(SELECT_ALL_PRODUCT_SQL);)
		{
			ResultSet rs = selectStm.executeQuery();
			while(rs.next()) {
				String id = rs.getInt("id") + "";
				String name = rs.getString("name");
				String description = rs.getString("description");
				double oldPrice = rs.getDouble("old_price");
				double newPrice = rs.getDouble("new_price");
				String brandID = rs.getString("brand_id");
				String categoryID = rs.getString("category_id");
				String imgSrc = rs.getString("img_src");
				products.add(new Product(id,name,description,oldPrice,newPrice,brandID,categoryID,imgSrc));
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}
}
