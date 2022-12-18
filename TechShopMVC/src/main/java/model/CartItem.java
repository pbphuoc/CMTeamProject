package model;

public class CartItem {
	private String id;
	private int quantity;
	
	public CartItem(String productID, int quantity) {
		
		this.id = productID;
		this.quantity = quantity;
	}

	public String getProductID() {
		return id;
	}

	public void setProductID(String productID) {
		this.id = productID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	
	
}
