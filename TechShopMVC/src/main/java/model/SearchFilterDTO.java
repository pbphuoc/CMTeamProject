package model;

public class SearchFilterDTO {
	private String id;
	private String name;
	private String selected = "";	
	private int stock = 0;
//	private String imgSrc;
	
	
//	public static final Map<String, String> AVAILABILITY_MAP = new LinkedHashMap<String, String>(){{
//		put("0", "Out Of Stock");
//		put("1", "In Stock");
//	}};		
//	
//	public static final Map<String, String> SORTBY_MAP = new LinkedHashMap<String, String>(){{
//		put("0", "Relevancy");
//		put("5", "Price Low To High");
//		put("-5", "Price High To Low");
//		put("2", "Name A To Z");
//		put("-2", "Name Z To A");
//		put("1", "Old To New");
//		put("-1", "New To Old");
//	}};
//	
//	public static final Map<String, String> RESULTPERPAGE_MAP = new LinkedHashMap<String, String>(){{
//		put("16", "16");
//		put("32", "32");
//		put("64", "64");
//		put("128", "128");
//	}};		
	
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

//	public String getImgSrc() {
//		return imgSrc;
//	}
//
//	public void setImgSrc(String imgSrc) {
//		this.imgSrc = imgSrc;
//	}
}
