package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import constant.GlobalConstant;
import model.OrderItemDTO;

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

	public static String get2DFPrice(double price) {
		if (price == 0)
			return "0.00";
		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(price);
	}

	public static double calculateTotalCost(List<OrderItemDTO> items) {
		double totalCost = 0;
		for (OrderItemDTO item : items) {
			totalCost += item.getSubtotal();
		}
		return totalCost;
	}

	public static double calculateShippingCost(String address) {
		return 0.5;
	}

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(GlobalConstant.DO_DB_URL, GlobalConstant.DO_DB_USERNAME,
					GlobalConstant.DO_DB_PASSWORD);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
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
			logger.error(e.getMessage());
		}
	}

	public static Utility.QueryResult getResultCode(int code) {
		if (code > 0)
			return Utility.QueryResult.SUCCESSFUL;

		return Utility.QueryResult.UNSUCCESSFUL;
	}
}
