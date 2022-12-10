package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.User;

public class UserDAO extends DAO<User>{
	
	private static final String INSERT_USER_SQL = "INSERT INTO user (email, password, fullname, phone_number) VALUES (?,?,?,?);";
	private static final String SELECT_USER_BY_EMAIL_SQL = "SELECT * FROM user WHERE email = ?;";
//	private static final String UPDATE_USER_BY_EMAIL_SQL = "UPDATE user SET fullname = ?, phone_number = ? WHERE email = ?;";
//	private static final String UPDATE_PASSWORD_SQL = "UPDATE user SET password = ? where id = ? AND password = ?;";
	private static final String DELETE_USER_SQL = "DELETE FROM user where email = ?;";
	
	private Connection connection;
	
	public UserDAO() {
		super();
		connection = getConnection();
	}	
	
	public QueryResult insertUser(User user) {
		try(
				PreparedStatement insertStm = connection.prepareStatement(INSERT_USER_SQL);
				){
			if(getRecordByID(user.getEmail()) != null)
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
	
	@Override
	public User getRecordByID(String email) {
		User user = null;
		try(PreparedStatement selectStm = connection.prepareStatement(SELECT_USER_BY_EMAIL_SQL);)
		{
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

	@Override
	public List<User> getAllRecords() {
		// TODO Auto-generated method stub
		return null;
	}	
}
