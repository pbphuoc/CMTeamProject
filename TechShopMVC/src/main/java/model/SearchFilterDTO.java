package model;

public class SearchFilterDTO {
	private String id;
	private String name;
	private String selected = "";
	private int stock = 0;

	public SearchFilterDTO(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
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
