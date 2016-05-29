/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursion;

/**
 *
 * @author sumittal
 */
public class FactorialRecursion {

    public static void main(String args[]) {
        int n = 10;
        int s = fact(n);
        System.out.println(s);
    }
    public static int fact(int n) {
        if(n == 1){
            return 1;
        }
        return n * fact(n-1);
    }
}