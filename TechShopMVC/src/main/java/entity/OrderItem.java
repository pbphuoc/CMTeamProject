package entity;

public class OrderItem {
	private String orderID;
	private String productID;
	private String productName;
	private String productDescription;
	private String productImgSrc;
	private double price;
	private int quantity;
	
	public OrderItem(String orderID, String productID, String productName, String productDescription,
			String productImgSrc, double price, int quantity) {
		this.orderID = orderID;
		this.productID = productID;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productImgSrc = productImgSrc;
		this.price = price;
		this.quantity = quantity;
	}

	public String getOrderID() {
		return orderID;
	}

	public String getProductID() {
		return productID;
	}

	public String getProductName() {
		return productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public String getProductImgSrc() {
		return productImgSrc;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}
}
