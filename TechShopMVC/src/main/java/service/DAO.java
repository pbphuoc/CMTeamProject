package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	private static final String  dbURL = "jdbc:mysql://localhost:3306/cm_project";
	private static final String dbUsername = "root";
	private static final String dbPassword = "codingmentor";
	
	public enum DAOType{
		USER
	}
	
	public enum QueryResult{
		SUCCESSFUL,
		UNSUCCESSFUL,
		DUPLICATE
	}	
	
	protected DAO() {

	}

	public static DAO getDAO(DAOType type) {
		try {
			switch (type) {
				case USER:
					return new UserDAO(DriverManager.getConnection(dbURL, dbUsername, dbPassword));
				default:
					return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
	}
}
