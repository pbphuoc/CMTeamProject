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

import entity.Product;
import global.GlobalConstant;
import global.SortByEnum;
import global.StockStatusEnum;
import mapper.ProductMapper;
import model.SearchFilter;
import model.OrderItemDTO;

public class ProductDAO extends BaseDAO<ProductMapper, Product, ProductDAO> {

	private static final String SEARCH_ALL_SQL = "SELECT * FROM product";
	private static final String SEARCH_FULLTEXT_SQL = "SELECT * FROM product WHERE MATCH (name, description) against (?)";

	private static ProductDAO productDAO;
	private static final Logger logger = LogManager.getLogger(ProductDAO.class);

	private static final int MAX_LIMIT_SQL = 999999;

	private ProductDAO() {
		super(new ProductMapper());
	}

	public static ProductDAO getProductDAO() {
		return productDAO != null ? productDAO : new ProductDAO();
	}

	public List<Product> getFirst15Products() {
		List<Product> products = getAll();

		if (products == null)
			return new ArrayList<Product>();

		int lastSubListIndex = (products.size() >= 15) ? 15 : products.size();

		return products.subList(0, lastSubListIndex);
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

	public Map<String, String> loadResultPerPage() {
		return new LinkedHashMap<String, String>() {
			{
				for (Integer resultPerPage : GlobalConstant.RESULT_PER_PAGE_LIST) {
					put(resultPerPage + "", "");
				}
			}
		};
	}

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

	public String getHavingClause(String[] statements) {
		String havingClause = "";
		boolean inHavingClause = false;

		for (String statement : statements) {
			if (!statement.isEmpty()) {
				if (!inHavingClause) {
					inHavingClause = true;
					havingClause = GlobalConstant.HAVING_QUERY + statement;
				} else {
					havingClause += GlobalConstant.AND_QUERY + statement;
				}
			}
		}

		return havingClause;
	}

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

	public Object[] searchProductByName(String keyword) {
		Map<String, SearchFilter> allBrandFilters = BrandDAO.getBrandDAO().loadAllBrandToSearchFilter();
		Map<String, SearchFilter> brandFilters = new HashMap<String, SearchFilter>();
		Map<String, SearchFilter> allCategoryFilters = CategoryDAO.getCategoryDAO().loadAllCategoryToSearchFilter();
		Map<String, SearchFilter> categoryFilters = new HashMap<String, SearchFilter>();
		Map<String, SearchFilter> allStockStatusFilters = loadStockStatusFilter();
		Map<String, SearchFilter> stockStatusFilters = new HashMap<String, SearchFilter>();

		Connection connection = getConnection();
		PreparedStatement selectStm = null;
		ResultSet result = null;

		try {
			if (keyword.isEmpty()) {
				selectStm = connection.prepareStatement(SEARCH_ALL_SQL);
			} else {
				selectStm = connection.prepareStatement(SEARCH_FULLTEXT_SQL);
				selectStm.setString(1, keyword);
			}

			logger.debug("Query: " + selectStm.toString());
			result = selectStm.executeQuery();

			while (result.next()) {
				Product product = mapper.map(result);

				updateCountInEachFilter(brandFilters, allBrandFilters, product.getBrandID());
				updateCountInEachFilter(categoryFilters, allCategoryFilters, product.getCategoryID());
				updateCountInEachFilter(stockStatusFilters, allStockStatusFilters, product.getStockStatus());
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
		} finally {
			close(connection, selectStm, result);
		}

		return new Object[] { brandFilters, categoryFilters, stockStatusFilters };
	}

	public Object[] searchProductByNameWithFilters(String keyword, String[] selectedBrands, String[] selectedCategories,
			String priceMin, String priceMax, String[] selectedAvailabilities, String selectedSorter, String perPage,
			String page) {
		Object[] originalSearchResultAndFilter = searchProductByName(keyword);
		Map<String, SearchFilter> allBrandFilters = (Map<String, SearchFilter>) originalSearchResultAndFilter[0];
		Map<String, SearchFilter> allCategoryFilters = (Map<String, SearchFilter>) originalSearchResultAndFilter[1];
		Map<String, SearchFilter> allStockStatusFilters = (Map<String, SearchFilter>) originalSearchResultAndFilter[2];

		Map<String, String> allSortBy = loadSortBy();
		Map<String, String> allResultPerPages = loadResultPerPage();
		Map<String, String> pagingMap = null;

		List<Product> filteredProducts = new ArrayList<Product>();

		int currentParam = 0;

		setSelectedSearchFilter(allBrandFilters, selectedBrands);
		setSelectedSearchFilter(allCategoryFilters, selectedCategories);
		setSelectedSearchFilter(allStockStatusFilters, selectedAvailabilities);

		setSelectedSetting(allSortBy, selectedSorter);
		setSelectedSetting(allResultPerPages, perPage);

		String priceMinCondition = priceMin.isEmpty() ? "" : "new_price >= ?";
		String priceMaxCondition = priceMax.isEmpty() ? "" : "new_price <= ?";

		String searchProductSQL = (keyword.isEmpty() ? SEARCH_ALL_SQL : SEARCH_FULLTEXT_SQL)
				+ getHavingClause(new String[] { getColumnCondition(selectedBrands, "brand_id"),
						getColumnCondition(selectedCategories, "category_id"),
						getAvailabilityCondition(selectedAvailabilities), priceMinCondition, priceMaxCondition });

		searchProductSQL += selectedSorter.isEmpty() ? "" : getSortCondition(selectedSorter);
		searchProductSQL += perPage.isEmpty() ? "" : " limit ? ";
		searchProductSQL += page.isEmpty() ? "" : " offset ? ";

		Connection connection = getConnection();
		PreparedStatement selectStm = null;
		ResultSet result = null;
		int rowCountBeforeLimit = 0;

		try {
			selectStm = connection.prepareStatement(searchProductSQL);
			
			if(!keyword.isEmpty())
				selectStm.setString(++currentParam, keyword);

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

			int totalPage = (int) Math.ceil((double) rowCountBeforeLimit / Double.parseDouble(perPage));
			pagingMap = getPagingMap(totalPage);

			setSelectedSetting(pagingMap, page);
			currentParam = currentParam - 2;

			if (!perPage.isEmpty())
				selectStm.setInt(++currentParam, Integer.parseInt(perPage));
			if (!page.isEmpty()) {
				int selectedPage = (Integer.parseInt(page) > totalPage) ? totalPage : Integer.parseInt(page);
				selectStm.setInt(++currentParam, (selectedPage - 1) * Integer.parseInt(perPage));
			}

			logger.debug("Query after limit and offset: " + selectStm.toString());
			result.close();
			result = selectStm.executeQuery();

			while (result.next()) {
				Product product = mapper.map(result);
				filteredProducts.add(product);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
		} finally {
			close(connection, selectStm, result);
		}

		return new Object[] { filteredProducts, allBrandFilters, allCategoryFilters, allStockStatusFilters, allSortBy,
				allResultPerPages, pagingMap, rowCountBeforeLimit };
	}

	public Product getProductByID(String id) {
		List<Product> products = getBy(new String[] { "id" }, new Object[] { Integer.parseInt(id) });

		return (products.isEmpty() || products == null) ? null : products.get(0);
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

	private void setSelectedSetting(Map<String, String> settingMaps, String selectedSetting) {
		for (Entry entry : settingMaps.entrySet()) {
			if (((String) entry.getKey()).equalsIgnoreCase(selectedSetting))
				settingMaps.put((String) entry.getKey(), "selected");
			else
				settingMaps.put((String) entry.getKey(), "");
		}
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
