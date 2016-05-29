package Generics;

import java.util.Arrays;
import java.util.List;

public class WildcardUpperBounded {
	
	//Using the type with bounds
	public static <T extends Number> double sumOfListT(List<T> list) {
	    double s = 0.0;
	    for (Number n : list)
	        s += n.doubleValue();
	    return s;
	}
	
	//Using the wildcard operator
	public static double sumOfList(List<? extends Number> list) {
	    double s = 0.0;
	    for (Number n : list)
	        s += n.doubleValue();
	    return s;
	}
	
	public static void main (String args[]) {
		List<Integer> li = Arrays.asList(1, 2, 3);
		System.out.println("sum = " + sumOfList(li));
		System.out.println("sum = " + sumOfListT(li));
		
		List<Double> ld = Arrays.asList(1.2, 2.3, 3.5);
		System.out.println("sum = " + sumOfList(ld));
		System.out.println("sum = " + sumOfListT(ld));
	}
}
