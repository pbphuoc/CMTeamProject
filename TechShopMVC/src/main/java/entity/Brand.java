package entity;

import annotation.DBField;
import annotation.Table;

@Table(name = "brand")
public class Brand {

	private String id;

	@DBField(name = "name", type = String.class)
	private String name;

	@DBField(name = "img_src", type = String.class)
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

	@Override
	public String toString() {
		return "Brand [id=" + id + ", name=" + name + ", imgSrc=" + imgSrc + "]";
	}
}
