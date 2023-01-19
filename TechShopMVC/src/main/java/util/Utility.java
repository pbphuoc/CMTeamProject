package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Utility {
//	private static final String  DB_URL = "jdbc:mysql://localhost:3306/cm_project";
//	private static final String DB_USERNAME = "projectuser";	
//	private static final String DB_PASSWORD = "codingmentor";
	private static final String DO_DB_URL = "jdbc:mysql://techiladb-do-user-13228925-0.b.db.ondigitalocean.com:25060/cm_project";
	private static final String DO_DB_USERNAME = "doadmin";
	private static final String DO_DB_PASSWORD = "AVNS_kt3ZJPXPT-M9qh-YgQJ";	
//	private Connection connection;
	
	public static final String SELECT_FROM_SUB_QUERY = "SELECT * FROM";
	public static final String AND_QUERY = " AND ";
	public static final String OR_QUERY = " OR ";
	public static final String WHERE_QUERY = " WHERE ";	
	
//	private static final String DEFAULT_AVAILABILITY_SETTING = "1";
//	private static final String DEFAULT_SORTBY_SETTING = "0";
//	private static final String DEFAULT_RESULTPERPAGE_SETTING = "16";
	public static final String STOCKSTATUS_OUTOFSTOCK = "0";	
	public static final String STOCKSTATUS_INSTOCK = "1";
	public static final String ORDERSTATUS_RECEIVED = "0";
	public static final String ORDERSTATUS_PROCESSING = "1";
	public static final String ORDERSTATUS_READY = "2";
	public static final String ORDERSTATUS_FINISHED = "3";
	public static final String ORDERSTATUS_REFUNDED = "4";
	public static final String ORDERSTATUS_CANCELLED = "-1";
	public static final String PAYMENT_UNPAID = "0";
	public static final String PAYMENT_CARD = "1";
	public static final String PAYMENT_TRANSFER = "2";
	public static final String PAYMENT_ATSTORE = "3";
	public static final String RECEIVEMETHOD_PICKUP = "1";
	public static final String RECEIVEMETHOD_DELIVER = "2";
	
	
	public static final Map<String, String> AVAILABILITY_MAP = new LinkedHashMap<String, String>(){{
		put(STOCKSTATUS_OUTOFSTOCK, "Out Of Stock");
		put(STOCKSTATUS_INSTOCK, "In Stock");
	}};		
	
	public static final Map<String, String> SORTBY_MAP = new LinkedHashMap<String, String>(){{
		put("0", "Relevancy");
		put("5", "Price Low To High");
		put("-5", "Price High To Low");
		put("2", "Name A To Z");
		put("-2", "Name Z To A");
		put("1", "Old To New");
		put("-1", "New To Old");
	}};
	
	public static final Map<String, String> RESULTPERPAGE_MAP = new LinkedHashMap<String, String>(){{
		put("16", "16");
		put("32", "32");
		put("64", "64");
		put("128", "128");
	}};		
	
	public static final Map<String, String> ORDERSTATUS_MAP = new HashMap<String, String>(){{
		put(ORDERSTATUS_RECEIVED, "RECEIVED");
		put(ORDERSTATUS_PROCESSING, "PROCESSING");
		put(ORDERSTATUS_READY, "READY");
		put(ORDERSTATUS_FINISHED, "FINISHED");
		put(ORDERSTATUS_REFUNDED, "REFUNDED");
		put(ORDERSTATUS_CANCELLED, "CANCELLED");
	}};		
	
	public static final Map<String, String> PAYMENT_MAP = new HashMap<String, String>(){{
		put(PAYMENT_UNPAID, "UNPAID");
		put(PAYMENT_CARD, "CARD");
		put(PAYMENT_TRANSFER, "AT STORE");
		put(PAYMENT_ATSTORE, "TRANSFER");		
	}};
	
	public static final Map<String, String> RECEIVEMETHOD_MAP = new HashMap<String, String>(){{
		put(RECEIVEMETHOD_PICKUP, "PICK UP AT STORE");
		put(RECEIVEMETHOD_DELIVER, "DELIVERY");	
	}};		
	
	public enum QueryResult{
		SUCCESSFUL,
		UNSUCCESSFUL
	}	
			
//	public enum PAYMENT{
//		UNPAID,
//		PAYATSTORE,
//		TRANSFER,
//		CARD
//	}
	
//	public enum STATUS{
//		RECEIVED,
//		PROCESSING,
//		READY,
//		FINISHED,
//		REFUNDED
//	}	
	
	public static Entry getEntryByKey(Map<?,?> theMap, Object theKey, Object theDefaultKey) {
		Entry defaultEntry = null;
		for(Entry entry: theMap.entrySet()) {
			if(entry.getKey().equals(theKey))
				return entry;
			else if(entry.getKey().equals(theDefaultKey))
				defaultEntry = entry;
		}
		return defaultEntry;
	}
	public static String getCorrectPrevUrl(String prevUrl) {
//		String newPrevUrl = "Home";
//		if(prevUrl != null && ! prevUrl.equalsIgnoreCase("")) {
//			if(prevUrl.indexOf("prevUrl=/") != -1)
//				newPrevUrl =  prevUrl.substring(prevUrl.indexOf("prevUrl=/") + 9,  prevUrl.length());
//			else
//				newPrevUrl = prevUrl.charAt(0) == '/' ? prevUrl.substring(1) : prevUrl;;
//		}		
		String newPrevUrl = (prevUrl == null || prevUrl.equalsIgnoreCase("")) ? "Home" : prevUrl;
		newPrevUrl = newPrevUrl.charAt(0) == '/' ? newPrevUrl.substring(1) : newPrevUrl;
		return newPrevUrl;
	}
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
//			connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			return DriverManager.getConnection(DO_DB_URL,DO_DB_USERNAME,DO_DB_PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}	
	
	public static void close(Connection connection, PreparedStatement stm, ResultSet rs) {
		try {
			if(rs != null)
				rs.close();
			if(stm != null)
				stm.close();				
			if(connection != null)
				connection.close();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public static QueryResult getResultCode(int code) {
		if (code > 0)
			return QueryResult.SUCCESSFUL;

			return QueryResult.UNSUCCESSFUL;
	}
}
