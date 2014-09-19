package Generics;

import java.util.ArrayList;
import java.util.List;

public class WildcardLowerBound {
	
	public static void addNumbers(List<? super Number> list) {
	    System.out.println(list.getClass().getName());
		for (int i = 1; i <= 10; i++) {
	        list.add(i);
	    }
	}
	
	public static void main (String args[]) {
		List<Object> list = new ArrayList<Object>();
		list.add("abc");
		list.add("cde");
		WildcardLowerBound.addNumbers(list);
		
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		list1.add(2);
		//WildcardLowerBound.addNumbers(list1); //Will not work
	}
}
