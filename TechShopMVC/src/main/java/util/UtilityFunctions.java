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
}
