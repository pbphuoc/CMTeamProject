package entity;

import constant.StockStatusEnum;

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
	}

	public Product(String id, String name, String description, double newPrice, String imgSrc) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.newPrice = newPrice;
		this.imgSrc = imgSrc;
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

	public void setName(String name) {
		this.name = name;
	}

	public String getStockStatus() {
		if (stock == 0)
			return StockStatusEnum.OUTOFSTOCK.toString();
		else
			return StockStatusEnum.INSTOCK.toString();
	}

	public String getStockStatusDescription() {
		if (stock == 0)
			return StockStatusEnum.OUTOFSTOCK.getValue();
		else
			return StockStatusEnum.INSTOCK.getValue();
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", oldPrice=" + oldPrice
				+ ", newPrice=" + newPrice + ", brandID=" + brandID + ", categoryID=" + categoryID + ", imgSrc="
				+ imgSrc + ", stock=" + stock + "]";
	}
}
