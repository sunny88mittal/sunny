/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DynamicProgramming;

import java.util.ArrayList;
import java.util.List;

 
/**
 * Given a sequence of n real numbers A(1) ... A(n), determine a contiguous subsequence A(i) ... A(j) 
 * for which the sum of elements in the subsequence is maximized.
 * @author sunny
 */
public class MaxValueContSeq {
     
    public static void main(String args[]){
        int s[] = new int[]{0, -1, 2, -1, 3, -1, 0};
        int length = s.length;
        int seqSum[] = new int[length];
        List<Integer> elements = new ArrayList<Integer>();
        seqSum[length -1] = s[length -1];
        elements.add(length -1);
        int maxAtPosition = length -1;
        for(int i=length-2; i>=0; i--){
            seqSum[i] = s[i];
            if(seqSum[i] < seqSum[i] + seqSum[i+1]){
                seqSum[i] = seqSum[i] + seqSum[i+1];
                if(seqSum[i] > seqSum[maxAtPosition]){
                    maxAtPosition = i;
                    elements.add(i);
                }
                continue;
            }
            elements.clear();
            elements.add(i);
        }
        int sum = 0;
        for(Integer element: elements){
            sum = sum + s[element];
            System.out.println(s[element]);
        }
        System.out.println(sum);
    } 
}
