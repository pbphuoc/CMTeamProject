package util;

import java.util.Map;

public class UtilityFunctions {
	public static Object getKeyByValue(Map<?,?> theMap, Object theValue) {
		for(Object key: theMap.keySet()) {
			if(theMap.get(key).equals(theValue))
				return key;
		}
		return null;
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
