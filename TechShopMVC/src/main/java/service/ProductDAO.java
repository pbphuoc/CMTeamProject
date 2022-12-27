package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.BrandDTO;
import model.CategoryDTO;
import model.Product;

public class ProductDAO extends DAO<Product> {
	
	private static final String SELECT_ALL_PRODUCT_SQL = "SELECT * FROM product;";
	private static final String SELECT_PRODUCT_BY_ID_SQL = "SELECT * FROM product where id=?";
	private static final String SEARCH_PRODUCT_BY_ID_SQL = "SELECT * FROM product where name like ?";
	private static final String SELECT_ALL_BRAND_SQL = "SELECT * FROM brand;";
	private static final String SELECT_ALL_CATEGORY_SQL = "SELECT * FROM category;";
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
				int stock = result.getInt("stock");
				products.add(new Product(id,name,description,oldPrice,newPrice,brandID,categoryID,imgSrc,stock));
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}
	
	public Map<String, BrandDTO> getAllBrandFilter() {	
		Map<String, BrandDTO> brands = new HashMap<String, BrandDTO>();		
		try(PreparedStatement selectStm = connection.prepareStatement(SELECT_ALL_BRAND_SQL);)
		{
			ResultSet result = selectStm.executeQuery();
			while(result.next()) {
				String id = result.getInt("id") + "";
				String name = result.getString("name");
				BrandDTO brand =  new BrandDTO(id, name);
				brands.put(id, brand);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return brands;
	}
	
	public Map<String, CategoryDTO> getAllCategoryFilter() {	
		Map<String, CategoryDTO> categories = new HashMap<String, CategoryDTO>();		
		try(PreparedStatement selectStm = connection.prepareStatement(SELECT_ALL_CATEGORY_SQL);)
		{
			ResultSet result = selectStm.executeQuery();
			while(result.next()) {
				String id = result.getInt("id") + "";
				String name = result.getString("name");
				CategoryDTO category =  new CategoryDTO(id, name);
				categories.put(id, category);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}	
	
	public Object[] searchProductByID(String[] keywords) {
		List<Product> products = new ArrayList<Product>();	
		Map<String,BrandDTO> brandFilters = getAllBrandFilter(); 
		Map<String,CategoryDTO> categoryFilters = getAllCategoryFilter(); 
		Map<String,Integer> availabilityFilters = new HashMap<String,Integer>();
		availabilityFilters.put(Product.STATUS_IN_STOCK, 0);
		availabilityFilters.put(Product.STATUS_OUT_OF_STOCK, 0);
		try(PreparedStatement selectStm = connection.prepareStatement(SEARCH_PRODUCT_BY_ID_SQL);){
			selectStm.setString(1, "%" + keywords[0] + "%");
			System.out.println("Query: " + selectStm.toString());
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
				int stock = result.getInt("stock");	
				Product product = new Product(id,name,description,oldPrice,newPrice,brandID,categoryID,imgSrc,stock);
				products.add(product);
				updateBrandFilter(brandFilters, brandID);
				updateCategoryFilter(categoryFilters, categoryID);
				updateAvailabilityFilter(availabilityFilters, product.getStockStatus());
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return new Object[]{products, brandFilters, categoryFilters, availabilityFilters};
	}

	@Override
	public Product getRecordByID(String id) {
		Product product = null;
		try(PreparedStatement selectStm = connection.prepareStatement(SELECT_PRODUCT_BY_ID_SQL);){
			selectStm.setString(1, id);
			ResultSet result = selectStm.executeQuery();
			if(!result.next())
				return product;
			String name = result.getString("name");
			String description = result.getString("description");
			double oldPrice = result.getDouble("old_price");
			double newPrice = result.getDouble("new_price");
			String brandID = result.getString("brand_id");
			String categoryID = result.getString("category_id");
			String imgSrc = result.getString("img_src");
			int stock = result.getInt("stock");			
			product = new Product(id, name, description, oldPrice, newPrice, brandID, categoryID, imgSrc, stock);
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
	
	private void updateBrandFilter(Map<String, BrandDTO> brandFilter, String brandID) {
		if(brandFilter.containsKey(brandID))
			brandFilter.get(brandID).setStock(brandFilter.get(brandID).getStock() + 1);
	}
	
	private void updateCategoryFilter(Map<String, CategoryDTO> categoryFilter, String categoryID) {
		if(categoryFilter.containsKey(categoryID))
			categoryFilter.get(categoryID).setStock(categoryFilter.get(categoryID).getStock() + 1);
	}
	
	private void updateAvailabilityFilter(Map<String, Integer> availabilityFilter, String stockStatus) {
		if(availabilityFilter.containsKey(stockStatus))
			availabilityFilter.put(stockStatus, availabilityFilter.get(stockStatus) + 1);
	}	

}
