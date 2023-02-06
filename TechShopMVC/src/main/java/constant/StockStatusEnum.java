package constant;

public enum StockStatusEnum {
	INSTOCK("In Stock"), OUTOFSTOCK("Out Of Stock");

	private String value;

	private StockStatusEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
