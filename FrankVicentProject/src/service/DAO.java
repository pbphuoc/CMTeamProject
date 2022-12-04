package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;

public class DAO {
	private final String  dbURL = "jdbc:mysql://localhost:3306/cm_project";
	private final String dbUsername = "root";
	private final String dbPassword = "codingmentor";
	private Connection connection;
	
	private static final String INSERT_USER_SQL = "INSERT INTO user (email, password, fullname, phone_number) VALUES (?,?,?,?);";
	private static final String SELECT_USER_BY_EMAIL_SQL = "SELECT * FROM user WHERE email = ?;";
	private static final String UPDATE_USER_BY_EMAIL_SQL = "UPDATE user SET fullname = ?, phone_number = ? WHERE email = ?;";
	private static final String UPDATE_PASSWORD_SQL = "UPDATE user SET password = ? where id = ? AND password = ?;";
	private static final String DELETE_USER_SQL = "DELETE FROM user where email = ?;";
	
	public enum QueryResult{
		SUCCESSFUL,
		UNSUCCESSFUL,
		DUPLICATE
	}
	
	public DAO() {
		try {
			connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public QueryResult insertUser(User user) {
		try(
				PreparedStatement insertStm = connection.prepareStatement(INSERT_USER_SQL);
				){
			if(selectUserByEmail(user.getEmail()) != null)
					return QueryResult.DUPLICATE;
			insertStm.setString(1, user.getEmail());
			insertStm.setString(2, user.getPassword());
			insertStm.setString(3, user.getFullname());
			insertStm.setString(4, user.getPhoneNumber());			
			return getResultCode(insertStm.executeUpdate());
		}catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return QueryResult.UNSUCCESSFUL;
	}
	
	public User selectUserByEmail(String email) {
		User user = null;
		try(PreparedStatement selectStm = connection.prepareStatement(SELECT_USER_BY_EMAIL_SQL);){
			selectStm.setString(1, email);
			ResultSet result = selectStm.executeQuery();
			if(!result.next())
				return user;
			user = new User(result.getString("email"), result.getString("fullname"), result.getString("phone_number"));
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	public QueryResult deleteUser(User user) {
		try(PreparedStatement deletetStm = connection.prepareStatement(DELETE_USER_SQL);){
			deletetStm.setString(1, user.getEmail());
			return getResultCode(deletetStm.executeUpdate());
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return QueryResult.UNSUCCESSFUL;		
	}
	
	private QueryResult getResultCode(int code) {
		if (code > 0)
			return QueryResult.SUCCESSFUL;

			return QueryResult.UNSUCCESSFUL;
	}
	
}
