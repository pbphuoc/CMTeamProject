package entity;

import util.Utility;

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
//	private String stockStatus;	

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
//		this.stockStatus = (stock == 0) ? Utility.STOCKSTATUS_OUTOFSTOCK : Utility.STOCKSTATUS_INSTOCK ;
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
		if (stock == 0)
			return Utility.STOCKSTATUS_OUTOFSTOCK;
		else
			return Utility.STOCKSTATUS_INSTOCK;
	}
	
	public String getStockStatusDescription() {
		if (stock == 0)
			return Utility.AVAILABILITY_MAP.get(Utility.STOCKSTATUS_OUTOFSTOCK);
		else
			return Utility.AVAILABILITY_MAP.get(Utility.STOCKSTATUS_INSTOCK);
	}
}
