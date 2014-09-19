/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursion;

/**
 *
 * @author sumittal
 */
public class Fibonacci {

    public static void main(String args[]) {
        int n = 6;
        int s = fib(n);
        System.out.println(s);
    }

    public static int fib(int n) {
        if (n<=1) {
            return n;
        } else {
            return fib(n -1) + fib(n-2);
        }
    }
}
