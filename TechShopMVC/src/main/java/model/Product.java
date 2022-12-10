package model;

public class Product {
	private String id;
	private String name;
	private String description;
	private double oldPrice;
	private double newPrice;
	private String brandID;
	private String categoryID;
	private String imgSrc;
	
	public Product(String id, String name, String description, double oldPrice, double newPrice, String brandID,
			String categoryID, String imgSrc) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.oldPrice = oldPrice;
		this.newPrice = newPrice;
		this.brandID = brandID;
		this.categoryID = categoryID;
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
	
}
