package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import dao.DAO.QueryResult;


public abstract class DAO<T> {
	
//	private static final String  DB_URL = "jdbc:mysql://localhost:3306/cm_project";
//	private static final String DB_USERNAME = "projectuser";	
//	private static final String DB_PASSWORD = "codingmentor";
	private static final String DO_DB_URL = "jdbc:mysql://techiladb-do-user-13228925-0.b.db.ondigitalocean.com:25060/cm_project";
	private static final String DO_DB_USERNAME = "doadmin";
	private static final String DO_DB_PASSWORD = "AVNS_kt3ZJPXPT-M9qh-YgQJ";	
	private Connection connection;
	
	protected static final String SELECT_FROM_SUB_QUERY = "SELECT * FROM";
	protected static final String AND_QUERY = " AND ";
	protected static final String OR_QUERY = " OR ";
	protected static final String WHERE_QUERY = " WHERE ";
	
	public enum DAOType{
		USER,
		PRODUCT
	}
	
	public enum QueryResult{
		SUCCESSFUL,
		UNSUCCESSFUL
	}	
	
	protected DAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
//			connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			connection = DriverManager.getConnection(DO_DB_URL,DO_DB_USERNAME,DO_DB_PASSWORD);
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
	
	protected void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	abstract
	public List<T> getAllRecords();
	
	abstract
	public T getRecordByID(String id);
}
