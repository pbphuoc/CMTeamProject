package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entity.Category;

public class CategoryMapper extends AbstractMapper<Category> {

	private static final Logger logger = LogManager.getLogger(CategoryMapper.class);

	@Override
	public Category map(ResultSet result) {
		try {
			String id = result.getString("id");
			String name = result.getString("name");
			String imgSrc = result.getString("img_src");

			Category category = new Category(id, name, imgSrc);

			return category;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return null;
	}

	@Override
	public PreparedStatement map(PreparedStatement statement, Category object) {
		// TODO Auto-generated method stub
		return null;
	}
}
