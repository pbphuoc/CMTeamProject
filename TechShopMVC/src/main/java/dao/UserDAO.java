package dao;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entity.User;
import mapper.UserMapper;
import model.UserSession;
import util.BCrypt;

public class UserDAO extends BaseDAO<UserMapper, User, UserDAO> {

	private static UserDAO userDAO;
	private static final Logger logger = LogManager.getLogger(UserDAO.class);

	private UserDAO() {
		super(new UserMapper());
	}

	public static UserDAO getUserDAO() {
		if (userDAO == null)
			userDAO = new UserDAO();
		return userDAO;
	}

	public UserSession insertUser(User user) {
		UserSession userSession = null;
		boolean successful = false;
		try {
			List<User> users = getBy(new String[] { "email" }, new Object[] { user.getEmail() });
			if (users != null && !users.isEmpty())
				return null;

			String salt = BCrypt.gensalt(15);

			user.setSalt(salt);
			user.setPassword(BCrypt.hashpw(user.getPassword(), salt));

			int generatedKey = create(user, null);

			if (generatedKey != -1) {
				successful = true;
				user.setId(generatedKey + "");
				userSession = mapper.mapToUserSession(user);
			}

			logger.debug(String.format("Insert User %s : %s", (successful ? "successful" : "failed"), user.toString()));

			return userSession;

		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			logger.error(String.format("Insert User %s : %s", (successful ? "successful" : "failed"), user.toString()));
		}

		return null;
	}

	public UserSession authenticateUser(String email, String password) {
		boolean successful = false;
		UserSession userSession = null;
		try {
			List<User> users = getBy(new String[] { "email" }, new Object[] { email });

			if (users == null || users.isEmpty())
				return null;

			User authenticatingUser = users.get(0);

			if (BCrypt.checkpw(password, authenticatingUser.getPassword())) {
				successful = true;
				userSession = mapper.mapToUserSession(authenticatingUser);
			}

			logger.debug(
					String.format("Auth User %s : %s", (successful ? "successful" : "failed"), userSession.toString()));

			return userSession;

		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(
					String.format("Auth User %s : %s", (successful ? "successful" : "failed"), userSession.toString()));
		}
		return null;
	}
}
