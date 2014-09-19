/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DynamicProgramming;

/**
 *
 * @author sunny
 */
public class MatrixChainMultiplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int infinity = 9999999;
        int []p = new int[]{3,5,7,3,4};
        int length = p.length;
        
        int m[][] = new int[length][length];
        int s[][] = new int[length] [length];
        
        for(int i=1; i<length; i++){
            m[i][i] = 0;
        }
        
        length = length -1;
        
        for(int i=length-1; i>=1; i--){
            for(int j=i+1; j<=length; j++){
                m[i][j] = infinity;
                for(int k=i; k<j; k++){
                    int temp = m[i][k] + m[k+1][j] + p[i-1]*p[k]*p[j];
                    if(temp < m[i][j]){
                        m[i][j] = temp;
                        s[i][j] = k;
                    }
                }
            }
        }
        
        for(int i=1; i<=length; i++){
            for(int j=i+1; j<=length; j++){
                System.out.println(i + "," + j + ":" + m[i][j]);
            }
        }
    }
}
