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

import entity.Product;
import model.SearchFilterDTO;
import model.CartItemDTO;

public class ProductDAO extends DAO<Product> {
	
	private static final String SELECT_ALL_PRODUCT_SQL = "SELECT * FROM product;";
	private static final String SELECT_PRODUCT_BY_ID_SQL = "SELECT * FROM product where id=? ;";
	private static final String SELECT_PRICE_BY_ID_SQL = "SELECT new_price FROM product where id =? ;";
	private static final String SEARCH_PRODUCT_BY_NAME_SQL = "SELECT * FROM product where name like ? ";
	public static final String SELECT_ALL_BRAND_SQL = "SELECT * FROM brand;";
	public static final String SELECT_ALL_CATEGORY_SQL = "SELECT * FROM category;";
	private static final String SELECT_MEDIA_BY_PRODUCT_ID_SQL = "SELECT * FROM media where product_id = ?; ";	
	private static final String SELECT_ALL_SETTING_AVAILABILITY = "SELECT * FROM setting_availability;";
	private static final String SELECT_ALL_SETTING_RESULTPERPAGE = "SELECT * FROM setting_resultperpage;";
	private static final String SELECT_ALL_SETTING_SORTBY = "SELECT * FROM setting_sortby order by 2 desc;";
	
	private static final int MAX_LIMIT_SQL = 999999;
	
	private Connection connection = null;
	
//	protected ProductDAO() {
//		super();
//		connection = getConnection();
//	}	

