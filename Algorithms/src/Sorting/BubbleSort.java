/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sorting;

/**
 * Time Complexity 
 *    Worst Case   : O(n2)
 *    Best Case    : O(n2)
 *    Average Case : O(n2)
 * Space Complexity
 *    O(1)
 * @author sumittal
 */
public class BubbleSort {
    
    private static int[] array = new int[]{1,5,3,6,9,8};
    
    public static void main(String args[]){
        int length = array.length;   
    
        int outerIndex = length - 1;
        
        for (int i=0; i<outerIndex; i++) {
            
        	boolean swapped = false;
            
        	for (int j=i; j<outerIndex; j++) {
                
                if (array[j] > array[j+1]) {
                    int temp = array[j+1];
                    array[j+1] = array[j];
                    array[j] = temp;
                    swapped = true;
                }
            }
        	
        	if (!swapped) {
        		break;
        	}
        }
        
        for (int i=0; i<length; i++) {
            System.out.println(array[i]);
        }
    }
}
