package model;

import entity.Product;

public class OrderItemDTO {
//	private String orderID;
	private Product product;
	private int quantity;
	
	public OrderItemDTO(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

//	public CartItemDTO(String orderID, Product product, int quantity) {
//		this.orderID = orderID;
//		this.product = product;
//		this.quantity = quantity;
//	}	
	// NO NO NO

	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

//	public String getOrderID() {
//		return orderID;
//	}
//
//	public void setOrderID(String orderID) {
//		this.orderID = orderID;
//	}	
}
