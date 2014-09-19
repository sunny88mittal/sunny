/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DynamicProgramming;

/**
 *
 * @author sumittal
 */

//http://community.topcoder.com/stat?c=problem_statement&pm=2402&rd=5009

public class BadNeighbors {
    
    public static void main(String args[]) {
        int donations[] = new int[] {874, 173, 392, 916, 119, 997, 923, 841, 301, 755, 320};
        System.out.println(maximumDonations(donations));
    }
    
    private static int maximumDonations (int [] donations) {
        int maxDonationValue = 0;
        int elements = donations.length;
        int maxDonation[][] = new int[elements][elements];
        int index[][] = new int[elements][elements];
        boolean containsLast[][] = new boolean[elements][elements];
        containsLast[elements-1][elements-1] = true;
        for (int i=0; i<elements; i++) {
            for (int j=0; j<elements; j++) {
                maxDonation[i][j] = donations[i];
                index[i][j] = i;
            }
        }
        
        int start = elements - 1;
        for (int i=start; i>=0; i--) {
            for (int j=start; j>i; j--) {
                int indexij = index[i][j];
                int maxDonationij = maxDonation[i][j];
                boolean containsLastE = false;
                for (int k=i+1; k<=j; k++){
                    if (i==0  && containsLast[k][j]) {
                        continue;
                    }
                    int indexkj = index[k][j];
                    
                    if (indexkj-index[i][j] != 1 && maxDonationij < (donations[i] + maxDonation[k][j])) {
                        indexij = index[i][j];
                        maxDonationij = donations[i] + maxDonation[k][j];
                        if (containsLast[k][j]){
                           containsLastE = true;
                        } 
                    } else if (maxDonationij < maxDonation[k][j]) {
                        indexij = index[k][j];
                        if (containsLast[k][j]){
                            containsLastE = true;
                        }
                        maxDonationij = maxDonation[k][j];
                    }
                }
                containsLast[i][j] = containsLastE;
                index[i][j] = indexij;
                maxDonation[i][j] = maxDonationij;
            }
        }
        
        for (int i=0; i<elements; i++) {
           for (int j=0; j<elements; j++){
                if (maxDonationValue < maxDonation[i][j]) {
                    maxDonationValue = maxDonation[i][j];
                }
            }
        }
        return maxDonationValue;
    }
    
    private static void printMatrix (int[][]matrix) {
        int length = matrix.length;
        for (int i=0; i<length; i++) {
            System.out.println();
            for (int j=0; j<length; j++){
                System.out.print(matrix[i][j] + "  ");
            }
        }
    }
}
