package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import model.AvailabilityDTO;
import model.BrandDTO;
import model.CategoryDTO;
import model.Product;
import model.SorterDTO;

public class ProductDAO extends DAO<Product> {
	
	private static final String SELECT_ALL_PRODUCT_SQL = "SELECT * FROM product;";
	private static final String SELECT_PRODUCT_BY_ID_SQL = "SELECT * FROM product where id=?";
	private static final String SEARCH_PRODUCT_BY_NAME_SQL = "SELECT * FROM product where name like ?";
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
	
	public Map<String, AvailabilityDTO> getAllAvailabilityFilter() {	
		Map<String, AvailabilityDTO> availabilities = new HashMap<String, AvailabilityDTO>();		
		for(Entry entry: Product.AVAILABILITY_MAP.entrySet()) {
			availabilities.put((String)entry.getKey(), new AvailabilityDTO((String)entry.getKey(), (String)entry.getValue()));
		}
		return availabilities;
	}
	
	public Map<String, SorterDTO> getAllSorter() {	
		Map<String, SorterDTO> sorters = new LinkedHashMap<String, SorterDTO>();		
		for(Entry entry: SorterDTO.SORTBY_MAP.entrySet()) {
			sorters.put((String)entry.getKey(), new SorterDTO((String)entry.getKey(), (String)entry.getValue()));
		}
		return sorters;
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
	
	public String getWhereClause(String[] statements) {
		String whereClause = "";
		boolean inWhereClause = false;
		for(String statement: statements) {				
			if(!statement.equalsIgnoreCase("")) {
				if (!inWhereClause){
					inWhereClause = true;
					whereClause += WHERE_QUERY + statement;							
				}else {
					whereClause += AND_QUERY + statement;		
				}
			}			
		}
		return whereClause;
	}	
	
	public String getNameCondition(String[] keywords) {
		String nameStm = "(";
		if(keywords.length == 1)
			nameStm += SEARCH_PRODUCT_BY_NAME_SQL;
		else if (keywords.length > 1) {
			for(int i = 0; i < keywords.length; i++) {
				nameStm += SEARCH_PRODUCT_BY_NAME_SQL;
				if(i != keywords.length - 1)
					nameStm += " union ";	
			}
		}
		nameStm += ") as resultByKeyWords";
		return nameStm;
	}	
	
	public String getBrandCondition(String[] brands) {
		String brandStm = "";
		if(brands.length == 1)
			brandStm = "brand_id = ?";
		else if (brands.length > 1) {
			brandStm = "brand_id in (";
			for(int i = 0; i < brands.length; i++) {
				brandStm += "?";
				if(i != brands.length - 1)
					brandStm += ",";	
			}
			brandStm += ")";
		}
		return brandStm;
	}
	
	public String getCategoryCondition(String[] categories) {
		String categoryStm = "";
		if(categories.length == 1)
			categoryStm = "category_id = ?";
		else if (categories.length > 1) {
			categoryStm = "category_id in (";
			for(int i = 0; i < categories.length; i++) {
				categoryStm += "?";
				if(i != categories.length - 1)
					categoryStm += ",";	
			}
			categoryStm += ")";
		}
		return categoryStm;
	}
	
	public String getAvailabilityCondition(String[] availabilities) {
		String availabilityStm = "";
		if(availabilities.length == 1 && availabilities[0].equalsIgnoreCase("1"))
			availabilityStm = "stock > 0";
		else if(availabilities.length == 1 && availabilities[0].equalsIgnoreCase("0"))
			availabilityStm = "stock = 0";
			
		return availabilityStm;
	}
	
	public String getSortCondition(String sorter) {
		String sorterStm = "";
		int sortBy = Integer.parseInt(sorter);
		if(sortBy > 0)
			sorterStm = " order by ? ";
		else if(sortBy < 0)
			sorterStm = " order by ? desc";
			
		return sorterStm;
	}	
	
	public Object[] searchProductByName(String[] keywords) {
		List<Product> products = new ArrayList<Product>();	
		Map<String,BrandDTO> allBrandFilters = getAllBrandFilter(); 
		Map<String,BrandDTO> brandFilters = new HashMap<String,BrandDTO>();
		Map<String,CategoryDTO> allCategoryFilters = getAllCategoryFilter();
		Map<String,CategoryDTO> categoryFilters = new HashMap<String,CategoryDTO>();
		Map<String,AvailabilityDTO> allAvailabilityFilters = getAllAvailabilityFilter();		
		Map<String,AvailabilityDTO> availabilityFilters = new HashMap<String,AvailabilityDTO>();
		Map<String,SorterDTO> allSorters = getAllSorter();
		String[] newKeywords = getAllPossibleMatchedKeywords(keywords);
		int currentParam = 0;	
		String searchProductSQL = DAO.SELECT_FROM_SUB_QUERY + getNameCondition(newKeywords);
		
		try(PreparedStatement selectStm = connection.prepareStatement(searchProductSQL);){
			for(String keyword: newKeywords) {
				selectStm.setString(++currentParam, "%" + keyword + "%");
			}					
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
				updateBrandFilter(brandFilters, allBrandFilters, brandID);
				updateCategoryFilter(categoryFilters, allCategoryFilters, categoryID);
				updateAvailabilityFilter(availabilityFilters,allAvailabilityFilters ,product.getStockStatus());
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return new Object[]{products, brandFilters, categoryFilters, availabilityFilters, allSorters};
	}	
	
	public Object[] searchProductByNameWithFilters(String[] keywords, String[] selectedBrands, String[] selectedCategories, String priceMin, String priceMax, String[] selectedAvailabilities, String selectedSorter, String perPage, String page) {
		Object[] originalSearchResultAndFilter = searchProductByName(keywords);
		Map<String,BrandDTO> brandFilters = (Map<String, BrandDTO>) originalSearchResultAndFilter[1];
		Map<String,CategoryDTO> categoryFilters = (Map<String, CategoryDTO>) originalSearchResultAndFilter[2];
		Map<String,AvailabilityDTO> availabilityFilters = (Map<String, AvailabilityDTO>) originalSearchResultAndFilter[3];		
		Map<String,SorterDTO> allSorters = (Map<String, SorterDTO>) originalSearchResultAndFilter[4];
		List<Product> products = new ArrayList<Product>();	 
		String[] newKeywords = getAllPossibleMatchedKeywords(keywords);
		int currentParam = 0;
		
		
		checkSelectedBrandFilter(brandFilters, selectedBrands);
		checkSelectedCategoryFilter(categoryFilters, selectedCategories);
		checkSelectedAvailabilityFilter(availabilityFilters, selectedAvailabilities);
		checkSelectedSorter(allSorters, selectedSorter);
		
		String priceMinCondition = "";
		String priceMaxCondition = "";
		if(!priceMin.equalsIgnoreCase(""))
			priceMinCondition = "new_price >= ?";
		if(!priceMax.equalsIgnoreCase(""))
			priceMaxCondition = "new_price <= ?";		
		
		String searchProductSQL = DAO.SELECT_FROM_SUB_QUERY + getNameCondition(newKeywords) + getWhereClause(new String[] {getBrandCondition(selectedBrands),getCategoryCondition(selectedCategories),getAvailabilityCondition(selectedAvailabilities),priceMinCondition,priceMaxCondition});

		searchProductSQL+= getSortCondition(selectedSorter);	
		if(!perPage.equalsIgnoreCase(""))
			searchProductSQL += " limit ? ";
		if(!page.equalsIgnoreCase(""))
			searchProductSQL += " offset ? ";		
		
		try(PreparedStatement selectStm = connection.prepareStatement(searchProductSQL);){
			for(String keyword: newKeywords) {
				selectStm.setString(++currentParam, "%" + keyword + "%");
			}
			for(String brand: selectedBrands) {
				selectStm.setString(++currentParam, brand);
			}
			for(String category: selectedCategories) {
				selectStm.setString(++currentParam, category);
			}
			if(!priceMin.equalsIgnoreCase(""))
				selectStm.setInt(++currentParam, Integer.parseInt(priceMin));
			if(!priceMax.equalsIgnoreCase(""))
				selectStm.setInt(++currentParam, Integer.parseInt(priceMax));
			if(!selectedSorter.equalsIgnoreCase("") && Integer.parseInt(selectedSorter) != 0) {
				int sortBy = (Integer.parseInt(selectedSorter) > 0) ? Integer.parseInt(selectedSorter) : -1*Integer.parseInt(selectedSorter); 
				selectStm.setInt(++currentParam, sortBy);
			}
			if(!perPage.equalsIgnoreCase(""))
				selectStm.setInt(++currentParam, Integer.parseInt(perPage));
			if(!page.equalsIgnoreCase(""))
				selectStm.setInt(++currentParam, Integer.parseInt(page));				
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
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return new Object[]{products, brandFilters, categoryFilters, availabilityFilters, allSorters};		
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
	
	private void updateBrandFilter(Map<String, BrandDTO> brandFilter, Map<String, BrandDTO> allBrandFilter, String brandID) {
		if(!brandFilter.containsKey(brandID))
			brandFilter.put(brandID, allBrandFilter.get(brandID));	
		brandFilter.get(brandID).setStock(brandFilter.get(brandID).getStock() + 1);		
	}
	
	private void updateCategoryFilter(Map<String, CategoryDTO> categoryFilter, Map<String, CategoryDTO> allCategoryFilter, String categoryID) {
		if(!categoryFilter.containsKey(categoryID))
			categoryFilter.put(categoryID, allCategoryFilter.get(categoryID));
		categoryFilter.get(categoryID).setStock(categoryFilter.get(categoryID).getStock() + 1);			
	}
	
	private void updateAvailabilityFilter(Map<String, AvailabilityDTO> availabilityFilter,Map<String, AvailabilityDTO> allAvailabilityFilter, String stockStatus) {		
		if(!availabilityFilter.containsKey(stockStatus))
			availabilityFilter.put(stockStatus, allAvailabilityFilter.get(stockStatus));
		availabilityFilter.get(stockStatus).setStock(availabilityFilter.get(stockStatus).getStock() + 1);				
	}
	
	private void checkSelectedBrandFilter(Map<String,BrandDTO> brandFilters, String[] selectedBrandFilters) {
		for(String selectedBrandFilter: selectedBrandFilters) {
			if(brandFilters.containsKey(selectedBrandFilter))
				brandFilters.get(selectedBrandFilter).setSelected("selected");
		}
	}
	
	private void checkSelectedCategoryFilter(Map<String,CategoryDTO> categoryFilters, String[] selectedCategoryFilters) {
		for(String selectedCategoryFilter: selectedCategoryFilters) {
			if(categoryFilters.containsKey(selectedCategoryFilter))
				categoryFilters.get(selectedCategoryFilter).setSelected("selected");
		}
	}	
	
	private void checkSelectedAvailabilityFilter(Map<String,AvailabilityDTO> availabilityFilters, String[] selectedAvailabilityFilters) {
		for(String selectedAvailabilityFilter: selectedAvailabilityFilters) {
			if(availabilityFilters.containsKey(selectedAvailabilityFilter))
				availabilityFilters.get(selectedAvailabilityFilter).setSelected("selected");
		}
	}	
	
	private void checkSelectedSorter(Map<String,SorterDTO> sorters, String selectedSorter) {
		sorters.get(selectedSorter).setSelected("selected");		
	}	
	
	private String[] getAllPossibleMatchedKeywords(String[] keywords) {
		List<String> newKeywords = new ArrayList<String>();
		String combinedKeyword = "";
		int currentCombinedWordLength = keywords.length;
		int startIndex = 0;
		int currentIndex = startIndex;
		while(currentCombinedWordLength > 1) {
			while(startIndex + currentCombinedWordLength <= keywords.length) {
				while(currentIndex < startIndex + currentCombinedWordLength){
					combinedKeyword += keywords[currentIndex] + " ";
					++currentIndex;
				}
				newKeywords.add(combinedKeyword.substring(0, combinedKeyword.length()-1));
				combinedKeyword = "";
				++startIndex;
				currentIndex = startIndex;
			}
			combinedKeyword = "";
			startIndex = 0;
			currentIndex = startIndex;
			--currentCombinedWordLength;
		}
		
		for(String keyword: keywords) {
			newKeywords.add(keyword);
		}
		
		for(String keyword: newKeywords) {
			System.out.println(keyword);
		}
		
		return (String[])newKeywords.toArray(new String[0]);
	}

}
