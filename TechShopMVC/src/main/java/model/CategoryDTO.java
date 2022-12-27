package model;

public class CategoryDTO  {
	private String id;
	private String name;
	private int stock;
	
	public CategoryDTO(String id, String name) {
		this.id = id;
		this.name = name;
		this.stock = 0;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
}
