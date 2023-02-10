package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.stream.IntStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractMapper<E> {
	private static final Logger logger = LogManager.getLogger(AbstractMapper.class);

	public abstract E map(ResultSet result);

	public abstract PreparedStatement map(PreparedStatement statement, E object);

	public PreparedStatement mapParamValue(PreparedStatement statement, Object[] args) {

		IntStream.range(0, args.length).forEach(i -> {
			Object obj = args[i];
			try {
				if (obj instanceof Integer)
					statement.setInt(i + 1, (Integer) obj);
				else if (obj instanceof String || obj instanceof Enum)
					statement.setString(i + 1, (String) obj);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		});

		return statement;
	}
}
