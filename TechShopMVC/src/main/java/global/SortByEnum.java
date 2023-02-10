package global;

public enum SortByEnum {
	RELEVANCY("Relevancy", 0), PRICELOWTOHIGH("Price Low To High", 5), PRICEHIGHTOLOW("Price High To Low", 5),
	NAMEATOZ("Name A To Z", 2), NAMEZTOA("Name Z To A", 2), OLDTONEW("Old To New", 1), NEWTOOLD("New To Old", 1);

	private String value;
	private int sortByValue;

	private SortByEnum(String value, int sortByValue) {
		this.value = value;
		this.sortByValue = sortByValue;
	}

	public String getValue() {
		return value;
	}

	public int getSortByValue() {
		return sortByValue;
	}
}
