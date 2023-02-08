package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entity.User;
import model.UserSession;
import util.BCrypt;
import util.Utility;

public class UserDAO {

	private static final String INSERT_USER_SQL = "INSERT INTO user (email, password, fullname, phone_number, salt) VALUES (?,?,?,?,?);";
	private static final String SELECT_USER_BY_EMAIL_SQL = "SELECT * FROM user WHERE email = ?;";
	private static final String SELECT_USER_BY_EMAIL_AND_PASSWORD_SQL = "SELECT * FROM user WHERE email = ? AND password = ?;";
	private static final String SELECT_SALT_BY_EMAIL_SQL = "SELECT salt FROM user WHERE email = ?;";

	private static UserDAO userDAO;
	private static final Logger logger = LogManager.getLogger(UserDAO.class);

	private UserDAO() {
	}

	public static UserDAO getUserDAO() {
		if (userDAO == null)
			userDAO = new UserDAO();
		return userDAO;
	}

	public UserSession insertUser(String email, String password, String fullname, String mobile) {
		Connection connection = Utility.getConnection();
		PreparedStatement insertStm = null;
		ResultSet generatedKeys = null;
		UserSession user = null;
		
		try {
			insertStm = connection.prepareStatement(INSERT_USER_SQL, Statement.RETURN_GENERATED_KEYS);
			if (userExist(email))
				return user;
			
			String salt = BCrypt.gensalt(15);
			String hashedPass = BCrypt.hashpw(password, salt);
			insertStm.setString(1, email);
			insertStm.setString(2, hashedPass);
			insertStm.setString(3, fullname);
			insertStm.setString(4, mobile);
			insertStm.setString(5, salt);
			if (insertStm.executeUpdate() > 0) {
				generatedKeys = insertStm.getGeneratedKeys();
				if (generatedKeys.next()) {
					user = new UserSession(generatedKeys.getInt(1) + "", email, fullname, mobile);
				}
			}
			
			logger.debug("Detail Insert User: - UserID %s - UserEmail %s - UserFullname %s - UserMobile %s", email,
					fullname, mobile);
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error("Detail Insert User: - UserID %s - UserEmail %s - UserFullname %s - UserMobile %s", email,
					fullname, mobile);
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			logger.error("Detail Insert User: - UserID %s - UserEmail %s - UserFullname %s - UserMobile %s", email,
					fullname, mobile);
		} finally {
			Utility.close(connection, insertStm, generatedKeys);
		}
		
		return user;
	}

	public boolean userExist(String email) {
		Boolean userExist = false;
		Connection connection = Utility.getConnection();
		PreparedStatement selectStm = null;
		ResultSet result = null;
		
		try {
			selectStm = connection.prepareStatement(SELECT_USER_BY_EMAIL_SQL);
			selectStm.setString(1, email);
			result = selectStm.executeQuery();
			System.out.println("query: " + selectStm);
			
			if (!result.next())
				return userExist;
			
			userExist = true;
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
		} finally {
			Utility.close(connection, selectStm, result);
		}
		
		return userExist;
	}

	private String getSaltByEmail(String email) {
		String salt = "";
		Connection connection = Utility.getConnection();
		PreparedStatement selectStm = null;
		ResultSet result = null;
		
		try {
			selectStm = connection.prepareStatement(SELECT_SALT_BY_EMAIL_SQL);
			selectStm.setString(1, email);
			result = selectStm.executeQuery();
			
			if (result.next())
				salt = result.getString("salt");
			
			return salt;
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
		} finally {
			Utility.close(connection, selectStm, result);
		}
		
		return salt;
	}

	public UserSession authenticateUser(String email, String password) {
		UserSession user = null;
		Connection connection = Utility.getConnection();
		PreparedStatement selectStm = null;
		ResultSet result = null;

		try {
			String salt = getSaltByEmail(email);
			String hashedPass = BCrypt.hashpw(password, salt);
			selectStm = connection.prepareStatement(SELECT_USER_BY_EMAIL_AND_PASSWORD_SQL);
			selectStm.setString(1, email);
			selectStm.setString(2, hashedPass);
			result = selectStm.executeQuery();

			if (!result.next())
				return user;

			user = new UserSession(result.getString("id"), result.getString("email"), result.getString("fullname"),
					result.getString("phone_number"));
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
		} finally {
			Utility.close(connection, selectStm, result);
		}
		
		return user;
	}
}
