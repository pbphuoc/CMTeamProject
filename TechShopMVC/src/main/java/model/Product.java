package model;

import java.util.HashMap;
import java.util.Map;

public class Product {
	private String id;
	private String name;
	private String description;
	private double oldPrice;
	private double newPrice;
	private String brandID;
	private String categoryID;
	private String imgSrc;
	private int stock;
	private String stockStatus;
	private static final String STATUS_IN_STOCK = "1";
	private static final String STATUS_OUT_OF_STOCK = "0";
	
	public static final Map<String, String> AVAILABILITY_MAP = new HashMap<String, String>(){{
		put("0", "Out Of Stock");
		put("1", "In Stock");
	}};			

	public Product(String id, String name, String description, double oldPrice, double newPrice, String brandID,
			String categoryID, String imgSrc, int stock) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.oldPrice = oldPrice;
		this.newPrice = newPrice;
		this.brandID = brandID;
		this.categoryID = categoryID;
		this.imgSrc = imgSrc;
		this.stock = stock;
		this.stockStatus = stock > 0 ? STATUS_IN_STOCK : STATUS_OUT_OF_STOCK;
	}
	
	public String getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public double getOldPrice() {
		return oldPrice;
	}

	public double getNewPrice() {
		return newPrice;
	}

	public String getBrandID() {
		return brandID;
	}

	public String getCategoryID() {
		return categoryID;
	}

	public String getImgSrc() {
		return imgSrc;
	}

	public int getStock() {
		return stock;
	}
	
	public String getStockStatus() {
		return stockStatus;
	}

	public static Map<String, String> getAvailabilityMap() {
		return AVAILABILITY_MAP;
	}	
	
	
}
