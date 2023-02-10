package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entity.Media;
import mapper.MediaMapper;

public class MediaDAO extends BaseDAO<MediaMapper, Media, MediaDAO> {

	private static MediaDAO mediaDAO;
	private static final Logger logger = LogManager.getLogger(MediaDAO.class);

	private MediaDAO() {
		super(new MediaMapper());
	}

	public static MediaDAO getMediaDAO() {
		return mediaDAO != null ? mediaDAO : new MediaDAO();
	}

	public List<String> getAllImgSrcByProductID(String productID) {
		List<Media> medias = getBy(new String[] { "product_id" }, new Object[] { productID });

		List<String> images = (medias == null) ? new ArrayList<String>()
				: medias.stream().map(x -> x.getSrc()).collect(Collectors.toList());

		return images;
	}
}
