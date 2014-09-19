/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DynamicProgramming;

//http://community.topcoder.com/stat?c=problem_statement&pm=1259&rd=4493

/**
 *
 * @author sumittal
 */
public class ZigZag {
    
    public static void main (String args[]){
        int[] testArray1 = new int [] { 1, 7, 4, 9, 2, 5 };
        System.out.println(getLength(testArray1));
        
        testArray1 = new int [] { 1, 17, 5, 10, 13, 15, 10, 5, 16, 8 };
        System.out.println(getLength(testArray1));
        
        testArray1 = new int [] { 44 };
        System.out.println(getLength(testArray1));
        
        testArray1 = new int [] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        System.out.println(getLength(testArray1));
        
        testArray1 = new int [] { 70, 55, 13, 2, 99, 2, 80, 80, 80, 80, 100, 19, 7, 5, 5, 5, 1000, 32, 32 };
        System.out.println(getLength(testArray1));
    }
    
    private static int getLength(int [] elements) {
        int finalLength = 0;
        int nelements = elements.length;
        int maxLength [][] = new int[nelements][nelements];
        int diff[][] = new int[nelements][nelements];
        
        for(int i=0; i<nelements; i++) {
            maxLength[i][i] = 1;
        }
        
        int start = nelements-1;
        for (int i=start; i>=0; i--){
            for (int j=i+1; j<nelements; j++) {
                for (int k=j; k<nelements; k++) {
                    int difference = elements[i] - elements[j]; 
                    if (difference == 0 && maxLength[i][k] < maxLength[j][k]) {
                        maxLength[i][k] = maxLength[j][k];
                        diff[i][k] = diff[j][k];
                    }
                    if (((diff[j][k] == 0) ||(diff[j][k] < 0 && difference > 0) || (diff[j][k] > 0 && difference < 0)) 
                            && (maxLength[i][k] < maxLength[j][k]))  {
                        maxLength[i][k] = maxLength[j][k] + 1;
                        diff[i][k] = difference;
                    }
                }
            }
        }
        
        for (int i=0; i<nelements; i++) {
            for (int j=0; j<nelements; j++){
                if (finalLength < maxLength[i][j]) {
                    finalLength = maxLength[i][j];
                }
            }
        }
        return finalLength;
    }
}
