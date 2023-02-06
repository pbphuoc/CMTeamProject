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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import constant.GlobalConstant;
import constant.SortByEnum;
import constant.StockStatusEnum;
import entity.Brand;
import entity.Category;
import entity.Product;
import model.SearchFilter;
import util.Utility;
import model.OrderItemDTO;

public class ProductDAO {

	private static final String SELECT_LATEST16_PRODUCT_SQL = "SELECT * FROM product order by 1 desc limit 16;";
	private static final String SELECT_PRODUCT_BY_ID_SQL = "SELECT * FROM product where id=? ;";
	private static final String SELECT_PRICE_BY_ID_SQL = "SELECT new_price FROM product where id =? ;";
	private static final String SEARCH_PRODUCT_BY_NAME_SQL = "SELECT * FROM product where name like ? ";
	private static final String SELECT_ALL_BRAND_SQL = "SELECT * FROM brand;";
	private static final String SELECT_ALL_CATEGORY_SQL = "SELECT * FROM category;";
	private static final String SELECT_MEDIA_BY_PRODUCTID_SQL = "SELECT * FROM media where product_id = ?; ";
	private static final String UPDATE_STOCK_BY_PRODUCTID_SQL = "UPDATE product SET stock = stock - ? WHERE id = ?;";

	private static ProductDAO productDAO;
	private static final Logger logger = LogManager.getLogger(ProductDAO.class);

	private static final int MAX_LIMIT_SQL = 999999;

	private ProductDAO() {
	}

	public static ProductDAO getProductDAO() {
		if (productDAO == null)
			productDAO = new ProductDAO();
		return productDAO;
	}

