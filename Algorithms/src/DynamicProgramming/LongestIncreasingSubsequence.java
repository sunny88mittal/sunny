/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DynamicProgramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-3-longest-increasing-subsequence/
 * Optimal Substructure : We can solve this problem for seq[n] if we already have solution for seq[n-1] using the recurrence equation
 *                        lis(n) = lis(n) + 1 if seq[n] > seq[n-1] && seq[n].size <= seq[n-1].size
 *                         
 * Overlapping Subproblems: We need the solution lis(n-1) before we can find lis(n)
 * 
 * 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15
 * 0, 2, 6, 9, 13, 15
 * 0, 4, 6, 9, 11, 15
 * @author sunny
 */
public class LongestIncreasingSubsequence {
    public static void main(String args[]){
        int[] seq = new int[]{1, 9, 3, 8, 11, 4, 5, 6, 4, 19, 7, 1, 7};
        int length = seq.length;
        
        //Initialize the LIS for all the elements as 1
        int[] countArray = new int[length];
        //This array computes where to look for the previous element in the LIS
        int[] positionArray = new int[length];
        
        //Initialize both the arrays
        for (int i=0; i<length; i++) {
        	//Initially LIS for all elements is of size 1
        	countArray[i] = 1;
        	//Initially all elements include only themselves in LIS
        	positionArray[i] = i;
        }
        
        //Check for all the elements if to include the current element in the list or previous solution is already better
        for (int i=0; i<length; i++) {
        	for (int j=i+1;j<length;j++) {
                if (seq[j] > seq[i] && countArray[j] <= countArray[i]) {
                	countArray[j] ++;
                    positionArray[j] = i;
                }
        	}
        }
        
        //Get the position where the last element of the LIS is
        int maxPos = 0;
        int max = countArray[0];
        for (int i=1; i<length; i++) {
        	if (max < countArray[i]) {
        		max = countArray[i];
        		maxPos = i;
        	}
        }
        System.out.println("LIS size is:" + countArray[maxPos]);
        
        //Print out the elements
        List<Integer> lisList = new ArrayList<Integer>();
        int previousIndex = maxPos;
        while (true) {
        	lisList.add(seq[previousIndex]);
        	previousIndex = positionArray[previousIndex];
        	if (positionArray[previousIndex] == previousIndex) {
        		lisList.add(seq[previousIndex]);
        		break;
        	}
        }
        Collections.reverse(lisList);
        System.out.println("LIS is:" + lisList);
    }
}
