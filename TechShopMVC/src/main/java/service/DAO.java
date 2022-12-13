package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import service.DAO.QueryResult;


public abstract class DAO<T> {
	
	private static final String  dbURL = "jdbc:mysql://localhost:3306/cm_project";
	private static final String dbUsername = "projectuser";
	private static final String dbPassword = "codingmentor";
	private Connection connection;
	public enum DAOType{
		USER,
		PRODUCT
	}
	
	public enum QueryResult{
		SUCCESSFUL,
		UNSUCCESSFUL,
		DUPLICATE
	}	
	
	protected DAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected Connection getConnection() {
		return connection;
	}
	
	protected QueryResult getResultCode(int code) {
		if (code > 0)
			return QueryResult.SUCCESSFUL;

			return QueryResult.UNSUCCESSFUL;
	}	

	abstract
	public List<T> getAllRecords();
	
	abstract
	public T getRecordByID(String id);
}
