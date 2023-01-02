package model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SorterDTO extends DTO{

	public SorterDTO(String id, String name) {
		super(id, name);
	}
	public static final Map<String, String> SORTBY_MAP = new LinkedHashMap<String, String>(){{
		put("0", "Relevancy");
		put("5", "Price Low To High");
		put("-5", "Price High To Low");
		put("2", "Name A To Z");
		put("-2", "Name Z To A");
		put("1", "Old To New");
		put("-1", "New To Old");
	}};	
}
