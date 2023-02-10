package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entity.Brand;

public class BrandMapper extends AbstractMapper<Brand> {

	private static final Logger logger = LogManager.getLogger(BrandMapper.class);

	@Override
	public Brand map(ResultSet result) {
		try {
			String id = result.getString("id");
			String name = result.getString("name");
			String imgSrc = result.getString("img_src");

			Brand brand = new Brand(id, name, imgSrc);

			return brand;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return null;
	}

	@Override
	public PreparedStatement map(PreparedStatement statement, Brand object) {
		// TODO Auto-generated method stub
		return null;
	}

}
