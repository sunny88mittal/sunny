/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Greedy;

/**
 *
 * @author Priyanka
 */
public class Knapsack {
 
    public static void main (String args[]){
        int m = 20;
        int[] price = new int[]{25, 24, 15};
        int[] weight = new int[]{18, 15, 10};
        knapsackAlgo(m, price, weight);
    }
    
    /**
     * Implements the knapsack algorithm
     * and finds the maximum weight that could be picked
     * @param n
     * @param m
     * @param price
     * @param weight 
     */
    private static void knapsackAlgo(int m, int[] price, int[] weight){
        // Find the profit of each item per unit weight 
        int length = price.length;
        
        float[] perUnitValue = new float[length];
        
        for(int i=0; i<length; i++){
            perUnitValue[i] = (float)price[i] / (float)weight[i];
        }
        
        //Sort the per unit value ratio array and sort items corresponding to them
        for(int i=0; i<length; i++){
            for(int j=i+1; j<length; j++){
                if(perUnitValue[i] < perUnitValue[j]){
                    float temp = perUnitValue[j];
                    perUnitValue[j] = perUnitValue[i];
                    perUnitValue[i] =  temp;
                    
                    int tempPrice = price[j];
                    price[j] = price[i];
                    price[i] = tempPrice;
                    
                    int tempWt = weight[j];
                    weight[j] = weight[i];
                    weight[i] = tempWt;
                }
            }
        }
         
        //Fill the knapsack with the items
        float maxPrice = 0;
        while(m>0){
            for(int i=0; i<length; i++){
                if(weight[i] <= m){
                    maxPrice = maxPrice + price[i];
                    m = m - weight[i];
                }else{
                    maxPrice = maxPrice + m * ((float)price[i]/ (float)weight[i]);
                    m = 0;
                }
            }
        }
        
        System.out.println("Max possible price is:" +  maxPrice);
    }
}
