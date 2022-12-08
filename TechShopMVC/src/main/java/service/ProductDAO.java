package service;

import java.sql.Connection;

public class ProductDAO extends DAO {
	private Connection connection;
	
	private static final String SELECT_ALL_PRODUCT_SQL = "SELECT * FROM product;";	
	
	protected ProductDAO(Connection connection) {
		this.connection = connection;
	}	
}
