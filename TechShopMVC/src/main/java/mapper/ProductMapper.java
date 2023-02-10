package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entity.Product;

public class ProductMapper extends AbstractMapper<Product> {

	private static final Logger logger = LogManager.getLogger(ProductMapper.class);

	@Override
	public Product map(ResultSet result) {
		try {
			String id = result.getString("id");
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

			return product;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return null;
	}

	@Override
	public PreparedStatement map(PreparedStatement statement, Product object) {
		// TODO Auto-generated method stub
		return null;
	}

}
