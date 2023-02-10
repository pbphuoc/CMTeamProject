package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import global.GlobalConstant;
import model.OrderItemDTO;

public class Utility {
	private static final Logger logger = LogManager.getLogger(Utility.class);

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
	
	public static String convertDMYToYMD(String date) {
		if (date.equalsIgnoreCase(""))
			return date;

		LocalDate originalFormat = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		return originalFormat.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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

	public static double calculateShippingCost(String addressLine1, String addressLine2, String city, String state, String postCode, String countryCode) {
		double baseShippingCost = 20;
		switch(state) {
			case "QLD":
				return baseShippingCost;
			case "NSW":
				return baseShippingCost + 5;
			case "ACT":
				return baseShippingCost + 10;				
			case "VIC":
				return baseShippingCost + 15;
			case "SA":
				return baseShippingCost + 20;					
			case "TAS":
				return baseShippingCost + 25;	
			case "NT":
				return baseShippingCost + 30;					
			case "WA":
				return baseShippingCost + 35;
			default:
				return baseShippingCost;
		}
	}
	
	public static void handleError(HttpServletResponse response, int errorCode) {
		try {
			response.sendError(errorCode);
		}
		catch(Exception e){
			logger.error(e.getMessage());
		}
	}
}
