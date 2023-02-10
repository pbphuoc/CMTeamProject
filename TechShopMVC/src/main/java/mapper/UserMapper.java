package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entity.User;
import model.UserSession;

public class UserMapper extends AbstractMapper<User> {

	private static final Logger logger = LogManager.getLogger(UserMapper.class);

	@Override
	public User map(ResultSet result) {
		try {
			String id = result.getString("id");
			String email = result.getString("email");
			String password = result.getString("password");
			String fullname = result.getString("fullname");
			String phone_number = result.getString("phone_number");
			String salt = result.getString("salt");

			return new User(id, email, password, fullname, phone_number, salt);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public PreparedStatement map(PreparedStatement statement, User object) {
		try {
			statement.setString(1, object.getEmail());
			statement.setString(2, object.getPassword());
			statement.setString(3, object.getName());
			statement.setString(4, object.getPhoneNumber());
			statement.setString(5, object.getSalt());

			return statement;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
		}

		return null;
	}

	public UserSession mapToUserSession(User user) {
		return new UserSession(user.getId(), user.getEmail(), user.getName(), user.getPhoneNumber());
	}

}
