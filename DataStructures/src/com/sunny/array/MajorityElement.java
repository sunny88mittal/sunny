package com.sunny.array;

/**
 * http://www.geeksforgeeks.org/majority-element/
 * @author sumittal
 *
 */
public class MajorityElement {
     public static void main (String args[]) {
    	 int [] numbers = new int[] {3, 3, 4, 2, 4, 4, 2, 4, 4};
    	 //int [] numbers = new int[] {3, 3, 4, 2, 4, 4, 2, 4};
    	 
    	 //Moores voting algorithm
    	 //1. Find if a majority element exists  	 
    	 int maj_index = 0;
    	 int count = 1;
    	 for (int i=1; i<numbers.length; i++) {
    		 if (numbers[i] == numbers[maj_index]){
    			 count++;
    		 } else {
    			 count--;
    			 if (count == 0) {
    				 maj_index = i;
    				 count = 1;
    			 }
    		 }
    	 }
    	 
      	 //2. Verify that it is actually a majority element
    	 count = 0;
    	 for (int i=0; i<numbers.length; i++) {
    		 if (numbers[i] ==  numbers[maj_index]) {
    			 count++;
    		 }
    	 }
    	 
    	 if (count > numbers.length/2) {
    		 System.out.println("Majority element is:" + numbers[maj_index]);	 
    	 } else {
    		 System.out.println("Majority element does not exist"); 
    	 }
     }
}
