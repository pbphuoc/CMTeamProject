package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entity.Media;

public class MediaMapper extends AbstractMapper<Media> {

	private static final Logger logger = LogManager.getLogger(MediaMapper.class);
	
	@Override
	public Media map(ResultSet result) {
		try {			
			String productID = result.getString("product_id");
			String src = result.getString("src");
			
			Media media = new Media(productID, src);
			
			return media;
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		
		return null;
	}

	@Override
	public PreparedStatement map(PreparedStatement statement, Media object) {
		// TODO Auto-generated method stub
		return null;
	}

}
