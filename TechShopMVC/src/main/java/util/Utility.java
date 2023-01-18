package util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Utility {
//	private static final String DEFAULT_AVAILABILITY_SETTING = "1";
//	private static final String DEFAULT_SORTBY_SETTING = "0";
//	private static final String DEFAULT_RESULTPERPAGE_SETTING = "16";
	public static final String STOCKSTATUS_OUTOFSTOCK = "0";	
	public static final String STOCKSTATUS_INSTOCK = "1";
	public static final Map<String, String> AVAILABILITY_MAP = new LinkedHashMap<String, String>(){{
		put(STOCKSTATUS_OUTOFSTOCK, "Out Of Stock");
		put(STOCKSTATUS_INSTOCK, "In Stock");
	}};		
	
	public static final Map<String, String> SORTBY_MAP = new LinkedHashMap<String, String>(){{
		put("0", "Relevancy");
		put("5", "Price Low To High");
		put("-5", "Price High To Low");
		put("2", "Name A To Z");
		put("-2", "Name Z To A");
		put("1", "Old To New");
		put("-1", "New To Old");
	}};
	
	public static final Map<String, String> RESULTPERPAGE_MAP = new LinkedHashMap<String, String>(){{
		put("16", "16");
		put("32", "32");
		put("64", "64");
		put("128", "128");
	}};		
	
	public static Entry getEntryByKey(Map<?,?> theMap, Object theKey, Object theDefaultKey) {
		Entry defaultEntry = null;
		for(Entry entry: theMap.entrySet()) {
			if(entry.getKey().equals(theKey))
				return entry;
			else if(entry.getKey().equals(theDefaultKey))
				defaultEntry = entry;
		}
		return defaultEntry;
	}
	public static String getCorrectPrevUrl(String prevUrl) {
//		String newPrevUrl = "Home";
//		if(prevUrl != null && ! prevUrl.equalsIgnoreCase("")) {
//			if(prevUrl.indexOf("prevUrl=/") != -1)
//				newPrevUrl =  prevUrl.substring(prevUrl.indexOf("prevUrl=/") + 9,  prevUrl.length());
//			else
//				newPrevUrl = prevUrl.charAt(0) == '/' ? prevUrl.substring(1) : prevUrl;;
//		}		
		String newPrevUrl = (prevUrl == null || prevUrl.equalsIgnoreCase("")) ? "Home" : prevUrl;
		newPrevUrl = newPrevUrl.charAt(0) == '/' ? newPrevUrl.substring(1) : newPrevUrl;
		return newPrevUrl;
	}
}
