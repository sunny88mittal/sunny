/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sorting;

/**
 *
 * @author sumittal
 */
public class InsertionSort {
    
	 private static int[] array = new int[]{1,5,3,6,9,8};
	    
     public static void main(String args[]){
    	
    	int length = array.length;
    	
    	for (int i=1; i<length; i++) {
    		
    		int j = i-1;
    		
    		int temp = array[i];
    		
    		while (j>=0 && array[j] > temp) {
    			
    			array[j+1] = array[j];
    			
    			j = j-1;
    		}
    		
    		array[j+1] = temp;
    	}
    	
    	for (int i=0; i<length; i++) {
            System.out.println(array[i]);
        }
     }
}
