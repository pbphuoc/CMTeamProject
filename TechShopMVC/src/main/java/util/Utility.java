package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import constant.GlobalConstant;

public class Utility {

	public enum QueryResult {
		SUCCESSFUL, UNSUCCESSFUL
	}

	public static Entry getEntryByKey(Map<?, ?> theMap, Object theKey, Object theDefaultKey) {
		Entry defaultEntry = null;
		for (Entry entry : theMap.entrySet()) {
			if (entry.getKey().equals(theKey))
				return entry;
			else if (entry.getKey().equals(theDefaultKey))
				defaultEntry = entry;
		}
		return defaultEntry;
	}

	public static String getCorrectPrevUrl(String prevUrl) {
		String newPrevUrl = (prevUrl == null || prevUrl.equalsIgnoreCase(GlobalConstant.BLANK))
				? GlobalConstant.HOME_URL
				: prevUrl;
		newPrevUrl = newPrevUrl.charAt(0) == '/' ? newPrevUrl.substring(1) : newPrevUrl;
		return newPrevUrl;
	}

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(GlobalConstant.DO_DB_URL, GlobalConstant.DO_DB_USERNAME,
					GlobalConstant.DO_DB_PASSWORD);
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
			if (rs != null)
				rs.close();
			if (stm != null)
				stm.close();
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Utility.QueryResult getResultCode(int code) {
		if (code > 0)
			return Utility.QueryResult.SUCCESSFUL;

		return Utility.QueryResult.UNSUCCESSFUL;
	}
}
