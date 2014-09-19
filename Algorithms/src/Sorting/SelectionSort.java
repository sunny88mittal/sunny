/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sorting;

/**
 * Algorithm
 *    1.Find the minimum value in the list 
 *    2.Swap it with the value in the first position 
 *    3.Repeat the steps above for the remainder of the list (starting at the second position and advancing each time)
 * 
 * Time Complexity
 *    Worst Case   : O(n2)
 *    Best Case    : O(n2)
 *    Average Case : O(n2)
 * 
 * Space Complexity
 *    O(1)
 * 
 * @author sumittal
 */
public class SelectionSort {
    
    private static int[] array = new int[]{1,5,3,6,9,8};
    
    public static void main(String args[]){
        int length = array.length;   
    
        int outerIndex = length - 1;
        
        for (int i=0; i<outerIndex; i++) {
            int min = array[i];
            int minIndex = i;
            for (int j=i+1; j<length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    minIndex = j;
                }
            }
            int temp = array[i]; 
            array[i] = min;
            array[minIndex] = temp;
        }
        
        for (int i=0; i<length; i++) {
            System.out.println(array[i]);
        }
    }
}
