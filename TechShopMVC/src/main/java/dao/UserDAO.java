package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entity.User;
import util.BCrypt;
import util.Utility;
import util.Utility.QueryResult;

public class UserDAO{
	
	private static final String INSERT_USER_SQL = "INSERT INTO user (email, password, fullname, phone_number, salt) VALUES (?,?,?,?,?);";
	private static final String SELECT_USER_BY_EMAIL_SQL = "SELECT * FROM user WHERE email = ?;";
	private static final String SELECT_USER_BY_EMAIL_AND_PASSWORD_SQL = "SELECT * FROM user WHERE email = ? AND password = ?;";	
	private static final String SELECT_SALT_BY_EMAIL_SQL = "SELECT salt FROM user WHERE email = ?;";	
//	private static final String UPDATE_USER_BY_EMAIL_SQL = "UPDATE user SET fullname = ?, phone_number = ? WHERE email = ?;";
//	private static final String UPDATE_PASSWORD_SQL = "UPDATE user SET password = ? where id = ? AND password = ?;";
//	private static final String DELETE_USER_SQL = "DELETE FROM user where email = ?;";	
	
//	protected UserDAO() {
//		super();
//		connection = getConnection();
//	}	
	
	public QueryResult insertUser(String email, String password, String fullname, String mobile) {
		Connection connection = Utility.getConnection();
		PreparedStatement insertStm = null;
		try {			
			insertStm = connection.prepareStatement(INSERT_USER_SQL);			
			if (getUserByEmail(email) != null)
				return QueryResult.UNSUCCESSFUL;
			String salt = BCrypt.gensalt(15);
			String hashedPass = BCrypt.hashpw(password, salt);	
			insertStm.setString(1, email);	
			insertStm.setString(2, hashedPass);
			insertStm.setString(3, fullname);
			insertStm.setString(4, mobile);
			insertStm.setString(5, salt);
			return Utility.getResultCode(insertStm.executeUpdate());
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally {
			Utility.close(connection, insertStm, null);
		}
		return QueryResult.UNSUCCESSFUL;
	}
	
	public User getUserByEmail(String email) {
		User user = null;
		Connection connection = Utility.getConnection();
		PreparedStatement selectStm = null;
		ResultSet result = null;
		try
		{			
			selectStm = connection.prepareStatement(SELECT_USER_BY_EMAIL_SQL);
			selectStm.setString(1, email);
			result = selectStm.executeQuery();
			if(!result.next())
				return user;
			user = new User(result.getString("email"), result.getString("fullname"), result.getString("phone_number"));		
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Utility.close(connection, selectStm, result);
		}
		return user;
	}
	
	private String getSaltByEmail(String email) {
		String salt = "";
		Connection connection = Utility.getConnection();
		PreparedStatement selectStm = null;
		ResultSet result = null;
		try
		{					
			selectStm = connection.prepareStatement(SELECT_SALT_BY_EMAIL_SQL);
			selectStm.setString(1, email);
			result = selectStm.executeQuery();
			if(result.next())
				salt = result.getString("salt");
			return salt;		
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Utility.close(connection, selectStm, result);
		}		
		return salt;		
	}
	
	public User getUserByEmailAndPassword(String email, String password) {
		User user = null;
		Connection connection = Utility.getConnection();
		PreparedStatement selectStm = null;
		ResultSet result = null;
		try
		{	
			String salt = getSaltByEmail(email);
			String hashedPass = BCrypt.hashpw(password, salt);				
			selectStm = connection.prepareStatement(SELECT_USER_BY_EMAIL_AND_PASSWORD_SQL);
			selectStm.setString(1, email);
			selectStm.setString(2, hashedPass);
			result = selectStm.executeQuery();
			if(!result.next())
				return user;
			user = new User(result.getString("email"), result.getString("fullname"), result.getString("phone_number"));		
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Utility.close(connection, selectStm, result);
		}		
		return user;
	}
}
