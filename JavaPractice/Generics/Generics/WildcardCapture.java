package Generics;

import java.util.Arrays;
import java.util.List;

public class WildcardCapture {

    static void swapFirst(List<? extends Number> l1, List<? extends Number> l2) {
      Number temp = l1.get(0);
      //l1.set(0, l2.get(0)); // expected a CAP#1 extends Number,
                            // got a CAP#2 extends Number;
                            // same bound, but different types
      //l2.set(0, temp);	    // expected a CAP#1 extends Number,
                            // got a Number
    }
    
    static <T extends Number>void swapFirstT(List<T> l1, List<T> l2) {
        Number temp = l1.get(0);
        l1.set(0, l2.get(0));
        l2.set(0, (T) temp);	    
      }
    
    public static void main (String args[]) {
    	List<Integer> li = Arrays.asList(1, 2, 3);
    	List<Double>  ld = Arrays.asList(10.10, 20.20, 30.30);
    	swapFirst(li, ld);
    }
}