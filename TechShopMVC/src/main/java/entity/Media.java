package entity;

import annotation.DBField;
import annotation.Table;

@Table(name = "media")
public class Media {
	
	@DBField(name = "product_id", type = String.class)
	private String productID;
	
	@DBField(name = "src", type = String.class)
	private String src;

	public Media(String productID, String src) {
		this.productID = productID;
		this.src = src;
	}

	public String getProductID() {
		return productID;
	}

	public String getSrc() {
		return src;
	}

	@Override
	public String toString() {
		return "Media [productID=" + productID + ", src=" + src + "]";
	}
	
}
