package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


public abstract class DAO {
	
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

	abstract
	public List<?> getAll();
}
