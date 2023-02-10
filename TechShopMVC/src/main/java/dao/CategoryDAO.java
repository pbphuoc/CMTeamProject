package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entity.Category;
import mapper.CategoryMapper;
import model.SearchFilter;

public class CategoryDAO extends BaseDAO<CategoryMapper, Category, CategoryDAO> {

	private static CategoryDAO categoryDAO;
	private static final Logger logger = LogManager.getLogger(CategoryDAO.class);

	private CategoryDAO() {
		super(new CategoryMapper());
	}

	public static CategoryDAO getCategoryDAO() {
		return categoryDAO != null ? categoryDAO : new CategoryDAO();
	}

	public List<Category> getAllCategory() {
		List<Category> categories = getAll();

		return (categories == null) ? new ArrayList<Category>() : categories;
	}

	public Map<String, SearchFilter> loadAllCategoryToSearchFilter() {
		return getAllCategory().stream()
				.collect(Collectors.toMap(x -> x.getId(), x -> new SearchFilter(x.getId(), x.getName())));
	}
}
