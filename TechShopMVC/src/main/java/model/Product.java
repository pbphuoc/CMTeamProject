package model;

public class Product {
	private String code;
	private String name;
	private String description;
	private double oldPrice;
	private double newPrice;
	private Brand brand;
	private Category category;
	private String imgSrc;
	
	public Product(String code, String name, String description, String oldPrice, String newPrice, Brand brand,
			Category category, String imgSrc) {
		this.code = code;
		this.name = name;
		this.description = description;
		this.oldPrice = convertPrice(oldPrice);
		this.newPrice = convertPrice(oldPrice);
		this.brand = brand;
		this.category = category;
		this.imgSrc = imgSrc;
	}
	
	private double convertPrice(String price) {
		try {
			return Double.parseDouble(price);
		}
		catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public String getCode() {
		return code;
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

	public Brand getBrand() {
		return brand;
	}

	public Category getCategory() {
		return category;
	}

	public String getImgSrc() {
		return imgSrc;
	}
	
}
