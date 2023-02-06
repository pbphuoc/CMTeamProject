package model;

public class SearchFilter {
	private String id;
	private String name;
	private boolean selected = false;
	private int stock = 0;

	public SearchFilter(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getSelected() {
		return selected ? "selected" : "";
	}

	public void setSelected(boolean selected) {
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
