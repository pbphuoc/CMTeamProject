package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;

public class ProductDAO extends DAO<Product> {
	
	private static final String SELECT_ALL_PRODUCT_SQL = "SELECT * FROM product;";
	private static final String SELECT_PRODUCT_BY_ID_SQL = "SELECT * FROM product where id=?";
	private static final String SELECT_MEDIA_BY_PRODUCT_ID_SQL = "SELECT * FROM media where product_id = ?";
	
	private Connection connection;
	
	public ProductDAO() {
		super();
		connection = getConnection();
	}	

	@Override
	public List<Product> getAllRecords() {	
		List<Product> products = new ArrayList<Product>();		
		try(PreparedStatement selectStm = connection.prepareStatement(SELECT_ALL_PRODUCT_SQL);)
		{
			ResultSet result = selectStm.executeQuery();
			while(result.next()) {
				String id = result.getInt("id") + "";
				String name = result.getString("name");
				String description = result.getString("description");
				double oldPrice = result.getDouble("old_price");
				double newPrice = result.getDouble("new_price");
				String brandID = result.getString("brand_id");
				String categoryID = result.getString("category_id");
				String imgSrc = result.getString("img_src");
				products.add(new Product(id,name,description,oldPrice,newPrice,brandID,categoryID,imgSrc));
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public Product getRecordByID(String id) {
		Product product = null;
		try(PreparedStatement selectStm = connection.prepareStatement(SELECT_PRODUCT_BY_ID_SQL);){
			selectStm.setString(1, id);
			ResultSet result = selectStm.executeQuery();
			if(!result.next())
				return product;
			product = new Product(result.getString("id"), result.getString("name"), result.getString("description"), result.getDouble("old_price"), result.getDouble("new_price"), result.getString("brand_id"), result.getString("category_id"), result.getString("img_src"));
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return product;
	}
	
	public List<String> getAllMediaByProductID(String id){
		List<String> medias = new ArrayList<String>();	
		try(PreparedStatement selectStm = connection.prepareStatement(SELECT_MEDIA_BY_PRODUCT_ID_SQL);)
		{
			selectStm.setString(1, id);
			ResultSet result = selectStm.executeQuery();
			while(result.next()) {
				medias.add(result.getString("src"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return medias;
	}

}