	@Override
	public List<Product> getAllRecords(){	
		List<Product> products = new ArrayList<Product>();	
		Map<String,SearchFilterDTO> allAvailabilityFilters = getAllFilterFromDB(SELECT_ALL_SETTING_AVAILABILITY);
		PreparedStatement selectStm = null;
		try
		{
			connection = getConnection();
			selectStm = connection.prepareStatement(SELECT_ALL_PRODUCT_SQL);
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
				String stockStatus = (stock == 0) ? allAvailabilityFilters.get("0").getName() : allAvailabilityFilters.get("1").getName();				
				products.add(new Product(id,name,description,oldPrice,newPrice,brandID,categoryID,imgSrc,stock, stockStatus));
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
				selectStm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return products;
	}
	
	public Map<String, String> getPagingMap(int totalPage) {	
		Map<String, String> pagingMap = new LinkedHashMap<String, String>();		
		int currentPage = 1;
		while(currentPage <= totalPage) {
			pagingMap.put("" + currentPage, "");
			++currentPage;
		}
		return pagingMap;
	}	
	
	public Map<String, SearchFilterDTO> getAllFilterFromDB(String query) {	
		Map<String, SearchFilterDTO> filters = new LinkedHashMap<String, SearchFilterDTO>();		
		PreparedStatement selectStm = null;
		try
		{
			connection = getConnection();
			selectStm = connection.prepareStatement(query);
			ResultSet result = selectStm.executeQuery();
			while(result.next()) {
				String id = result.getInt("id") + "";
				String name = result.getString("name");
				SearchFilterDTO filter =  new SearchFilterDTO(id, name);
				if(result.getMetaData().getColumnCount() == 3) {
					String imgSrc = result.getString("img_src");
					filter.setImgSrc(imgSrc);
				}								
				filters.put(id, filter);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
				selectStm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return filters;
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
		Map<String,SearchFilterDTO> allBrandFilters = getAllFilterFromDB(SELECT_ALL_BRAND_SQL); 
		Map<String,SearchFilterDTO> brandFilters = new HashMap<String,SearchFilterDTO>();
		Map<String,SearchFilterDTO> allCategoryFilters = getAllFilterFromDB(SELECT_ALL_CATEGORY_SQL);
		Map<String,SearchFilterDTO> categoryFilters = new HashMap<String,SearchFilterDTO>();
		Map<String,SearchFilterDTO> allAvailabilityFilters = getAllFilterFromDB(SELECT_ALL_SETTING_AVAILABILITY);		
		Map<String,SearchFilterDTO> availabilityFilters = new HashMap<String,SearchFilterDTO>();
		String[] newKeywords = getAllPossibleMatchedKeywords(keywords);
		int currentParam = 0;	
		String searchProductSQL = DAO.SELECT_FROM_SUB_QUERY + getNameCondition(newKeywords);
		PreparedStatement selectStm = null;
		try{
			connection = getConnection();
			selectStm = connection.prepareStatement(searchProductSQL);
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
				String stockStatus = (stock == 0) ? allAvailabilityFilters.get("0").getName() : allAvailabilityFilters.get("1").getName();
				Product product = new Product(id,name,description,oldPrice,newPrice,brandID,categoryID,imgSrc,stock, stockStatus);
				products.add(product);
				updateQuantityInEachFilter(brandFilters,allBrandFilters,brandID);
				updateQuantityInEachFilter(categoryFilters,allCategoryFilters,categoryID);
				updateQuantityInEachFilter(availabilityFilters,allAvailabilityFilters,product.getStockStatus());
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
				selectStm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new Object[]{products, brandFilters, categoryFilters, availabilityFilters};
	}	
	
	public Object[] searchProductByNameWithFilters(String[] keywords, String[] selectedBrands, String[] selectedCategories, String priceMin, String priceMax, String[] selectedAvailabilities, String selectedSorter, String perPage, String page) {
		Object[] originalSearchResultAndFilter = searchProductByName(keywords);
		List<Product> allProducts = (List<Product>)originalSearchResultAndFilter[0];
		Map<String,SearchFilterDTO> allBrandFilters = (Map<String, SearchFilterDTO>) originalSearchResultAndFilter[1];
		Map<String,SearchFilterDTO> allCategoryFilters = (Map<String, SearchFilterDTO>) originalSearchResultAndFilter[2];
		Map<String,SearchFilterDTO> allAvailabilityFilters = (Map<String, SearchFilterDTO>) originalSearchResultAndFilter[3];		
		Map<String,SearchFilterDTO> allSorters = getAllFilterFromDB(SELECT_ALL_SETTING_SORTBY);
		Map<String,SearchFilterDTO> allResultPerPages = getAllFilterFromDB(SELECT_ALL_SETTING_RESULTPERPAGE);
		Map<String,String> pagingMap = null; 
		List<Product> filteredProducts = new ArrayList<Product>();	 
		String[] newKeywords = getAllPossibleMatchedKeywords(keywords);
		int currentParam = 0;
				
		setSelectedDTOInMap(allBrandFilters,selectedBrands);
		setSelectedDTOInMap(allCategoryFilters,selectedCategories);
		setSelectedDTOInMap(allAvailabilityFilters,selectedAvailabilities);
		setSelectedDTOInMap(allSorters, new String[]{selectedSorter});
		setSelectedDTOInMap(allResultPerPages, new String[]{perPage});
		
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
		
		PreparedStatement selectStm = null;		
		int rowCountBeforeLimit = 0;
		try{
			connection = getConnection();
			selectStm = connection.prepareStatement(searchProductSQL);
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

			selectStm.setInt(++currentParam, MAX_LIMIT_SQL);
			selectStm.setInt(++currentParam, 0);	
			System.out.println("Query before limit and offset: " + selectStm.toString());			
			ResultSet result = selectStm.executeQuery();
			while(result.next()) {
				++rowCountBeforeLimit;
			}
			pagingMap = getPagingMap((int)Math.ceil((double)rowCountBeforeLimit/Double.parseDouble(perPage)));
			setSelectedStringInMap(pagingMap,page);
			currentParam = currentParam - 2;
			if(!perPage.equalsIgnoreCase(""))
				selectStm.setInt(++currentParam, Integer.parseInt(perPage));
			if(!page.equalsIgnoreCase(""))
				selectStm.setInt(++currentParam, (Integer.parseInt(page)-1)*Integer.parseInt(perPage));				
			System.out.println("Query after limit and offset: " + selectStm.toString());
			result = selectStm.executeQuery();
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
				String stockStatus = (stock == 0) ? allAvailabilityFilters.get("0").getName() : allAvailabilityFilters.get("1").getName();
				Product product = new Product(id,name,description,oldPrice,newPrice,brandID,categoryID,imgSrc,stock, stockStatus);
				filteredProducts.add(product);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
				selectStm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new Object[]{filteredProducts, allBrandFilters, allCategoryFilters, allAvailabilityFilters, allSorters, allResultPerPages, pagingMap, rowCountBeforeLimit};		
	}

	@Override
	public Product getRecordByID(String id) {
		Product product = null;
		PreparedStatement selectStm = null;
		Map<String,SearchFilterDTO> allAvailabilityFilters = getAllFilterFromDB(SELECT_ALL_SETTING_AVAILABILITY);
		try{
			connection = getConnection();
			selectStm = connection.prepareStatement(SELECT_PRODUCT_BY_ID_SQL);
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
			String stockStatus = (stock == 0) ? allAvailabilityFilters.get("0").getName() : allAvailabilityFilters.get("1").getName();
			product = new Product(id, name, description, oldPrice, newPrice, brandID, categoryID, imgSrc, stock, stockStatus);
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
				selectStm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		return product;
	}
	
	public List<String> getAllMediaByProductID(String id){
		List<String> medias = new ArrayList<String>();	
		PreparedStatement selectStm = null;
		try
		{
			connection = getConnection();
			selectStm = connection.prepareStatement(SELECT_MEDIA_BY_PRODUCT_ID_SQL);
			selectStm.setString(1, id);
			ResultSet result = selectStm.executeQuery();
			while(result.next()) {
				medias.add(result.getString("src"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
				selectStm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return medias;
	}
	
	public double getPriceByProductID(String id) {
		double price = 0;		
		PreparedStatement selectStm = null;
		try
		{
			connection = getConnection();
			selectStm = connection.prepareStatement(SELECT_PRICE_BY_ID_SQL);
			selectStm.setString(1, id);
			ResultSet result = selectStm.executeQuery();
			if(!result.next())
				return price;
			price = result.getDouble("new_price");						
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
				selectStm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return price;
	}
	
	private void updateQuantityInEachFilter(Map<String, SearchFilterDTO> theFilter,Map<String, SearchFilterDTO> allFilters, String filterID) {		
		if(!theFilter.containsKey(filterID))
			theFilter.put(filterID, allFilters.get(filterID));
		theFilter.get(filterID).setStock(theFilter.get(filterID).getStock() + 1);				
	}	
	
	private void setSelectedDTOInMap(Map<String,SearchFilterDTO> theMap, String[] selectedDTOs) {
		for(String selectedDTO: selectedDTOs) {
			if(theMap.containsKey(selectedDTO))
				theMap.get(selectedDTO).setSelected("selected");
		}
	}	
	
	private void setSelectedStringInMap(Map<String,String> theMap, String selectedString) {
		for(Entry entry: theMap.entrySet()) {
			if (((String)entry.getKey()).equalsIgnoreCase(selectedString))
				theMap.put((String)entry.getKey(), "selected");
			else
				theMap.put((String)entry.getKey(), "");
		}		
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
	
	public List<CartItemDTO> getAllProductInCartByID(HashMap<String, Integer> cartItems){
		List<CartItemDTO> cartList = new ArrayList<CartItemDTO>();
		
		for (Map.Entry<String, Integer> cI : cartItems.entrySet()) {
			cartList.add(new CartItemDTO(getRecordByID((String) cI.getKey()),(int) cI.getValue()));
		}		
		return cartList;
	}

}
