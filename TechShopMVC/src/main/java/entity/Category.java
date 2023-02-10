package entity;

import annotation.DBField;
import annotation.Table;

@Table(name = "category")
public class Category {
	
	private String id;
	
	@DBField(name = "name", type = String.class)
	private String name;
	
	@DBField(name = "img_src", type = String.class)
	private String imgSrc;

	public Category(String id, String name, String imgSrc) {
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
		return "Category [id=" + id + ", name=" + name + ", imgSrc=" + imgSrc + "]";
	}
	
}
