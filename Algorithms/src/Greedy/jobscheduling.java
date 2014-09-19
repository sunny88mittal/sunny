/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Greedy;


/**
 *
 * @author Priyanka
 */
public class jobscheduling {

    public static void main(String args[]) {
        int[] profit = new int[]{100, 10, 15, 27, 30};
        int[] deadline = new int[]{2, 1, 2, 1, 3};
        jobscheduling(profit, deadline);
    }

    private static void jobscheduling(int[] profit, int[] deadline) //find  the maximum profit job and sort the deadline corresponding the job 
    {
        int maxprofit = 0;
        int length = profit.length;
        
        //Sort the jobs according to the deadline
        for(int i=0; i<length; i++){
            for(int j=i+1; j<length; j++){
                if(deadline[i] > deadline[j]){
                    int tempDeadline = deadline[j];
                    deadline[j] = deadline[i];
                    deadline[i] = tempDeadline;
                    
                    int tempProfit = profit[j];
                    profit[j] = profit[i];
                    profit[i] = tempProfit; 
                }
            }
        }
        
        boolean done = false;
        int index = 0;
        int deadlineValue = deadline[0];
        int profitValue = profit[0];
        
        while(!done && index < (length - 1)){
            ++index;
            if(deadlineValue == deadline[index]){
                if(profitValue < profit[index]){
                    profitValue = profit[index];
                }
            }else{
                maxprofit = maxprofit + profitValue;
                deadlineValue = deadline[index];
                profitValue = profit[index];
            }
            
            if(index == length -1){
                maxprofit = maxprofit + profitValue;
            }
        }
        
        //Print the jobs with the maximum profit
        System.out.println("maximumprofit:" +maxprofit);
    }
}
