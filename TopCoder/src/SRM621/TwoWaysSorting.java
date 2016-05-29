package SRM621;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TwoWaysSorting {
	
	String lexicographically = "lexicographically";

	String lengths = "lengths";
	
	String both = "both";
	
	String none = "none";
			
	public String sortingMethod(String[] stringList) {
		List<String> strings = new ArrayList<String>();
		List<Integer> strLen = new ArrayList<Integer>(); 
		for (int i=0; i< stringList.length; i++) {
			strings.add(stringList[i]);
			strLen.add(stringList[i].length());
		}
		
		Collections.sort(strings);
		Collections.sort(strLen);
		
		boolean isLenSorted = isLenSorted(strLen, stringList);
		boolean isLexSorted = isLexSorted(strings, stringList);
		
		if (isLenSorted && isLexSorted) {
			return both;
		} else if (isLenSorted) {
			return lengths;
		} else if (isLexSorted) {
			return lexicographically;
		}
		
		return none;
	}
	
	private boolean isLexSorted(List<String> strings, String[] stringList) {
		for (int i=0; i<stringList.length; i++) {
			if (!strings.get(i).equals(stringList[i])) return false;
		}
		return true;
	}
	
	private boolean isLenSorted(List<Integer> strings, String[] stringList) {
		for (int i=0; i<stringList.length; i++) {
			if (strings.get(i) != stringList[i].length()) return false;
		}
		return true;
	}
}
