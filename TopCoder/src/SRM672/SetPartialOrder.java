package SRM672;

import java.util.HashSet;
import java.util.Set;

public class SetPartialOrder {

	public String compareSets(int[] a, int[] b) {
		Set<Integer> setA = new HashSet<Integer>();
		Set<Integer> setB = new HashSet<Integer>();
		
		for (int i : a) {
			setA.add(i);
		}
		
		for (int i : b) {
			setB.add(i);
		}
		
		if (setA.size() == setB.size() && setA.containsAll(setB)) {
			return "EQUAL";
		} else if (setA.size() > setB.size() && setA.containsAll(setB)) {
			return "GREATER";
		} else if (setA.size() < setB.size() && setB.containsAll(setA)) {
			return "LESS";
		} 
		
		return "INCOMPARABLE";
	}
	
	public static void main (String args[]) {
		SetPartialOrder obj = new SetPartialOrder();
		System.out.println(obj.compareSets(new int[] {1, 2, 3, 5, 8}, new int[] {8, 5, 1, 3, 2}));
		System.out.println(obj.compareSets(new int[] {2, 3, 5, 7}, new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
		System.out.println(obj.compareSets(new int[] {2, 4, 6, 8, 10, 12, 14, 16}, new int[] {2, 4, 8, 16}));
		System.out.println(obj.compareSets(new int[] {42, 23, 17}, new int[] {15, 23, 31}));
	}
}
