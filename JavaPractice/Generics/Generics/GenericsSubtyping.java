package Generics;

import java.util.ArrayList;


public class GenericsSubtyping{

	static class GenericTesting<E,V> extends ArrayList<E> {
		
	}
	
	public static void main (String args[]) {
		ArrayList<String> arrayList = new ArrayList<String>();
		
		GenericTesting<String, Integer> genericList1 = new GenericTesting<String, Integer>(); //extends  ArrayList<String>
		GenericTesting<String, Boolean> genericList2 = new GenericTesting<String, Boolean>(); //extends  ArrayList<String>
		GenericTesting<Integer, Double> genericList3 = new GenericTesting<Integer, Double>(); //does not extends  ArrayList<String>
		
		arrayList = genericList1; //Works as the E argument value is same
		arrayList = genericList2; //Works as the E argument value is same
		//arrayList = genericList3; //Does not Works as the E argument value is same
	}
}
