package model;

public class OrderItem {
	private String orderID;
	private String productID;
	private double price;
	private int quantity;
	
	public OrderItem(String orderID, String productID, double price, int quantity) {
		this.orderID = orderID;
		this.productID = productID;
		this.price = price;
		this.quantity = quantity;
	}
}
