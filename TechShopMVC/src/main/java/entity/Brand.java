package entity;

public class Brand {
	private String id;
	private String name;
	private String imgSrc;

	public Brand(String id, String name, String imgSrc) {
		this.id = id;
		this.name = name;
		this.imgSrc = imgSrc;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getImgSrc() {
		return imgSrc;
	}
}
