package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import constant.GlobalConstant;

public class Utility {
	private static final Logger logger = LogManager.getLogger(Utility.class);
	public enum QueryResult {
		SUCCESSFUL, UNSUCCESSFUL
	}

	public static String getCorrectPrevUrl(String prevUrl) {
		String newPrevUrl = (prevUrl == null || prevUrl.equalsIgnoreCase(GlobalConstant.BLANK))
				? GlobalConstant.HOME_URL
				: prevUrl;
		newPrevUrl = newPrevUrl.charAt(0) == '/' ? newPrevUrl.substring(1) : newPrevUrl;
		return newPrevUrl;
	}
	
	public static String convertYMDToDMY(String date) {
		if (date.equalsIgnoreCase(""))
			return date;

		LocalDate originalFormat = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		return originalFormat.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));		
	}

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(GlobalConstant.DO_DB_URL, GlobalConstant.DO_DB_USERNAME,
					GlobalConstant.DO_DB_PASSWORD);
		} catch (Exception e) {
			logger.error(e.toString());
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
		} catch (Exception e) {
			logger.error(e.toString());
		}
	}

	public static Utility.QueryResult getResultCode(int code) {
		if (code > 0)
			return Utility.QueryResult.SUCCESSFUL;

		return Utility.QueryResult.UNSUCCESSFUL;
	}
}
