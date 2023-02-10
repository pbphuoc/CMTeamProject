package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entity.Brand;
import mapper.BrandMapper;
import model.SearchFilter;

public class BrandDAO extends BaseDAO<BrandMapper, Brand, BrandDAO> {

	private static BrandDAO brandDAO;
	private static final Logger logger = LogManager.getLogger(BrandDAO.class);
	
	private BrandDAO() {
		super(new BrandMapper());
	}

	public static BrandDAO getBrandDAO() {
		return brandDAO != null ? brandDAO : new BrandDAO();
	}

	public List<Brand> getAllBrands() {
		List<Brand> brands = getAll();

		return (brands == null) ? new ArrayList<Brand>() : brands;
	}

	public Map<String, SearchFilter> loadAllBrandToSearchFilter() {
		return getAllBrands().stream()
				.collect(Collectors.toMap(x -> x.getId(), x -> new SearchFilter(x.getId(), x.getName())));
	}
}