	public List<Product> getPopularProducts() {
		List<Product> products = new ArrayList<Product>();
		Connection connection = Utility.getConnection();
		PreparedStatement selectStm = null;
		ResultSet result = null;
		try {
			selectStm = connection.prepareStatement(SELECT_LATEST16_PRODUCT_SQL);
			result = selectStm.executeQuery();
			while (result.next()) {
				String id = result.getInt("id") + "";
				String name = result.getString("name");
				String description = result.getString("description");
				double oldPrice = result.getDouble("old_price");
				double newPrice = result.getDouble("new_price");
				String brandID = result.getString("brand_id");
				String categoryID = result.getString("category_id");
				String imgSrc = result.getString("img_src");
				int stock = result.getInt("stock");
				products.add(
						new Product(id, name, description, oldPrice, newPrice, brandID, categoryID, imgSrc, stock));
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
		} finally {
			Utility.close(connection, selectStm, result);
		}
		return products;
	}

	public Map<String, String> getPagingMap(int totalPage) {
		Map<String, String> pagingMap = new LinkedHashMap<String, String>();
		int currentPage = 1;
		while (currentPage <= totalPage) {
			pagingMap.put("" + currentPage, "");
			++currentPage;
		}
		return pagingMap;
	}

	public Map<String, SearchFilter> loadStockStatusFilter() {
		return new LinkedHashMap<String, SearchFilter>() {
			{
				put(StockStatusEnum.INSTOCK.toString(),
						new SearchFilter(StockStatusEnum.INSTOCK.toString(), StockStatusEnum.INSTOCK.getValue()));
				put(StockStatusEnum.OUTOFSTOCK.toString(),
						new SearchFilter(StockStatusEnum.OUTOFSTOCK.toString(), StockStatusEnum.OUTOFSTOCK.getValue()));
			}
		};
	}

//	public Map<String, SearchFilter> loadResultPerPage() {
//		return new LinkedHashMap<String, SearchFilter>() {
//			{		
//				put(ResultPerPageEnum.SHOW16.toString(), new SearchFilter(ResultPerPageEnum.SHOW16.toString(), ResultPerPageEnum.SHOW16.getValue()));
//				put(ResultPerPageEnum.SHOW32.toString(), new SearchFilter(ResultPerPageEnum.SHOW32.toString(), ResultPerPageEnum.SHOW32.getValue()));
//				put(ResultPerPageEnum.SHOW64.toString(), new SearchFilter(ResultPerPageEnum.SHOW64.toString(), ResultPerPageEnum.SHOW64.getValue()));
//				put(ResultPerPageEnum.SHOW128.toString(), new SearchFilter(ResultPerPageEnum.SHOW128.toString(), ResultPerPageEnum.SHOW128.getValue()));
//			}
//		};
//	}

	public Map<String, String> loadResultPerPage() {
		return new LinkedHashMap<String, String>() {
			{
				for (Integer resultPerPage : GlobalConstant.RESULT_PER_PAGE_LIST) {
					put(resultPerPage + "", "");
				}
			}
		};
	}

//	public Map<String, SearchFilter> loadSortBy() {
//		return new LinkedHashMap<String, SearchFilter>() {
//			{			
//				put(SortByEnum.RELEVANCY.toString(), new SearchFilter(SortByEnum.RELEVANCY.toString(), SortByEnum.RELEVANCY.getValue()));
//				put(SortByEnum.PRICELOWTOHIGH.toString(), new SearchFilter(SortByEnum.PRICELOWTOHIGH.toString(), SortByEnum.PRICELOWTOHIGH.getValue()));
//				put(SortByEnum.PRICEHIGHTOLOW.toString(), new SearchFilter(SortByEnum.PRICEHIGHTOLOW.toString(), SortByEnum.PRICEHIGHTOLOW.getValue()));
//				put(SortByEnum.NAMEATOZ.toString(), new SearchFilter(SortByEnum.NAMEATOZ.toString(), SortByEnum.NAMEATOZ.getValue()));
//				put(SortByEnum.NAMEZTOA.toString(), new SearchFilter(SortByEnum.NAMEZTOA.toString(), SortByEnum.NAMEZTOA.getValue()));
//				put(SortByEnum.OLDTONEW.toString(), new SearchFilter(SortByEnum.OLDTONEW.toString(), SortByEnum.OLDTONEW.getValue()));
//				put(SortByEnum.NEWTOOLD.toString(), new SearchFilter(SortByEnum.NEWTOOLD.toString(), SortByEnum.NEWTOOLD.getValue()));
//			}
//		};
//	}		

	public Map<String, String> loadSortBy() {
		return new LinkedHashMap<String, String>() {
			{
				put(SortByEnum.RELEVANCY.toString(), "");
				put(SortByEnum.PRICELOWTOHIGH.toString(), "");
				put(SortByEnum.PRICEHIGHTOLOW.toString(), "");
				put(SortByEnum.NAMEATOZ.toString(), "");
				put(SortByEnum.NAMEZTOA.toString(), "");
				put(SortByEnum.OLDTONEW.toString(), "");
				put(SortByEnum.NEWTOOLD.toString(), "");
			}
		};
	}

	public Map<String, SearchFilter> loadSearchFilterFromDB(String query) {
		Map<String, SearchFilter> filters = new LinkedHashMap<String, SearchFilter>();
		Connection connection = Utility.getConnection();
		PreparedStatement selectStm = null;
		ResultSet result = null;

		try {
			selectStm = connection.prepareStatement(query);
			result = selectStm.executeQuery();

			while (result.next()) {
				String id = result.getInt("id") + "";
				String name = result.getString("name");
				SearchFilter filter = new SearchFilter(id, name);
				filters.put(id, filter);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
		} finally {
			Utility.close(connection, selectStm, result);
		}

		return filters;
	}

	public String getWhereClause(String[] statements) {
		String whereClause = "";
		boolean inWhereClause = false;
		for (String statement : statements) {
			if (!statement.isEmpty()) {
				if (!inWhereClause) {
					inWhereClause = true;
					whereClause += GlobalConstant.WHERE_QUERY + statement;
				} else {
					whereClause += GlobalConstant.AND_QUERY + statement;
				}
			}
		}
		return whereClause;
	}

	public String getNameCondition(String[] keywords) {
		String nameStm = "(";
		if (keywords.length == 1)
			nameStm += SEARCH_PRODUCT_BY_NAME_SQL;
		else if (keywords.length > 1) {
			for (int i = 0; i < keywords.length; i++) {
				nameStm += SEARCH_PRODUCT_BY_NAME_SQL;
				if (i != keywords.length - 1)
					nameStm += " union ";
			}
		}
		nameStm += ") as resultByKeyWords";
		return nameStm;
	}

//	public String getBrandCondition(String[] brands) {
//		String brandStm = "";
//		if (brands.length == 1)
//			brandStm = "brand_id = ?";
//		else if (brands.length > 1) {
//			brandStm = "brand_id in (";
//			for (int i = 0; i < brands.length; i++) {
//				brandStm += "?";
//				if (i != brands.length - 1)
//					brandStm += ",";
//			}
//			brandStm += ")";
//		}
//		return brandStm;
//	}
//
//	public String getCategoryCondition(String[] categories) {
//		String categoryStm = "";
//		if (categories.length == 1)
//			categoryStm = "category_id = ?";
//		else if (categories.length > 1) {
//			categoryStm = "category_id in (";
//			for (int i = 0; i < categories.length; i++) {
//				categoryStm += "?";
//				if (i != categories.length - 1)
//					categoryStm += ",";
//			}
//			categoryStm += ")";
//		}
//		return categoryStm;
//	}

	public String getColumnCondition(String[] conditions, String columnName) {
		String conditionStm = "";
		if (conditions.length == 1)
			conditionStm = columnName + " = ?";
		else if (conditions.length > 1) {
			conditionStm = columnName + " in (";
			for (int i = 0; i < conditions.length; i++) {
				conditionStm += "?";
				if (i != conditions.length - 1)
					conditionStm += ",";
			}
			conditionStm += ")";
		}
		return conditionStm;
	}

	public String getAvailabilityCondition(String[] availabilities) {
		String availabilityStm = "";
		if (availabilities.length == 1 && availabilities[0].equalsIgnoreCase(StockStatusEnum.INSTOCK.toString()))
			availabilityStm = "stock > 0";
		else if (availabilities.length == 1
				&& availabilities[0].equalsIgnoreCase(StockStatusEnum.OUTOFSTOCK.toString()))
			availabilityStm = "stock = 0";

		return availabilityStm;
	}

	public String getSortCondition(String sorter) {
		SortByEnum sortBy = SortByEnum.valueOf(sorter);
		switch (sortBy) {
		case RELEVANCY:
			return "";
		case PRICELOWTOHIGH:
			return " order by ?";
		case NAMEATOZ:
			return " order by ?";
		case OLDTONEW:
			return " order by ?";
		case PRICEHIGHTOLOW:
			return " order by ? desc";
		case NAMEZTOA:
			return " order by ? desc";
		case NEWTOOLD:
			return " order by ? desc";
		default:
			return "";
		}
	}

	public Object[] searchProductByName(String[] keywords) {
		Map<String, SearchFilter> allBrandFilters = loadSearchFilterFromDB(SELECT_ALL_BRAND_SQL);
		Map<String, SearchFilter> brandFilters = new HashMap<String, SearchFilter>();
		Map<String, SearchFilter> allCategoryFilters = loadSearchFilterFromDB(SELECT_ALL_CATEGORY_SQL);
		Map<String, SearchFilter> categoryFilters = new HashMap<String, SearchFilter>();
		Map<String, SearchFilter> allStockStatusFilters = loadStockStatusFilter();
		Map<String, SearchFilter> stockStatusFilters = new HashMap<String, SearchFilter>();
		String[] newKeywords = getAllPossibleMatchedKeywords(keywords);

		int currentParam = 0;
		String searchProductSQL = GlobalConstant.SELECT_FROM_SUB_QUERY + getNameCondition(newKeywords);
		Connection connection = Utility.getConnection();
		PreparedStatement selectStm = null;
		ResultSet result = null;

		try {
			selectStm = connection.prepareStatement(searchProductSQL);
			for (String keyword : newKeywords) {
				selectStm.setString(++currentParam, "%" + keyword + "%");
			}
			logger.info("Query: " + selectStm.toString());
			result = selectStm.executeQuery();
			while (result.next()) {
				String id = result.getInt("id") + "";
				String name = result.getString("name");
				String description = result.getString("description");
				double oldPrice = result.getDouble("old_price");
				double newPrice = result.getDouble("new_price");
				String brandID = result.getString("brand_id");
				String categoryID = result.getString("category_id");
				String imgSrc = result.getString("img_src");
				int stock = result.getInt("stock");
				Product product = new Product(id, name, description, oldPrice, newPrice, brandID, categoryID, imgSrc,
						stock);
				updateCountInEachFilter(brandFilters, allBrandFilters, brandID);
				updateCountInEachFilter(categoryFilters, allCategoryFilters, categoryID);
				updateCountInEachFilter(stockStatusFilters, allStockStatusFilters, product.getStockStatus());
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
		} finally {
			Utility.close(connection, selectStm, result);
		}
		return new Object[] { brandFilters, categoryFilters, stockStatusFilters };
	}

	public Object[] searchProductByNameWithFilters(String[] keywords, String[] selectedBrands,
			String[] selectedCategories, String priceMin, String priceMax, String[] selectedAvailabilities,
			String selectedSorter, String perPage, String page) {
		Object[] originalSearchResultAndFilter = searchProductByName(keywords);
		Map<String, SearchFilter> allBrandFilters = (Map<String, SearchFilter>) originalSearchResultAndFilter[0];
		Map<String, SearchFilter> allCategoryFilters = (Map<String, SearchFilter>) originalSearchResultAndFilter[1];
		Map<String, SearchFilter> allStockStatusFilters = (Map<String, SearchFilter>) originalSearchResultAndFilter[2];

		Map<String, String> allSortBy = loadSortBy();
		Map<String, String> allResultPerPages = loadResultPerPage();
		Map<String, String> pagingMap = null;

		List<Product> filteredProducts = new ArrayList<Product>();
		String[] newKeywords = getAllPossibleMatchedKeywords(keywords);
		int currentParam = 0;

		setSelectedSearchFilter(allBrandFilters, selectedBrands);
		setSelectedSearchFilter(allCategoryFilters, selectedCategories);
		setSelectedSearchFilter(allStockStatusFilters, selectedAvailabilities);

		setSelectedSetting(allSortBy, selectedSorter);
		setSelectedSetting(allResultPerPages, perPage);

		String priceMinCondition = priceMin.isEmpty() ? "" : "new_price >= ?";
		String priceMaxCondition = priceMax.isEmpty() ? "" : "new_price <= ?";

		String searchProductSQL = GlobalConstant.SELECT_FROM_SUB_QUERY + getNameCondition(newKeywords)
				+ getWhereClause(new String[] { getColumnCondition(selectedBrands, "brand_id"),
						getColumnCondition(selectedCategories, "category_id"),
						getAvailabilityCondition(selectedAvailabilities), priceMinCondition, priceMaxCondition });

		searchProductSQL += selectedSorter.isEmpty() ? "" : getSortCondition(selectedSorter);
		searchProductSQL += perPage.isEmpty() ? "" : " limit ? ";
		searchProductSQL += page.isEmpty() ? "" : " offset ? ";

		Connection connection = Utility.getConnection();
		PreparedStatement selectStm = null;
		ResultSet result = null;
		int rowCountBeforeLimit = 0;

		try {
			selectStm = connection.prepareStatement(searchProductSQL);
			for (String keyword : newKeywords) {
				selectStm.setString(++currentParam, "%" + keyword + "%");
			}
			for (String brand : selectedBrands) {
				selectStm.setString(++currentParam, brand);
			}
			for (String category : selectedCategories) {
				selectStm.setString(++currentParam, category);
			}
			if (!priceMin.isEmpty())
				selectStm.setInt(++currentParam, Integer.parseInt(priceMin));
			if (!priceMax.isEmpty())
				selectStm.setInt(++currentParam, Integer.parseInt(priceMax));
			if (!selectedSorter.isEmpty() && !selectedSorter.equalsIgnoreCase(SortByEnum.RELEVANCY.toString())) {
				selectStm.setInt(++currentParam, SortByEnum.valueOf(selectedSorter).getSortByValue());
			}
			selectStm.setInt(++currentParam, MAX_LIMIT_SQL);
			selectStm.setInt(++currentParam, 0);

			logger.debug("Query before limit and offset: " + selectStm.toString());
			result = selectStm.executeQuery();
			while (result.next()) {
				++rowCountBeforeLimit;
			}

			pagingMap = getPagingMap((int) Math.ceil((double) rowCountBeforeLimit / Double.parseDouble(perPage)));

			setSelectedSetting(pagingMap, page);
			currentParam = currentParam - 2;

			if (!perPage.equalsIgnoreCase(""))
				selectStm.setInt(++currentParam, Integer.parseInt(perPage));
			if (!page.equalsIgnoreCase(""))
				selectStm.setInt(++currentParam, (Integer.parseInt(page) - 1) * Integer.parseInt(perPage));

			logger.debug("Query after limit and offset: " + selectStm.toString());
			result.close();
			result = selectStm.executeQuery();
			while (result.next()) {
				String id = result.getInt("id") + "";
				String name = result.getString("name");
				String description = result.getString("description");
				double oldPrice = result.getDouble("old_price");
				double newPrice = result.getDouble("new_price");
				String brandID = result.getString("brand_id");
				String categoryID = result.getString("category_id");
				String imgSrc = result.getString("img_src");
				int stock = result.getInt("stock");
				Product product = new Product(id, name, description, oldPrice, newPrice, brandID, categoryID, imgSrc,
						stock);
				filteredProducts.add(product);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
		} finally {
			Utility.close(connection, selectStm, result);
		}
		return new Object[] { filteredProducts, allBrandFilters, allCategoryFilters, allStockStatusFilters, allSortBy,
				allResultPerPages, pagingMap, rowCountBeforeLimit };
	}

	public Product getProductByID(String id) {
		Connection connection = Utility.getConnection();
		Product product = null;
		PreparedStatement selectStm = null;
		try {
			selectStm = connection.prepareStatement(SELECT_PRODUCT_BY_ID_SQL);
			selectStm.setString(1, id);
			ResultSet result = selectStm.executeQuery();
			if (!result.next())
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
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
		} finally {
			Utility.close(connection, selectStm, null);
		}
		return product;
	}

	public List<String> getAllMediaByProductID(String id) {
		List<String> medias = new ArrayList<String>();
		Connection connection = Utility.getConnection();
		PreparedStatement selectStm = null;
		ResultSet result = null;
		try {
			selectStm = connection.prepareStatement(SELECT_MEDIA_BY_PRODUCTID_SQL);
			selectStm.setString(1, id);
			result = selectStm.executeQuery();
			while (result.next()) {
				medias.add(result.getString("src"));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
		} finally {
			Utility.close(connection, selectStm, result);
		}
		return medias;
	}

	public double getPriceByProductID(String id) {
		double price = 0;
		Connection connection = Utility.getConnection();
		PreparedStatement selectStm = null;
		ResultSet result = null;
		try {
			selectStm = connection.prepareStatement(SELECT_PRICE_BY_ID_SQL);
			selectStm.setString(1, id);
			result = selectStm.executeQuery();
			if (!result.next())
				return price;
			price = result.getDouble("new_price");
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
		} finally {
			Utility.close(connection, selectStm, result);
		}
		return price;
	}

	public List<Brand> getAllBrands() {
		List<Brand> brands = new ArrayList<Brand>();
		Connection connection = Utility.getConnection();
		PreparedStatement selectStm = null;
		ResultSet rs = null;
		try {
			selectStm = connection.prepareStatement(SELECT_ALL_BRAND_SQL);
			rs = selectStm.executeQuery();
			while (rs.next()) {
				String id = rs.getInt("id") + "";
				String name = rs.getString("name");
				String imgSrc = rs.getString("img_src");
				brands.add(new Brand(id, name, imgSrc));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
		} finally {
			Utility.close(connection, selectStm, rs);
		}
		return brands;
	}

	public List<Category> getAllCategory() {
		List<Category> categories = new ArrayList<Category>();
		Connection connection = Utility.getConnection();
		PreparedStatement selectStm = null;
		ResultSet rs = null;
		try {
			selectStm = connection.prepareStatement(SELECT_ALL_CATEGORY_SQL);
			rs = selectStm.executeQuery();
			while (rs.next()) {
				String id = rs.getInt("id") + "";
				String name = rs.getString("name");
				String imgSrc = rs.getString("img_src");
				categories.add(new Category(id, name, imgSrc));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
		} finally {
			Utility.close(connection, selectStm, rs);
			;
		}
		return categories;
	}

	private void updateCountInEachFilter(Map<String, SearchFilter> filtersToBeUpdated,
			Map<String, SearchFilter> originalFilters, String filterID) {
		if (!filtersToBeUpdated.containsKey(filterID))
			filtersToBeUpdated.put(filterID, originalFilters.get(filterID));
		filtersToBeUpdated.get(filterID).setStock(filtersToBeUpdated.get(filterID).getStock() + 1);

	}

	private void setSelectedSearchFilter(Map<String, SearchFilter> searchFilters, String[] selectedFilters) {
		for (String selectedFilter : selectedFilters) {
			if (searchFilters.containsKey(selectedFilter))
				searchFilters.get(selectedFilter).setSelected(true);
		}
	}

//	private void setSelectedPage(Map<String, String> paginationMap, String selectedPage) {
//	for (Entry entry : paginationMap.entrySet()) {
//		if (((String) entry.getKey()).equalsIgnoreCase(selectedPage))
//			paginationMap.put((String) entry.getKey(), "selected");
//		else
//			paginationMap.put((String) entry.getKey(), "");
//	}
//}	

	private void setSelectedSetting(Map<String, String> settingMaps, String selectedSetting) {
		for (Entry entry : settingMaps.entrySet()) {
			if (((String) entry.getKey()).equalsIgnoreCase(selectedSetting))
				settingMaps.put((String) entry.getKey(), "selected");
			else
				settingMaps.put((String) entry.getKey(), "");
		}
	}

	private String[] getAllPossibleMatchedKeywords(String[] keywords) {
		List<String> newKeywords = new ArrayList<String>();
		String combinedKeyword = "";
		int currentCombinedWordLength = keywords.length;
		int startIndex = 0;
		int currentIndex = startIndex;
		while (currentCombinedWordLength > 1) {
			while (startIndex + currentCombinedWordLength <= keywords.length) {
				while (currentIndex < startIndex + currentCombinedWordLength) {
					combinedKeyword += keywords[currentIndex] + " ";
					++currentIndex;
				}
				newKeywords.add(combinedKeyword.substring(0, combinedKeyword.length() - 1));
				combinedKeyword = "";
				++startIndex;
				currentIndex = startIndex;
			}
			combinedKeyword = "";
			startIndex = 0;
			currentIndex = startIndex;
			--currentCombinedWordLength;
		}

		for (String keyword : keywords) {
			newKeywords.add(keyword);
		}

		for (String keyword : newKeywords) {
			System.out.println(keyword);
		}

		return (String[]) newKeywords.toArray(new String[0]);
	}

	public List<OrderItemDTO> getAllProductInCartByID(HashMap<String, Integer> cartItems) {
		List<OrderItemDTO> cartList = new ArrayList<OrderItemDTO>();

		for (Map.Entry<String, Integer> cI : cartItems.entrySet()) {
			Product product = getProductByID((String) cI.getKey());
			int quantity = Math.min(product.getStock(), (int) cI.getValue());
			logger.info("Stock: " + product.getStock() + " - Quantity: " + (int) cI.getValue() + " - Order Quantity: "
					+ quantity);
			cartList.add(new OrderItemDTO(product, quantity));
		}
		return cartList;
	}
}
